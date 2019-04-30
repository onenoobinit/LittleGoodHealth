package com.youyijia.goodhealth.app.center;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.Constants;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.green.GreenIndexActivity;
import com.youyijia.goodhealth.app.web.CommonWebActivity;
import com.youyijia.goodhealth.app.web.NewsWebActivity;
import com.youyijia.goodhealth.app.web.ShopWebActivity;
import com.youyijia.goodhealth.entity.MicCenterInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.SPUtil;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MicCenterAcitivity extends BaseActivity {


    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.cbanner_center)
    ConvenientBanner cbannerCenter;
    @BindView(R.id.rv_mic_center)
    RecyclerView rvMicCenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MicCenterAdapter micCenterAdapter;
    private ArrayList<MicCenterInfo.DoctorBean> miccenters = new ArrayList<>();
    private ArrayList<MicCenterInfo.BannerBean> banners = new ArrayList<>();
    private JSONObject jsonObject;
    private JSONObject jsonObject1;
    private MicCenterInfo micCenterInfo;
    //定义当前的刷新的状态
    private LOADSTATE mCurrentState = LOADSTATE.IDLE;//第一次进来空闲状态
    private int pageNum;
    private int totalPage;

    //上拉,下拉,空闲
    private enum LOADSTATE {
        LOAD, //加载中
        MORE, //上拉更多
        IDLE //空闲
    }


    @Override

    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);


        LinearLayoutManager manager = new LinearLayoutManager(MicCenterAcitivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMicCenter.setLayoutManager(manager);
        micCenterAdapter = new MicCenterAdapter(MicCenterAcitivity.this, miccenters);
        rvMicCenter.setAdapter(micCenterAdapter);

        getBannerDatas();

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            getBannerDatas();
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            refreshLayout.autoLoadMore();
            getMoreData();
        });

        rvMicCenter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mCurrentState == LOADSTATE.LOAD) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void getMoreData() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        if (pageNum == totalPage || totalPage == 0) {
            refreshLayout.finishLoadMore();
            ToastUtil.show(MicCenterAcitivity.this, "没有更多的信息了！");
            return;
        }
        mCurrentState = LOADSTATE.MORE;
        params.clear();
        params.put("channelId", "1");
        pageNum += 1;
        params.put("pageNum", pageNum);
        String json = gson.toJson(params);
        try {
            jsonObject1 = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitManager.getInstance().create(CommonService.class)
                .getmicenter(jsonObject1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
//                            ToastUtil.show(mContext, baseEntity.getData());
                            return;
                        } else {
                            micCenterInfo = gson.fromJson(baseEntity.getData(), MicCenterInfo.class);
                            List<MicCenterInfo.DoctorBean> doctor = micCenterInfo.getDoctor();

                            miccenters.addAll(doctor);
                            refreshData(miccenters, true);
                            micCenterAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                        mCurrentState = LOADSTATE.IDLE;
                    }
                });
    }

    private void getBannerDatas() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        mCurrentState = LOADSTATE.LOAD;
        params.clear();
        params.put("channelId", "1");
        pageNum = 1;
