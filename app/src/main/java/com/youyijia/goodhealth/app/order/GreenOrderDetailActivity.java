package com.youyijia.goodhealth.app.order;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.pay.PayTypeActivity;
import com.youyijia.goodhealth.entity.CommentPost;
import com.youyijia.goodhealth.entity.ExmineInfo;
import com.youyijia.goodhealth.entity.MyGreenOrderDetailInfo;
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

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GreenOrderDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_green_order_type)
    TextView tvGreenOrderType;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.iv_order_item)
    ImageView ivOrderItem;
    @BindView(R.id.tv_order_whole_name)
    TextView tvOrderWholeName;
    @BindView(R.id.tv_order_whole_style)
    TextView tvOrderWholeStyle;
    @BindView(R.id.tv_order_real_price)
    TextView tvOrderRealPrice;
    @BindView(R.id.tv_order_out_price)
    TextView tvOrderOutPrice;
    @BindView(R.id.tv_order_shop_number)
    TextView tvOrderShopNumber;
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
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    private ExmineInfo datas;
    private String orderId;
    private MyGreenOrderDetailInfo myGreenOrderDetailInfo;
    private CommentSubmitDialog commentSubmitDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        logiToolBar.setNavigationOnClickListener(v -> finish());
        orderId = getIntent().getStringExtra("orderId");
        getData();
    }

    private void getData() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getMyOrderDetail(orderId)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            myGreenOrderDetailInfo = gson.fromJson(baseEntity.getData(), MyGreenOrderDetailInfo.class);
                            initData();

                        } else {
                            ToastUtil.show(GreenOrderDetailActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }

    private void initData() {
        tvOrderStatus.setText(myGreenOrderDetailInfo.getOrderStatus().getText());
        tvOrderWholeName.setText(myGreenOrderDetailInfo.getOrderItems().get(0).getCommodityName());
        tvOrderRealPrice.setText("¥" + myGreenOrderDetailInfo.getOrderItems().get(0).getPresentUnitPrice());
        tvOrderOutPrice.setText("¥" + myGreenOrderDetailInfo.getOrderItems().get(0).getCostUnitPrice());
        tvOrderOutPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvOrderShopNumber.setText("×" + myGreenOrderDetailInfo.getOrderItems().get(0).getNumber());
        tvOrderBianhao.setText(myGreenOrderDetailInfo.getSerialNumber() + "");
        tvOrderTime.setText(myGreenOrderDetailInfo.getCreateDate());
        tvGreenPriceTotal.setText("¥" + myGreenOrderDetailInfo.getOriginProductAmountTotal());
        tvGreenPriceYh.setText("¥" + myGreenOrderDetailInfo.getReducedPrice());
        tvGreenPriceYf.setText("¥" + myGreenOrderDetailInfo.getLogisticsFee());
        tvOrderPayReal.setText("¥" + myGreenOrderDetailInfo.getOrderAmountTotal());
        tvPriceGreen.setText("¥" + myGreenOrderDetailInfo.getOrderAmountTotal());
        tvOrderPayStyle.setText(myGreenOrderDetailInfo.getOrderType().getText());

        if ("待支付".equals(myGreenOrderDetailInfo.getOrderStatus().getText())) {
            llBottom.setVisibility(View.VISIBLE);
            llLeft.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setVisibility(View.VISIBLE);
            tvGreenOrderRight.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setText("取消订单");
            tvGreenOrderRight.setText("立即支付");

            tvGreenOrderLeft.setOnClickListener(v -> {
                getDelete(myGreenOrderDetailInfo.getOrderId() + "");
            });
            tvGreenOrderRight.setOnClickListener(v -> {
                String orderId = myGreenOrderDetailInfo.getOrderId() + "";
                Intent intent = new Intent(GreenOrderDetailActivity.this, PayTypeActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
            });
        } else if ("待发货".equals(myGreenOrderDetailInfo.getOrderStatus().getText())) {
            llLeft.setVisibility(View.GONE);
            tvOrderPayStyle.setText(myGreenOrderDetailInfo.getPayMethod().getText());
            llBottom.setVisibility(View.GONE);
        } else if ("待收货".equals(myGreenOrderDetailInfo.getOrderStatus().getText())) {
            llLeft.setVisibility(View.GONE);
            tvOrderPayStyle.setText(myGreenOrderDetailInfo.getPayMethod().getText());
            llBottom.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setText("查看物流");
            tvGreenOrderRight.setVisibility(View.GONE);
            tvGreenOrderLeft.setOnClickListener(v -> {
                Intent intent = new Intent(GreenOrderDetailActivity.this, MyTraceActivity.class);
                intent.putExtra("orderId", myGreenOrderDetailInfo.getOrderId() + "");
                startActivity(intent);
            });
        } else if ("待评价".equals(myGreenOrderDetailInfo.getOrderStatus().getText())) {
            llLeft.setVisibility(View.GONE);
            tvOrderPayStyle.setText(myGreenOrderDetailInfo.getPayMethod().getText());
            llBottom.setVisibility(View.VISIBLE);
            tvGreenOrderLeft.setVisibility(View.GONE);
            tvGreenOrderRight.setVisibility(View.VISIBLE);
            tvGreenOrderRight.setText("评价");

            tvGreenOrderRight.setOnClickListener(v -> {
                String orderId = myGreenOrderDetailInfo.getOrderId() + "";
                if (commentSubmitDialog == null) {
                    commentSubmitDialog = new CommentSubmitDialog(GreenOrderDetailActivity.this) {
                        @Override
                        public void setOnComment(int number, String content) {
                            submitComment(number, content, orderId);
                        }
                    };
                }
                commentSubmitDialog.show();
            });
        } else if ("已关闭".equals(myGreenOrderDetailInfo.getOrderStatus().getText())) {
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
                            ToastUtil.show(GreenOrderDetailActivity.this, "订单取消成功！");
                            finish();
                        } else {
                            ToastUtil.show(GreenOrderDetailActivity.this, baseEntity.getMessage());
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
                            ToastUtil.show(GreenOrderDetailActivity.this, "评价成功");
                        } else {
                            ToastUtil.show(GreenOrderDetailActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_order_detail;
    }

    @Override
    public void initToolBar() {

    }
}
