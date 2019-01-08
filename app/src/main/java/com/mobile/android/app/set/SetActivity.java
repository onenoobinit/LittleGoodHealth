package com.mobile.android.app.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class SetActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_set_sex)
    TextView tvSetSex;
    @BindView(R.id.tv_set_phone)
    TextView tvSetPhone;
    @BindView(R.id.arl_set_phone)
    AutoRelativeLayout arlSetPhone;
    @BindView(R.id.tv_set_mail)
    TextView tvSetMail;
    @BindView(R.id.arl_set_mail)
    AutoRelativeLayout arlSetMail;
    @BindView(R.id.tv_set_id)
    TextView tvSetId;
    @BindView(R.id.arl_set_id)
    AutoRelativeLayout arlSetId;
    @BindView(R.id.tv_set_vip)
    TextView tvSetVip;
    @BindView(R.id.arl_set_vip)
    AutoRelativeLayout arlSetVip;
    @BindView(R.id.arl_set_password)
    AutoRelativeLayout arlSetPassword;
    @BindView(R.id.arl_set_cache)
    AutoRelativeLayout arlSetCache;
    @BindView(R.id.arl_set_about)
    AutoRelativeLayout arlSetAbout;
    @BindView(R.id.tv_set_out)
    TextView tvSetOut;
    @BindView(R.id.tv_set_cache)
    TextView tvSetCache;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.arl_set_phone, R.id.arl_set_mail, R.id.arl_set_id, R.id.arl_set_vip, R.id.arl_set_password, R.id.arl_set_cache, R.id.arl_set_about, R.id.tv_set_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arl_set_phone://绑定手机
                startActivity(new Intent(this, SetPhoneActivity.class));
                break;
            case R.id.arl_set_mail://绑定邮箱
                startActivity(new Intent(this, SetMailActivity.class));
                break;
            case R.id.arl_set_id://绑定身份证

                break;
            case R.id.arl_set_vip://会员等级
                break;
            case R.id.arl_set_password://修改密码:
                startActivity(new Intent(this, SetPasswordActivity.class));
                break;
            case R.id.arl_set_cache://清除缓存
                break;
            case R.id.arl_set_about://关于
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.tv_set_out://退出
                break;
        }
    }

}
