package com.youyijia.goodhealth.app.green;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.Constants;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.web.CommonWebActivity;
import com.youyijia.goodhealth.widgets.dialog.GreenTellDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenInstrctionActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_ljzx)
    TextView tvLjzx;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rl_frist)
    RelativeLayout rlFrist;
    @BindView(R.id.rl_second)
    RelativeLayout rlSecond;
    @BindView(R.id.rl_three)
    RelativeLayout rlThree;
    private GreenTellDialog greenTellDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_instrction;
    }

    @Override
    public void initToolBar() {

    }


    @OnClick({R.id.iv_back, R.id.tv_ljzx, R.id.rl_frist, R.id.rl_second, R.id.rl_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ljzx:
                if (greenTellDialog == null) {
                    greenTellDialog = new GreenTellDialog(GreenInstrctionActivity.this);
                }
                greenTellDialog.show();
                break;
            case R.id.rl_frist:
                Intent intent = new Intent(GreenInstrctionActivity.this, CommonWebActivity.class);
                intent.putExtra("url", Constants.LVCPJS_URL);
                intent.putExtra("title", "绿通产品介绍");
                startActivity(intent);
                break;
            case R.id.rl_second:
                Intent intent1 = new Intent(GreenInstrctionActivity.this, CommonWebActivity.class);
                intent1.putExtra("url", Constants.LVFUXZ_URL);
                intent1.putExtra("title", "绿通服务细则");
                startActivity(intent1);
                break;
            case R.id.rl_three:
                startActivity(new Intent(GreenInstrctionActivity.this, GreenLcActivity.class));
                break;
        }
    }
}
