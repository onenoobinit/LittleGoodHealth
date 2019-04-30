package com.youyijia.goodhealth.app.green;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GreenLcActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_lc;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

}
