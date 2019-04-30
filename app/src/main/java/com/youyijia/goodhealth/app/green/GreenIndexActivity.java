package com.youyijia.goodhealth.app.green;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.GreenIndexInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.GreenTellDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenIndexActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_lv_introduction)
    TextView tvLvIntroduction;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.ll_lv_tell)
    LinearLayout llLvTell;
    @BindView(R.id.ll_lv_yy)
    LinearLayout llLvYy;
    @BindView(R.id.iv_lv_bottom)
    ImageView ivLvBottom;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private GreenIndexInfo greenIndexInfo;
    private ArrayList<GreenIndexInfo.BannerListBean> banners = new ArrayList<>();
    private GreenTellDialog greenTellDialog;


    @Override

    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            getdata();
        });

        getdata();
    }

    private void getdata() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getLvInstrodution()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
                            return;
                        } else {
                            greenIndexInfo = gson.fromJson(baseEntity.getData(), GreenIndexInfo.class);
                            initData();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
    }

    private void initData() {
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(GreenIndexActivity.this)
                .load(greenIndexInfo.getIntroductionImg().getUrl())
                .apply(options)
                .into(ivLvBottom);

        List<GreenIndexInfo.BannerListBean> bannerList = greenIndexInfo.getBannerList();
        banners.clear();
        banners.addAll(bannerList);
        //加载banner
        convenientBanner.setPages(
                () -> new LocalImageHolderView(), banners)
                .startTurning(5000)
                .setPointViewVisible(true)//设置指示器是否可见
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.sy_banner_dh_wxz_icon, R.mipmap.sy_banner_dh_xz_icon})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //banner点击事件

        convenientBanner.setOnItemClickListener(position -> {
        });
    }


    /**
     * banner轮换的holer
     */
    public class LocalImageHolderView implements Holder<GreenIndexInfo.BannerListBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, GreenIndexInfo.BannerListBean data) {
            Glide.with(context).load(data.getBanner()).apply(new RequestOptions().error(R.mipmap.zz_zxal_mrbj_icon))
                    .into(imageView);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_index;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    @OnClick({R.id.ll_lv_tell, R.id.ll_lv_yy, R.id.tv_lv_introduction})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_lv_tell://打电话:
                if (greenTellDialog == null) {
                    greenTellDialog = new GreenTellDialog(GreenIndexActivity.this);
                }
                greenTellDialog.show();
                break;
            case R.id.ll_lv_yy://在线预约
                Intent intent = new Intent(GreenIndexActivity.this, GreenBookActivity.class);
                intent.putExtra("url", greenIndexInfo.getProcessImg().getUrl());
                startActivity(intent);
                break;
            case R.id.tv_lv_introduction:
                startActivity(new Intent(GreenIndexActivity.this, GreenInstrctionActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (greenTellDialog != null) {
            greenTellDialog.dismiss();
        }
    }
}
