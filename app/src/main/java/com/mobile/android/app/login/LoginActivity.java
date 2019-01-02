package com.mobile.android.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.manager.ActivityManager;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.mobile.android.MainActivity;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.User;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.utils.AESUtils;
import com.mobile.android.utils.Constant;
import com.mobile.android.widgets.dialog.LoadingDialog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author clz
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_login_pic)
    ImageView mIvLoginPic;
    @BindView(R.id.et_login_number)
    EditText mEtLoginNumber;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.tv_login_sure)
    TextView mTvLoginSure;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //全屏没有状态栏。
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        mTvLoginSure.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_sure:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        params.clear();
        final String account = mEtLoginNumber.getText().toString().trim();
        final String pwd = mEtLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pwd)) {
            ToastUtil.show(this, "账号和密码不能为空!");
        } else {
            String safePwd = AESUtils.encode(pwd);
            L.i("login1", safePwd);
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(this);
            }
            mLoadingDialog.show();
            params.put(ApiContstants.USERNAME, account);
            params.put(ApiContstants.PASSWORD, safePwd);
            params.put("version", getVersionName());
            RetrofitManager.getInstance().create(CommonService.class)
                    .login(params)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                    .compose(RxSchedulers.<BaseEntity<User>>io_main())
                    .subscribe(new BaseObserver<User>() {
                        @Override
                        protected void onFinally() {
                            super.onFinally();
                            mLoadingDialog.dismiss();
                        }

                        @Override
                        protected void onHandleSuccess(User user) {
                            SPUtil.put(getApplicationContext(), Constant.IS_LOGIN, true);
                            SupervisorApp.setUser(null);
                            user.setAccount(account);
                            SPUtil.setObject(SupervisorApp.getInstance(), Constant.USER, user);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            ToastUtil.show(LoginActivity.this, "登录成功");
                            ActivityManager.getInstance().finishActivity();
                        }
                    });

        }

    }

    private String getVersionName() {
        try {
            String pkName = this.getPackageName();
            String versionName = this.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            return versionName;
        } catch (Exception e) {
        }
        return null;
    }
}

