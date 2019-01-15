package com.mobile.android.app.set;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.GetCodeInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.mobile.hyoukalibrary.utils.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class SetMailActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.tv_bind_mail)
    TextView tvBindMail;
    private CountDownTimer timer;
    private String sPhone;
    private String TOKEN;
    private Map<String, Object> mParams = new HashMap<String, Object>();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
        TOKEN = SupervisorApp.getUser().getToken();
        tvVerify.setClickable(false);
        tvBindMail.setClickable(false);
        etMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sPhone = editable.toString();
                if (Validator.isEmail(sPhone)) {
                    tvVerify.setTextColor(Color.parseColor("#00A7F7"));
                    tvVerify.setClickable(true);
                } else {
                    tvVerify.setTextColor(Color.parseColor("#CDCDCD"));
                }
            }
        });

        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sCode = editable.toString();
                if (!TextUtils.isEmpty(sPhone) && sCode.length() > 3) {
                    tvBindMail.setBackgroundResource(R.drawable.tv_login);
                    tvBindMail.setClickable(true);
                } else {
                    tvBindMail.setBackgroundResource(R.drawable.bg_tv_gray);
                    tvBindMail.setClickable(false);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_mail;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.tv_verify, R.id.tv_bind_mail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verify:
                VerifyUtils();//验证计时
                getCode();
                break;
            case R.id.tv_bind_mail:
                getBindPhone();
                break;
        }
    }


    private void getBindPhone() {
        mParams.clear();
        mParams.put("act", "postAccountVerificationCodeData");
        mParams.put("method", "email");
        mParams.put("sendInfo", etMail.getText().toString().trim());
        mParams.put("verificationCode", etCode.getText().toString().trim());
        RetrofitManager.getInstance().create(CommonService.class)
                .checkCode(TOKEN, mParams)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SetMailActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            GetCodeInfo getCodeInfo = gson.fromJson(baseEntity.getSuccess(), GetCodeInfo.class);
                            if ("ok".equals(getCodeInfo.getMsg())) {
                                ToastUtil.show(SetMailActivity.this, "邮箱绑定成功！");
                            }
                        }
                    }
                });
    }

    private void getCode() {
        params.clear();
        params.put("act", "getAccountVerificationCodeData");
        params.put("method", "email");
        params.put("sendInfo", etMail.getText().toString().trim());
        RetrofitManager.getInstance().create(CommonService.class)
                .getCode(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SetMailActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            GetCodeInfo getCodeInfo = gson.fromJson(baseEntity.getSuccess(), GetCodeInfo.class);
                            if ("ok".equals(getCodeInfo.getMsg())) {
                                ToastUtil.show(SetMailActivity.this, "邮箱验证码已发送，请注意查收！");
                            }
                        }
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
                tvVerify.setTextColor(getResources().getColor(R.color.c_CDCDCD));
            }

            @Override
            public void onFinish() {
                tvVerify.setText("重新发送");
                tvVerify.setClickable(true);//重新获得点击
                tvVerify.setTextColor(getResources().getColor(R.color.c_00A7F7));
            }
        }.start();
    }
}
