package com.mobile.android;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.app.home.IndexFragment;
import com.mobile.android.app.home.MeFragment;
import com.mobile.android.app.login.GlobalReceiver;
import com.mobile.android.entity.LoginStautsInfo;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.updatebyrx2.UpdateManager;
import com.mobile.android.utils.Constant;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.StatusBarCompat;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wangqiang
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.all_sy)
    AutoLinearLayout allSy;
    @BindView(R.id.all_me)
    AutoLinearLayout allMe;
    @BindView(R.id.iv_index)
    ImageView ivIndex;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;

    private IntentFilter mIntentFilter = null;
    private GlobalReceiver mGlobalReceiver;
    private int viewHeight;
    private int old_offset;
    private String remarkId;
    private Fragment[] fragments;
    private FragmentTransaction trx;
    private int currentTabIndex;
    private int selectedCurrent = 0;
    private String TOKEN = "";
    private LoginStautsInfo loginStautsInfo;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initFragments();
        registerLoginOut();
        TOKEN = SupervisorApp.getUser().getToken();
//        scrollviewdo();
//        initData();
        if (!TextUtils.isEmpty(TOKEN)) {
            checkIsLogin();
        }
        //检查更新
        UpdateManager.getInstance().checkUpdate(this);
    }

    private void checkIsLogin() {
        params.clear();
        params.put("act", "getLoginTypeData");
        RetrofitManager.getInstance().create(CommonService.class)
                .getLoginStauts(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(MainActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            loginStautsInfo = gson.fromJson(baseEntity.getSuccess(), LoginStautsInfo.class);
                            if ("0".equals(loginStautsInfo.getLoginType())) {//未登录
                                SPUtil.put(SupervisorApp.getInstance(), Constant.IS_LOGIN, false);
                                SPUtil.remove(SupervisorApp.getInstance(), "user");
                                SupervisorApp.setUser(null);
                            } else if ("1".equals(loginStautsInfo.getLoginType())) {//已登录

                            }
                        }
                    }
                });
    }

    private void initFragments() {
        IndexFragment indexFragment = IndexFragment.newInstance();
        MeFragment meFragment = MeFragment.newInstance();
        fragments = new Fragment[]{indexFragment, meFragment};
        int checkFragmentPosition = getIntent().getIntExtra("checkFragmentPosition", 0);
        if (checkFragmentPosition > 0) {
            setShowingFrament(checkFragmentPosition, true);
            getIntent().removeExtra("checkFragmentPosition");
            return;
        }

        trx = getSupportFragmentManager().beginTransaction();
        trx.add(R.id.fl_content, indexFragment)
                .add(R.id.fl_content, meFragment)
                .hide(meFragment)
                .show(indexFragment).commitAllowingStateLoss();
    }

    /**
     * Fragment切换
     */
    private void setShowingFrament(int postion, boolean b) {
        if (currentTabIndex != postion) {
            trx = getSupportFragmentManager().beginTransaction();
            if (b) {
                if (postion > currentTabIndex) {
                    trx.setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out
                    );
                } else {
                    trx.setCustomAnimations(
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    );
                }
            }
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[postion].isAdded()) {
                trx.add(R.id.fl_content, fragments[postion]);
            }
            //首页
            if (postion == 0) {
                ivIndex.setImageResource(R.mipmap.tab_index_select);
                ivMe.setImageResource(R.mipmap.tab_me);
            }

            if (postion == 1) {
                ivIndex.setImageResource(R.mipmap.tab_index);
                ivMe.setImageResource(R.mipmap.tab_me_select);
            }
            trx.show(fragments[postion]).commitAllowingStateLoss();
            currentTabIndex = postion;
        }
    }

    private void initData() {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getTodayBw(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<TodayBwBean>>io_main())
                .subscribe(new BaseObserver<TodayBwBean>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
//                        mRefreshLayout.finishRefresh();
                    }

                    @Override
                    protected void onHandleSuccess(TodayBwBean bean) {
                        L.i("main", bean.toString());
                       *//* mRefreshLayout.finishRefresh();
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
                        }*//*
                    }
                });*/
//        final String account = SupervisorApp.getUser().getAccount();
       /* if (null != account) {
//            tvHadloginNumber.setText("已登录账号: " + account);
        }*/
    }

    /*private void scrollviewdo() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.autoRefresh();
                initData();
            }
        });
    }*/

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


    @OnClick({R.id.all_sy, R.id.all_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_sy://首页
                selectedCurrent = 0;
                setShowingFrament(0, true);
                break;
            case R.id.all_me://我的
                setShowingFrament(1, true);
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
