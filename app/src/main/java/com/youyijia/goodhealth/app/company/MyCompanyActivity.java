package com.youyijia.goodhealth.app.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tmall.ultraviewpager.UltraViewPager;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.web.CommonWebActivity;
import com.youyijia.goodhealth.entity.MyHouseInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.DensityUtil;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCompanyActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.vp_ask_banner1)
    UltraViewPager vpAskBanner1;
    @BindView(R.id.ll_house)
    LinearLayout llHouse;
    @BindView(R.id.vp_ask_banner2)
    UltraViewPager vpAskBanner2;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.vp_ask_banner3)
    UltraViewPager vpAskBanner3;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.tv_house_detail)
    TextView tvHouseDetail;
    @BindView(R.id.iv_yh_logo)
    ImageView ivYhLogo;
    private ArrayList<MyHouseInfo.NurseListBean> fristsdata = new ArrayList<>();
    private ArrayList<MyHouseInfo.InnerImgListBean> secondsdata = new ArrayList<>();
    private ArrayList<MyHouseInfo.CabinServiceListBean> threesdata = new ArrayList<>();
    private ExpertAskBannerOneAdapter mBannerOneAdapter;
    private HouserBannerAdapter houserBannerAdapter;
    private MyHouseInfo myHouseInfo;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        ivBack.setOnClickListener(v -> finish());
        getData();
    }

    private void getData() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getHouse()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            myHouseInfo = gson.fromJson(baseEntity.getData(), MyHouseInfo.class);
                            List<MyHouseInfo.NurseListBean> nurseList = myHouseInfo.getNurseList();
                            List<MyHouseInfo.InnerImgListBean> innerImgList = myHouseInfo.getInnerImgList();
                            List<MyHouseInfo.CabinServiceListBean> cabinServiceList = myHouseInfo.getCabinServiceList();

                            fristsdata.clear();
                            fristsdata.addAll(nurseList);
                            secondsdata.clear();
                            secondsdata.addAll(innerImgList);
                            threesdata.clear();
                            threesdata.addAll(cabinServiceList);
                            initData();
                        } else {
                            ToastUtil.show(MyCompanyActivity.this, baseEntity.getMessage());

                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();

                    }
                });
    }

    private void initData() {
        tvTitle.setText(myHouseInfo.getHealthCabinName());
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(MyCompanyActivity.this)
                .load(myHouseInfo.getHealthCabinLogo())
                .apply(options)
                .into(ivYhLogo);


        vpAskBanner1.setAdapter(new ExpertAskBannerOneAdapter(MyCompanyActivity.this, fristsdata) {
            @Override
            public void onItemClick(int position) {

            }
        });
        //第一个banner的初始化
        vpAskBanner1.setOffscreenPageLimit(1);
        vpAskBanner1.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //设定页面循环播放
        vpAskBanner1.setInfiniteLoop(true);
        vpAskBanner1.getViewPager().setPageMargin((int) DensityUtil.px2dp(MyCompanyActivity.this, 60));


        vpAskBanner2.setAdapter(new HouserBannerAdapter(MyCompanyActivity.this, secondsdata) {
            @Override
            public void onItemClick(int position) {

            }
        });
        //第一个banner的初始化
        vpAskBanner2.setOffscreenPageLimit(3);
        vpAskBanner2.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //设定页面循环播放
        vpAskBanner2.setInfiniteLoop(true);
        vpAskBanner2.getViewPager().setPageMargin((int) DensityUtil.px2dp(MyCompanyActivity.this, 60));


        vpAskBanner3.setAdapter(new ServiceBannerAdapter(MyCompanyActivity.this, threesdata) {
            @Override
            public void onItemClick(int position) {

            }
        });
        //第一个banner的初始化
        vpAskBanner3.setOffscreenPageLimit(4);
        vpAskBanner3.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //设定页面循环播放
        vpAskBanner3.setInfiniteLoop(true);
        vpAskBanner3.getViewPager().setPageMargin((int) DensityUtil.px2dp(MyCompanyActivity.this, 60));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_company;
    }

    @Override
    public void initToolBar() {

    }


    @OnClick(R.id.tv_house_detail)
    public void onClick() {
        Intent intent1 = new Intent(MyCompanyActivity.this, CommonWebActivity.class);
        intent1.putExtra("url", myHouseInfo.getCabinDetail());
        intent1.putExtra("title", "健康小屋");
        startActivity(intent1);
    }

}
