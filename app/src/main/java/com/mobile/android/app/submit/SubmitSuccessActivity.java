package com.mobile.android.app.submit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.MainActivity;
import com.mobile.android.R;
import com.mobile.android.app.order.OrderActivity;
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
        logiToolBar.setNavigationOnClickListener(view -> {
            Intent intent = new Intent(SubmitSuccessActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    @OnClick({R.id.tv_look_order, R.id.tv_look_index})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_look_order://查看订单
                Intent intent1 = new Intent(SubmitSuccessActivity.this, OrderActivity.class);
                intent1.putExtra("number", 1);
                startActivity(intent1);
                break;
            case R.id.tv_look_index://首页逛逛
                Intent intent = new Intent(SubmitSuccessActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SubmitSuccessActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
