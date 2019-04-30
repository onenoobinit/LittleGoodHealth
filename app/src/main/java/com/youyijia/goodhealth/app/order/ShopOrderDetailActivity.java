package com.youyijia.goodhealth.app.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.order.adapter.ShopSecondAdapter;
import com.youyijia.goodhealth.app.pay.PayTypeActivity;
import com.youyijia.goodhealth.entity.CommentPost;
import com.youyijia.goodhealth.entity.MyShopOrderInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.CommentSubmitDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopOrderDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_address_left)
    ImageView ivAddressLeft;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_adddress_phone)
    TextView tvAdddressPhone;
    @BindView(R.id.tv_address_detail)
    TextView tvAddressDetail;
    @BindView(R.id.rl_add_have)
    RelativeLayout rlAddHave;
    @BindView(R.id.tv_green_order_type)
    TextView tvGreenOrderType;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.rv_order_item)
    RecyclerView rvOrderItem;
    @BindView(R.id.tv_order_bianhao)
    TextView tvOrderBianhao;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_pay_style)
    TextView tvOrderPayStyle;
    @BindView(R.id.tv_green_price_total)
    TextView tvGreenPriceTotal;
    @BindView(R.id.tv_green_price_yh)
    TextView tvGreenPriceYh;
    @BindView(R.id.tv_green_price_yf)
    TextView tvGreenPriceYf;
    @BindView(R.id.tv_order_pay_real)
    TextView tvOrderPayReal;
    @BindView(R.id.tv_price_green)
    TextView tvPriceGreen;
    @BindView(R.id.tv_green_order_left)
    TextView tvGreenOrderLeft;
    @BindView(R.id.tv_green_order_right)
    TextView tvGreenOrderRight;
    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    private String orderId;
    private MyShopOrderInfo myShopOrderInfo;
    private CommentSubmitDialog commentSubmitDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        logiToolBar.setNavigationOnClickListener(v -> finish());
        orderId = getIntent().getStringExtra("orderId");

        getOrderDetail();
    }

    private void getOrderDetail() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getMyOrderDetail(orderId)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            myShopOrderInfo = gson.fromJson(baseEntity.getData(), MyShopOrderInfo.class);
                            initData();

                        } else {
                            ToastUtil.show(ShopOrderDetailActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }

    private void initData() {
        tvAddressName.setText(myShopOrderInfo.getRecipientsName());
        tvAdddressPhone.setText(myShopOrderInfo.getRecipientsTelephone());
        tvAddressDetail.setText(myShopOrderInfo.getAddress());
        tvOrderStatus.setText(myShopOrderInfo.getOrderStatus().getText());
        rvOrderItem.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ShopOrderDetailActivity.this, 1, GridLayoutManager.VERTICAL, false);
        rvOrderItem.setLayoutManager(gridLayoutManager);
        ShopSecondAdapter orderItemAdapter = new ShopSecondAdapter(ShopOrderDetailActivity.this, (ArrayList<MyShopOrderInfo.OrderItemsBean>) myShopOrderInfo.getOrderItems(), 1);
        rvOrderItem.setAdapter(orderItemAdapter);

        tvOrderBianhao.setText(myShopOrderInfo.getSerialNumber() + "");
        tvOrderTime.setText(myShopOrderInfo.getCreateDate());
        tvGreenPriceTotal.setText("¥" + myShopOrderInfo.getOriginProductAmountTotal());
        tvGreenPriceYh.setText("¥" + myShopOrderInfo.getReducedPrice());
        tvGreenPriceYf.setText("¥" + myShopOrderInfo.getLogisticsFee());
        tvOrderPayReal.setText("¥" + myShopOrderInfo.getOrderAmountTotal());
        tvPriceGreen.setText("¥" + myShopOrderInfo.getOrderAmountTotal());


        if ("待支付".equals(myShopOrderInfo.getOrderStatus().getText())) {
            llBottom.setVisibility(View.VISIBLE);
            llLeft.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setVisibility(View.VISIBLE);
            tvGreenOrderRight.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setText("取消订单");
            tvGreenOrderRight.setText("立即支付");

            tvGreenOrderLeft.setOnClickListener(v -> {
                getDelete(myShopOrderInfo.getOrderId() + "");
            });
            tvGreenOrderRight.setOnClickListener(v -> {
                String orderId = myShopOrderInfo.getOrderId() + "";
                Intent intent = new Intent(ShopOrderDetailActivity.this, PayTypeActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
            });
        } else if ("待发货".equals(myShopOrderInfo.getOrderStatus().getText())) {
            llLeft.setVisibility(View.GONE);
            tvOrderPayStyle.setText(myShopOrderInfo.getPayMethod().getText());
            llBottom.setVisibility(View.GONE);
        } else if ("待收货".equals(myShopOrderInfo.getOrderStatus().getText())) {
            llLeft.setVisibility(View.GONE);
            tvOrderPayStyle.setText(myShopOrderInfo.getPayMethod().getText());
            llBottom.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setText("查看物流");
            tvGreenOrderRight.setVisibility(View.GONE);
            tvGreenOrderLeft.setOnClickListener(v -> {
                Intent intent = new Intent(ShopOrderDetailActivity.this, MyTraceActivity.class);
                intent.putExtra("orderId", myShopOrderInfo.getOrderId() + "");
                startActivity(intent);
            });
        } else if ("待评价".equals(myShopOrderInfo.getOrderStatus().getText())) {
            llLeft.setVisibility(View.GONE);
            tvOrderPayStyle.setText(myShopOrderInfo.getPayMethod().getText());
            llBottom.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setVisibility(View.GONE);
            tvGreenOrderRight.setVisibility(View.VISIBLE);
            tvGreenOrderRight.setText("评价");

            tvGreenOrderRight.setOnClickListener(v -> {
                String orderId = myShopOrderInfo.getOrderId() + "";
                if (commentSubmitDialog == null) {
                    commentSubmitDialog = new CommentSubmitDialog(ShopOrderDetailActivity.this) {
                        @Override
                        public void setOnComment(int number, String content) {
                            submitComment(number, content, orderId);
                        }
                    };
                }
                commentSubmitDialog.show();
            });
        } else if ("已关闭".equals(myShopOrderInfo.getOrderStatus().getText())) {
            llLeft.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
        }
    }

    private void getDelete(String orderId) {
        RetrofitManager.getInstance().create(CommonService.class)
                .cancelOrder(orderId)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(ShopOrderDetailActivity.this, "订单取消成功！");
                            finish();
                        } else {
                            ToastUtil.show(ShopOrderDetailActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }


    private void submitComment(int number, String content, String orderId) {
        CommentPost loginPost = new CommentPost();
        CommentPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setOrderId(orderId);
        postInfoBean.setEvaluateLevel(number + "");
        postInfoBean.setEvaluateContent(content);

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);

        RetrofitManager.getInstance().create(CommonService.class)
                .getComment(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            if (commentSubmitDialog != null) {
                                commentSubmitDialog.dismiss();
                            }
                            ToastUtil.show(ShopOrderDetailActivity.this, "评价成功");
                        } else {
                            ToastUtil.show(ShopOrderDetailActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_order_detail;
    }

    @Override
    public void initToolBar() {

    }

}
