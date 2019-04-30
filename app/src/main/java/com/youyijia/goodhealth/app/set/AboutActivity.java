package com.youyijia.goodhealth.app.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.widgets.dialog.ServiceCallDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.ApplicationUtils;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

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
    RelativeLayout arlFeedBack;
    @BindView(R.id.arl_question_search)
    RelativeLayout arlQuestionSearch;
    @BindView(R.id.arl_about_share)
    RelativeLayout arlAboutShare;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_about_logo)
    ImageView ivAboutLogo;
    @BindView(R.id.rl_iv)
    RelativeLayout rlIv;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.arl_about_comformation)
    RelativeLayout arlAboutComformation;
    private ServiceCallDialog serviceCallDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        String verName = ApplicationUtils.getVerName(AboutActivity.this);
        arlAboutComformation.setVisibility(View.GONE);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.arl_feed_back, R.id.arl_question_search, R.id.arl_about_share, R.id.arl_about_comformation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arl_feed_back://版本信息
                startActivity(new Intent(this, AboutVersionActivity.class));
                break;
            case R.id.arl_question_search://客服电话
                if (serviceCallDialog == null) {
                    serviceCallDialog = new ServiceCallDialog(AboutActivity.this);
                }
                serviceCallDialog.show();
                break;
            case R.id.arl_about_share://评价建议
                startActivity(new Intent(AboutActivity.this, FeedBackActivity.class));
                break;
            case R.id.arl_about_comformation://小屋医生信息及介绍
                break;
        }
    }

}
