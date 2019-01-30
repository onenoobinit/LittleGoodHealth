package com.mobile.android.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.app.login.LoginActivity;
import com.mobile.android.app.order.OrderActivity;
import com.mobile.android.app.set.SetActivity;
import com.mobile.android.entity.MeInfo;
import com.mobile.android.entity.MeMarkInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.utils.Constant;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseFragment;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.rxbus.RxBus;
import com.mobile.hyoukalibrary.rxbus.annotation.Subscribe;
import com.mobile.hyoukalibrary.rxbus.annotation.Tag;
import com.mobile.hyoukalibrary.rxbus.thread.EventThread;
import com.mobile.hyoukalibrary.utils.SPUtil;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by wangqiang on 2019/1/3.
 */
public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_set)
    TextView tvSet;
    @BindView(R.id.tv_warning_number)
    TextView tvWarningNumber;
    @BindView(R.id.tv_warning_content)
    TextView tvWarningContent;
    @BindView(R.id.tv_over_number)
    TextView tvOverNumber;
    @BindView(R.id.tv_over_content)
    TextView tvOverContent;
    @BindView(R.id.tv_discount_number)
    TextView tvDiscountNumber;
    @BindView(R.id.tv_discount_content)
    TextView tvDiscountContent;
    @BindView(R.id.tv_quota_number)
    TextView tvQuotaNumber;
    @BindView(R.id.tv_ticket_content)
    TextView tvTicketContent;
    @BindView(R.id.arl_head_view)
    AutoRelativeLayout arlHeadView;
    @BindView(R.id.all_number_more)
    AutoLinearLayout allNumberMore;
    @BindView(R.id.all_wait)
    AutoLinearLayout allWait;
    @BindView(R.id.all_write)
    AutoLinearLayout allWrite;
    @BindView(R.id.all_wait_pay)
    AutoLinearLayout allWaitPay;
    @BindView(R.id.all_wait_comment)
    AutoLinearLayout allWaitComment;
    @BindView(R.id.ll_root)
    AutoRelativeLayout llRoot;
    Unbinder unbinder;
    @BindView(R.id.sv_scrollview)
    ScrollView svScrollview;
    int old_offset = 0;
    int viewHeight = 0;
    @BindView(R.id.iv_marker1)
    ImageView ivMarker1;
    @BindView(R.id.tv_marker_number1)
    TextView tvMarkerNumber1;
    @BindView(R.id.iv_marker2)
    ImageView ivMarker2;
    @BindView(R.id.tv_marker_number2)
    TextView tvMarkerNumber2;
    @BindView(R.id.iv_marker3)
    ImageView ivMarker3;
    @BindView(R.id.tv_marker_number3)
    TextView tvMarkerNumber3;
    @BindView(R.id.iv_marker4)
    ImageView ivMarker4;
    @BindView(R.id.tv_marker_number4)
    TextView tvMarkerNumber4;
    Unbinder unbinder1;
    private String TOKEN = "";
    private MeInfo meInfo;
    private MeMarkInfo meMarkInfo;
    private Map<String, Object> mParams = new HashMap<String, Object>();
    private Boolean isLogin;

    public static MeFragment newInstance() {
        return new MeFragment();
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void finishCreateView(Bundle state) {
        RxBus.get().register(this);
        unbinder = ButterKnife.bind(this, parentView);
        initScrollMove();
    }

    @Override
    public void onResume() {
        isLogin = (Boolean) SPUtil.get(getApplicationContext(), Constant.IS_LOGIN, false);
        System.out.println("AAAAA" + isLogin);
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isLogin == true) {
            getMedata();
            getMarkNumber();
        }
    }

    private void getMarkNumber() {
        mParams.clear();
        mParams.put("act", "getExportOrderBillSubscript");
//        params.put("token", SupervisorApp.getUser().getToken());
        TOKEN = SupervisorApp.getUser().getToken();
        RetrofitManager.getInstance().create(CommonService.class)
                .meMarker(TOKEN, mParams)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(mContext, baseEntity.getErrMsg());
                            return;
                        } else {
                            meMarkInfo = gson.fromJson(baseEntity.getSuccess(), MeMarkInfo.class);
                            initMark();
                        }
                    }
                });
    }

    private void initMark() {
        String pendingReview = meMarkInfo.getOrderBillInfo().getPendingReview();
        String pendingOperation = meMarkInfo.getOrderBillInfo().getPendingOperation();
        String pendingPayment = meMarkInfo.getOrderBillInfo().getPendingPayment();
        String pendingComment = meMarkInfo.getOrderBillInfo().getPendingComment();
        showMark(pendingReview, tvMarkerNumber1);
        showMark(pendingOperation, tvMarkerNumber2);
        showMark(pendingPayment, tvMarkerNumber3);
        showMark(pendingComment, tvMarkerNumber4);
    }

    private void showMark(String s, TextView tv) {
        if (tv != null) {
            if (s != null && "0".equals(s)) {
                tv.setVisibility(View.GONE);
            } else if (s != null && s.length() > 0) {
                tv.setVisibility(View.VISIBLE);
                if (s.length() > 2) {
                    tv.setText("99+");
                } else {
                    tv.setText(s);
                }
            }
        }
    }

    private void getMedata() {
        params.clear();
        params.put("act", "getUserInfoData");
//        params.put("token", SupervisorApp.getUser().getToken());
        TOKEN = SupervisorApp.getUser().getToken();
        RetrofitManager.getInstance().create(CommonService.class)
                .me(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(mContext, baseEntity.getErrMsg());
                            return;
                        } else {
                            meInfo = gson.fromJson(baseEntity.getSuccess(), MeInfo.class);
                            initData();
                        }
                    }
                });
    }

    private void initData() {
        tvUserName.setText(meInfo.getUserDetail().getUserName());
        tvDiscountNumber.setText(meInfo.getUserDetail().getCouponCount() + "张");
        tvQuotaNumber.setText(meInfo.getAccDetail().getOverQuota() + "元");
    }

    /*
     * 设置下拉效果
     * */
    private void initScrollMove() {
        IOverScrollDecor iOverScrollDecor = OverScrollDecoratorHelper.setUpOverScroll(svScrollview);
        iOverScrollDecor.setOverScrollStateListener((decor, state, offset) -> {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) arlHeadView.getLayoutParams();
            if (viewHeight == 0) {
                viewHeight = arlHeadView.getHeight();
            }
            lp.height = (int) offset + viewHeight;
            old_offset = (int) offset;
            if (offset > 0) {
                llRoot.setPadding(0, -(int) offset, 0, 0);
            }
            if (lp.height > viewHeight) {
                arlHeadView.setLayoutParams(lp);
                return;
            }
            if (state == 3) {
                RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) arlHeadView.getLayoutParams();
                lp1.height = -1;
                old_offset = 0;
                arlHeadView.setLayoutParams(lp1);
                if (isLogin == true) {
                    //刷新数据
                    getMedata();
                    getMarkNumber();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        RxBus.get().unregister(this);
    }

    @OnClick({R.id.iv_user_icon, R.id.tv_user_name, R.id.tv_set, R.id.all_number_more, R.id.all_wait, R.id.all_write, R.id.all_wait_pay, R.id.all_wait_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                break;
            case R.id.tv_user_name:
                if (isLogin == false) {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.tv_set://设置
                if (isLogin == true) {
                    startActivity(new Intent(mContext, SetActivity.class));
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.all_number_more://订单更多
                if (isLogin == true) {
                    Intent intent = new Intent(mContext, OrderActivity.class);
                    intent.putExtra("number", 0);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.all_wait:
                if (isLogin == true) {
                    Intent intentwait = new Intent(mContext, OrderActivity.class);
                    intentwait.putExtra("number", 1);
                    startActivity(intentwait);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.all_write:
                if (isLogin == true) {
                    Intent intentwrite = new Intent(mContext, OrderActivity.class);
                    intentwrite.putExtra("number", 2);
                    startActivity(intentwrite);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.all_wait_pay:
                if (isLogin == true) {
                    Intent intentpay = new Intent(mContext, OrderActivity.class);
                    intentpay.putExtra("number", 3);
                    startActivity(intentpay);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.all_wait_comment:
                if (isLogin == true) {
                    Intent intentorder = new Intent(mContext, OrderActivity.class);
                    intentorder.putExtra("number", 4);
                    startActivity(intentorder);
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            default:
                break;
        }
    }

    /**
     * 登陆后判断是否领取注册基金
     */
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag("loginOut")}
    )
    public void loginOut(String nothing) {
        tvUserName.setText("点击登录");
        tvDiscountNumber.setText("0张");
        tvQuotaNumber.setText("0元");
        tvMarkerNumber1.setText("");
        tvMarkerNumber2.setText("");
        tvMarkerNumber3.setText("");
        tvMarkerNumber4.setText("");
        tvMarkerNumber1.setVisibility(View.GONE);
        tvMarkerNumber2.setVisibility(View.GONE);
        tvMarkerNumber3.setVisibility(View.GONE);
        tvMarkerNumber4.setVisibility(View.GONE);
    }
}
