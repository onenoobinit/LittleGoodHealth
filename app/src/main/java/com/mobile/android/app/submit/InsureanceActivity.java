package com.mobile.android.app.submit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.hyoukalibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/19.
 */
public class InsureanceActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_insurance_mobile)
    TextView tvInsuranceMobile;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick(R.id.tv_insurance_mobile)
    public void onClick() {
        Intent telIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:400-728-5256"));
        startActivity(telIntent);
    }
}
