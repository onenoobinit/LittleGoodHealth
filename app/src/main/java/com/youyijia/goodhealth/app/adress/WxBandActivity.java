package com.youyijia.goodhealth.app.adress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.login.LoginActivity;
import com.youyijia.goodhealth.app.register.RegisterActivity;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WxBandActivity extends BaseActivity {

    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_band_have)
    TextView tvBandHave;
    @BindView(R.id.tv_band_no)
    TextView tvBandNo;
    private String authId;
    private String nickName;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        authId = getIntent().getStringExtra("authId");
        nickName = getIntent().getStringExtra("nickName");

        tvName.setText("Hi," + nickName + "(微信用户名)");
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_band;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.tv_band_have, R.id.tv_band_no})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_band_have://跳到登录页面
                Intent intent = new Intent(WxBandActivity.this, LoginActivity.class);
                intent.putExtra("authId", authId);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_band_no://跳到注册页面
                Intent intent1 = new Intent(WxBandActivity.this, RegisterActivity.class);
                intent1.putExtra("authId", authId);
                startActivity(intent1);
                break;
        }
    }
}
