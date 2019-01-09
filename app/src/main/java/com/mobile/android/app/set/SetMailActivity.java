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
    private String modile;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
        tvVerify.setClickable(true);
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
                modile = etMail.getText().toString().trim();
                VerifyUtils();//验证计时
//            getToken();
                break;
            case R.id.tv_bind_mail:
                break;
        }
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
