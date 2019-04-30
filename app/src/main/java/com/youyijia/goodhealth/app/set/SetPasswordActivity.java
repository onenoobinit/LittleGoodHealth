package com.youyijia.goodhealth.app.set;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.SetPassWordPost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.utils.Validator;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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
    @BindView(R.id.tv_change_password)
    TextView tvChangePassword;
    private String TOKEN;
    private String oldpassword;
    private String newpassword;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
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
        oldpassword = etOldPassword.getText().toString().trim();
        newpassword = etNewPassword.getText().toString().trim();
        if (!Validator.isPassword(oldpassword)) {
            ToastUtil.show(SetPasswordActivity.this, "旧密码格式不正确！");
            return;
        } else if (!Validator.isPassword(newpassword)) {
            ToastUtil.show(SetPasswordActivity.this, "新密码格式不正确！");
            return;
        } else {
            setPassword();
        }
    }

    private void setPassword() {
        SetPassWordPost loginPost = new SetPassWordPost();
        SetPassWordPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setOldPassword(oldpassword);
        postInfoBean.setNewPassword(newpassword);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .setPassWord(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(SetPasswordActivity.this, "密码修改成功");
                            finish();
                        } else {
                            ToastUtil.show(SetPasswordActivity.this, baseEntity.getMessage());
                        }
                    }
                });
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
