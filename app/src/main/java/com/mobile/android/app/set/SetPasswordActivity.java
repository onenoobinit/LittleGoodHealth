package com.mobile.android.app.set;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
public class SetPasswordActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_new_password_sure)
    EditText etNewPasswordSure;
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
        TextChange textChange = new TextChange();
        etOldPassword.addTextChangedListener(textChange);
        etNewPassword.addTextChangedListener(textChange);
        etNewPasswordSure.addTextChangedListener(textChange);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

    private class TextChange implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (etNewPassword.getText().toString().length() > 0 && etNewPasswordSure.getText().toString().length() > 0
                    && etNewPassword.getText().toString().length() > 0) {
                tvChangePassword.setBackgroundResource(R.drawable.bg_set_out);
            } else {
                tvChangePassword.setBackgroundResource(R.drawable.bg_tv_gray);
            }
        }
    }

}
