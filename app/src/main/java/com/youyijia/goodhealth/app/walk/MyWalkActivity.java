package com.youyijia.goodhealth.app.walk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalkActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_refresh)
    LinearLayout llRefresh;
    @BindView(R.id.iv_history)
    ImageView ivHistory;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rl_frist)
    RelativeLayout rlFrist;
    @BindView(R.id.rl_second)
    RelativeLayout rlSecond;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_walk;
    }

    @Override
    public void initToolBar() {

    }


    @OnClick({R.id.iv_back, R.id.ll_refresh, R.id.iv_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_refresh:
                ToastUtil.show(this,"还没做！");
                break;
            case R.id.iv_history:
                startActivity(new Intent(MyWalkActivity.this, WalkDetailActivity.class));
                break;
        }
    }
}
