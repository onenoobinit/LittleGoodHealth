package com.mobile.android.app.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.manager.ActivityManager;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.app.login.LoginActivity;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.utils.Constant;
import com.mobile.android.widgets.dialog.LoadingDialog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 * @desc 更多功能页面
 */
public class MoreActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("更多功能");
    }

    @OnClick({R.id.iv_back, R.id.arl_exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.arl_exit_login:
                exitLogin();
                break;
            default:
                break;
        }
    }

    private void exitLogin() {
        params.clear();
        String uid = SupervisorApp.getUser().getUid();
        String token = SupervisorApp.getUser().getToken();
        params.put("uid", uid);
        params.put("token", token);
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        RetrofitManager.getInstance().create(CommonService.class)
                .loginOut(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List>>io_main())
                .subscribe(new BaseObserver<List>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(List list) {
                        if (null != list) {
                            SPUtil.put(MoreActivity.this, Constant.IS_LOGIN, false);
                            SupervisorApp.setUser(null);
                            SPUtil.remove(SupervisorApp.getInstance(), Constant.USER);
                            startActivity(new Intent(MoreActivity.this, LoginActivity.class));
                            ToastUtil.show(MoreActivity.this, "退出登录成功");
                            ActivityManager.getInstance().finishActivity(MoreActivity.class);
                        }
                    }
                });
    }
}
