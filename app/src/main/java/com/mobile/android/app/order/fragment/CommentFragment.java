package com.mobile.android.app.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.mobile.android.R;
import com.mobile.android.app.order.adapter.WholeAdapter;
import com.mobile.hyoukalibrary.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class CommentFragment extends BaseFragment {
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
    private ArrayList<String> complains = new ArrayList<>();
    private int count_page;

    //定义当前的刷新的状态
    private LOADSTATE mCurrentState = LOADSTATE.IDLE;//第一次进来空闲状态
    private WholeAdapter wholeAapter;

    //上拉,下拉,空闲
    private enum LOADSTATE {
        LOAD, //加载中
        MORE, //上拉更多
        IDLE //空闲
    }

    public static CommentFragment newInstance() {
        CommentFragment fragment = new CommentFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void finishCreateView(Bundle state) {
        unbinder = ButterKnife.bind(this, parentView);
        getWhole();

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
        complains.add("dfk");
        complains.add("dfk");
        complains.add("dfk");
        complains.add("dfk");
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWhole.setLayoutManager(manager);
        wholeAapter = new WholeAdapter(mContext, 4, complains);
        rvWhole.setAdapter(wholeAapter);

    }

    private void getMoreWhole() {
        /*if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        if (page == count_page) {
            refreshLayout.finishLoadMore();
            ToastUtil.show(mContext, "没有更多的信息了！");
            return;
        }
        mCurrentState = LOADSTATE.MORE;
        params.clear();
        page += 1;
        params.put("token", SaleApp.getUser().getToken());
        params.put("member_id", SaleApp.getUser().getMember_id());
        params.put("version", ApplicationUtils.getVerCode(mContext));
        params.put("model", "1");
        params.put("type", "1");
        params.put("page", page);
        L.i("参数", params + "");
        RetrofitManager.getInstance().create(CommonService.class)
                .getcomplain(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<ComplainInfo>>io_main())
                .subscribe(new BaseObserver<ComplainInfo>() {

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                    }

                    @Override
                    protected void onHandleSuccess(ComplainInfo complainInfo) {
                        if (refreshLayout != null) {
                            refreshLayout.finishLoadMore();
                        }
                        mCurrentState = LOADSTATE.IDLE;
                        List<ComplainInfo.ListBean> list = complainInfo.getList();
                        if (list == null || list.size() == 0) {
                            initNodataView();
                        } else if (list != null && list.size() > 0) {
                            initHaveDataView();
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
                });*/
    }

    private void getWhole() {
        /*if (mCurrentState != LOADSTATE.IDLE) {
            return;
        }
        mCurrentState = LOADSTATE.LOAD;
        complains.clear();
        params.clear();
        page = 1;
        params.put("token", SaleApp.getUser().getToken());
        params.put("member_id", SaleApp.getUser().getMember_id());
        params.put("version", ApplicationUtils.getVerCode(mContext));
        params.put("model", "1");
        params.put("type", "1");
        params.put("page", page);
        L.i("参数", params + "");
        RetrofitManager.getInstance().create(CommonService.class)
                .getcomplain(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<ComplainInfo>>io_main())
                .subscribe(new BaseObserver<ComplainInfo>() {

                    @Override
                    protected void onHandleSuccess(ComplainInfo complainInfo) {
                        L.i("coutpage", complainInfo.toString());
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        ArrayList<ComplainInfo.ListBean> list = (ArrayList<ComplainInfo.ListBean>) complainInfo.getList();
                        count_page = complainInfo.getCount_page();
                        if (list == null || list.size() == 0) {
                            initNodataView();
                        } else if (list != null && list.size() > 0) {
                            initHaveDataView();
                            complainAdapter.notifyDataSetChanged();
                            complains = list;
                            refreshData(complains, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
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
                });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void refreshData(ArrayList<String> datas, boolean b) {
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
