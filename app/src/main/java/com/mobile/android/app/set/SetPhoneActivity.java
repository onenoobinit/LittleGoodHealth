package com.mobile.android.app.set;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class SetPhoneActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.tv_bind_phone)
    TextView tvBindPhone;
    private String modile;
    private CountDownTimer timer;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
        tvVerify.setClickable(true);
        tvVerify.setOnClickListener(this);
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
        modile = etPhone.getText().toString().trim();
        /*if (Validator.isMobile(modile)) {
            if (!(boolean) SPUtil.get(this, "img_code_isShow", true)) {
                new ImgCodeDialog(this) {
                    @Override
                    public void match() {
                        modile = editTextMobile.getText().toString();
                        if (Validator.isMobile(modile)) {
                            VerifyUtils();//验证计时
                            getToken();
                        } else {
                            ToastUtil.show(LoginActivity.this, "手机号格式不正确,请检查下呦");
                        }
                    }
                }.show();
                break;
            }*/
        VerifyUtils();//验证计时
//            getToken();
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
