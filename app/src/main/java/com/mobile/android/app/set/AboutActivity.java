package com.mobile.android.app.set;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mobile.android.Constants;
import com.mobile.android.R;
import com.mobile.android.app.web.CommonWebActivity;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_set_cache)
    TextView tvSetCache;
    @BindView(R.id.arl_feed_back)
    AutoRelativeLayout arlFeedBack;
    @BindView(R.id.arl_question_search)
    AutoRelativeLayout arlQuestionSearch;
    @BindView(R.id.arl_about_share)
    AutoRelativeLayout arlAboutShare;
    @BindView(R.id.tv_xieyi1)
    TextView tvXieyi1;
    @BindView(R.id.tv_xieyi2)
    TextView tvXieyi2;
    @BindView(R.id.tv_about_version_code)
    TextView tvAboutVersionCode;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.parseColor("#F5A623"), 0);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.arl_feed_back, R.id.arl_question_search, R.id.arl_about_share, R.id.tv_xieyi1, R.id.tv_xieyi2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arl_feed_back://意见反馈
                startActivity(new Intent(AboutActivity.this, FeedBackActivity.class));
                break;
            case R.id.arl_question_search:
                break;
            case R.id.arl_about_share:
                break;
            case R.id.tv_xieyi1:
                Intent intent = new Intent(AboutActivity.this, CommonWebActivity.class);
                intent.putExtra("url", Constants.URL_ID);
                intent.putExtra("title", "用户协议");
                startWebActivity(Constants.URL_ID, intent);
                break;
            case R.id.tv_xieyi2:
                Intent intent1 = new Intent(AboutActivity.this, CommonWebActivity.class);
                intent1.putExtra("url", Constants.URL_MIMI);
                intent1.putExtra("title", "隐私条款");
                startWebActivity(Constants.URL_MIMI, intent1);
                break;
        }
    }
}
