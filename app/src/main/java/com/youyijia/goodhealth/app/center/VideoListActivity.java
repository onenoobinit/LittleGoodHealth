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
import com.youyijia.goodhealth.entity.AllVideoInfo;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rv_video)
    RecyclerView rvVideo;
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
    private AllVideoAdapter allVideoAdapter;
    private ArrayList<AllVideoInfo> allDatas = new ArrayList<>();
    private JSONObject jsonObject1;

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


        LinearLayoutManager manager = new LinearLayoutManager(VideoListActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvVideo.setLayoutManager(manager);
        allVideoAdapter = new AllVideoAdapter(VideoListActivity.this, allDatas);
        rvVideo.setAdapter(allVideoAdapter);
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            getdata();
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            refreshLayout.autoLoadMore();
            getMoreData();
        });

        rvVideo.setOnTouchListener(new View.OnTouchListener() {
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
            ToastUtil.show(VideoListActivity.this, "没有更多的信息了！");
            return;
        }
        mCurrentState = LOADSTATE.MORE;
        params.clear();
        pageNum += 1;
        params.put("pageNum", pageNum);

        RetrofitManager.getInstance().create(CommonService.class)
                .getAllVideo(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
                            return;
                        } else {
                            List<AllVideoInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<AllVideoInfo>>() {
                            }.getType());
                            allDatas.addAll(datas);
                            refreshData(allDatas, true);
                            allVideoAdapter.notifyDataSetChanged();
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
        pageNum = 1;
        params.put("pageNum", pageNum);

        RetrofitManager.getInstance().create(CommonService.class)
                .getAllVideo(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
                            return;
                        } else {
                            ArrayList<AllVideoInfo> datas = (ArrayList<AllVideoInfo>) gson.fromJson(baseEntity.getData(), new TypeToken<List<AllVideoInfo>>() {
                            }.getType());
                            PageInfo pageInfo = gson.fromJson(baseEntity.getPageInfo(), PageInfo.class);
                            totalPage = pageInfo.getTotalPage();
                            allDatas.clear();
                            allDatas = datas;
                            refreshData(allDatas, false);
                            allVideoAdapter.notifyDataSetChanged();
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
        return R.layout.activity_video_list;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    private void refreshData(ArrayList<AllVideoInfo> datas, boolean b) {
        int size = allVideoAdapter.getData().size();
        if (!b) {
            allVideoAdapter.notifyItemRangeRemoved(0, allVideoAdapter.getData().size());
        }
        allVideoAdapter.setData(datas);
        if (b) {
            rvVideo.getAdapter().notifyItemInserted(size);
        } else {
            rvVideo.getAdapter().notifyDataSetChanged();
        }
    }
}
