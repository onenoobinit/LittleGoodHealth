package com.youyijia.goodhealth.app.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.youyijia.goodhealth.MainActivity;
import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;

/**
 * @author Administrator
 * @desc 闪屏页
 */
public class SplashActivity extends BaseActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        jumpActivity();
    }

    private void jumpActivity() {
        //是否登录
//        boolean isLogin = (boolean) SPUtil.get(this, Constant.IS_LOGIN, false);
        mHandler.postDelayed(() -> {
            /*if (isLogin) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }*/
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 1000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initToolBar() {

    }
}
