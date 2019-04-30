package com.youyijia.goodhealth.app.web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.JknewsDetailInfo;
import com.youyijia.goodhealth.entity.PutCommentPost;
import com.youyijia.goodhealth.entity.WindetailInfo;
import com.youyijia.goodhealth.observer.WXLoginObserver;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.utils.WxShareUtils;
import com.youyijia.goodhealth.widgets.dialog.CommentDialog;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.goodhealth.widgets.dialog.WxShareDialog;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.sonic.SonicWebViewClient;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.NetUtil;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.x5_web_view.BaseWebView;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class NewsWebActivity extends WrapActivity implements Observer {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.progressbar_web)
    ProgressBar progressbarWeb;
    @BindView(R.id.et_content)
    TextView etContent;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.tv_marker_number1)
    TextView tvMarkerNumber1;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.rl_share)
    RelativeLayout rlShare;
    @BindView(R.id.ev_web)
    BaseWebView evWeb;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;
    private String title;
    private WebViewClient client;
    private boolean needClearHistory;
    private String directionId;
    private WindetailInfo windetailInfo;
    private String articleType;
    private JknewsDetailInfo jknewsDetailInfo;
    private LinearLayout ll_edit;
    private CommentDialog commentDialog;
    private MyDialog myDialog;
    private IWXAPI api;
    private String introduction;
    private String wxTitle;
    private String wxImageUrl;
    private WxShareDialog wxShareDialog;

    @Override

    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        WXLoginObserver.Companion.getINSTANCE().addObserver(this);

        mTBSWebView = (BaseWebView) findViewById(R.id.ev_web);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        directionId = getIntent().getStringExtra("directionId");
        articleType = getIntent().getStringExtra("articleType");
        tvTitle.setText(title);
        ibBack.setOnClickListener(v -> {
            if (tvTitle.getText().equals("请求失败")) {
                finish();
            }
            if (mTBSWebView.canGoBack()) {
                mTBSWebView.goBack();
                if (!TextUtils.isEmpty(title) && !mTBSWebView.canGoBack()) {
                    tvTitle.setText(title);
                }
            } else {
                out();
            }
        });
        initWeb();

        if ("优医健康".equals(title)) {
            getData();
        } else if ("健康资讯".equals(title)) {
            getjkData();
        }

    }

    private void getjkData() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getJKDetail(directionId)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            jknewsDetailInfo = gson.fromJson(baseEntity.getData(), JknewsDetailInfo.class);
                            introduction = jknewsDetailInfo.getIntroduction();
                            wxTitle = jknewsDetailInfo.getTitle();
                            wxImageUrl = jknewsDetailInfo.getTitleImgList().get(0);
                            initjkData();
                        } else {
                            ToastUtil.show(NewsWebActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initjkData() {
        String commentCount = jknewsDetailInfo.getCommentCount();
        if (tvMarkerNumber1 != null) {
            if (commentCount != null && "0".equals(commentCount)) {
                tvMarkerNumber1.setVisibility(View.GONE);
            } else if (commentCount != null && commentCount.length() > 0) {
                tvMarkerNumber1.setVisibility(View.VISIBLE);
                if (commentCount.length() > 2) {
                    tvMarkerNumber1.setText("99+");
                } else {
                    tvMarkerNumber1.setText(commentCount);
                }
            }
        }
    }

    private void getData() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getWindDetail(directionId)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            windetailInfo = gson.fromJson(baseEntity.getData(), WindetailInfo.class);
                            introduction = windetailInfo.getIntroduction();
                            wxTitle = windetailInfo.getTitle();
                            wxImageUrl = windetailInfo.getTitleImgList().get(0);
                            initData();
                        } else {
                            ToastUtil.show(NewsWebActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initData() {
        String commentCount = windetailInfo.getCommentCount();
        if (tvMarkerNumber1 != null) {
            if (commentCount != null && "0".equals(commentCount)) {
                tvMarkerNumber1.setVisibility(View.GONE);
            } else if (commentCount != null && commentCount.length() > 0) {
                tvMarkerNumber1.setVisibility(View.VISIBLE);
                if (commentCount.length() > 2) {
                    tvMarkerNumber1.setText("99+");
                } else {
                    tvMarkerNumber1.setText(commentCount);
                }
            }
        }
    }

    private void initWeb() {
        client = new SonicWebViewClient(sonicSession) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {

                if (TextUtils.isEmpty(s)) {
                    return true;
                }
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
                    if (NetUtil.isNetAvailable(getApplicationContext())) {
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
            public void onReceivedTitle(WebView webView, String s) {
                if (TextUtils.isEmpty(title)) {
                    tvTitle.setText(s);
                }
            }

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                if (newProgress == 100) {
                    progressbarWeb.setVisibility(View.GONE);
                } else {
                    progressbarWeb.setVisibility(View.VISIBLE);
                    progressbarWeb.setProgress(newProgress);
                }

            }
        });
        mTBSWebView.addJavascriptInterface(this, "app");
        loadUrl(url);
    }

    /**
     * 是否执行下一步
     */
    public boolean isRunNextStep() {
        return true;
    }

    private void out() {
        finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_web;
    }

    @Override
    public void initToolBar() {

    }

    /**
     * 浏览器回退
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (tvTitle.getText().equals("请求失败")) {
                finish();
            }
            if (mTBSWebView.canGoBack()) {
                mTBSWebView.goBack();
                if (!TextUtils.isEmpty(title) && !mTBSWebView.canGoBack()) {
                    tvTitle.setText(title);
                }
                return true;
            } else {
                out();
                return false;
            }

        }
        return false;
    }

    @OnClick({R.id.rl_message, R.id.rl_share, R.id.et_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_message:
                if ("优医健康".equals(title)) {
                    Intent intent = new Intent(NewsWebActivity.this, CommentListActivity.class);
                    intent.putExtra("articleId", windetailInfo.getDirectionId());
                    intent.putExtra("articleType", articleType);
                    intent.putExtra("title", windetailInfo.getTitle());
                    intent.putExtra("author", windetailInfo.getAuthor());
                    intent.putExtra("date", windetailInfo.getCreateDate());
                    intent.putExtra("pageviews", windetailInfo.getPageviews());
                    startActivity(intent);
                } else if ("健康资讯".equals(title)) {
                    Intent intent = new Intent(NewsWebActivity.this, CommentListActivity.class);
                    intent.putExtra("articleId", jknewsDetailInfo.getInformationId());
                    intent.putExtra("articleType", articleType);
                    intent.putExtra("title", jknewsDetailInfo.getTitle());
                    intent.putExtra("author", jknewsDetailInfo.getAuthor());
                    intent.putExtra("date", jknewsDetailInfo.getCreateDate());
                    intent.putExtra("pageviews", jknewsDetailInfo.getPageviews());
                    startActivity(intent);
                }

                break;
            case R.id.rl_share://分享到朋友圈
                    wxShareDialog = new WxShareDialog(this).setListener(new WxShareDialog.WxListener() {
                        @Override
                        public void setOnWx() {
                            WxShareUtils.shareWeb(NewsWebActivity.this, url, wxTitle, introduction, wxImageUrl, false);
                            L.i("AAAA1","我被点击了");
                        }

                        @Override
                        public void setOnFriend() {
                            WxShareUtils.shareWeb(NewsWebActivity.this, url, wxTitle, introduction, wxImageUrl, true);
                            L.i("AAAA1","我被点击了");
                        }
                    });
                wxShareDialog.show();

                break;
            case R.id.et_content:
                commentDialog = new CommentDialog(NewsWebActivity.this, new CommentDialog.SendListener() {
                    @Override
                    public void sendComment(String inputText) {
                        getComment(inputText);
                    }
                });
                commentDialog.show(getSupportFragmentManager(), "comment");
                break;
            default:
                break;
        }
    }


    private void getComment(String content) {
        if (myDialog == null) {
            myDialog = new MyDialog(NewsWebActivity.this);
        }
        myDialog.showDialog();
        PutCommentPost loginPost = new PutCommentPost();
        PutCommentPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        if ("优医健康".equals(title)) {
            postInfoBean.setArticleId(windetailInfo.getDirectionId());
            postInfoBean.setArticleType(articleType);
        } else if ("健康资讯".equals(title)) {
            postInfoBean.setArticleId(jknewsDetailInfo.getInformationId());
            postInfoBean.setArticleType(articleType);
        }

        postInfoBean.setCommentContent(content);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .putComment(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                            if ("优医健康".equals(title)) {
                                getData();
                            } else if ("健康资讯".equals(title)) {
                                getjkData();
                            }
                        } else {
                            ToastUtil.show(NewsWebActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        WXLoginObserver.Companion.getINSTANCE().deleteObserver(this);
        if (myDialog != null) {
            myDialog.dismissDialog();
        }

        if (commentDialog != null) {
            commentDialog.dismiss();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WXLoginObserver) {
            BaseResp res = (BaseResp) arg;
            switch (res.errCode) {
                case 0:
                    ToastUtil.show(this, "分享成功!");
                    break;
                case -2:
                    ToastUtil.show(this, "分享取消!");
                    break;
                case -4:
                    ToastUtil.show(this, "分享失败!");
                    break;
            }
            wxShareDialog.dismiss();
        }
    }
}
