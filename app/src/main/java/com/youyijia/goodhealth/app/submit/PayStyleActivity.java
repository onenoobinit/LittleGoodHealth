package com.youyijia.goodhealth.app.submit;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangqiang on 2019/1/19.
 */
public class PayStyleActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_pay_one)
    TextView tvPayOne;
    @BindView(R.id.tv_pay_second)
    TextView tvPaySecond;
    @BindView(R.id.tv_pay_three)
    TextView tvPayThree;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        tvPayOne.setText("计费重量 = ( 体积 / 0.006 - 重量 ) * 分泡比例 + 重量 若: 体积 / 0.006 < 重量 则: 计费重量 = 重量");
        tvPaySecond.setText("转运费=转运单价*重量 若: 转运单价*重量＜最低转运费，则: 转运费=最低转运费");
        tvPayThree.setText("总运费 = 单价 * 计费重量 + 转运费 若: 转运单价*重量<最低 转运费， 则: 转运费=最低转运费");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_paystyle;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

}
