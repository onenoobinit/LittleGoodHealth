package com.youyijia.goodhealth.app.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.reflect.TypeToken;
import com.qisheng.app.activitys.DiseaseCheckActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youyijia.goodhealth.Constants;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.body.BodyHeartActivity;
import com.youyijia.goodhealth.app.center.MicCenterAcitivity;
import com.youyijia.goodhealth.app.company.CompanyActivity;
import com.youyijia.goodhealth.app.company.MyCompanyActivity;
import com.youyijia.goodhealth.app.green.GreenIndexActivity;
import com.youyijia.goodhealth.app.jknews.HealthNewsActivity;
import com.youyijia.goodhealth.app.login.LoginActivity;
import com.youyijia.goodhealth.app.web.CommonWebActivity;
import com.youyijia.goodhealth.app.web.NewsWebActivity;
import com.youyijia.goodhealth.app.web.ShopWebActivity;
import com.youyijia.goodhealth.entity.IndexBannerInfo;
import com.youyijia.goodhealth.entity.IndexRecycleInfo;
import com.youyijia.goodhealth.entity.RollMessageInfo;
import com.youyijia.goodhealth.entity.StartInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.MarqueeView;
import com.youyijia.goodhealth.widgets.ObservableScrollView;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseFragment;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.rxbus.RxBus;
import com.youyijia.hyoukalibrary.utils.SPUtil;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wangqiang on 2019/1/3.
 */
