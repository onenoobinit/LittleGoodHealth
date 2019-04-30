package com.youyijia.goodhealth.app.set;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.adress.AdressActivity;
import com.youyijia.goodhealth.app.adress.GreenInformationActivity;
import com.youyijia.goodhealth.app.adress.UserSafeActivity;
import com.youyijia.goodhealth.entity.SetInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.utils.DataCleanManager;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.rxbus.RxBus;
import com.youyijia.hyoukalibrary.utils.SPUtil;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class SetActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    TextView tvSetVip;
    @BindView(R.id.arl_set_vip)
    RelativeLayout arlSetVip;
    @BindView(R.id.arl_set_password)
    RelativeLayout arlSetPassword;
    @BindView(R.id.arl_set_cache)
    RelativeLayout arlSetCache;
    @BindView(R.id.tv_set_out)
    TextView tvSetOut;
    @BindView(R.id.tv_set_cache)
    TextView tvSetCache;
    TextView tvCacheNumber;
    private String TOKEN = "";
    private SetInfo setInfo;
    private String totalCacheSize;
    private MyDialog myDialog;
    private TimerTask task;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.arl_set_vip, R.id.arl_set_password, R.id.arl_set_cache, R.id.tv_set_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arl_set_vip://我的收获地址
                Intent intent1 = new Intent(SetActivity.this, AdressActivity.class);
                intent1.putExtra("type", "0");
                startActivity(intent1);
                break;
            case R.id.arl_set_password://绿通就医人信息
                Intent intent = new Intent(this, GreenInformationActivity.class);
                intent.putExtra("green", "0");
                startActivity(intent);
                break;
            case R.id.arl_set_cache://账号安全
                startActivity(new Intent(SetActivity.this, UserSafeActivity.class));
                break;
            case R.id.tv_set_out://退出
                GoodHealthApp.getInstance().setUser(null, false);
                ToastUtil.show(SetActivity.this, "退出登录");
                RetrofitManager.clearCook();
                SPUtil.remove(SetActivity.this, "TOKEN");
                RxBus.get().post("MeFragment.loginOut", "");
                finish();
                break;
        }
    }


    //清楚本地缓存
    private void showPromptDialog() {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("是否清楚缓存")
                .setPositiveButton("清除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (myDialog == null) {
                            myDialog = new MyDialog(SetActivity.this);
                        }
                        DataCleanManager.clearAllCache(SetActivity.this);
                        myDialog.showDialog();
                        task = new TimerTask() {
                            @Override
                            public void run() {
                                if (myDialog != null) {
                                    myDialog.dismissDialog();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvCacheNumber.setText("0.0MB");
                                        }
                                    });

                                }
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, 500);
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (task != null) {
            task.cancel();
        }

        if (myDialog != null) {
            myDialog.dismissDialog();
        }
    }
}
