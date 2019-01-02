package com.mobile.android.app.missed_calls;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.MissedCallsBean;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.widgets.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author CLZ
 * @desc 未接来电页面
 */
public class MissedCallsActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rcl_missed_calls)
    RecyclerView mRclMissedCalls;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    private MissedCallPhonesAdapter mAdapter;
    private List<MissedCallsBean> mData = new ArrayList<>();
    private View mEmptyView;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initRecyclerView();
        initRefresh();
        getMissedCalls(false);
    }

    /**
     * 获取未接来电列表
     *
     * @param isRefresh
     */
    private void getMissedCalls(boolean isRefresh) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!isRefresh) {
            mLoadingDialog.show();
        }
        params.clear();
        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        RetrofitManager.getInstance().create(CommonService.class)
                .missedCalls(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List<MissedCallsBean>>>io_main())
                .subscribe(new BaseObserver<List<MissedCallsBean>>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                        if (isRefresh) {
                            mRefresh.setRefreshing(false);
                        }
                    }

                    @Override
                    protected void onHandleSuccess(List<MissedCallsBean> data) {
                        L.i("missedCalls", data.toString());
                        if (isRefresh) {
                            mData.clear();
                        }
                        if (data.size() > 0) {
                            mData.addAll(data);
                            mAdapter.setNewData(mData);
                        } else {
                            mAdapter.setEmptyView(mEmptyView);
                        }
                    }
                });
    }

    private void initRefresh() {
        mRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.c_85B175));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMissedCalls(true);
            }
        });
    }

    private void initRecyclerView() {
        mRclMissedCalls.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MissedCallPhonesAdapter(R.layout.item_missed_calls, mData);
        mRclMissedCalls.setAdapter(mAdapter);
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.list_no_data, null);
        TextView tvMsg = mEmptyView.findViewById(R.id.tv_prompt_msg);
        tvMsg.setText("没有未接来电~");
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final MissedCallsBean item = mAdapter.getItem(position);
                contactOwner(item);
            }
        });
    }
    /**
     * 未接来电去电
     *
     * @param bean
     */
    private void contactOwner(MissedCallsBean bean) {
        params.clear();
        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        params.put("missed_id", bean.getId());
        params.put("type", bean.getType());
        if (null == mLoadingDialog) {
            mLoadingDialog = new LoadingDialog(this);
        }
        mLoadingDialog.show();
        RetrofitManager.getInstance().create(CommonService.class)
                .callOutPhone(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List>>io_main())
                .subscribe(new BaseObserver<List>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        mLoadingDialog.dismiss();
                    }

                    @Override
                    protected void onHandleSuccess(List data) {
                        if (data != null) {
                            L.i("contact_owner", data.toString());
                            showPromptDialog();
                        }
                    }
                });
    }

    private void showPromptDialog() {
        new AlertDialog.Builder(this)
                .setMessage("稍后会为你接通业主的电话，请耐心等待")
                .setPositiveButton("我知道了", null).show();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_missed_calls;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("未接来电");
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

}
