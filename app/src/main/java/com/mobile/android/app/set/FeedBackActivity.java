package com.mobile.android.app.set;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.OutInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    AutoRelativeLayout arlFeedback;
    @BindView(R.id.tv_feed_submit)
    TextView tvFeedSubmit;
    private String text;
    private String TOKEN;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
        TOKEN = SupervisorApp.getUser().getToken();
        initListener();
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
                if (!TextUtils.isEmpty(sContent) && sContent.length() > 0) {
                    tvFeedSubmit.setBackgroundResource(R.drawable.bg_set_out);
                } else {
                    tvFeedSubmit.setBackgroundResource(R.drawable.bg_tv_gray);
                }
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
        getFeedBack();
    }

    private void getFeedBack() {
        params.clear();
        params.put("act", "postFeedbackData");
        params.put("type", "1");
        params.put("feedback", editFeedbackContent.getText().toString().trim());
        TOKEN = SupervisorApp.getUser().getToken();
        RetrofitManager.getInstance().create(CommonService.class)
                .feedBack(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(FeedBackActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            OutInfo outInfo = gson.fromJson(baseEntity.getSuccess(), OutInfo.class);
                            if ("ok".equals(outInfo.getMsg())) {
                                showPromptDialog();
                            }
                        }
                    }
                });
    }

    private void showPromptDialog() {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("提交成功")
                .setPositiveButton("我知道了", (dialogInterface, i) -> finish()).show();
    }
}
