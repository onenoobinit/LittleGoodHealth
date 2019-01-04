package com.mobile.android.app.register;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/3.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_desiginer_search)
    Toolbar tbDesiginerSearch;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.tv_password_sure)
    EditText tvPasswordSure;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick(R.id.tv_register)
    public void onClick() {
    }
}
