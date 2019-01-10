package com.mobile.android.app.Memorandum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.entity.MemoRandomListBean;
import com.mobile.android.retrofit.ApiContstants;
import com.mobile.android.widgets.dialog.LoadingDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.NetworkUtils;
import com.mobile.hyoukalibrary.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author clz
 * @desc 备忘录列表页
 */
public class MemorandumActivity extends BaseActivity {


    @BindView(R.id.rcl_bw)
    RecyclerView mRclBw;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    private MemoRandumListAdapter mAdapter;
    private List<MemoRandomListBean> mData;
    private View mEmptyView;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initRecyclerView();
        initRefresh();
        getBwList(false);
    }

    /**
     * 获取备忘列表
     */
    private void getBwList(boolean isRefresh) {
        params.clear();
//        params.put(ApiContstants.UID, SupervisorApp.getUser().getUid());
        params.put(ApiContstants.TOKEN, SupervisorApp.getUser().getToken());
        if (!isRefresh) {
            if (null == mLoadingDialog) {
                mLoadingDialog = new LoadingDialog(this);
            }
            mLoadingDialog.show();
        }
        /*RetrofitManager.getInstance().create(CommonService.class)
                .getBwList(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.<BaseEntity<List<MemoRandomListBean>>>io_main())
                .subscribe(new BaseObserver<List<MemoRandomListBean>>() {
                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (isRefresh) {
                            mRefresh.setRefreshing(false);
                        } else {
                            mLoadingDialog.dismiss();
                        }
                    }

                    @Override
                    protected void onHandleSuccess(List<MemoRandomListBean> data) {
                        L.i("bwlist",data.size()+"");
                        if (data.size() <= 0) {
                            mData=data;
                            mAdapter.setNewData(mData);
                            mAdapter.setEmptyView(mEmptyView);
                        } else {
                            mData = data;
                            mAdapter.setNewData(mData);
                        }
                    }
                });*/
    }

    private void initRefresh() {
        mRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.c_85B175));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBwList(true);
            }
        });
    }

    private void initRecyclerView() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.list_no_data, null);
        TextView tvPromptMsg = mEmptyView.findViewById(R.id.tv_prompt_msg);
        tvPromptMsg.setText("没有备忘录~");
        mRclBw.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MemoRandumListAdapter(R.layout.item_memorandum, mData);
        mRclBw.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!NetworkUtils.isConnected(MemorandumActivity.this)) {
                    ToastUtil.show(MemorandumActivity.this, "网络连接不可用，请检查你的网络");
                } else {
                    final Intent intent = new Intent(MemorandumActivity.this, MemoRandumDetailActivity.class);
                    intent.putExtra("bw_id", mAdapter.getItem(position).getId());
                    startActivityForResult(intent,104);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.i("resultCode",resultCode+"");
        if (resultCode == 102) {
            getBwList(false);
            L.i("bwlist","刷新了");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_memorandum;
    }

    @Override
    public void initToolBar() {
        mTvTitle.setText("备忘记录");
    }


    @OnClick({R.id.iv_back, R.id.all_add_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.all_add_data:
                Intent intent = new Intent(this, AddEditMemoRandumActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent,103);
                break;
            default:
                break;
        }
    }
}
