package com.mobile.android.app.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.app.order.adapter.WholeAdapter;
import com.mobile.android.entity.OrderInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.widgets.dialog.MyDialog;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.base.LazyFragment;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class WholeFragment extends LazyFragment {

    @BindView(R.id.rv_whole)
    RecyclerView rvWhole;
    @BindView(R.id.arl_have_data)
    AutoRelativeLayout arlHaveData;
    @BindView(R.id.arl_no_data)
    AutoRelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private int page = 1;
    private ArrayList<OrderInfo.OrderBillListInfoBean> complains = new ArrayList<>();
    private int count_page;
    private String TOKEN = "";

    //定义当前的刷新的状态
    private LOADSTATE mCurrentState = LOADSTATE.IDLE;//第一次进来空闲状态
    private WholeAdapter wholeAapter;
    private boolean isPrepared;
    private MyDialog myDialog;

    //上拉,下拉,空闲
    private enum LOADSTATE {
        LOAD, //加载中
        MORE, //上拉更多
        IDLE //空闲
    }

    public static WholeFragment newInstance() {
        WholeFragment fragment = new WholeFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_whole;
    }

    @Override
    public void finishCreateView(Bundle state) {
        TOKEN = SupervisorApp.getUser().getToken();
        unbinder = ButterKnife.bind(this, parentView);
//        getWhole();
        isPrepared = true;
        lazyLoad();
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout.autoRefresh();
            getWhole();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout1 -> {
            refreshLayout.autoLoadMore();
            getMoreWhole();
        });

        rvWhole.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mCurrentState == LOADSTATE.LOAD) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWhole.setLayoutManager(manager);
        wholeAapter = new WholeAdapter(mContext, 0, complains);
        rvWhole.setAdapter(wholeAapter);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        getWhole();
    }

    private void getMoreWhole() {
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        if (page == count_page || count_page == 0) {
            refreshLayout.finishLoadMore();
            ToastUtil.show(mContext, "没有更多的信息了！");
            return;
        }
        mCurrentState = LOADSTATE.MORE;
        params.clear();
        page += 1;
        params.put("act", "getExportOrderBillList");
        params.put("orderType", "0");
        params.put("limitPage", "10");
        params.put("pageNumber", page);
        L.i("参数", params + "");
        RetrofitManager.getInstance().create(CommonService.class)
                .getOrder(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                    }

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(mContext, baseEntity.getErrMsg());
                            return;
                        } else if (TextUtils.isEmpty(baseEntity.getErrMsg()) && baseEntity.getSuccess() != null) {
                            mCurrentState = LOADSTATE.IDLE;
                            OrderInfo orderInfo = gson.fromJson(baseEntity.getSuccess(), OrderInfo.class);
                            ArrayList<OrderInfo.OrderBillListInfoBean> list = (ArrayList<OrderInfo.OrderBillListInfoBean>) orderInfo.getOrderBillListInfo();
                            complains.addAll(list);
                            refreshData(complains, true);
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

    private void getWhole() {
        if (myDialog == null) {
            myDialog = new MyDialog(mContext);
        }
        myDialog.showDialog();
        if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        mCurrentState = LOADSTATE.LOAD;
        complains.clear();
        params.clear();
        page = 1;
        params.put("act", "getExportOrderBillList");
        params.put("orderType", "0");
        params.put("limitPage", "10");
        params.put("pageNumber", page);
        L.i("参数", params + "");
        RetrofitManager.getInstance().create(CommonService.class)
                .getOrder(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                    }

                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(mContext, baseEntity.getErrMsg());
                            return;
                        } else if (TextUtils.isEmpty(baseEntity.getErrMsg()) && baseEntity.getSuccess() != null) {
                            OrderInfo orderInfo = gson.fromJson(baseEntity.getSuccess(), OrderInfo.class);
                            ArrayList<OrderInfo.OrderBillListInfoBean> list = (ArrayList<OrderInfo.OrderBillListInfoBean>) orderInfo.getOrderBillListInfo();
                            count_page = Integer.parseInt(orderInfo.getAllCount()) / 10;
                            if (list != null && list.size() > 0) {
                                arlHaveData.setVisibility(View.VISIBLE);
                                arlNoData.setVisibility(View.GONE);
                                complains = list;
                                refreshData(complains, false);
                                wholeAapter.notifyDataSetChanged();
                            } else {
                                arlHaveData.setVisibility(View.GONE);
                                arlNoData.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                        mCurrentState = LOADSTATE.IDLE;
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (myDialog != null) {
            myDialog.dismissDialog();
        }
    }

    private void refreshData(ArrayList<OrderInfo.OrderBillListInfoBean> datas, boolean b) {
        int size = wholeAapter.getData().size();
        if (!b) {
            wholeAapter.notifyItemRangeRemoved(0, wholeAapter.getData().size());
        }
        wholeAapter.setData(datas);
        if (b) {
            rvWhole.getAdapter().notifyItemInserted(size);
        } else {
            rvWhole.getAdapter().notifyDataSetChanged();
        }
    }
}
