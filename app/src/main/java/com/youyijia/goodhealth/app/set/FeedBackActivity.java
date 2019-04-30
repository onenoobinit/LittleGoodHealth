package com.youyijia.goodhealth.app.set;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.FeedBackPost;
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
 * Created by wangqiang on 2019/1/9.
 */
public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.edit_feedback_content)
    EditText editFeedbackContent;
    @BindView(R.id.tv_content_max_length)
    TextView tvContentMaxLength;
    @BindView(R.id.tv_content_count)
    TextView tvContentCount;
    @BindView(R.id.arl_feedback)
    RelativeLayout arlFeedback;
    @BindView(R.id.tv_feed_submit)
    TextView tvFeedSubmit;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    private String text;
    private String TOKEN;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
//        TOKEN = GoodHealthApp.getUser().getToken();
        initListener();
        System.out.println("AAAA" + GoodHealthApp.getInstance().getUser().getPhone());
        etPhoneNumber.setText(GoodHealthApp.getInstance().getUser().getPhone());
        getPhone();
    }

    private void getPhone() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getPhone()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            String phone = baseEntity.getData();
                            etPhoneNumber.setText(phone);
                        } else {
                            ToastUtil.show(FeedBackActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initListener() {
        editFeedbackContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String sContent = editable.toString();
                tvContentCount.setText(sContent.trim().length() + "");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

    @OnClick(R.id.tv_feed_submit)
    public void onClick() {
        if ("只针对本产品进行反馈，如果咨询健康问题或者对商品有问题请使用首页“问问他”或请找客服帮忙。".equals(editFeedbackContent.getText().toString().trim()) || TextUtils.isEmpty(editFeedbackContent.getText().toString().trim())) {
            ToastUtil.show(FeedBackActivity.this, "请您输入评价建议！");
            return;
        } else if (TextUtils.isEmpty(etPhoneNumber.getText().toString().trim())) {
            ToastUtil.show(FeedBackActivity.this, "请您输入手机号！");
            return;
        } else if (!Validator.isMobile(etPhoneNumber.getText().toString().trim())) {
            ToastUtil.show(FeedBackActivity.this, "手机格式不正确！");
            return;
        } else {
            getFeedBack();
        }
    }

    private void getFeedBack() {

        FeedBackPost loginPost = new FeedBackPost();
        FeedBackPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setContent(editFeedbackContent.getText().toString().trim());
        postInfoBean.setPhoneNumber(etPhoneNumber.getText().toString().trim());

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .feedBack(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(FeedBackActivity.this, "评价成功！");
                            finish();
                        } else {
                            ToastUtil.show(FeedBackActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void showPromptDialog() {
        new AlertDialog.Builder(this)
                .setMessage("提交成功")
                .setPositiveButton("我知道了", (dialogInterface, i) -> finish()).show();
    }

}
