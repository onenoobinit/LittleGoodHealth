package com.youyijia.goodhealth.app.adress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.set.SetMailActivity;
import com.youyijia.goodhealth.app.set.SetPasswordActivity;
import com.youyijia.goodhealth.entity.User;
import com.youyijia.goodhealth.entity.WxbandPost;
import com.youyijia.goodhealth.observer.WXLoginObserver;
import com.youyijia.goodhealth.observer.Wxback;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserSafeActivity extends BaseActivity implements Observer {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_safe_phone)
    TextView tvSafePhone;
    @BindView(R.id.ll_safe_phone)
    LinearLayout llSafePhone;
    @BindView(R.id.arl_set_vip)
    RelativeLayout arlSetVip;
    @BindView(R.id.tv_safe_password)
    TextView tvSafePassword;
    @BindView(R.id.ll_safe_password)
    LinearLayout llSafePassword;
    @BindView(R.id.arl_set_password)
    RelativeLayout arlSetPassword;
    @BindView(R.id.tv_set_cache)
    TextView tvSetCache;
    @BindView(R.id.tv_safe_wx)
    TextView tvSafeWx;
    @BindView(R.id.ll_safe_bd)
    LinearLayout llSafeBd;
    @BindView(R.id.arl_set_cache)
    RelativeLayout arlSetCache;
    private String wxStatus = "1003";
    private IWXAPI api;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        WXLoginObserver.Companion.getINSTANCE().addObserver(this);
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, GoodHealthApp.APP_ID, true);
        api.registerApp(GoodHealthApp.APP_ID);

        getPhone();
        getwxBand();
    }

    private void getwxBand() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getwxStatus()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            wxStatus = "200";
                            tvSafeWx.setText("绑定");
                        } else if ("1003".equals(baseEntity.getCode())) {
                            wxStatus = "1003";
                            tvSafeWx.setText("未绑定");
                        } else {
                            ToastUtil.show(UserSafeActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getPhone() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getPhone()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            String phone = baseEntity.getData();
                            tvSafePhone.setText(phone);
                            User user = GoodHealthApp.getInstance().getUser();
                            user.setPhone(phone);
                            GoodHealthApp.getInstance().setUser(user, true);

                        } else {
                            ToastUtil.show(UserSafeActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_safe;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    @OnClick({R.id.ll_safe_phone, R.id.ll_safe_password, R.id.arl_set_cache})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_safe_phone://手机号
                startActivity(new Intent(UserSafeActivity.this, SetMailActivity.class));
                break;
            case R.id.ll_safe_password://去修改
                startActivity(new Intent(UserSafeActivity.this, SetPasswordActivity.class));
                break;
            case R.id.arl_set_cache://绑定与解绑:
                if ("200".equals(wxStatus)) {//当前是绑定状态 点击解绑
                    getunBand();
                } else if ("1003".equals(wxStatus)) {//绑定
                    if (api == null) {
                        api = WXAPIFactory.createWXAPI(this, GoodHealthApp.APP_ID, true);
                    }
                    if (!api.isWXAppInstalled()) {
                        ToastUtil.show(UserSafeActivity.this, "您手机尚未安装微信，请安装后再登录");
                        return;
                    }
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_goodhealth";
                    api.sendReq(req);
                }
                break;
        }
    }

    private void getunBand() {
        RetrofitManager.getInstance().create(CommonService.class)
                .wxUnbing()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            tvSafeWx.setText("未绑定");
                            wxStatus = "1003";
                        } else {
                            ToastUtil.show(UserSafeActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvSafePhone.setText(GoodHealthApp.getInstance().getUser().getPhone());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WXLoginObserver.Companion.getINSTANCE().deleteObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WXLoginObserver) {
            SendAuth.Resp res = (SendAuth.Resp) arg;
            String code = res.code;
            wxtoBand(code);
        }
    }

    private void wxtoBand(String code) {
        L.i("wxbandcode", code);
        WxbandPost loginPost = new WxbandPost();
        WxbandPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setCode(code);

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);
        RetrofitManager.getInstance().create(CommonService.class)
                .wxBing(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            tvSafeWx.setText("绑定");
                            wxStatus = "200";
                        } else {
                            ToastUtil.show(UserSafeActivity.this, baseEntity.getMessage());
                        }
                    }

                });
    }
}
