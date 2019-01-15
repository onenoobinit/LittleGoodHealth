package com.mobile.android.app.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.android.Constants;
import com.mobile.android.R;
import com.mobile.android.app.web.CommonWebActivity;
import com.mobile.android.entity.OutInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.Validator;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.concurrent.TimeUnit;

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
        TextChange textChange = new TextChange();
        tvName.addTextChangedListener(textChange);
        tvPassword.addTextChangedListener(textChange);
        tvPasswordSure.addTextChangedListener(textChange);
        tvRegister.setClickable(false);
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
                if (!tvPassword.getText().toString().trim().equals(tvPasswordSure.getText().toString().trim())) {
                    tvRegister.setClickable(false);
                } else {
                    toRegister();
                }
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

    private void toRegister() {
        params.clear();
        params.put("act", "postPlatformRegisteredData");
        params.put("username", tvName.getText().toString().trim());
        params.put("password", tvPassword.getText().toString().trim());
        params.put("tokenType", "1");
        RetrofitManager.getInstance().create(CommonService.class)
                .register(params)
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
                                showPromptDialog("注册成功！");
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
            if (tvName.getText().toString().length() > 0 && tvPassword.getText().toString().length() > 0
                    && tvPasswordSure.getText().toString().length() > 0 && Validator.isregister(tvName.getText().toString().trim())
                    && Validator.isregister(tvPassword.getText().toString().trim()) && Validator.isregister(tvPasswordSure.getText().toString().trim())) {
                tvRegister.setBackgroundResource(R.drawable.tv_login);
                tvRegister.setClickable(true);
            } else {
                tvRegister.setBackgroundResource(R.drawable.bg_tv_gray);
            }
        }
    }

    private void showPromptDialog(String s) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage(s)
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    if ("注册成功！".equals(s)) {
                        finish();
                    }
                }).show();
    }
}
