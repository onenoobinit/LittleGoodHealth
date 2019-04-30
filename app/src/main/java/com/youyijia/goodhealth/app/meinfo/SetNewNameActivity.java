package com.youyijia.goodhealth.app.meinfo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.SetUserPost;
import com.youyijia.goodhealth.entity.User;
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

public class SetNewNameActivity extends BaseActivity {


    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.et_new_name)
    EditText etNewName;
    @BindView(R.id.iv_colse)
    ImageView ivColse;
    @BindView(R.id.arl_set_sex)
    LinearLayout arlSetSex;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_new_name;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

    @OnClick(R.id.tv_save)
    public void onClick() {
        if ("请输入您的昵称".equals(etNewName) || TextUtils.isEmpty(etNewName.getText().toString().trim())) {
            ToastUtil.show(SetNewNameActivity.this, "请输入您的昵称");
            return;
        } else if (Validator.isNicheng(etNewName.getText().toString().trim())) {
            ToastUtil.show(SetNewNameActivity.this, "你输入的昵称格式不正确，请重新输入！");
            return;
        } else {
            getData();
        }
    }

    private void getData() {
        SetUserPost loginPost = new SetUserPost();
        SetUserPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setNickName(etNewName.getText().toString().trim());

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .setUserInfo(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            User user = GoodHealthApp.getInstance().getUser();
                            user.setNickName(etNewName.getText().toString().trim());
                            GoodHealthApp.getInstance().setUser(user, true);
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            ToastUtil.show(SetNewNameActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }
}
