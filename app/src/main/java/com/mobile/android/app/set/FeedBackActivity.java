package com.mobile.android.app.set;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.zhy.autolayout.AutoRelativeLayout;

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

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
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
                text = editFeedbackContent.getText().toString().trim();
                tvContentCount.setText(text.trim().length() + "");
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

    }
}
