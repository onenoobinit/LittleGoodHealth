package com.youyijia.goodhealth.app.order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.OrderBillTypeInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/29.
 */
public class OrderComeActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_item_order_date)
    TextView tvItemOrderDate;
    @BindView(R.id.tv_start_py)
    TextView tvStartPy;
    @BindView(R.id.tv_start_hy)
    TextView tvStartHy;
    @BindView(R.id.tv_end_py)
    TextView tvEndPy;
    @BindView(R.id.tv_end_hy)
    TextView tvEndHy;
    @BindView(R.id.tv_head_number)
    TextView tvHeadNumber;
    @BindView(R.id.tv_head_weight)
    TextView tvHeadWeight;
    @BindView(R.id.tv_head_vol)
    TextView tvHeadVol;
    @BindView(R.id.tv_head_portion)
    TextView tvHeadPortion;
    @BindView(R.id.all_set_line)
    AutoLinearLayout allSetLine;
    @BindView(R.id.tv_order_rksl)
    TextView tvOrderRksl;
    @BindView(R.id.tv_order_rkzl)
    TextView tvOrderRkzl;
    @BindView(R.id.tv_order_rktj)
    TextView tvOrderRktj;
    @BindView(R.id.tv_order_hwbz)
    TextView tvOrderHwbz;
    @BindView(R.id.tv_order_sure)
    TextView tvOrderSure;
    @BindView(R.id.rv_ruku)
    RecyclerView rvRuku;
    private String orderBillCode;
    private String TOKEN;
    private OrderBillTypeInfo orderBillTypeInfo;
    private String startpy;
    private String starthy;
    private String endhy;
    private String endpy;
    private String pieces;
    private String weight;
    private String vol;
    private String proportion;
    private String date;
    private RukuAdapter rukuAdapter;
    private List<OrderBillTypeInfo.IntoCabinDataListBean> intoCabinDataList;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        TOKEN = GoodHealthApp.getUser().getToken();
        orderBillCode = getIntent().getStringExtra("orderBillCode");

        startpy = getIntent().getStringExtra("startpy");
        starthy = getIntent().getStringExtra("starthy");
        endhy = getIntent().getStringExtra("endhy");
        endpy = getIntent().getStringExtra("endpy");
        pieces = getIntent().getStringExtra("pieces");
        weight = getIntent().getStringExtra("weight");
        vol = getIntent().getStringExtra("vol");
        proportion = getIntent().getStringExtra("proportion");
        date = getIntent().getStringExtra("date");

        if (endhy.contains(",")) {
            String[] split = endhy.split(",");
            tvEndHy.setText(split[0]);
        } else if (endhy.contains("，")) {
            String[] split = endhy.split("，");
            tvEndHy.setText(split[0]);
        } else {
            tvEndHy.setText(endhy);
        }
        tvStartPy.setText(startpy);
        tvStartHy.setText(starthy);
        tvEndPy.setText(endpy);
        tvHeadNumber.setText(pieces);
        tvHeadWeight.setText(weight);
        tvHeadVol.setText(vol);
        tvHeadPortion.setText(proportion);
        tvItemOrderDate.setText(date);
        getdata();
    }

    private void getdata() {
        params.clear();
        params.put("act", "getBillingDataConfirmData");
        params.put("orderBillCode", orderBillCode);
        RetrofitManager.getInstance().create(CommonService.class)
                .getOrderThree(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getData())) {
                            ToastUtil.show(OrderComeActivity.this, baseEntity.getData());
                            return;
                        } else {
                            orderBillTypeInfo = gson.fromJson(baseEntity.getSuccess(), OrderBillTypeInfo.class);
                            initdata();
                        }
                    }
                });
    }

    private void initdata() {
//        tvOrderNumber.setText(orderBillTypeInfo.getIntoCabinDataList().);
        /*intoCabinDataList = orderBillTypeInfo.getIntoCabinDataList();
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvRuku.setLayoutManager(manager2);
        rukuAdapter = new RukuAdapter(this,  intoCabinDataList);
        rvRuku.setAdapter(rukuAdapter);*/
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_come;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick(R.id.tv_order_sure)
    public void onClick() {

    }

}
