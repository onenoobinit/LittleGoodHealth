package com.mobile.android.app.set;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.MainActivity;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.OutInfo;
import com.mobile.android.entity.SetInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.utils.Constant;
import com.mobile.android.utils.DataCleanManager;
import com.mobile.android.widgets.dialog.MyDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.rxbus.RxBus;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class SetActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_set_sex)
    TextView tvSetSex;
    @BindView(R.id.tv_set_phone)
    TextView tvSetPhone;
    @BindView(R.id.arl_set_phone)
    AutoRelativeLayout arlSetPhone;
    @BindView(R.id.tv_set_mail)
    TextView tvSetMail;
    @BindView(R.id.arl_set_mail)
    AutoRelativeLayout arlSetMail;
    @BindView(R.id.tv_set_id)
    TextView tvSetId;
    @BindView(R.id.arl_set_id)
    AutoRelativeLayout arlSetId;
    @BindView(R.id.tv_set_vip)
    TextView tvSetVip;
    @BindView(R.id.arl_set_vip)
    AutoRelativeLayout arlSetVip;
    @BindView(R.id.arl_set_password)
    AutoRelativeLayout arlSetPassword;
    @BindView(R.id.arl_set_cache)
    AutoRelativeLayout arlSetCache;
    @BindView(R.id.arl_set_about)
    AutoRelativeLayout arlSetAbout;
    @BindView(R.id.tv_set_out)
    TextView tvSetOut;
    @BindView(R.id.tv_set_cache)
    TextView tvSetCache;
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.tv_set_brithday)
    TextView tvSetBrithday;
    @BindView(R.id.tv_cache_number)
    TextView tvCacheNumber;
    private String TOKEN = "";
    private SetInfo setInfo;
    private String totalCacheSize;
    private MyDialog myDialog;
    private TimerTask task;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSet();
        tvCacheNumber.setText(totalCacheSize);
    }

    private void getSet() {
        params.clear();
        params.put("act", "getPersonalInfoData");
//        params.put("token", SupervisorApp.getUser().getToken());
        TOKEN = SupervisorApp.getUser().getToken();
        RetrofitManager.getInstance().create(CommonService.class)
                .getSet(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SetActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            setInfo = gson.fromJson(baseEntity.getSuccess(), SetInfo.class);
                            initData();
                        }
                    }
                });
    }

    private void initData() {
        tvSetName.setText(setInfo.getUserName());
        tvSetSex.setText(setInfo.getSex());
        tvSetBrithday.setText(setInfo.getBirthday());
        tvSetPhone.setText(setInfo.getMobile());
        tvSetMail.setText(setInfo.getEmail());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.arl_set_phone, R.id.arl_set_mail, R.id.arl_set_id, R.id.arl_set_vip, R.id.arl_set_password, R.id.arl_set_cache, R.id.arl_set_about, R.id.tv_set_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arl_set_phone://绑定手机
                startActivity(new Intent(this, SetPhoneActivity.class));
                break;
            case R.id.arl_set_mail://绑定邮箱
                startActivity(new Intent(this, SetMailActivity.class));
                break;
            case R.id.arl_set_id://绑定身份证

                break;
            case R.id.arl_set_vip://会员等级
                break;
            case R.id.arl_set_password://修改密码:
                startActivity(new Intent(this, SetPasswordActivity.class));
                break;
            case R.id.arl_set_cache://清除缓存
                showPromptDialog();
                break;
            case R.id.arl_set_about://关于
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.tv_set_out://退出
                getOut();
                break;
        }
    }

    private void getOut() {
        params.clear();
        params.put("act", "postUserSignOutData");
//        params.put("token", SupervisorApp.getUser().getToken());
        params.put("tokenType", "2");//2是android
        TOKEN = SupervisorApp.getUser().getToken();
        RetrofitManager.getInstance().create(CommonService.class)
                .out(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(SetActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            OutInfo outInfo = gson.fromJson(baseEntity.getSuccess(), OutInfo.class);
                            if ("ok".equals(outInfo.getMsg())) {
                                SPUtil.put(SupervisorApp.getInstance(), Constant.IS_LOGIN, false);
                                SPUtil.remove(SupervisorApp.getInstance(), "user");
                                SupervisorApp.setUser(null);
                                RxBus.get().post("loginOut", "");
                                Intent intentout = new Intent(SupervisorApp.getInstance(), MainActivity.class);
                                intentout.setFlags(FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intentout);
                                finish();
                            }
                        }
                    }
                });
    }

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
