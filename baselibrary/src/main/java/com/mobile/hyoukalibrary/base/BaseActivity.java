package com.mobile.hyoukalibrary.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.mobile.hyoukalibrary.manager.ActivityManager;
import com.mobile.hyoukalibrary.rxbus.RxBus;
import com.mobile.hyoukalibrary.service.HideService;
import com.mobile.hyoukalibrary.sonic.SonicJavaScriptInterface;
import com.mobile.hyoukalibrary.sonic.SonicSessionClientImpl;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.NetworkUtils;
import com.mobile.hyoukalibrary.utils.StatusBarCompat;
import com.mobile.hyoukalibrary.x5_web_view.BaseWebView;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 描述:Acitivity基类
 * <p>
 * 工程:
 * #0000    wangqiang    2016-09-06 13:38
 *
 * @author Administrator
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    private final long RETRY_TIMES = 1;
    /**
     * 访问网络时 提交数据的集合
     */
    protected Map<String, Object> params = new HashMap<String, Object>();

    protected Gson gson = new Gson();

    /**
     * ----------------TBS 配置--------------------
     */

    protected WebSettings mWebSetting;

    private String appCacheDir;

    protected BaseWebView mTBSWebView;
    protected String url;
    protected SonicSession sonicSession;
    protected SonicSessionClientImpl sonicSessionClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        doSomeThingBeforeInitView();
        setContentView(getLayoutId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        setStatusColor();
        /*
         * 状态栏字体颜色,适配小米的MIUI、魅族的Flyme和Android6.0以上
         */
        boolean b = FitterSetStatusBarLightMode(this.getWindow(), true);
        if (!b) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        initViews(savedInstanceState);
        initToolBar();
        ActivityManager.getInstance().addActivity(this);
        RxBus.get().register(this);
        startHideService();
        if (mTBSWebView != null) {
            initializeWebSetting(url);
        }
        initWebView();

    }

    /**
     * TBSWebSetting，TbsWeb缓存
     */
    private void initializeWebSetting(@Nullable String url) {
        // 如果是声波模式，在第一时间启动声波会议。
        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);
        // 创建声波会话并运行音速流
        if (!TextUtils.isEmpty(url)) {
            sonicSession = SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build());
        }
        if (null != sonicSession) {
            sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
            Intent intent = new Intent();
            intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
            mTBSWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            mTBSWebView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");
        }
        //获取WebSettings
        mWebSetting = mTBSWebView.getSettings();
        mWebSetting.setDefaultTextEncodingName("utf-8"); //设置文本编码
        //确认加载JS
        mWebSetting.setJavaScriptEnabled(true);
        mWebSetting.setLoadWithOverviewMode(true);
        mWebSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置自适应屏幕，两者合用
        mWebSetting.setUseWideViewPort(true); //将图片调整到适合webview的大小
        mWebSetting.setDomStorageEnabled(true);//存储机制
        mWebSetting.setAllowFileAccess(true);// 允许访问文件
        mWebSetting.setLoadsImagesAutomatically(true); //支持自动加载图片
        mWebSetting.setSupportZoom(true);
        mWebSetting.setBuiltInZoomControls(true);
        mTBSWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }

    public void loadUrl(String url) {
        // WebView是准备好了，就告诉客户绑定会话
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(mTBSWebView);
            sonicSessionClient.clientReady();
        } else { // 默认模式
            mTBSWebView.loadUrl(url);
        }
    }

    /**
     * 设置状态栏字体图标为深色，需要小米的MIUI、魅族的Flyme或者Android6.0以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean FitterSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                Class clazz = window.getClass();
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }

            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    protected void doSomeThingBeforeInitView() {

    }

    public void setStatusColor() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"), 0);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTBSWebView != null) {
            //释放资源
            mTBSWebView.stopLoading();
            mTBSWebView.removeAllViews();
            mTBSWebView.destroy();
            mTBSWebView = null;
        }
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        RxBus.get().unregister(this);
        ActivityManager.getInstance().finishActivity(this);
        stopHideService();
    }

    protected abstract void initViews(Bundle savedInstanceState);

    public abstract int getLayoutId();

    public abstract void initToolBar();

    public void initWebView() {
    }

    /**
     * 获取Intent
     */
    protected void handleIntent(Intent intent) {
    }

    public <T> ObservableTransformer<T, T> newWorkAvailable() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.retry(RETRY_TIMES)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                Log.i("newWorkAvailable", "Thread:" + Thread.currentThread().getName());
                                if (!NetworkUtils.isConnected(BaseActivity.this)) {
                                    Toast.makeText(BaseActivity.this,
                                            "网络连接不可用，请检查网络 ", Toast.LENGTH_LONG).show();
                                    /*disposable.dispose();*/
                                    /*throw new NetworkErrorException();*/
                                }
                            }
                        }).subscribeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 开始预加载进程
     */
    private void startHideService() {
        Intent intent = new Intent(this, HideService.class);
        this.startService(intent);
    }

    private void stopHideService() {
        Intent intent = new Intent(this, HideService.class);
        this.stopService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i("onResume:" + this.getClass().getSimpleName());
        if (mTBSWebView != null) {
            mTBSWebView.onResume();
            mTBSWebView.resumeTimers();
        }
        StatService.onResume(this);
    }

    @Override
    protected void onPause() {
        L.i("onPause:" + this.getClass().getSimpleName());
        super.onPause();
        if (mTBSWebView != null) {
            mTBSWebView.onPause();
            mTBSWebView.pauseTimers();
        }
        StatService.onPause(this);
    }

    /**
     * 跳转WEB ACtivity的方法。
     * 此方法为了提前预加载，使用sonic 让网页加载更快
     *
     * @param url
     * @param intent
     */
    public void startWebActivity(@Nullable String url, @Nullable Intent intent) {
        //-----------------------
        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);
        boolean preloadSuccess = SonicEngine.getInstance().preCreateSession(url, sessionConfigBuilder.build());
        intent.putExtra(BaseWebActivity.URL, url);
        intent.putExtra(SonicJavaScriptInterface.PARAM_CLICK_TIME, System.currentTimeMillis());
        startActivity(intent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
//                if (hideInputMethod(this, v)) {
//                    return false; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
//                }
                hideInputMethod(this, v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }
}
