package com.youyijia.goodhealth.app.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.MainActivity;
import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySucceedActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_back_index)
    TextView tvBackIndex;
    @BindView(R.id.tv_look)
    TextView tvLook;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        logiToolBar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(PaySucceedActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_succeed;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.tv_back_index, R.id.tv_look})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back_index:
                Intent intent = new Intent(PaySucceedActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_look:
                ToastUtil.show(PaySucceedActivity.this, "功能开发中...");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PaySucceedActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
