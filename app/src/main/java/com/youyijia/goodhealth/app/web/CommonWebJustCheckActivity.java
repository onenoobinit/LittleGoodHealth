package com.youyijia.goodhealth.app.web;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.refreshLayout.RefreshLayout;
import com.youyijia.hyoukalibrary.sonic.SonicWebViewClient;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.NetUtil;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.x5_web_view.BaseWebView;


/**
 * 需要传递两个参数    url(必传) 和 title
 * Created by wangqiang on 2019/1/9.
 */
public abstract class CommonWebJustCheckActivity extends WrapActivity implements View.OnClickListener {
    public static String LINK = "url";
    public static String TITLE = "title";
    private WebViewClient client;
    public String title;
    public TextView tvTitle;
    public ImageButton ibBack;
    public RelativeLayout arl_header;
    public FrameLayout afl_footer;
    private ProgressBar mWebProgressBar;
    FrameLayout videoView;
    View xCustomView;
    private final int CAMERA = 10245;//相机
    private final int PICTURE = 20245;//相册
    private AlertDialog.Builder alertDialog;
    private RefreshLayout refreshLayout;
    public boolean needClearHistory;
    private String userId;
    private TimePickerView pvTime;
    private int yType;

    @Override
    public void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        mTBSWebView = (BaseWebView) findViewById(R.id.ev_web);
        mWebProgressBar = findViewById(R.id.progressbar_web);
        url = getIntent().getStringExtra(LINK);
        title = getIntent().getStringExtra(TITLE);
        arl_header = findViewById(R.id.arl_header);
        afl_footer = findViewById(R.id.afl_footer);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        videoView = (FrameLayout) findViewById(R.id.video_view);
        ibBack.setOnClickListener(this);
//        refreshLayout=(RefreshLayout)findViewById(R.id.refreshLayout);
        alterSource();
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        initWeb();
    }

    /**
     * 修改数据资源，初始化特有的数据
     */
    public void alterSource() {

    }

    /**
     * @param webView
     * @param s       a链接拦截器
     */
    public abstract void myShouldOverrideUrlLoading(WebView webView, String s);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        pvTime = null;
    }


    private void initWeb() {
        client = new SonicWebViewClient(sonicSession) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {

                if (TextUtils.isEmpty(s)) {
                    return true;
                }
               /* if (!GoodHealthApp.getInstance().isNeedLogin()) {
                    s = disposeUrl(s);
                }*/
                myShouldOverrideUrlLoading(mTBSWebView, s);
                if (isRunNextStep()) {
                    webView.loadUrl(s);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {

            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                if (!s.contains("404")) {
                    /*if (!GoodHealthApp.getInstance().) {
                        webView.loadUrl("file:///android_asset/html/404.html");
                    }*/
                    if (NetUtil.isNetAvailable(getApplicationContext())) {
                        pageLoadFinish(s);
                    } else {
                        webView.loadUrl("file:///android_asset/html/404.html");
                    }
                }

            }

            @Override
            public void onReceivedError(WebView webView, int errorCode, String s, String s1) {
                webView.stopLoading();
                L.d("sss", "加载完成錯誤");
                webView.loadUrl("file:///android_asset/html/404.html");
            }

            @Override
            public void doUpdateVisitedHistory(WebView webView, String s, boolean b) {
                super.doUpdateVisitedHistory(webView, s, b);
                if (needClearHistory) {
                    needClearHistory = false;
                    webView.clearHistory();//清除历史记录
                }
            }
        };
        mTBSWebView.setWebViewClient(client);
        mTBSWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                if (newProgress == 100) {
                    mWebProgressBar.setVisibility(View.GONE);
                } else {
                    mWebProgressBar.setVisibility(View.VISIBLE);
                    mWebProgressBar.setProgress(newProgress);
                }

            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                if (TextUtils.isEmpty(title)) {
                    tvTitle.setText(s);
                }
            }

            @Override
            public boolean onJsAlert(WebView webView, String s, String message, JsResult jsResult) {
                if (message != null) {
                    //弹出对话框
                    ToastUtil.show(CommonWebJustCheckActivity.this, message, Gravity.CENTER);
                }
                jsResult.cancel();
                return true;
            }

            @Override
            // 播放网络视频时全屏会被调用的方法
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback callback) {
                CommonWebJustCheckActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                if (mTBSWebView != null) {
                    mTBSWebView.setVisibility(View.GONE);
                }

                // 如果一个视图已经存在，那么立刻终止并新建一个
                if (xCustomView != null && callback != null) {
                    callback.onCustomViewHidden();
                    return;
                }
                if (view != null && videoView != null) {
                    videoView.addView(view);
                    xCustomView = view;
                }
                if (videoView != null) {
                    videoView.setVisibility(View.VISIBLE);
                }
            }

            @SuppressLint("NewApi")
            @Override
            // 视频播放退出全屏会被调用的
            public void onHideCustomView() {
                if (xCustomView == null || videoView == null)// 不是全屏播放状态
                {
                    return;
                }
                videoView.removeView(xCustomView);
                CommonWebJustCheckActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                xCustomView.setVisibility(View.GONE);
                xCustomView = null;
                videoView.setVisibility(View.GONE);
                if (mTBSWebView != null) {
                    mTBSWebView.setVisibility(View.VISIBLE);
                }
                if (mTBSWebView.getSettings() != null) {
                    mTBSWebView.getSettings().setLayoutAlgorithm(com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
                }
            }
        });
        mTBSWebView.addJavascriptInterface(this, "app");
//        initGallery(MAX_SIZE);
        L.e("--------", "ComloadUrl: " + url);
        loadUrl(url);
    }

    /**
     * 是否执行下一步
     */
    public boolean isRunNextStep() {
        return true;
    }

    /**
     * 页面加载完成调用的方法
     */
    public void pageLoadFinish(String s) {
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public int getLayoutId() {
        return R.layout.avtivity_common_web;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View v) {

    }

    protected void out() {
        /*if (!ActivityManager.getInstance().isContains(MainActivity.class)) {
            startActivity(new Intent(CommonWebJustCheckActivity.this, MainActivity.class));
        }*/
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            out();
        }
        return false;
    }

}