public class IndexFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.iv_index_zs1)
    ImageView ivIndexZs1;
    @BindView(R.id.ll_index_zjzx)
    RelativeLayout llIndexZjzx;
    @BindView(R.id.iv_index_zs2)
    ImageView ivIndexZs2;
    @BindView(R.id.ll_index_bjq)
    RelativeLayout llIndexBjq;
    @BindView(R.id.iv_index_zs3)
    ImageView ivIndexZs3;
    @BindView(R.id.ll_index_yzgs)
    RelativeLayout llIndexYzgs;
    @BindView(R.id.iv_index_zs4)
    ImageView ivIndexZs4;
    @BindView(R.id.ll_index_ygj)
    RelativeLayout llIndexYgj;
    @BindView(R.id.iv_index_zs5)
    ImageView ivIndexZs5;
    @BindView(R.id.ll_index_xgt)
    RelativeLayout llIndexXgt;
    @BindView(R.id.iv_index_wxt_center)
    ImageView ivIndexWxtCenter;
    @BindView(R.id.rv_index)
    RecyclerView rvIndex;
    @BindView(R.id.scroll_view)
    ObservableScrollView scrollView;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rl_index_toolbar_center)
    RelativeLayout rlIndexToolbarCenter;
    @BindView(R.id.textView_message_num)
    TextView textViewMessageNum;
    @BindView(R.id.relativeLayout_tool_bar_message)
    RelativeLayout relativeLayoutToolBarMessage;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.v_sy_tl_bot)
    View vSyTlBot;
    @BindView(R.id.titilelayout)
    RelativeLayout titilelayout;
    @BindView(R.id.mq_index)
    MarqueeView mqIndex;
    @BindView(R.id.tool_bar_imageView)
    ImageView toolBarImageView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder1;
    private ArrayList<IndexRecycleInfo> recycleDatas = new ArrayList<>();
    private String TOKEN;
    private List<StartInfo.StartPortBean> startPort;
    private IndexRvAdapter indexRvAdapter;
    private ArrayList<RollMessageInfo> notices = new ArrayList<>();
    private List<RollMessageInfo> rollDatas;
    private int headHeight;
    private ArrayList<IndexBannerInfo> banners = new ArrayList<>();
    protected Map<String, Object> mParams = new HashMap<String, Object>();
    private String name;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        RxBus.get().unregister(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    public void finishCreateView(Bundle state) {
        unbinder = ButterKnife.bind(this, parentView);
        RxBus.get().register(this);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvIndex.setLayoutManager(manager);
        indexRvAdapter = new IndexRvAdapter(mContext, recycleDatas);
        rvIndex.setAdapter(indexRvAdapter);
        initScrollview();

        getRollMessage();//获取滚动消息
        getBannerDatas();//获取轮播图
        getRecycleDatas();//获取列里消息

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getRollMessage();
                getBannerDatas();
                getRecycleDatas();
            }
        });
    }

    private void getRecycleDatas() {
        mParams.clear();
        mParams.put("offset", "0");
        mParams.put("limit", "3");
        RetrofitManager.getInstance().create(CommonService.class)
                .getRecycle(mParams)
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
                            List<IndexRecycleInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<IndexRecycleInfo>>() {
                            }.getType());
                            recycleDatas.clear();
                            recycleDatas.addAll(datas);

                            refreshData(recycleDatas, false);
                        }
                    }
                });
    }

    private void getBannerDatas() {
        params.clear();
        params.put("position", "HEAD");
        params.put("launchPlatform", "ANDROID");
        RetrofitManager.getInstance().create(CommonService.class)
                .getBanner(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<IndexBannerInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<IndexBannerInfo>>() {
                            }.getType());
                            banners.clear();
                            banners.addAll(datas);
                            initBanners();
                        } else {
                            ToastUtil.show(mContext, baseEntity.getMessage());
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

    private void initBanners() {
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
            if (banners.get(position).getBannerType() != null) {
                name = banners.get(position).getBannerType().getName();
            }
            //Type为0 代表是跳转普通网页  type 为1 表示跳转制定Activity Target 表示具体目标 type为其他的表示不进行跳转
            if ("LONG_IMAGE".equals(name)) {//长图
                Intent intent1 = new Intent(mContext, CommonWebActivity.class);
                intent1.putExtra("url", Constants.LONG_URL + banners.get(position).getId());
                intent1.putExtra("title", banners.get(position).getBannerName());
                startWebActivity(banners.get(position).getBannerAim(), intent1);
            } else if ("UHEALTH_DIRECTION".equals(name)) {//健康风向文章
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                intent.putExtra("url", Constants.WIND_URL + banners.get(position).getId());
                intent.putExtra("title", "优医健康");
                intent.putExtra("directionId", banners.get(position).getId() + "");
                intent.putExtra("articleType", "HEALTH_DIRECTION");
                mContext.startActivity(intent);
            } else if ("UHEALTH_INFORMATION".equals(name)) {//健康资讯文章
                Intent intent = new Intent(mContext, NewsWebActivity.class);
                intent.putExtra("url", Constants.JK_URL + banners.get(position).getId());
                intent.putExtra("title", "健康资讯");
                intent.putExtra("directionId", banners.get(position).getId() + "");
                intent.putExtra("articleType", "HEALTH_INFORMATION");
                mContext.startActivity(intent);
            } else if ("COMMONDITY".equals(name)) {//商品
                Intent intent = new Intent(mContext, ShopWebActivity.class);
                intent.putExtra("url", Constants.SHOP_URL + "id=" + banners.get(position).getId() + "&type=RODUCTDETAIL");
                intent.putExtra("title", "商品详情");
                intent.putExtra("commodityId", banners.get(position).getId() + "");
                mContext.startActivity(intent);

            } else if ("LINK".equals(name)) {//链接
                Intent intent1 = new Intent(mContext, CommonWebActivity.class);
                intent1.putExtra("url", banners.get(position).getBannerAim());
                intent1.putExtra("title", banners.get(position).getBannerName());
                startActivity(intent1);

            } else if ("LECTURE_ROOM".equals(name)) {//在线讲堂
                String token = (String) SPUtil.get(mContext, "TOKEN", String.class);
                String url = Constants.URL_DETAIL_VIDEO + "roomId=" + banners.get(position).getId() + "&token=" + token;
                Intent intent1 = new Intent(mContext, CommonWebActivity.class);
                intent1.putExtra("url", url);
                intent1.putExtra("title", banners.get(position).getBannerName());
                startActivity(intent1);

            } else if ("GREEN_CHANNEL_INTRODUCTION".equals(name)) {//绿通首页介绍
                startActivity(new Intent(mContext, GreenIndexActivity.class));

            } else if ("GREEN_CHANNEL_EXPLAIN".equals(name)) {//绿通产品说明
                Intent intent = new Intent(mContext, CommonWebActivity.class);
                intent.putExtra("url", Constants.LVCPJS_URL);
                intent.putExtra("title", "绿通产品介绍");
                startActivity(intent);
            }
        });
    }


    /**
     * banner轮换的holer
     */
    public class LocalImageHolderView implements Holder<IndexBannerInfo> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, IndexBannerInfo data) {
            Glide.with(context).load(data.getBanner()).apply(new RequestOptions().error(R.mipmap.zz_zxal_mrbj_icon))
                    .into(imageView);
        }
    }

    //设置滑动标题栏变化
    private void initScrollview() {
        scrollView.setScrollViewListener((view, scrollX, scrollY, clampedX, clampedY) -> {
           /* headHeight = convenientBanner.getMeasuredHeight()
                    - titilelayout.getMeasuredHeight();
//            int alpha = (int) (((float) scrollY / headHeight) * 255);*/
            if (scrollY > 0) {
                titilelayout.setVisibility(View.VISIBLE);
            } else {
                titilelayout.setVisibility(View.INVISIBLE);
            }

            if (scrollY > 0 && scrollY <= 255) {
                titilelayout.getBackground().setAlpha(scrollY);
            }

            if (scrollY <= 0) {
                titilelayout.getBackground().setAlpha(0);
            }

            if (scrollY > 255) {
                titilelayout.getBackground().setAlpha(255);
            }

        });

    }

    private void getRollMessage() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getRollMessage()
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
                            rollDatas = gson.fromJson(baseEntity.getData(), new TypeToken<List<RollMessageInfo>>() {
                            }.getType());

                            notices.clear();
                            notices.addAll(rollDatas);
                            mqIndex.startWithList(notices, 0);
                            mqIndex.setOnItemClickListener((position) -> {
                                Intent intent = new Intent(mContext, NewsWebActivity.class);
                                intent.putExtra("url", Constants.WIND_URL + rollDatas.get(position).getArticleId());
                                intent.putExtra("title", "优医健康");
                                intent.putExtra("directionId", rollDatas.get(position).getArticleId());
                                intent.putExtra("articleType", "HEALTH_DIRECTION");
                                mContext.startActivity(intent);
                            });
                        }
                    }
                });
    }

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @OnClick({R.id.ll_index_zjzx, R.id.ll_index_bjq, R.id.ll_index_yzgs, R.id.ll_index_ygj, R.id.ll_index_xgt, R.id.iv_index_wxt_center})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_index_zjzx://绿色通道
                startActivity(new Intent(mContext, GreenIndexActivity.class));
                break;
            case R.id.ll_index_bjq://症状自查:
                if (GoodHealthApp.getInstance().isNeedLogin()) {
                    Intent intent1 = new Intent(mContext, LoginActivity.class);
                    intent1.putExtra("authId", "");
                    startActivityForResult(intent1, 1119);
                } else {
                    startActivity(new Intent(mContext, DiseaseCheckActivity.class));
                }
                break;
            case R.id.ll_index_yzgs://身心咨询
                startActivity(new Intent(mContext, BodyHeartActivity.class));
                break;
            case R.id.ll_index_ygj://心理侧评
                if (GoodHealthApp.getInstance().isNeedLogin()) {
                    Intent intent1 = new Intent(mContext, LoginActivity.class);
                    intent1.putExtra("authId", "");
                    startActivityForResult(intent1, 1120);
                } else if (TextUtils.isEmpty(GoodHealthApp.getInstance().getUser().getCompanyName())) {
                    startActivityForResult(new Intent(mContext, CompanyActivity.class), 1121);
                } else {
//                    startActivity(new Intent(mContext, MyCompanyActivity.class));
                    ToastUtil.show(mContext, "暂不开放！");
                }
