package com.youyijia.goodhealth.app.green;

import android.content.Intent;
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
import com.youyijia.goodhealth.entity.GreenServiceInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GreenSeviceActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_title_item)
    TextView tvTitleItem;
    @BindView(R.id.tv_item_content)
    TextView tvItemContent;
    @BindView(R.id.rv_service)
    RecyclerView rvService;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_have_data)
    LinearLayout llHaveData;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    private String id;
    private GreenServiceAdapter greenServiceAdapter;
    private ArrayList<GreenServiceInfo.CityServicesBean> services = new ArrayList<>();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        refreshLayout.setOnRefreshListener(refreshLayout1 -> getdata());
        getdata();
    }

    private void getdata() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getService(id)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            GreenServiceInfo greenServiceInfo = gson.fromJson(baseEntity.getData(), GreenServiceInfo.class);
                            List<GreenServiceInfo.CityServicesBean> cityServices = greenServiceInfo.getCityServices();
                            if (cityServices != null && cityServices.size() > 0) {
                                llHaveData.setVisibility(View.VISIBLE);
                                arlNoData.setVisibility(View.GONE);
                                services.clear();
                                services.addAll(cityServices);
                                initData();
                            } else {
                                llHaveData.setVisibility(View.GONE);
                                arlNoData.setVisibility(View.VISIBLE);
                            }
                        } else {
                            ToastUtil.show(GreenSeviceActivity.this, baseEntity.getMessage());
                            llHaveData.setVisibility(View.GONE);
                            arlNoData.setVisibility(View.VISIBLE);
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
        LinearLayoutManager manager = new LinearLayoutManager(GreenSeviceActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvService.setLayoutManager(manager);
        greenServiceAdapter = new GreenServiceAdapter(GreenSeviceActivity.this, services) {
            @Override
            public void setonClick(int position) {
                GreenServiceAdapter.selectPostion = position;
                Intent intent = new Intent(GreenSeviceActivity.this, GreenBookActivity.class);
                intent.putExtra("service", services.get(position).getServiceTypeName());
                intent.putExtra("serviceId", services.get(position).getId());
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        rvService.setAdapter(greenServiceAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_sevice;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GreenServiceAdapter.selectPostion = -1;
    }
}
