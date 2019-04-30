package com.youyijia.goodhealth.app.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.adress.WxBandActivity;
import com.youyijia.goodhealth.app.register.ForgetPasswordActivity;
import com.youyijia.goodhealth.app.register.RegisterActivity;
import com.youyijia.goodhealth.entity.LoginPost;
import com.youyijia.goodhealth.entity.User;
import com.youyijia.goodhealth.entity.WxLoginPost;
import com.youyijia.goodhealth.entity.WxbanInfo;
import com.youyijia.goodhealth.observer.WXLoginObserver;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.rxbus.annotation.Subscribe;
import com.youyijia.hyoukalibrary.rxbus.annotation.Tag;
import com.youyijia.hyoukalibrary.rxbus.thread.EventThread;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.utils.Validator;

import org.json.JSONObject;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author wangqiang
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, Observer {


    @BindView(R.id.et_login_number)
    EditText mEtLoginNumber;
    @BindView(R.id.et_login_password)
    EditText mEtLoginPassword;
    @BindView(R.id.tv_login_sure)
    TextView mTvLoginSure;
    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.rl_login_top)
    RelativeLayout rlLoginTop;
    @BindView(R.id.ll_login_phone)
    LinearLayout llLoginPhone;
    @BindView(R.id.cd_name)
    CardView cdName;
    @BindView(R.id.ll_login_password)
    LinearLayout llLoginPassword;
    @BindView(R.id.cd_password)
    CardView cdPassword;
    @BindView(R.id.cd_login)
    CardView cdLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.rl_register_forget)
    RelativeLayout rlRegisterForget;
    @BindView(R.id.rl_wx_split)
    RelativeLayout rlWxSplit;
    @BindView(R.id.iv_login_wx)
    ImageView ivLoginWx;
    @BindView(R.id.cb_login_pw)
    CheckBox cbLoginPw;
    private User user;
    private int detail = 0;
    private JSONObject jsonObject;
    private String phone;
    private String pwd;
    private IWXAPI api;
    private String authId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        WXLoginObserver.Companion.getINSTANCE().addObserver(this);
        /*//全屏没有状态栏。
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ButterKnife.bind(this);
        api = WXAPIFactory.createWXAPI(this, GoodHealthApp.APP_ID, true);
        api.registerApp(GoodHealthApp.APP_ID);

        authId = getIntent().getStringExtra("authId");
        L.i("AAAA2", authId);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.tv_login_sure, R.id.iv_login_back, R.id.tv_register, R.id.tv_forget_password, R.id.iv_login_wx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_sure://登录
                if (TextUtils.isEmpty(mEtLoginNumber.getText().toString().trim()) || "请输入大陆11位手机号".equals(mEtLoginNumber.getText().toString().trim())) {
                    ToastUtil.show(LoginActivity.this, "请输入手机号码！");
                    return;
                } else if (TextUtils.isEmpty(mEtLoginPassword.getText().toString().trim()) || "密码6到16位字母或数字".equals(mEtLoginPassword.getText().toString().trim())) {
                    ToastUtil.show(LoginActivity.this, "请输入密码!");
                    return;
                } else if (!Validator.isMobile(mEtLoginNumber.getText().toString().trim())) {
                    ToastUtil.show(LoginActivity.this, "手机格式不正确！");
                    return;
                } else if (!Validator.isPassword(mEtLoginPassword.getText().toString().trim())) {
                    ToastUtil.show(LoginActivity.this, "密码格式不正确！");
                    return;
                } else {
                    login();
                }
                break;
            case R.id.iv_login_back://返回键
                finish();
                break;
            case R.id.tv_register://注册
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra("authId", "");
                startActivity(intent);
                break;
            case R.id.tv_forget_password://忘记密码
                startActivityForResult(new Intent(this, ForgetPasswordActivity.class), 1117);
                break;
            case R.id.iv_login_wx://微信登录
                wxLogin();
                break;
            default:
                break;

        }
    }

    private void wxLogin() {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, GoodHealthApp.APP_ID, true);
        }
        if (!api.isWXAppInstalled()) {
            ToastUtil.show(LoginActivity.this, "您手机尚未安装微信，请安装后再登录");
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_goodhealth";
        api.sendReq(req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1117 && resultCode == RESULT_OK) {
            String phoneNumer = data.getStringExtra("phoneNumer");
            String password = data.getStringExtra("password");
            mEtLoginNumber.setText(phoneNumer);
            mEtLoginPassword.setText(password);
        }
    }

    private void login() {
        phone = mEtLoginNumber.getText().toString().trim();
        pwd = mEtLoginPassword.getText().toString().trim();

        LoginPost loginPost = new LoginPost();
        LoginPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setLoginName(phone);
        postInfoBean.setPassword(pwd);
        postInfoBean.setAuthId(authId);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);

        RetrofitManager.getInstance().create(CommonService.class)
                .login(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!"200".equals(baseEntity.getCode())) {
                            ToastUtil.show(LoginActivity.this, baseEntity.getMessage());
                            return;
                        } else {
                            user = gson.fromJson(baseEntity.getData(), User.class);

                            user.setLogined(true);
                            GoodHealthApp.getInstance().setUser(user, true);
                            ToastUtil.show(LoginActivity.this, "登录成功");
                            setResult(RESULT_OK);
                            out();
                        }
                    }
                });
    }

    private void out() {
        finish();
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
    protected void onDestroy() {
        super.onDestroy();
        WXLoginObserver.Companion.getINSTANCE().deleteObserver(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag("Login.finish")}
    )
    public void register(String nothing) {
        finish();
    }

    private void getData(String code) {
        WxLoginPost loginPost = new WxLoginPost();
        WxLoginPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setWxCode(code);

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);

        RetrofitManager.getInstance().create(CommonService.class)
                .wxLogin(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {

                        if ("200".equals(baseEntity.getCode())) {
                            user = gson.fromJson(baseEntity.getData(), User.class);

                            user.setLogined(true);
                            GoodHealthApp.getInstance().setUser(user, true);
                            ToastUtil.show(LoginActivity.this, "登录成功");
                            setResult(RESULT_OK);
                            out();
                        } else if ("1003".equals(baseEntity.getCode())) {
                            WxbanInfo wxbanInfo = gson.fromJson(baseEntity.getData(), WxbanInfo.class);
                            Intent intent = new Intent(LoginActivity.this, WxBandActivity.class);
                            intent.putExtra("authId", wxbanInfo.getAuthId());
                            intent.putExtra("nickName", wxbanInfo.getNickName());
                            //杀死登录页，防止数据刷新不成功
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtil.show(LoginActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WXLoginObserver) {
            SendAuth.Resp res = (SendAuth.Resp) arg;
            String code = res.code;
            getData(code);
        }
    }
}

