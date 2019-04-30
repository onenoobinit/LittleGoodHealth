package com.youyijia.goodhealth.app.set;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.RegisterPost;
import com.youyijia.goodhealth.entity.User;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.utils.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class SetPhoneActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.tv_bind_phone)
    TextView tvBindPhone;
    @BindView(R.id.tv_change_phone)
    TextView tvChangePhone;
    @BindView(R.id.et_phone_new)
    EditText etPhoneNew;
    private CountDownTimer timer;
    private String sPhone;
    private String TOKEN;
    private Map<String, Object> mParams = new HashMap<String, Object>();
    private String modile;
    private String code;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
//        TOKEN = GoodHealthApp.getUser().getToken();
        tvVerify.setClickable(false);
        tvBindPhone.setClickable(false);
        tvVerify.setOnClickListener(this);
        tvBindPhone.setOnClickListener(this);
        tvChangePhone.setText(GoodHealthApp.getInstance().getUser().getPhone());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_phone;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verify:
                modile = etPhoneNew.getText().toString().trim();
                if (Validator.isMobile(modile)) {
                    getCode();
                    VerifyUtils();//验证计时
                } else {
                    ToastUtil.show(SetPhoneActivity.this, "手机格式不正确！");
                }
                break;
            case R.id.tv_bind_phone:
                modile = etPhoneNew.getText().toString().trim();
                code = etCode.getText().toString().trim();
                if ("请输入手机号".equals(modile) || TextUtils.isEmpty(modile)) {
                    ToastUtil.show(SetPhoneActivity.this, "请输入手机号");
                    return;
                } else if (!Validator.isMobile(modile)) {
                    ToastUtil.show(SetPhoneActivity.this, "手机号格式不正确");
                    return;
                } else if ("请输入验证码".equals(code) || TextUtils.isEmpty(code)) {
                    ToastUtil.show(SetPhoneActivity.this, "请输入验证码");
                    return;
                } else if (!Validator.isCode(code)) {
                    ToastUtil.show(SetPhoneActivity.this, "验证码格式不正确");
                    return;
                } else {
                    getBindPhone();
                }
                break;
            default:
                break;
        }
    }

    private void getBindPhone() {
        RegisterPost loginPost = new RegisterPost();
        RegisterPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setPhoneNumber(modile);
        postInfoBean.setAuthCode(code);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .setChangePhone(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            User user = GoodHealthApp.getInstance().getUser();
                            user.setPhone(modile);
                            GoodHealthApp.getInstance().setUser(user, true);
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            ToastUtil.show(SetPhoneActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getCode() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getChangeCode(modile)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(SetPhoneActivity.this, "验证码已发送");
                        } else if ("2101".equals(baseEntity.getCode())) {
                            ToastUtil.show(SetPhoneActivity.this, "距离上次发送短信不到1分钟");
                        } else {
                            ToastUtil.show(SetPhoneActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }

    private void VerifyUtils() {
        timer = new CountDownTimer(60000, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                tvVerify.setClickable(false); //设置不可点击
                tvVerify.setText("重新发送(" + l / 1000 + ")");  //设置倒计时时间
                tvVerify.setBackgroundResource(R.drawable.bg_code_gray);
            }

            @Override
            public void onFinish() {
                tvVerify.setText("重新发送");
                tvVerify.setClickable(true);//重新获得点击
                tvVerify.setBackgroundResource(R.drawable.tv_code_blue);
            }
        }.start();
    }

}
