package com.youyijia.goodhealth.app.company;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.QyPost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CompanyActivity extends BaseActivity {


    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.rl_login_top)
    RelativeLayout rlLoginTop;
    @BindView(R.id.et_register_qy_code)
    EditText etRegisterQyCode;
    @BindView(R.id.ll_register_qy_code)
    LinearLayout llRegisterQyCode;
    @BindView(R.id.cd_qiye_code)
    CardView cdQiyeCode;
    @BindView(R.id.tv_register_sure)
    TextView tvRegisterSure;
    @BindView(R.id.cd_login)
    CardView cdLogin;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_company;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick(R.id.tv_register_sure)
    public void onClick() {
        if (TextUtils.isEmpty(etRegisterQyCode.getText().toString().trim()) || "请输入所在企业发放的服务卡卡号".equals(etRegisterQyCode.getText().toString().trim())) {
            ToastUtil.show(CompanyActivity.this, "请输入所在企业发放的服务卡卡号");
        } else {
            getData();
        }
    }

    private void getData() {
        QyPost loginPost = new QyPost();
        QyPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setCode(etRegisterQyCode.getText().toString().trim());

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);

        RetrofitManager.getInstance().create(CommonService.class)
                .getQyBind(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!"200".equals(baseEntity.getCode())) {
                            ToastUtil.show(CompanyActivity.this, baseEntity.getMessage());
                            return;
                        } else {
                           /* user = gson.fromJson(baseEntity.getData(), User.class);

                            user.setLogined(true);
                            GoodHealthApp.getInstance().setUser(user, true);
                            ToastUtil.show(CompanyActivity.this, "登录成功");
                            setResult(RESULT_OK);
                            out();*/
                        }
                    }
                });
    }
}
