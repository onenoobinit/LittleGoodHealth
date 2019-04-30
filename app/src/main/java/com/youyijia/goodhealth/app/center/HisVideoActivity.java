package com.youyijia.goodhealth.app.center;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.HisVideoInfo;
import com.youyijia.goodhealth.entity.PageInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HisVideoActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rv_his_video)
    RecyclerView rvHisVideo;
    @BindView(R.id.rl_have_data)
    RelativeLayout rlHaveData;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    //定义当前的刷新的状态
    private LOADSTATE mCurrentState = LOADSTATE.IDLE;//第一次进来空闲状态
    private int pageNum;
    private int totalPage;
    private ArrayList<HisVideoInfo> hisDatas = new ArrayList<>();
    private OtherVideoAdapter otherVideoAdapter;
    private String doctorId;
    private String id;

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

        doctorId = getIntent().getStringExtra("doctorId");
        id = getIntent().getStringExtra("id");
        LinearLayoutManager manager = new LinearLayoutManager(HisVideoActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHisVideo.setLayoutManager(manager);
        otherVideoAdapter = new OtherVideoAdapter(HisVideoActivity.this, hisDatas);
        rvHisVideo.setAdapter(otherVideoAdapter);
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            getdata();
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            refreshLayout.autoLoadMore();
            getMoreData();
        });

        rvHisVideo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mCurrentState == LOADSTATE.LOAD) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        getdata();
    }

    private void getMoreData() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        if (pageNum == totalPage || totalPage == 0) {
            refreshLayout.finishLoadMore();
            ToastUtil.show(HisVideoActivity.this, "没有更多的信息了！");
            return;
        }
        mCurrentState = LOADSTATE.MORE;
        params.clear();
        params.put("doctorId", doctorId);
        params.put("id", id);
        pageNum += 1;
        params.put("pageNum", pageNum);
        RetrofitManager.getInstance().create(CommonService.class)
                .getOtherVideo(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
                            return;
                        } else {
                            List<HisVideoInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<HisVideoInfo>>() {
                            }.getType());
                            hisDatas.addAll(datas);
                            refreshData(hisDatas, true);
                            otherVideoAdapter.notifyDataSetChanged();
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

    private void getdata() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        mCurrentState = LOADSTATE.LOAD;
        params.clear();
        params.put("doctorId", doctorId);
        params.put("id", id);
        pageNum = 1;
        params.put("pageNum", pageNum);
        RetrofitManager.getInstance().create(CommonService.class)
                .getOtherVideo(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
                            return;
                        } else {
                            ArrayList<HisVideoInfo> datas = (ArrayList<HisVideoInfo>) gson.fromJson(baseEntity.getData(), new TypeToken<List<HisVideoInfo>>() {
                            }.getType());
                            PageInfo pageInfo = gson.fromJson(baseEntity.getPageInfo(), PageInfo.class);
                            totalPage = pageInfo.getTotalPage();
                            System.out.println("AAAA" + totalPage);
                            hisDatas.clear();
                            hisDatas = datas;
                            refreshData(hisDatas, false);
                            otherVideoAdapter.notifyDataSetChanged();
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_his_video;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    private void refreshData(ArrayList<HisVideoInfo> datas, boolean b) {
        int size = otherVideoAdapter.getData().size();
        if (!b) {
            otherVideoAdapter.notifyItemRangeRemoved(0, otherVideoAdapter.getData().size());
        }
        otherVideoAdapter.setData(datas);
        if (b) {
            rvHisVideo.getAdapter().notifyItemInserted(size);
        } else {
            rvHisVideo.getAdapter().notifyDataSetChanged();
        }
    }

}
