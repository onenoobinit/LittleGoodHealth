package com.mobile.android.app.submit;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/20.
 */
public class SubmitSuccessActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.iv_success)
    ImageView ivSuccess;
    @BindView(R.id.tv_sucess)
    TextView tvSucess;
    @BindView(R.id.tv_look_order)
    TextView tvLookOrder;
    @BindView(R.id.tv_look_index)
    TextView tvLookIndex;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_submit_success;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.tv_look_order, R.id.tv_look_index})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_look_order://查看订单
                break;
            case R.id.tv_look_index://首页逛逛
                break;
        }
    }
}
