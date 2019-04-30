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
import com.youyijia.goodhealth.entity.GreenCityInfo;
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

public class GreenCityActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rv_city)
    RecyclerView rvCity;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rl_have_data)
    RelativeLayout rlHaveData;
    @BindView(R.id.iv_no_data)
    ImageView ivNoData;
    @BindView(R.id.arl_no_data)
    RelativeLayout arlNoData;
    private CityAdapter cityAdapter;
    private ArrayList<GreenCityInfo> citys = new ArrayList<>();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        refreshLayout.setOnRefreshListener(refreshLayout1 -> getdata());
        getdata();
    }

    private void getdata() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getCity()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<GreenCityInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<GreenCityInfo>>() {
                            }.getType());
                            if (datas != null && datas.size() > 0) {
                                arlNoData.setVisibility(View.GONE);
                                rlHaveData.setVisibility(View.VISIBLE);
                                citys.clear();
                                citys.addAll(datas);
                                initData();
                            } else {
                                arlNoData.setVisibility(View.VISIBLE);
                                rlHaveData.setVisibility(View.GONE);
                            }
                        } else {
                            ToastUtil.show(GreenCityActivity.this, baseEntity.getMessage());
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
        LinearLayoutManager manager = new LinearLayoutManager(GreenCityActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCity.setLayoutManager(manager);
        cityAdapter = new CityAdapter(GreenCityActivity.this, citys) {
            @Override
            public void setonClick(int position, String city, String id) {
                CityAdapter.selectPostion = position;
                Intent intent = new Intent(GreenCityActivity.this, GreenBookActivity.class);
                intent.putExtra("city", city);
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
        rvCity.setAdapter(cityAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_city;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CityAdapter.selectPostion = -1;
    }
}
