package com.youyijia.goodhealth.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.Constants;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.company.CompanyActivity;
import com.youyijia.goodhealth.app.company.MyCompanyActivity;
import com.youyijia.goodhealth.app.login.LoginActivity;
import com.youyijia.goodhealth.app.meinfo.MeinfoActivity;
import com.youyijia.goodhealth.app.order.OrderActivity;
import com.youyijia.goodhealth.app.set.AboutActivity;
import com.youyijia.goodhealth.app.set.SetActivity;
import com.youyijia.goodhealth.app.walk.MyWalkActivity;
import com.youyijia.goodhealth.app.web.CommonWebActivity;
import com.youyijia.goodhealth.entity.MeInfo;
import com.youyijia.goodhealth.entity.MeMarkInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.GlideCircleTransform;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseFragment;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.rxbus.RxBus;
import com.youyijia.hyoukalibrary.rxbus.annotation.Subscribe;
import com.youyijia.hyoukalibrary.rxbus.annotation.Tag;
import com.youyijia.hyoukalibrary.rxbus.thread.EventThread;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * Created by wangqiang on 2019/1/3.
 */
public class MeFragment extends BaseFragment {
    @BindView(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_set)
    ImageView iv_set;
    TextView tvTicketContent;
    @BindView(R.id.arl_head_view)
    RelativeLayout arlHeadView;
    @BindView(R.id.all_number_more)
    LinearLayout allNumberMore;
    @BindView(R.id.all_wait)
    LinearLayout allWait;
    @BindView(R.id.all_write)
    LinearLayout allWrite;
    @BindView(R.id.all_wait_pay)
    LinearLayout allWaitPay;
    @BindView(R.id.all_wait_comment)
    LinearLayout allWaitComment;
    @BindView(R.id.ll_root)
    RelativeLayout llRoot;
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
    @BindView(R.id.iv_my_company)
    ImageView ivMyCompany;
    @BindView(R.id.rl_my_company)
    RelativeLayout rlMyCompany;
    @BindView(R.id.iv_my_run)
    ImageView ivMyRun;
    @BindView(R.id.rl_my_run)
    RelativeLayout rlMyRun;
    @BindView(R.id.iv_my_textself)
    ImageView ivMyTextself;
    @BindView(R.id.rl_my_testself)
    RelativeLayout rlMyTestself;
    @BindView(R.id.iv_my_aboutwe)
    ImageView ivMyAboutwe;
    @BindView(R.id.rl_my_about)
    RelativeLayout rlMyAbout;
    @BindView(R.id.tv_user_meinfo)
    TextView tvUserMeinfo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_meinfo)
    LinearLayout llMeinfo;
    private String TOKEN = "";
    private MeInfo meInfo;
    private MeMarkInfo meMarkInfo;
    private Map<String, Object> mParams = new HashMap<String, Object>();

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
        if (!GoodHealthApp.getInstance().isNeedLogin()) {
            tvUserName.setVisibility(View.GONE);
            llMeinfo.setVisibility(View.VISIBLE);
            tvName.setText(GoodHealthApp.getInstance().getUser().getNickName());
            Glide.with(mContext)
                    .load(GoodHealthApp.getInstance().getUser().getPortrait())
                    .apply(new RequestOptions().transform(new GlideCircleTransform()))
                    .into(ivUserIcon);
        } else {
            tvUserName.setVisibility(View.VISIBLE);
            llMeinfo.setVisibility(View.GONE);
        }
        initScrollMove();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!GoodHealthApp.getInstance().isNeedLogin()) {
            tvUserName.setVisibility(View.GONE);
            llMeinfo.setVisibility(View.VISIBLE);
            tvName.setText(GoodHealthApp.getInstance().getUser().getNickName());
            Glide.with(mContext)
                    .load(GoodHealthApp.getInstance().getUser().getPortrait())
                    .apply(new RequestOptions().transform(new GlideCircleTransform()))
                    .into(ivUserIcon);
        } else {
            tvUserName.setVisibility(View.VISIBLE);
            llMeinfo.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(GoodHealthApp.getInstance().getUser().getPortrait())
                    .apply(new RequestOptions().transform(new GlideCircleTransform()).error(R.mipmap.img_boy_weixuanzhong))
                    .into(ivUserIcon);

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && !GoodHealthApp.getInstance().isNeedLogin()) {
            getMarkNumber();
        }
    }

    private void getMarkNumber() {

        RetrofitManager.getInstance().create(CommonService.class)
                .meMarker()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            meMarkInfo = gson.fromJson(baseEntity.getData(), MeMarkInfo.class);
                            initMark();
                        } else {
                            ToastUtil.show(mContext, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initMark() {
        String pendingReview = meMarkInfo.getNonPayMentNum();
        String pendingOperation = meMarkInfo.getNonSendNum();
        String pendingPayment = meMarkInfo.getNonReceiveNum();
        String pendingComment = meMarkInfo.getNonEvaluatedNum();
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
                if (!GoodHealthApp.getInstance().isNeedLogin()) {
                    getMarkNumber();
                    getHousedata();
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

    @OnClick({R.id.iv_user_icon, R.id.tv_user_name, R.id.iv_set, R.id.all_number_more, R.id.all_wait, R.id.all_write, R.id.all_wait_pay,
            R.id.all_wait_comment, R.id.rl_my_company, R.id.rl_my_run, R.id.rl_my_testself, R.id.rl_my_about, R.id.tv_user_meinfo, R.id.ll_meinfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                if (!GoodHealthApp.getInstance().isNeedLogin()) {
                    startActivity(new Intent(mContext, MeinfoActivity.class));
                }
                break;
            case R.id.tv_user_name:
                Intent intent1 = new Intent(mContext, LoginActivity.class);
                intent1.putExtra("authId", "");
                startActivityForResult(intent1, 1112);
                break;
            case R.id.ll_meinfo:
                startActivity(new Intent(mContext, MeinfoActivity.class));
                break;
            case R.id.iv_set://设置
                startActivity(new Intent(mContext, SetActivity.class));
                break;
            case R.id.all_number_more://订单更多
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("number", 0);
                startActivity(intent);
                break;
            case R.id.all_wait://待支付
                Intent intentwait = new Intent(mContext, OrderActivity.class);
                intentwait.putExtra("number", 1);
                startActivity(intentwait);
                break;
            case R.id.all_write://待发货
                Intent intentwrite = new Intent(mContext, OrderActivity.class);
                intentwrite.putExtra("number", 2);
                startActivity(intentwrite);
                break;
            case R.id.all_wait_pay://待收货
                Intent intentpay = new Intent(mContext, OrderActivity.class);
                intentpay.putExtra("number", 3);
                startActivity(intentpay);
                break;
            case R.id.all_wait_comment://待评价
                Intent intentorder = new Intent(mContext, OrderActivity.class);
                intentorder.putExtra("number", 4);
                startActivity(intentorder);
                break;
            case R.id.rl_my_company://我的企业
                if (GoodHealthApp.getInstance().isNeedLogin()) {
                    Intent intent2 = new Intent(mContext, LoginActivity.class);
                    intent2.putExtra("authId", "");
                    startActivityForResult(intent2, 1122);
                } else if (TextUtils.isEmpty(GoodHealthApp.getInstance().getUser().getCompanyName())) {
                    startActivityForResult(new Intent(mContext, CompanyActivity.class), 1123);
                } else {
                    startActivity(new Intent(mContext, MyCompanyActivity.class));
                }
                break;
            case R.id.rl_my_run://健步走
                startActivity(new Intent(mContext, MyWalkActivity.class));
                break;
            case R.id.rl_my_testself://健康自测
                Intent jkintent = new Intent(mContext, CommonWebActivity.class);
                jkintent.putExtra("url", Constants.JKZC_URL);
                jkintent.putExtra("title", "健康自测");
                startActivity(jkintent);
                break;
            case R.id.rl_my_about://关于我们
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1112 && resultCode == getActivity().RESULT_OK) {
            tvUserName.setVisibility(View.GONE);
            llMeinfo.setVisibility(View.VISIBLE);
            tvName.setText(GoodHealthApp.getInstance().getUser().getNickName());
            Glide.with(mContext)
                    .load(GoodHealthApp.getInstance().getUser().getPortrait())
                    .apply(new RequestOptions().transform(new GlideCircleTransform()))
                    .into(ivUserIcon);
            if (!GoodHealthApp.getInstance().isNeedLogin()) {
                getMarkNumber();
            }
        }

        if (requestCode == 1122 && resultCode == getActivity().RESULT_OK) {
            if (TextUtils.isEmpty(GoodHealthApp.getInstance().getUser().getCompanyName())) {
                startActivityForResult(new Intent(mContext, CompanyActivity.class), 1123);
            } else {
                startActivity(new Intent(mContext, MyCompanyActivity.class));
            }
        }

        if (requestCode == 1123 && resultCode == getActivity().RESULT_OK) {
            getHousedata();
            startActivity(new Intent(mContext, MyCompanyActivity.class));
        }
    }

    private void getHousedata() {

    }

    /**
     *
     */
    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {@Tag("MeFragment.loginOut")}
    )
    public void loginOut(String nothing) {
        llMeinfo.setVisibility(View.GONE);
        tvUserName.setVisibility(View.VISIBLE);
        Glide.with(mContext)
                .load(GoodHealthApp.getInstance().getUser().getPortrait())
                .apply(new RequestOptions().transform(new GlideCircleTransform()).error(R.mipmap.img_boy_weixuanzhong))
                .into(ivUserIcon);
    }
}