//                startActivity(new Intent(mContext, MyTraceActivity.class));
                break;
            case R.id.ll_index_xgt://健康资讯
                startActivity(new Intent(mContext, HealthNewsActivity.class));
                break;
            case R.id.iv_index_wxt_center://微生态中心
                startActivity(new Intent(mContext, MicCenterAcitivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1119 && resultCode == getActivity().RESULT_OK) {
            startActivity(new Intent(mContext, DiseaseCheckActivity.class));
        }
        if (requestCode == 1120 && resultCode == getActivity().RESULT_OK) {
            if (TextUtils.isEmpty(GoodHealthApp.getInstance().getUser().getCompanyName())) {
                startActivityForResult(new Intent(mContext, CompanyActivity.class), 1121);
            } else {
                startActivity(new Intent(mContext, MyCompanyActivity.class));
            }
        }

        if (requestCode == 1121 && resultCode == getActivity().RESULT_OK) {
            startActivity(new Intent(mContext, MyCompanyActivity.class));
        }

    }

    private void refreshData(ArrayList<IndexRecycleInfo> datas, boolean b) {
        int size = indexRvAdapter.getData().size();
        if (!b) {
            indexRvAdapter.notifyItemRangeRemoved(0, indexRvAdapter.getData().size());
        }
        indexRvAdapter.setData(datas);
        if (b) {
            rvIndex.getAdapter().notifyItemInserted(size);
        } else {
            rvIndex.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mqIndex.startWithList(notices, 0);
    }
}
