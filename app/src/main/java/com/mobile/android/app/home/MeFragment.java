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
import com.mobile.android.app.order.OrderActivity;
import com.mobile.android.app.set.SetActivity;
import com.mobile.android.entity.MeInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseFragment;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

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

    public static MeFragment newInstance() {
        return new MeFragment();
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void finishCreateView(Bundle state) {
        unbinder = ButterKnife.bind(this, parentView);
        initScrollMove();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMedata();
    }

    private void getMedata() {
        params.clear();
        params.put("act", "getUserInfoData");
//        params.put("token", SupervisorApp.getUser().getToken());
        RetrofitManager.getInstance().create(CommonService.class)
                .me(params)
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
                            MeInfo meInfo = gson.fromJson(baseEntity.getSuccess(), MeInfo.class);
                        }
                    }
                });
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
                //刷新数据
                getMedata();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user_icon, R.id.tv_user_name, R.id.tv_set, R.id.all_number_more, R.id.all_wait, R.id.all_write, R.id.all_wait_pay, R.id.all_wait_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                break;
            case R.id.tv_user_name:
                break;
            case R.id.tv_set://设置
                startActivity(new Intent(mContext, SetActivity.class));
                break;
            case R.id.all_number_more://订单更多
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("number", 0);
                startActivity(intent);
                break;
            case R.id.all_wait:
                Intent intentwait = new Intent(mContext, OrderActivity.class);
                intentwait.putExtra("number", 1);
                startActivity(intentwait);
                break;
            case R.id.all_write:
                Intent intentwrite = new Intent(mContext, OrderActivity.class);
                intentwrite.putExtra("number", 2);
                startActivity(intentwrite);
                break;
            case R.id.all_wait_pay:
                Intent intentpay = new Intent(mContext, OrderActivity.class);
                intentpay.putExtra("number", 3);
                startActivity(intentpay);
                break;
            case R.id.all_wait_comment:
                Intent intentorder = new Intent(mContext, OrderActivity.class);
                intentorder.putExtra("number", 4);
                startActivity(intentorder);
                break;
        }
    }

}
