package com.mobile.hyoukalibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.mobile.hyoukalibrary.sonic.SonicJavaScriptInterface;
import com.mobile.hyoukalibrary.sonic.SonicRuntimeImpl;
import com.mobile.hyoukalibrary.sonic.SonicSessionClientImpl;
import com.mobile.hyoukalibrary.utils.L;

/**
 * 描述:
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2018-01-16 15:31
 */
public abstract class BaseWebFragment extends BaseFragment {

    public final static String URL = "url";

    public final static String MODE = "mode";

    public static final int MODE_DEFAULT = 0;

    public static final int MODE_SONIC = 1;

    public static final int MODE_SONIC_WITH_OFFLINE_CACHE = 2;

    private static final int PERMISSION_REQUEST_CODE_STORAGE = 1;
    protected String url;
    private SonicSessionClientImpl sonicSessionClient;

    private SonicSession sonicSession;

    //-------------TBS------------------
    protected WebView mTBSWebView;
    protected WebSettings mWebSetting;
    private String appCacheDir;

    @Override
    public void initWebView() {
        super.initWebView();
        Bundle bundle = getArguments();
        int mode = bundle.getInt(MODE, 1);
        String iurl = bundle.getString(URL);
        url = iurl;
        if (mTBSWebView != null && url != null) {
            initializeWebSetting(new Intent(), url, mode);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(BaseApplication.getInstance()), new SonicConfig.Builder().build());
        }
        return super.onCreateView(inflater, container, state);
    }

    public void initializeWebSetting(Intent intent, String url, int mode) {
        // 如果是声波模式，在第一时间启动声波会议。
        if (MODE_DEFAULT != mode) { // sonic mode
            SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
            sessionConfigBuilder.setSupportLocalServer(true);

            // 如果是离线包的模式，我们需要拦截会话连接
//            if (MODE_SONIC_WITH_OFFLINE_CACHE == mode) {
//                sessionConfigBuilder.setCacheInterceptor(new SonicCacheInterceptor(null) {
//                    @Override
//                    public String getCacheData(SonicSession session) {
//                        return null; // 脱机缓存不需要
//                    }
//                });
//
//                sessionConfigBuilder.setConnectionInterceptor(new SonicSessionConnectionInterceptor() {
//                    @Override
//                    public SonicSessionConnection getConnection(SonicSession session, Intent intent) {
//                        return new OfflinePkgSessionConnection(getBaseContext(), session, intent);
//                    }
//                });
//            }
            try {
                sonicSession = SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build());
            } catch (Exception e) {
            }
            // 创建声波会话并运行音速流
            if (null != sonicSession) {
                sonicSession.bindClient(sonicSessionClient = new SonicSessionClientImpl());
            } else {
                // 这只发生在同一音速会话已经运行时
                // u可以将以下代码注释为默认模式。.
                L.d("---------------------------create session fail!-----------------------");
                //Toast.makeText(this, "create sonic session fail!", Toast.LENGTH_LONG).show();
            }
        }

        //获取WebSettings
        mWebSetting = mTBSWebView.getSettings();
        mWebSetting.setDefaultTextEncodingName("utf-8"); //设置文本编码
        //确认加载JS
        mWebSetting.setJavaScriptEnabled(true);
        mTBSWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis());
        mTBSWebView.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient, intent), "sonic");
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
        loadUrl();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadUrl() {
        // WebView是准备好了，就告诉客户绑定会话
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(mTBSWebView);
            sonicSessionClient.clientReady();
        } else { // 默认模式
            mTBSWebView.loadUrl(url);
        }
    }

    @Override
    public void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        if (null != mTBSWebView) {
            mTBSWebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        L.i("onResume:" + this.getClass().getSimpleName());
        if (mTBSWebView != null) {
            mTBSWebView.onResume();
            mTBSWebView.resumeTimers();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        L.i("onPause:" + this.getClass().getSimpleName());
        if (mTBSWebView != null) {
            mTBSWebView.onPause();
            mTBSWebView.pauseTimers();
        }
        super.onPause();
    }
}
