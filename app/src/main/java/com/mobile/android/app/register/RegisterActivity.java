package com.mobile.android.app.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.android.Constants;
import com.mobile.android.R;
import com.mobile.android.app.web.CommonWebActivity;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;

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
    @BindView(R.id.tv_login_id)
    TextView tvLoginId;
    @BindView(R.id.all_recept)
    AutoLinearLayout allRecept;
    @BindView(R.id.tv_login_mimi)
    TextView tvLoginMimi;
    @BindView(R.id.all_yinsi)
    AutoLinearLayout allYinsi;

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
        tbDesiginerSearch.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.tv_register, R.id.tv_login_id, R.id.tv_login_mimi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                break;
            case R.id.tv_login_id:
                Intent intent = new Intent(RegisterActivity.this, CommonWebActivity.class);
                intent.putExtra("url", Constants.URL_ID);
                intent.putExtra("title", "用户协议");
                startWebActivity(Constants.URL_ID, intent);
                break;
            case R.id.tv_login_mimi:
                Intent intent1 = new Intent(RegisterActivity.this, CommonWebActivity.class);
                intent1.putExtra("url", Constants.URL_MIMI);
                intent1.putExtra("title", "隐私条款");
                startWebActivity(Constants.URL_MIMI, intent1);
                break;
            default:
                break;
        }
    }
}
