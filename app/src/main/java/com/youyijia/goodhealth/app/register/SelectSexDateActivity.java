package com.youyijia.goodhealth.app.register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.MainActivity;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.LoginPost;
import com.youyijia.goodhealth.entity.RegisterPost;
import com.youyijia.goodhealth.entity.User;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.PickTimeView;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SelectSexDateActivity extends BaseActivity implements PickTimeView.onSelectedChangeListener {

    @BindView(R.id.tv_line1)
    TextView tvLine1;
    @BindView(R.id.tv_line2)
    TextView tvLine2;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_boy)
    ImageView ivBoy;
    @BindView(R.id.tv_boy)
    TextView tvBoy;
    @BindView(R.id.ll_boy)
    LinearLayout llBoy;
    @BindView(R.id.iv_girl)
    ImageView ivGirl;
    @BindView(R.id.tv_girl)
    TextView tvGirl;
    @BindView(R.id.ll_girl)
    LinearLayout llGirl;
    @BindView(R.id.pt_date)
    PickTimeView ptDate;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private SimpleDateFormat sdfDate;
    private String date;
    private String phone;
    private String code;
    private String password;
    private String qycode;
    private int type;
    private int sex = 2;
    private User user;
    private String authId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);

        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        password = getIntent().getStringExtra("password");
        qycode = getIntent().getStringExtra("qycode");
        type = getIntent().getIntExtra("type", 0);
        authId = getIntent().getStringExtra("authId");
        ivBack.setOnClickListener(v -> finish());
        ptDate.setViewType(PickTimeView.TYPE_PICK_DATE);
        ptDate.setOnSelectedChangeListener(this);
        sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_sex_date;
    }

    @Override
    public void initToolBar() {

    }


    @OnClick({R.id.ll_boy, R.id.ll_girl, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_boy:
                sex = 1;
                ivBoy.setImageResource(R.mipmap.img_boy_xuanzhong);
                tvBoy.setTextColor(Color.parseColor("#4A4A4A"));
                ivGirl.setImageResource(R.mipmap.img_girl_weixuanzhong);
                tvGirl.setTextColor(Color.parseColor("#AAAAAA"));
                break;
            case R.id.ll_girl:
                sex = 0;
                ivBoy.setImageResource(R.mipmap.img_boy_weixuanzhong);
                tvBoy.setTextColor(Color.parseColor("#AAAAAA"));
                ivGirl.setImageResource(R.mipmap.img_girl_xuanzhong);
                tvGirl.setTextColor(Color.parseColor("#4A4A4A"));
                break;
            case R.id.tv_next:

                if (sex == 2) {
                    ToastUtil.show(SelectSexDateActivity.this, "请选择性别");
                } else if (type == 1) {//个人
                    getRegister();
                } else if (type == 0) {//企业
                    getregistercp();
                }
                break;
        }
    }

    private void getregistercp() {
        RegisterPost loginPost = new RegisterPost();
        RegisterPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setPhoneNumber(phone);
        postInfoBean.setAuthCode(code);
        postInfoBean.setPassword(password);
        postInfoBean.setCompanyRegisterCode(qycode);
        if (sex == 0) {
            postInfoBean.setGender("FEMALE");
        } else if (sex == 1) {
            postInfoBean.setGender("MALE");
        }
        postInfoBean.setBirthday(date);
        postInfoBean.setAuthId(authId);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getCompanyRegister(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            login();
                        } else {
                            ToastUtil.show(SelectSexDateActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getRegister() {
        RegisterPost loginPost = new RegisterPost();
        RegisterPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setPhoneNumber(phone);
        postInfoBean.setAuthCode(code);
        postInfoBean.setPassword(password);
        if (sex == 0) {
            postInfoBean.setGender("FEMALE");
        } else if (sex == 1) {
            postInfoBean.setGender("MALE");
        }
        postInfoBean.setBirthday(date);
        postInfoBean.setAuthId(authId);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getSelfRegister(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            login();
                        } else {
                            ToastUtil.show(SelectSexDateActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }


    private void login() {
        LoginPost loginPost = new LoginPost();
        LoginPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setLoginName(phone);
        postInfoBean.setPassword(password);

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
                        if ("200".equals(baseEntity.getCode())) {
                            user = gson.fromJson(baseEntity.getData(), User.class);
                            user.setLogined(true);
                            GoodHealthApp.getInstance().setUser(user, true);
                            ToastUtil.show(SelectSexDateActivity.this, "登录成功");
                            setResult(RESULT_OK);
                            startActivity(new Intent(SelectSexDateActivity.this, MainActivity.class));
                        } else {
                            ToastUtil.show(SelectSexDateActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onSelected(PickTimeView view, long timeMillis) {
        date = sdfDate.format(timeMillis);
    }
}
