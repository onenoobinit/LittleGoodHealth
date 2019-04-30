package com.youyijia.goodhealth.app.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class SetMailActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;

    @BindView(R.id.tv_bind_mail)
    TextView tvBindMail;
    @BindView(R.id.tv_chage_phone)
    TextView tvChagePhone;
    @BindView(R.id.ll_change_phone)
    LinearLayout llChangePhone;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        tvChagePhone.setText(GoodHealthApp.getInstance().getUser().getPhone());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_mail;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.tv_bind_mail})


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bind_mail:
                startActivityForResult(new Intent(SetMailActivity.this, SetPhoneActivity.class), 1118);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1118 && resultCode == RESULT_OK) {
            tvBindMail.setText(GoodHealthApp.getInstance().getUser().getPhone());
        }
    }

}
