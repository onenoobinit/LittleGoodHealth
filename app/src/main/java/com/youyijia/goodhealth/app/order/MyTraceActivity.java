package com.youyijia.goodhealth.app.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.MyTracesInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class MyTraceActivity extends BaseActivity {

    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rv_trace)
    RecyclerView rvTrace;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_have_data)
    LinearLayout rlHaveData;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    @BindView(R.id.tv_trace_type)
    TextView tvTraceType;
    @BindView(R.id.tv_trace_number)
    TextView tvTraceNumber;
    private String orderId;
    private MyTraceAdapter myTraceAdapter;
    private ArrayList<MyTracesInfo.TracesBean> mytraces = new ArrayList<>();
    private MyDialog myDialog;
    private MyTracesInfo myTracesInfo;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        logiToolBar.setNavigationOnClickListener(v -> finish());
        orderId = getIntent().getStringExtra("orderId");
        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout.autoRefresh();
            getTrace(orderId);
        });


        LinearLayoutManager manager = new LinearLayoutManager(MyTraceActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTrace.setLayoutManager(manager);
        myTraceAdapter = new MyTraceAdapter(MyTraceActivity.this, mytraces);
        rvTrace.setAdapter(myTraceAdapter);
        getTrace(orderId);
    }

    /*查询物流信息*/
    private void getTrace(String orderId) {
        if (myDialog == null) {
            myDialog = new MyDialog(MyTraceActivity.this);
        }
        myDialog.showDialog();
        params.clear();
        params.put("orderId", orderId);
        RetrofitManager.getInstance().create(CommonService.class)
                .myTrace(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            myTracesInfo = gson.fromJson(baseEntity.getData(), MyTracesInfo.class);
                            initdata();
                        } else {
                            ToastUtil.show(MyTraceActivity.this, baseEntity.getMessage());
                            rlHaveData.setVisibility(View.GONE);
                            arlNoData.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    protected void onFinally() {
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                        super.onFinally();
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
    }

    private void initdata() {
        tvTraceType.setText("配送方式：" + myTracesInfo.getShipperName());
        tvTraceNumber.setText("运单号：" + myTracesInfo.getLogisticCode());
        ArrayList<MyTracesInfo.TracesBean> traces = (ArrayList<MyTracesInfo.TracesBean>) myTracesInfo.getTraces();
        mytraces.addAll(traces);
        refreshData(traces, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_trace;
    }

    @Override
    public void initToolBar() {

    }

    private void refreshData(ArrayList<MyTracesInfo.TracesBean> datas, boolean b) {
        int size = myTraceAdapter.getData().size();
        if (!b) {
            myTraceAdapter.notifyItemRangeRemoved(0, myTraceAdapter.getData().size());
        }
        myTraceAdapter.setData(datas);
        if (b) {
            rvTrace.getAdapter().notifyItemInserted(size);
        } else {
            rvTrace.getAdapter().notifyDataSetChanged();
        }
    }

}
