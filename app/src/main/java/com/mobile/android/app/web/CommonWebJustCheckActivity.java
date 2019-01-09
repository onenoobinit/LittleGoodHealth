package com.mobile.android.app.web;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.mobile.android.R;
import com.mobile.android.utils.GlidePauseOnScrollListener;
import com.mobile.hyoukalibrary.refreshLayout.RefreshLayout;
import com.mobile.hyoukalibrary.sonic.SonicWebViewClient;
import com.mobile.hyoukalibrary.utils.ApplicationUtils;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.NetUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.mobile.hyoukalibrary.x5_web_view.BaseWebView;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.PauseOnScrollListener;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.rxgalleryfinal.imageloader.GlideImageLoader;

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
    public AutoRelativeLayout arl_header;
    public AutoFrameLayout afl_footer;
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
        mTBSWebView = (BaseWebView) findViewById(R.id.ev_web);
        mWebProgressBar = findViewById(R.id.progressbar_web);
        url = getIntent().getStringExtra(LINK);
        title = getIntent().getStringExtra(TITLE);
        arl_header = (AutoRelativeLayout) findViewById(R.id.arl_header);
        afl_footer = (AutoFrameLayout) findViewById(R.id.afl_footer);
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
               /* if (!SupervisorApp.getInstance().isNeedLogin()) {
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
                    /*if (!SupervisorApp.getInstance().) {
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

            //扩展浏览器上传文件
            //3.0++版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                // 悬着相机或者图片的弹框
                showSelectDialog(1, uploadMsg, null);
            }

            //3.0--版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                showSelectDialog(1, uploadMsg, null);
            }

            @Override
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                showSelectDialog(1, uploadMsg, null);
            }

            // For Android > 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, WebChromeClient.FileChooserParams fileChooserParams) {
                showSelectDialog(2, null, uploadMsg);
                return true;
            }

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            //grantResults授权结果
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //成功，开启摄像头
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
            } else {
                //授权失败
                ToastUtil.show(CommonWebJustCheckActivity.this, "请开启相机权限");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private static final int TAKE_PHOTO_REQUEST_CODE = 1;

    private void showSelectDialog(final int i, ValueCallback<Uri> uploadMsg, ValueCallback<Uri[]> uploadMs) {
        if (i == 1) {
            mUploadMessage = uploadMsg;
        } else {
            mUploadMessageForAndroid5 = uploadMs;
        }
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this);
        }
        alertDialog.setOnCancelListener(new ReOnCancelListener());
        alertDialog.setTitle("请选择照片");
        alertDialog.setItems(R.array.options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {//相机
                    if (ContextCompat.checkSelfPermission(CommonWebJustCheckActivity.this, android.Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CommonWebJustCheckActivity.this, new String[]{android.Manifest.permission.CAMERA},
                                TAKE_PHOTO_REQUEST_CODE);
                    } else {
                        GalleryFinal.openCamera(REQUEST_CODE_CAMERA, functionConfig, mOnHanlderResultCallback);
                    }


                } else {
                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);
                }
            }
        });
        alertDialog.show();
    }

    private class ReOnCancelListener implements DialogInterface.OnCancelListener {
        @Override
        public void onCancel(DialogInterface dialogInterface) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }
            if (mUploadMessageForAndroid5 != null) {
                mUploadMessageForAndroid5.onReceiveValue(null);
                mUploadMessageForAndroid5 = null;
            }
        }
    }

    private static int MAX_SIZE = 9;
    private final int REQUEST_CODE_CAMERA = 1000;//相机
    private final int REQUEST_CODE_GALLERY = 1001;//相册
    private List<PhotoInfo> mPhotoList = new ArrayList<>();
    private FunctionConfig functionConfig;
    private GlideImageLoader imageLoader;

    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList == null || resultList.size() == 0) {
                valueCallbackReset();
                return;
            }
            if (resultList != null && resultList.size() > 0) {
                if (null != mUploadMessage) {
                    for (int i = 0; i < resultList.size(); i++) {
                        File file = new File(resultList.get(i).getPhotoPath());
                        try {
                            long fileSize = getFileSize(file);
                            System.out.println("AAA" + fileSize);
                            if (fileSize < 600000) {
                                //将图片处理成大小符合要求的文件
                                Uri result = Uri.fromFile(file);
                                mUploadMessage.onReceiveValue(result);
                            } else {
                                //将图片处理成大小符合要求的文件
                                Uri result = Uri.fromFile(handleFile(file));
                                mUploadMessage.onReceiveValue(result);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mUploadMessage = null;
                } else if (null != mUploadMessageForAndroid5) {
                    Uri[] uris = new Uri[resultList.size()];
                    for (int i = 0; i < resultList.size(); i++) {
                        File file = new File(resultList.get(i).getPhotoPath());
                        try {
                            long fileSize = getFileSize(file);
                            if (fileSize < 600000) {
                                //将图片处理成大小符合要求的文件
                                Uri result = Uri.fromFile(file);
                                uris[i] = result;
                            } else {
                                //将图片处理成大小符合要求的文件
                                Uri result = Uri.fromFile(handleFile(file));
                                uris[i] = result;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mUploadMessageForAndroid5.onReceiveValue(uris);
                    mUploadMessageForAndroid5 = null;
                }
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            ToastUtil.show(CommonWebJustCheckActivity.this, errorMsg);
            valueCallbackReset();
        }

    };

    private void valueCallbackReset() {
        if (mUploadMessage != null) {
            mUploadMessage.onReceiveValue(null);
            mUploadMessage = null;
        }
        if (mUploadMessageForAndroid5 != null) {
            mUploadMessageForAndroid5.onReceiveValue(null);
            mUploadMessageForAndroid5 = null;
        }
    }

    /**
     * 获取指定文件大小
     *
     * @param
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

    private File handleFile(File file) {
        DisplayMetrics dMetrics = getResources().getDisplayMetrics();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;
//        System.out.println("  imageWidth = " + imageWidth + " imageHeight = " + imageHeight)
        int widthSample = (int) (imageWidth / (dMetrics.density * 250));
        int heightSample = (int) (imageHeight / (dMetrics.density * 250));
//        System.out.println("widthSample = " + widthSample + " heightSample = " + heightSample)
        options.inSampleSize = widthSample < heightSample ? heightSample : widthSample;
        options.inJustDecodeBounds = false;
        Bitmap newBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//        System.out.println("newBitmap.size = " + newBitmap.getRowBytes() * newBitmap.getHeight())
        String savePath = ApplicationUtils.getSDFilePackagePath(getApplicationContext()) + "/images/compress/";
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File handleFile = new File(dir, System.currentTimeMillis() + ".png");
        try {
            if (newBitmap.compress(Bitmap.CompressFormat.PNG, 80, new FileOutputStream(handleFile))) {
                System.out.println("保存图片成功");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return handleFile;

    }


    public void initGallery(int maxSzie) {
        ThemeConfig themeConfig = new ThemeConfig.Builder().setTitleBarBgColor(Color.rgb(0x00, 0xC2, 0x4F))
                .setCheckSelectedColor(Color.rgb(0x00, 0xC2, 0x4F))
                .setFabNornalColor(Color.rgb(0x00, 0xC2, 0x4F))
                .build();
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        //functionConfigBuilder.setEnableCamera(true);
        functionConfigBuilder.setMutiSelectMaxSize(maxSzie);//最大多选数量等于4减去当前已经有的数量
        //functionConfigBuilder.setEnablePreview(true);//预览
//        functionConfigBuilder.setSelected(mPhotoList);//添加过滤集合
        //functionConfigBuilder.setEnableEdit(true);//可编辑
        functionConfig = functionConfigBuilder.build();
        PauseOnScrollListener pauseOnScrollListener = new GlidePauseOnScrollListener(false, true);
        imageLoader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, (ImageLoader) imageLoader, themeConfig)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(pauseOnScrollListener)
                .setNoAnimcation(false)
                .build();
        GalleryFinal.init(coreConfig);
    }


    public ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;


    @Override
    protected void onResume() {
        super.onResume();
        valueCallbackReset();
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
