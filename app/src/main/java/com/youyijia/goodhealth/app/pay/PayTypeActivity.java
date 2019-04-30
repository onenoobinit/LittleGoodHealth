package com.youyijia.goodhealth.app.pay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.order.OrderActivity;
import com.youyijia.goodhealth.entity.WxPayInfo;
import com.youyijia.goodhealth.entity.WxPayPost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.goodhealth.widgets.dialog.PaySuggestionDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PayTypeActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.cb_zhifubao)
    CheckBox cbZhifubao;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.cb_weixin)
    CheckBox cbWeixin;
    @BindView(R.id.tv_pay_sure)
    TextView tvPaySure;
    private int payType = 0;
    private String orderId;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private PaySuggestionDialog paySuggestionDialog;
    private WxPayInfo wxPayInfo;
    private IWXAPI api;
    private MyDialog myDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, GoodHealthApp.APP_ID, true);
        api.registerApp(GoodHealthApp.APP_ID);

        logiToolBar.setNavigationOnClickListener(v -> {
            if (paySuggestionDialog == null) {
                paySuggestionDialog = new PaySuggestionDialog(PayTypeActivity.this) {
                    @Override
                    public void setOnleave() {
                        finish();
                        Intent intentwait = new Intent(PayTypeActivity.this, OrderActivity.class);
                        intentwait.putExtra("number", 1);
                        startActivity(intentwait);
                    }
                };
            }
            paySuggestionDialog.show();
        });

        orderId = getIntent().getStringExtra("orderId");

        cbWeixin.setOnCheckedChangeListener((button, isCheck) -> {
            if (isCheck) {
                cbZhifubao.setChecked(false);
                payType = 0;
            } else {

            }
        });

        cbZhifubao.setOnCheckedChangeListener((button, isCheck) -> {
            if (isCheck) {
                payType = 1;
                cbWeixin.setChecked(false);
            } else {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_type;
    }

    @Override
    public void initToolBar() {

    }


    @OnClick(R.id.tv_pay_sure)
    public void onClick() {
        if (payType == 1) {
            alipay();
        } else if (payType == 0) {
            weixinpay();
        }
    }

    private void weixinpay() {
        if (myDialog == null) {
            myDialog = new MyDialog(PayTypeActivity.this);
        }
        myDialog.showDialog();
        WxPayPost loginPost = new WxPayPost();
        WxPayPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setOrderId(orderId);

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);

        RetrofitManager.getInstance().create(CommonService.class)
                .wxpayInfo(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            wxPayInfo = gson.fromJson(baseEntity.getData(), WxPayInfo.class);
                            String appId = wxPayInfo.getAppId();
                            String nonceStr = wxPayInfo.getNonceStr();
                            String partnerId = wxPayInfo.getPartnerId();
                            String prepayId = wxPayInfo.getPrepayId();
                            String sign = wxPayInfo.getSign();
                            String timestamp = wxPayInfo.getTimestamp() + "";
                            String wxPackage = wxPayInfo.getWxPackage();

                            PayReq req = new PayReq();
                            req.appId = appId;
                            req.partnerId = partnerId;
                            req.prepayId = prepayId;
                            req.nonceStr = nonceStr;
                            req.timeStamp = timestamp;
                            req.packageValue = wxPackage;
                            req.sign = sign;

                            api.sendReq(req);
                        } else {
                            ToastUtil.show(PayTypeActivity.this, baseEntity.getMessage());
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

    private void alipay() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getOrderInfo(orderId)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            String orderInfo = baseEntity.getMessage();
                            final Runnable payRunnable = new Runnable() {
                                @Override
                                public void run() {
                                    PayTask alipay = new PayTask(PayTypeActivity.this);
                                    Map<String, String> result = alipay.payV2(orderInfo, true);
                                    L.i("支付msp", result.toString());
                                    Message message = new Message();
                                    message.what = SDK_PAY_FLAG;
                                    message.obj = result;
                                    mHandler.sendMessage(message);
                                }
                            };
                            // 必须异步调用
                            Thread payThread = new Thread(payRunnable);
                            payThread.start();
                        } else {
                            ToastUtil.show(PayTypeActivity.this, baseEntity.getMessage());

                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();

                    }
                });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /* *
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。*/

                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    L.i("支付msg结果", resultInfo);
                    L.i("支付msg状态", resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtil.show(PayTypeActivity.this, "支付成功");
                        finish();
                        Intent intent = new Intent(PayTypeActivity.this, PaySucceedActivity.class);
                        startActivity(intent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        if (TextUtils.equals(resultStatus, "8000") || TextUtils.equals(resultStatus, "6004")) {
                            Toast.makeText(PayTypeActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            Toast.makeText(PayTypeActivity.this, "支付已取消", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayTypeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                        ToastUtil.show(PayTypeActivity.this, "支付失败");
                    }
                    break;
                }

                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (paySuggestionDialog != null) {
            paySuggestionDialog.dismiss();
        }
        if (myDialog != null) {
            myDialog.dismissDialog();
        }
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (paySuggestionDialog == null) {
            paySuggestionDialog = new PaySuggestionDialog(PayTypeActivity.this) {
                @Override
                public void setOnleave() {
                    finish();
                    Intent intentwait = new Intent(PayTypeActivity.this, OrderActivity.class);
                    intentwait.putExtra("number", 1);
                    startActivity(intentwait);
                }
            };
        }
        paySuggestionDialog.show();
    }*/
}
