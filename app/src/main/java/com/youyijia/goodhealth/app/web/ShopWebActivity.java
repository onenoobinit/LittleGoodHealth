package com.youyijia.goodhealth.app.web;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.shopcart.ShopCartActivity;
import com.youyijia.goodhealth.app.shopcart.ShopOrderActivity;
import com.youyijia.goodhealth.entity.AddToCartPost;
import com.youyijia.goodhealth.entity.ShopDetailInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.GuigeDialog;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.sonic.SonicWebViewClient;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.NetUtil;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.x5_web_view.BaseWebView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopWebActivity extends WrapActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.progressbar_web)
    ProgressBar progressbarWeb;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.ll_shopcart)
    LinearLayout llShopcart;
    @BindView(R.id.tv_add_shopcar)
    TextView tvAddShopcar;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.iv_detail)
    ImageView ivDetail;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_center_item_change)
    TextView tvCenterItemChange;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_center_item_price)
    TextView tvCenterItemPrice;
    @BindView(R.id.tv_center_item_price_out)
    TextView tvCenterItemPriceOut;
    @BindView(R.id.tv_baoyou)
    TextView tvBaoyou;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ev_web)
    BaseWebView evWeb;
    @BindView(R.id.iv_right_guige)
    ImageView ivRightGuige;
    @BindView(R.id.tv_gg_check)
    TextView tvGgCheck;
    @BindView(R.id.tv_gg_number)
    TextView tvGgNumber;
    @BindView(R.id.tv_gg_color)
    TextView tvGgColor;
    @BindView(R.id.rl_guige)
    RelativeLayout rlGuige;
    private String title;
    private WebViewClient client;
    public boolean needClearHistory;
    private String commodityId;
    private ShopDetailInfo shopDetailInfo;
    private MyDialog myDialog;
    private boolean isGuige = false;
    private GuigeDialog guigeDialog;
    private ShopDetailInfo.SpecificationsBeanX specifications;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        mTBSWebView = (BaseWebView) findViewById(R.id.ev_web);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        commodityId = getIntent().getStringExtra("commodityId");
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
        getData();
    }

    private void getData() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getShopDetail(commodityId)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            shopDetailInfo = gson.fromJson(baseEntity.getData(), ShopDetailInfo.class);
                            initData();
                        } else {
                            ToastUtil.show(ShopWebActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initData() {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(ShopWebActivity.this)
                .load(shopDetailInfo.getCommodityAttachmentsAtCenterPosition().get(0).getAttachmentFullUrl())
                .apply(options)
                .into(ivDetail);
        tvShopName.setText(shopDetailInfo.getName());
        tvCenterItemChange.setText(shopDetailInfo.getPriceName());
        tvCenterItemPrice.setText(shopDetailInfo.getPresentPrice() + "");
        tvCenterItemPriceOut.setText(shopDetailInfo.getOriginalPrice() + "");
        tvCenterItemPriceOut.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvBaoyou.setText(shopDetailInfo.getExtend1());
        tvAddress.setText("上海");

        if (shopDetailInfo.getSpecifications().isHasDefaultOption() == true) {
            rlGuige.setVisibility(View.GONE);
            isGuige = true;

        } else if (shopDetailInfo.getSpecifications().isHasDefaultOption() == false) {
            rlGuige.setVisibility(View.VISIBLE);
            isGuige = false;
        }

        specifications = shopDetailInfo.getSpecifications();
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_web;
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

    private void out() {
        finish();
    }


    @OnClick({R.id.ll_shoucang, R.id.ll_shopcart, R.id.tv_add_shopcar, R.id.tv_buy, R.id.rl_guige})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_shoucang://收藏
                showPromptDialog("功能开发中...");
                break;
            case R.id.ll_shopcart://购物车:
                startActivity(new Intent(ShopWebActivity.this, ShopCartActivity.class));
                break;
            case R.id.tv_add_shopcar://添加到购物车
                if (isGuige == true) {
                    getaddShop();
                } else {
                    //TODO 弹出选择规格
                }
//                getaddShop();
                break;
            case R.id.tv_buy://立即购买
                if (isGuige == true) {
                    Intent intent = new Intent(ShopWebActivity.this, ShopOrderActivity.class);
                    intent.putExtra("commodityId", shopDetailInfo.getCommodityId() + "");
                    intent.putExtra("itemNumber", shopDetailInfo.getSpecifications().getDefaultItemNumber() + "");
                    intent.putExtra("commodityCount", "1");
                    startActivity(intent);
                } else {

                }
                break;
            case R.id.rl_guige:
                guigeDialog = new GuigeDialog(ShopWebActivity.this, specifications) {

                    @Override
                    public void setOnSize(String ggtype, String typename) {
//                        L.i("我选择的是", ggtype + typename);

                    }
                };
                guigeDialog.show();
                break;
        }
    }

    private void getaddShop() {
        if (myDialog == null) {
            myDialog = new MyDialog(ShopWebActivity.this);
        }
        myDialog.showDialog();
        AddToCartPost loginPost = new AddToCartPost();
        AddToCartPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setCommodityId(commodityId);
        postInfoBean.setCommodityCount("1");
        postInfoBean.setCommoditySpecificationNo(shopDetailInfo.getSpecifications().getDefaultItemNumber() + "");

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .addToCart(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(ShopWebActivity.this, "添加成功！");
                        } else {
                            ToastUtil.show(ShopWebActivity.this, baseEntity.getMessage());
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

    private void showPromptDialog(String s) {
        new AlertDialog.Builder(this)
                .setMessage(s)
                .setPositiveButton("确定", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismissDialog();
        }
    }

}
