package com.mobile.android;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.NetworkUtils;
import com.mobile.hyoukalibrary.utils.StatusBarCompat;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.mobile.android.app.Memorandum.MemoRandumDetailActivity;
import com.mobile.android.app.Memorandum.MemorandumActivity;
import com.mobile.android.app.build_material_feedback.BuildingMaterialsFeedBackActivity;
import com.mobile.android.app.contact_owner.ContactOwnerActivity;
import com.mobile.android.app.decfeedback.DecFeedbackActivity;
import com.mobile.android.app.desinger.DesiginerSearchActivity;
import com.mobile.android.app.login.GlobalReceiver;
import com.mobile.android.app.missed_calls.MissedCallsActivity;
import com.mobile.android.app.more.MoreActivity;
import com.mobile.android.app.sms_send.SmsSendActivity;
import com.mobile.android.entity.TodayBwBean;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.updatebyrx2.UpdateManager;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.iv_main_logo)
    ImageView ivMainLogo;
    @BindView(R.id.tv_hadlogin_number)
    TextView tvHadloginNumber;
    @BindView(R.id.all_member_more)
    AutoLinearLayout allMemberMore;
    @BindView(R.id.tv_member_content)
    TextView tvMemberContent;
    @BindView(R.id.arl_main_toolbar)
    AutoRelativeLayout arlMainToolbar;
    @BindView(R.id.iv_main_callyz)
    ImageView ivMainCallyz;
    @BindView(R.id.arl_main_contact)
    AutoRelativeLayout arlMainContact;
    @BindView(R.id.iv_main_misscall)
    ImageView ivMainMisscall;
    @BindView(R.id.all_main_misscall)
    AutoRelativeLayout allMainMisscall;
    @BindView(R.id.iv_main_search)
    ImageView ivMainSearch;
    @BindView(R.id.arl_main_desiginer)
    AutoRelativeLayout arlMainDesiginer;
    @BindView(R.id.iv_main_dec)
    ImageView ivMainDec;
    @BindView(R.id.arl_main_dec)
    AutoRelativeLayout arlMainDec;
    @BindView(R.id.iv_main_material)
    ImageView ivMainMaterial;
    @BindView(R.id.arl_main_material)
    AutoRelativeLayout arlMainMaterial;
    @BindView(R.id.iv_main_manager)
    ImageView ivMainManager;
    @BindView(R.id.arl_main_member)
    AutoRelativeLayout arlMainMember;
    @BindView(R.id.iv_main_message)
    ImageView ivMainMessage;
    @BindView(R.id.arl_main_message)
    AutoRelativeLayout arlMainMessage;
    @BindView(R.id.iv_main_more)
    ImageView ivMainMore;
    @BindView(R.id.arl_main_more)
    AutoRelativeLayout arlMainMore;
    @BindView(R.id.sv_scrollview)
    ScrollView svScrollview;
    @BindView(R.id.arl_main_toppic)
    AutoLinearLayout arlMainPic;
    @BindView(R.id.ll_root)
    AutoLinearLayout ll_root;
    @BindView(R.id.iv_toolbar_bgd)
    ImageView mIvToolbarBgd;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private IntentFilter mIntentFilter = null;
    private GlobalReceiver mGlobalReceiver;
    private int viewHeight;
    private int old_offset;
    private String remarkId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        registerLoginOut();
        scrollviewdo();
        initData();
        //检查更新
        UpdateManager.getInstance().checkUpdate(this);
    }

    private void initData() {
        params.clear();
        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        RetrofitManager.getInstance().create(CommonService.class)
                .getTodayBw(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<TodayBwBean>>io_main())
                .subscribe(new BaseObserver<TodayBwBean>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mRefreshLayout.finishRefresh();
                    }

                    @Override
                    protected void onHandleSuccess(TodayBwBean bean) {
                        L.i("main", bean.toString());
                        mRefreshLayout.finishRefresh();
                        if (null != bean) {
                            //设置最新的备忘
                            tvMemberContent.setText(bean.getRemark_txt());
                            if (TextUtils.isEmpty(bean.getRemark_id())) {
                                allMemberMore.setVisibility(View.GONE);
                                if (TextUtils.isEmpty(bean.getRemark_txt())) {
                                    tvMemberContent.setText("今日无备忘");
                                }
                            } else {
                                allMemberMore.setVisibility(View.VISIBLE);
                                remarkId = bean.getRemark_id();
                            }
                        }
                    }
                });
        final String account = SupervisorApp.getUser().getAccount();
        if (null != account) {
            tvHadloginNumber.setText("已登录账号: " + account);
        }
    }

    private void scrollviewdo() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
               refreshlayout.autoRefresh();
                initData();
            }
        });
    }

    private void registerLoginOut() {
        //过滤器
        mIntentFilter = new IntentFilter("android.content.BroadcastReceiver.ACTION_TO_LOGIN");
        //创建广播接收者的对象
        mGlobalReceiver = new GlobalReceiver();
        //注册广播接收者的对象
        registerReceiver(mGlobalReceiver, mIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mGlobalReceiver);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initToolBar() {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
    }


    @OnClick({R.id.all_member_more, R.id.arl_main_contact, R.id.all_main_misscall, R.id.arl_main_desiginer, R.id.arl_main_dec, R.id.arl_main_material, R.id.arl_main_member, R.id.arl_main_message, R.id.arl_main_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_member_more://今日备忘更多
                final Intent intent = new Intent(MainActivity.this, MemoRandumDetailActivity.class);
                intent.putExtra("bw_id", remarkId);
                startActivity(intent);
                break;
            case R.id.arl_main_contact://联系业主
                startActivity(new Intent(MainActivity.this, ContactOwnerActivity.class));
                break;
            case R.id.all_main_misscall://未接来电
                startActivity(new Intent(MainActivity.this, MissedCallsActivity.class));
                break;
            case R.id.arl_main_desiginer://设计师查询
                startActivity(new Intent(MainActivity.this, DesiginerSearchActivity.class));
                break;
            case R.id.arl_main_dec://装修反馈
                startActivity(new Intent(MainActivity.this, DecFeedbackActivity.class));
                break;
            case R.id.arl_main_material://建材反馈
                if (!NetworkUtils.isConnected(this)) {
                    ToastUtil.show(this, "网络连接不可用，请检查你的网络");
                    return;
                }
                startActivity(new Intent(this, BuildingMaterialsFeedBackActivity.class));
                break;
            case R.id.arl_main_member://备忘管理
                startActivity(new Intent(MainActivity.this, MemorandumActivity.class));
                break;
            case R.id.arl_main_message://短信发送
                startActivity(new Intent(this, SmsSendActivity.class));
                break;
            case R.id.arl_main_more://更多功能
                startActivity(new Intent(this, MoreActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 声明一个long类型变量：用于存放上一点击“返回键”的时刻
     */
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtil.show(this, "再按一次退出程序");
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
