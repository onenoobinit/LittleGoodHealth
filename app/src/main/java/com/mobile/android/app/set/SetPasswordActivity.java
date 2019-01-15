package com.mobile.android.app.set;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.OutInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String TOKEN;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
        TOKEN = SupervisorApp.getUser().getToken();
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


    @OnClick(R.id.tv_change_password)
    public void onClick() {
        if (!etNewPassword.getText().toString().trim().equals(etNewPasswordSure.getText().toString().trim())) {
            showPromptDialog("两次输入的密码不一致！");
            return;
        } else {
            setPassword();
        }
    }

    private void setPassword() {
        params.clear();
        params.put("act", "postChangePasswordData");
        params.put("oldPassword", etOldPassword.getText().toString().trim());
        params.put("newPassword", etNewPassword.getText().toString().trim());
        TOKEN = SupervisorApp.getUser().getToken();
        RetrofitManager.getInstance().create(CommonService.class)
                .setPassWord(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            showPromptDialog(baseEntity.getErrMsg());
                            return;
                        } else {
                            OutInfo outInfo = gson.fromJson(baseEntity.getSuccess(), OutInfo.class);
                            if ("ok".equals(outInfo.getMsg())) {
                                showPromptDialog("密码修改成功");
                            }
                        }
                    }
                });
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

    private void showPromptDialog(String s) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage(s)
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    if ("密码修改成功".equals(s)) {
                        finish();
                    }
                }).show();
    }
}
