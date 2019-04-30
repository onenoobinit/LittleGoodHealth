package com.youyijia.hyoukalibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.youyijia.hyoukalibrary.sonic.SonicJavaScriptInterface;
import com.youyijia.hyoukalibrary.sonic.SonicRuntimeImpl;
import com.youyijia.hyoukalibrary.sonic.SonicSessionClientImpl;
import com.youyijia.hyoukalibrary.utils.L;

/**
 * 描述:封装WebViewAcitivity X5+sonic
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2018-01-15 16:09
 * *声波模式：声波的方式加载HTML WebView声波，
 *离线模式：离线方式加载HTML WebView本地脱机包，
 *默认模式：默认模式的WebView加载HTML以正常的方式。
 * @author Administrator
 */
public abstract class BaseWebActivity  extends BaseActivity{


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
        Intent intent = getIntent();
        int mode = intent.getIntExtra(MODE, 1);
        url = intent.getStringExtra(URL);
        if (mTBSWebView!=null&&!TextUtils.isEmpty(url)) {
            initializeWebSetting(intent,url,mode);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 必要时初始化音速引擎，或者U在应用程序创建时可以这样做
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }
    }

    public void initializeWebSetting(Intent intent, String url, int mode) {
        // 如果是声波模式，在第一时间启动声波会议。
        if (MODE_DEFAULT != mode) { // sonic mode
            SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
            sessionConfigBuilder.setSupportLocalServer(true);
            sonicSession = SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build());
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
    public void loadUrl()
    {
        // WebView是准备好了，就告诉客户绑定会话
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(mTBSWebView);
            sonicSessionClient.clientReady();
        } else { // 默认模式
            mTBSWebView.loadUrl(url);
        }
    }

    @Override
    protected void onDestroy() {
        if (null != sonicSession) {
            sonicSession.destroy();
            sonicSession = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        L.i("onResume:" + this.getClass().getSimpleName());
        if (mTBSWebView != null) {
            mTBSWebView.onResume();
            mTBSWebView.resumeTimers();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        L.i("onPause:" + this.getClass().getSimpleName());
        if (mTBSWebView != null) {
            mTBSWebView.onPause();
            mTBSWebView.pauseTimers();
        }
        super.onPause();
    }

}