//        String json = new RequestBean.JsonMsg(params).toJson();
        String json = gson.toJson(params);
        try {
            jsonObject1 = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RetrofitManager.getInstance().create(CommonService.class)
                .getmicenter(jsonObject1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
//                            ToastUtil.show(mContext, baseEntity.getData());
                            return;
                        } else {
                            micCenterInfo = gson.fromJson(baseEntity.getData(), MicCenterInfo.class);
                            totalPage = micCenterInfo.getDoctorPageinfo().getTotalPage();
                            List<MicCenterInfo.BannerBean> banner = micCenterInfo.getBanner();
                            ArrayList<MicCenterInfo.DoctorBean> doctor = (ArrayList<MicCenterInfo.DoctorBean>) micCenterInfo.getDoctor();
                            banners.clear();
                            banners.addAll(banner);
                            initBanners();//填充轮播图


                            miccenters.clear();
                            miccenters = doctor;
                            refreshData(miccenters, false);
                            micCenterAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        mCurrentState = LOADSTATE.IDLE;
                    }
                });
    }


    private void initBanners() {
        //加载banner
        cbannerCenter.setPages(
                () -> new LocalImageHolderView(), banners)
                .startTurning(5000)
                .setPointViewVisible(true)//设置指示器是否可见
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.sy_banner_dh_wxz_icon, R.mipmap.sy_banner_dh_xz_icon})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //banner点击事件

        cbannerCenter.setOnItemClickListener(position -> {
            String name = banners.get(position).getBannerType().getName();
            //Type为0 代表是跳转普通网页  type 为1 表示跳转制定Activity Target 表示具体目标 type为其他的表示不进行跳转
            if ("LONG_IMAGE".equals(name)) {//长图
                Intent intent1 = new Intent(MicCenterAcitivity.this, CommonWebActivity.class);
                intent1.putExtra("url", Constants.LONG_URL + banners.get(position).getId());
                intent1.putExtra("title", banners.get(position).getBannerName());
                startWebActivity(banners.get(position).getBannerAim(), intent1);
            } else if ("UHEALTH_DIRECTION".equals(name)) {//健康风向文章
                Intent intent = new Intent(MicCenterAcitivity.this, NewsWebActivity.class);
                intent.putExtra("url", Constants.WIND_URL + banners.get(position).getId());
                intent.putExtra("title", "优医健康");
                intent.putExtra("directionId", banners.get(position).getId() + "");
                intent.putExtra("articleType", "HEALTH_DIRECTION");
                startActivity(intent);
            } else if ("UHEALTH_INFORMATION".equals(name)) {//健康资讯文章
                Intent intent = new Intent(MicCenterAcitivity.this, NewsWebActivity.class);
                intent.putExtra("url", Constants.JK_URL + banners.get(position).getId());
                intent.putExtra("title", "健康资讯");
                intent.putExtra("directionId", banners.get(position).getId() + "");
                intent.putExtra("articleType", "HEALTH_INFORMATION");
                startActivity(intent);
            } else if ("COMMONDITY".equals(name)) {//商品
                Intent intent = new Intent(MicCenterAcitivity.this, ShopWebActivity.class);
                intent.putExtra("url", Constants.SHOP_URL + "id=" + banners.get(position).getId() + "&type=RODUCTDETAIL");
                intent.putExtra("title", "商品详情");
                intent.putExtra("commodityId", banners.get(position).getId() + "");
               startActivity(intent);

            } else if ("LINK".equals(name)) {//链接
                Intent intent1 = new Intent(MicCenterAcitivity.this, CommonWebActivity.class);
                intent1.putExtra("url", banners.get(position).getBannerAim());
                intent1.putExtra("title", banners.get(position).getBannerName());
                startActivity(intent1);

            } else if ("LECTURE_ROOM".equals(name)) {//在线讲堂
                String token = (String) SPUtil.get(MicCenterAcitivity.this, "TOKEN", String.class);
                String url = Constants.URL_DETAIL_VIDEO + "roomId=" + banners.get(position).getId() + "&token=" + token;
                Intent intent1 = new Intent(MicCenterAcitivity.this, CommonWebActivity.class);
                intent1.putExtra("url", url);
                intent1.putExtra("title", banners.get(position).getBannerName());
                startActivity(intent1);

            } else if ("GREEN_CHANNEL_INTRODUCTION".equals(name)) {//绿通首页介绍
                startActivity(new Intent(MicCenterAcitivity.this, GreenIndexActivity.class));

            } else if ("GREEN_CHANNEL_EXPLAIN".equals(name)) {//绿通产品说明
                Intent intent = new Intent(MicCenterAcitivity.this, CommonWebActivity.class);
                intent.putExtra("url", Constants.LVCPJS_URL);
                intent.putExtra("title", "绿通产品介绍");
                startActivity(intent);
            }
        });
    }


    /**
     * banner轮换的holer
     */
    public class LocalImageHolderView implements Holder<MicCenterInfo.BannerBean> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, MicCenterInfo.BannerBean data) {
            RequestOptions op = new RequestOptions().error(R.mipmap.zz_zxal_mrbj_icon);
            Glide.with(context).load(data.getBanner()).apply(op)
                    .into(imageView);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mic_center_acitivity;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    private void refreshData(ArrayList<MicCenterInfo.DoctorBean> datas, boolean b) {
        int size = micCenterAdapter.getData().size();
        if (!b) {
            micCenterAdapter.notifyItemRangeRemoved(0, micCenterAdapter.getData().size());
        }
        micCenterAdapter.setData(datas);
        if (b) {
            rvMicCenter.getAdapter().notifyItemInserted(size);
        } else {
            rvMicCenter.getAdapter().notifyDataSetChanged();
        }
    }
}
