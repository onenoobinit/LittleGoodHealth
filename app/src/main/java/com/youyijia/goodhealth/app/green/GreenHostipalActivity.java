package com.youyijia.goodhealth.app.green;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.GreenHospitalInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GreenHostipalActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rv_hospital)
    RecyclerView rvHospital;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_have_data)
    RelativeLayout rlHaveData;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    private ArrayList<GreenHospitalInfo> hospitals = new ArrayList<>();
    private String serviceId;
    private GreenHospitalAdapter greenHospitalAdapter;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        serviceId = getIntent().getStringExtra("serviceId");
        refreshLayout.setOnRefreshListener(refreshLayout1 -> getdata());
        getdata();
    }

    private void getdata() {
        params.clear();
        params.put("cityServiceId", serviceId);
        RetrofitManager.getInstance().create(CommonService.class)
                .getHospital(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<GreenHospitalInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<GreenHospitalInfo>>() {
                            }.getType());

                            if (datas != null && datas.size() > 0) {
                                arlNoData.setVisibility(View.GONE);
                                rlHaveData.setVisibility(View.VISIBLE);
                                hospitals.clear();
                                hospitals.addAll(datas);
                                initData();
                            } else {
                                arlNoData.setVisibility(View.VISIBLE);
                                rlHaveData.setVisibility(View.GONE);
                            }
                        } else {
                            arlNoData.setVisibility(View.VISIBLE);
                            rlHaveData.setVisibility(View.GONE);
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
        LinearLayoutManager manager = new LinearLayoutManager(GreenHostipalActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHospital.setLayoutManager(manager);
        greenHospitalAdapter = new GreenHospitalAdapter(GreenHostipalActivity.this, hospitals) {
            @Override
            public void setonClick(int position) {
                GreenHospitalAdapter.selectPostion = position;
                Intent intent = new Intent(GreenHostipalActivity.this, GreenBookActivity.class);
                intent.putExtra("hospital", hospitals.get(position).getHospitalName());
                intent.putExtra("hosId", hospitals.get(position).getId());
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        rvHospital.setAdapter(greenHospitalAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_hostipal;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GreenHospitalAdapter.selectPostion = -1;
    }
}
