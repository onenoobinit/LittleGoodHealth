package com.mobile.android.app.login;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.Constants;
import com.mobile.android.MainActivity;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.app.register.RegisterActivity;
import com.mobile.android.app.web.CommonWebActivity;
import com.mobile.android.entity.User;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.utils.AESUtils;
import com.mobile.android.utils.Constant;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.manager.ActivityManager;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangqiang
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, SurfaceHolder.Callback {

    @BindView(R.id.iv_login_pic)
    ImageView mIvLoginPic;
    @BindView(R.id.et_login_number)
    EditText mEtLoginNumber;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.tv_login_sure)
    TextView mTvLoginSure;
    @BindView(R.id.myVideo)
    SurfaceView myVideo;
    @BindView(R.id.tv_login_id)
    TextView tvLoginId;
    @BindView(R.id.all_recept)
    AutoLinearLayout allRecept;
    @BindView(R.id.tv_login_mimi)
    TextView tvLoginMimi;
    @BindView(R.id.all_yinsi)
    AutoLinearLayout allYinsi;
    @BindView(R.id.tv_to_register)
    TextView tvToRegister;
    @BindView(R.id.iv_login_left)
    ImageView ivLoginLeft;
    private LoadingDialog mLoadingDialog;
    private SurfaceHolder holder;
    private MediaPlayer mediaPlayer;
    private User user;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        /*//全屏没有状态栏。
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ButterKnife.bind(this);
        initViewVideo();

        TextChange textChange = new TextChange();
        mEtLoginNumber.addTextChangedListener(textChange);
        mEtLoginPassword.addTextChangedListener(textChange);
    }

    private void initViewVideo() {
        holder = myVideo.getHolder();
        holder.addCallback(this);
        holder.setKeepScreenOn(true);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(mediaPlayer1 -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });
        try {
            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.myview);
            mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                    file.getLength());
            mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onRestart() {
        initViewVideo();
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.tv_login_sure, R.id.tv_login_id, R.id.tv_login_mimi, R.id.tv_to_register, R.id.iv_login_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_left:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                ActivityManager.getInstance().finishActivity();
                break;
            case R.id.tv_login_sure:
                login();
                break;
            case R.id.tv_login_id:
                Intent intent = new Intent(LoginActivity.this, CommonWebActivity.class);
                intent.putExtra("url", Constants.URL_ID);
                intent.putExtra("title", "用户协议");
                startWebActivity(Constants.URL_ID, intent);
                break;
            case R.id.tv_login_mimi:
                Intent intent1 = new Intent(LoginActivity.this, CommonWebActivity.class);
                intent1.putExtra("url", Constants.URL_MIMI);
                intent1.putExtra("title", "隐私条款");
                startWebActivity(Constants.URL_MIMI, intent1);
                break;
            case R.id.tv_to_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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
           /* if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(this);
            }
            mLoadingDialog.show();*/

            params.put(ApiContstants.ACT, "postUserLoginData");
            params.put(ApiContstants.USERNAME, account);
            params.put(ApiContstants.PASSWORD, pwd);
            params.put("autoLoginType", 0);
            params.put("tokenType", "2");//2是android
            RetrofitManager.getInstance().create(CommonService.class)
                    .login(params)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                    .compose(RxSchedulers.io_main())
                    .subscribe(new BaseObserver() {
                        @Override
                        protected void onHandleSuccess(BaseEntity baseEntity) {
                            if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                                ToastUtil.show(LoginActivity.this, baseEntity.getErrMsg());
                                return;
                            } else if (TextUtils.isEmpty(baseEntity.getErrMsg()) && baseEntity.getSuccess() != null) {
                                user = gson.fromJson(baseEntity.getSuccess(), User.class);
                                user.setLogined(true);
                                String token = user.getToken();
                                SupervisorApp.setUser(user);
                                SPUtil.put(getApplicationContext(), Constant.IS_LOGIN, true);
                                SPUtil.setObject(SupervisorApp.getInstance(), Constant.USER, user);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                ToastUtil.show(LoginActivity.this, "登录成功");
                                ActivityManager.getInstance().finishActivity();
                            }
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

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
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
            if (mEtLoginNumber.getText().toString().length() > 0 && mEtLoginPassword.getText().toString().length() > 0) {
                mTvLoginSure.setBackgroundResource(R.drawable.tv_login);
            } else {
                mTvLoginSure.setBackgroundResource(R.drawable.bg_tv_gray);
            }
        }
    }
}

