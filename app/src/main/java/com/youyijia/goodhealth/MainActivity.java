package com.youyijia.goodhealth;

import android.Manifest;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.app.home.FristChooseFragment;
import com.youyijia.goodhealth.app.home.IndexFragment;
import com.youyijia.goodhealth.app.home.MeFragment;
import com.youyijia.goodhealth.app.login.GlobalReceiver;
import com.youyijia.goodhealth.entity.LoginStautsInfo;
import com.youyijia.goodhealth.updatebyrx2.UpdateManager;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import kr.co.namee.permissiongen.PermissionGen;

/**
 * @author wangqiang
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.all_sy)
    LinearLayout allSy;
    @BindView(R.id.all_me)
    LinearLayout allMe;
    @BindView(R.id.iv_index)
    ImageView ivIndex;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.tv_center)
    TextView tvCenter;
    @BindView(R.id.all_center)
    LinearLayout allCenter;

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
    private Disposable mDisposable;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initFragments();
        registerLoginOut();
        checkcheck();
//        TOKEN = GoodHealthApp.getUser().getToken();
//        scrollviewdo();
//        initData();
        if (!TextUtils.isEmpty(TOKEN)) {
//            checkIsLogin();
        }
        //检查更新
        UpdateManager.getInstance().checkUpdate(this);
    }


    private void initFragments() {
        IndexFragment indexFragment = IndexFragment.newInstance();
        FristChooseFragment fristChooseFragment = FristChooseFragment.newInstance();
        MeFragment meFragment = MeFragment.newInstance();
        fragments = new Fragment[]{indexFragment, fristChooseFragment, meFragment};
        int checkFragmentPosition = getIntent().getIntExtra("checkFragmentPosition", 0);
        if (checkFragmentPosition > 0) {
            setShowingFrament(checkFragmentPosition, true);
            getIntent().removeExtra("checkFragmentPosition");
            return;
        }

        trx = getSupportFragmentManager().beginTransaction();
        trx.add(R.id.fl_content, indexFragment)
                .add(R.id.fl_content, meFragment)
                .add(R.id.fl_content, fristChooseFragment)
                .hide(fristChooseFragment)
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
                ivIndex.setImageResource(R.mipmap.ic_index_click);
                ivCenter.setImageResource(R.mipmap.ic_frist_check);
                ivMe.setImageResource(R.mipmap.ic_me_gray);
                tvIndex.setTextColor(Color.parseColor("#4EB2FF"));
                tvCenter.setTextColor(Color.parseColor("#4A4A4A"));
                tvMe.setTextColor(Color.parseColor("#4A4A4A"));
            }

            if (postion == 1) {
                ivIndex.setImageResource(R.mipmap.ic_index_gray);
                ivCenter.setImageResource(R.mipmap.ic_frist_check);
                ivMe.setImageResource(R.mipmap.ic_me_gray);
                tvIndex.setTextColor(Color.parseColor("#4A4A4A"));
                tvCenter.setTextColor(Color.parseColor("#4EB2FF"));
                tvMe.setTextColor(Color.parseColor("#4A4A4A"));
            }
            if (postion == 2) {
                ivIndex.setImageResource(R.mipmap.ic_index_gray);
                ivCenter.setImageResource(R.mipmap.ic_frist_check);
                ivMe.setImageResource(R.mipmap.ic_me_click);
                tvIndex.setTextColor(Color.parseColor("#4A4A4A"));
                tvCenter.setTextColor(Color.parseColor("#4A4A4A"));
                tvMe.setTextColor(Color.parseColor("#4EB2FF"));
            }
            trx.show(fragments[postion]).commitAllowingStateLoss();
            currentTabIndex = postion;
        }
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


    @OnClick({R.id.all_sy, R.id.all_me, R.id.all_center})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_sy://首页
                selectedCurrent = 0;
                setShowingFrament(0, true);
                break;
            case R.id.all_center://优选
                setShowingFrament(1, true);
                break;
            case R.id.all_me://我的
                setShowingFrament(2, true);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    private void checkcheck() {
        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable t) {
                        cancel();
                    }

                    @Override
                    public void onComplete() {
                        PermissionGen.needPermission(MainActivity.this, 100, PERMISSIONS_STORAGE);
                    }
                });
    }

    private void cancel() {
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}
