package com.mobile.android.app.order;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/29.
 */
public class OrderComeActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_item_order_date)
    TextView tvItemOrderDate;
    @BindView(R.id.tv_start_py)
    TextView tvStartPy;
    @BindView(R.id.tv_start_hy)
    TextView tvStartHy;
    @BindView(R.id.tv_end_py)
    TextView tvEndPy;
    @BindView(R.id.tv_end_hy)
    TextView tvEndHy;
    @BindView(R.id.tv_head_number)
    TextView tvHeadNumber;
    @BindView(R.id.tv_head_weight)
    TextView tvHeadWeight;
    @BindView(R.id.tv_head_vol)
    TextView tvHeadVol;
    @BindView(R.id.tv_head_portion)
    TextView tvHeadPortion;
    @BindView(R.id.all_set_line)
    AutoLinearLayout allSetLine;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_js)
    TextView tvOrderJs;
    @BindView(R.id.tv_order_weight)
    TextView tvOrderWeight;
    @BindView(R.id.tv_order_vol)
    TextView tvOrderVol;
    @BindView(R.id.tv_order_size)
    TextView tvOrderSize;
    @BindView(R.id.tv_order_style)
    TextView tvOrderStyle;
    @BindView(R.id.tv_order_qk)
    TextView tvOrderQk;
    @BindView(R.id.tv_order_rksl)
    TextView tvOrderRksl;
    @BindView(R.id.tv_order_rkzl)
    TextView tvOrderRkzl;
    @BindView(R.id.tv_order_rktj)
    TextView tvOrderRktj;
    @BindView(R.id.tv_order_hwbz)
    TextView tvOrderHwbz;
    @BindView(R.id.tv_order_sure)
    TextView tvOrderSure;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_come;
    }

    @Override
    public void initToolBar() {

    }


    @OnClick(R.id.tv_order_sure)
    public void onClick() {
    }
}
