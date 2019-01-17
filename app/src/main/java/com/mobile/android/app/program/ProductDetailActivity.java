package com.mobile.android.app.program;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.SupervisorApp;
import com.mobile.android.app.program.adapter.ProDetailAdpater;
import com.mobile.android.entity.ProductDetailInfo;
import com.mobile.android.retrofit.RetrofitManager;
import com.mobile.android.retrofit.RetryWhenNetworkException;
import com.mobile.android.retrofit.RxSchedulers;
import com.mobile.android.retrofit.api.CommonService;
import com.mobile.android.widgets.dialog.MyDialog;
import com.mobile.hyoukalibrary.base.BaseActivity;
import com.mobile.hyoukalibrary.base.BaseEntity;
import com.mobile.hyoukalibrary.base.BaseObserver;
import com.mobile.hyoukalibrary.utils.L;
import com.mobile.hyoukalibrary.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class ProductDetailActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_fly_type)
    TextView tvFlyType;
    @BindView(R.id.tv_detail_vol)
    TextView tvDetailVol;
    @BindView(R.id.tv_deatail_weight)
    TextView tvDeatailWeight;
    @BindView(R.id.tv_weight_min)
    TextView tvWeightMin;
    @BindView(R.id.tv_bao_zhuang)
    TextView tvBaoZhuang;
    @BindView(R.id.tv_li_dian)
    TextView tvLiDian;
    @BindView(R.id.tv_bao_xian)
    TextView tvBaoXian;
    @BindView(R.id.tv_bei_zhu)
    TextView tvBeiZhu;
    @BindView(R.id.tv_sec1)
    TextView tvSec1;
    @BindView(R.id.rv_item1)
    RecyclerView rvItem1;
    @BindView(R.id.tv_sec2)
    TextView tvSec2;
    @BindView(R.id.rv_item2)
    RecyclerView rvItem2;
    @BindView(R.id.tv_sec3)
    TextView tvSec3;
    @BindView(R.id.rv_item3)
    RecyclerView rvItem3;
    @BindView(R.id.tv_sec4)
    TextView tvSec4;
    @BindView(R.id.rv_item4)
    RecyclerView rvItem4;
    @BindView(R.id.tv_tou_cheng)
    TextView tvTouCheng;
    @BindView(R.id.tv_fly_number)
    TextView tvFlyNumber;
    @BindView(R.id.tv_yun_gong)
    TextView tvYunGong;
    @BindView(R.id.tv_fly_time)
    TextView tvFlyTime;
    @BindView(R.id.tv_arrive_time)
    TextView tvArriveTime;
    @BindView(R.id.tv_hang_ban)
    TextView tvHangBan;
    @BindView(R.id.tv_ji_xing)
    TextView tvJiXing;
    @BindView(R.id.tv_ban_hao)
    TextView tvBanHao;
    @BindView(R.id.tv_gong_ju)
    TextView tvGongJu;
    @BindView(R.id.tv_yun_time)
    TextView tvYunTime;
    @BindView(R.id.tv_dao_time)
    TextView tvDaoTime;
    @BindView(R.id.tv_ban_ci)
    TextView tvBanCi;
    @BindView(R.id.tv_er_ji)
    TextView tvErJi;
    @BindView(R.id.arl_have_data)
    AutoLinearLayout arlHaveData;
    @BindView(R.id.arl_no_data)
    AutoRelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String TOKEN;
    private ProductDetailInfo productDetailInfo;
    private ProDetailAdpater pro1;
    private ProDetailAdpater pro2;
    private ProDetailAdpater pro3;
    private ProDetailAdpater pro4;
    private List<String> items1 = new ArrayList<>();
    private List<String> items2 = new ArrayList<>();
    private List<String> items3 = new ArrayList<>();
    private List<String> items4 = new ArrayList<>();
    private MyDialog myDialog;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        TOKEN = SupervisorApp.getUser().getToken();
        getDetail();

        refreshLayout.setOnRefreshListener(refreshLayout1 -> {
            refreshLayout.autoRefresh();
            getDetail();
        });
    }

    private void getDetail() {
        if (myDialog == null) {
            myDialog = new MyDialog(this);
        }
        myDialog.showDialog();
        params.clear();
        params.put("act", "getProductsDetailData");
        params.put("productNo", "4801");
//        params.put("startPort", "");
        params.put("productDate", "2018-01-19");
        params.put("destination", "AMS");
        params.put("goodNumber", "");
        params.put("goodWeight", "");
        params.put("goodVolume", "");
        params.put("bookingPosition", "0");
        params.put("packageWay", "0");
        params.put("proportion", "");


        L.i("详情参数", params.toString());
        RetrofitManager.getInstance().create(CommonService.class)
                .getProdetail(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                        myDialog.dismissDialog();
                        if (!TextUtils.isEmpty(baseEntity.getErrMsg())) {
                            ToastUtil.show(ProductDetailActivity.this, baseEntity.getErrMsg());
                            return;
                        } else {
                            productDetailInfo = gson.fromJson(baseEntity.getSuccess(), ProductDetailInfo.class);
                            initData();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }

                        if (refreshLayout != null) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
    }

    private void initData() {
        ProductDetailInfo.ProductAttachInfoBean.GeneralCargoPolicyBean generalCargoPolicy = productDetailInfo.getProductAttachInfo().getGeneralCargoPolicy();
        List<ProductDetailInfo.ProductAttachInfoBean.DeliverySituationBean> deliverySituation = productDetailInfo.getProductAttachInfo().getDeliverySituation();
        ProductDetailInfo.ProductAttachInfoBean.FlightInfoBean flightInfo = productDetailInfo.getProductAttachInfo().getFlightInfo();

        //普通装载要求
        tvFlyType.setText(generalCargoPolicy.getDirectTransfer());
        tvDetailVol.setText(generalCargoPolicy.getSizeLimit());
        tvDeatailWeight.setText(generalCargoPolicy.getWeightLimit());
        tvWeightMin.setText(generalCargoPolicy.getMinWeight());
        tvBaoZhuang.setText(generalCargoPolicy.getChemicalsPackageClaim());
        tvLiDian.setText(generalCargoPolicy.getLithiumBatteryModel());
        tvBaoXian.setText(generalCargoPolicy.getPackageLimit());
        tvBeiZhu.setText(generalCargoPolicy.getTips());

        if (deliverySituation != null && deliverySituation.size() > 0) {
            items1.clear();
            items2.clear();
            items3.clear();
            items4.clear();
            for (int i = 0; i < deliverySituation.size(); i++) {
                items1.add(deliverySituation.get(i).getFlightNumber());
                items2.add(deliverySituation.get(i).getDeliveryCity());
                items3.add(deliverySituation.get(i).getDeliveryTime());
                items4.add(deliverySituation.get(i).getIntoCabinTime());
            }
            //交货情况
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            rvItem1.setLayoutManager(manager);
            pro1 = new ProDetailAdpater(this, 0, items1);
            rvItem1.setAdapter(pro1);

            LinearLayoutManager manager1 = new LinearLayoutManager(this);
            manager1.setOrientation(LinearLayoutManager.VERTICAL);
            rvItem2.setLayoutManager(manager1);
            pro2 = new ProDetailAdpater(this, 0, items2);
            rvItem2.setAdapter(pro2);

            LinearLayoutManager manager2 = new LinearLayoutManager(this);
            manager2.setOrientation(LinearLayoutManager.VERTICAL);
            rvItem3.setLayoutManager(manager2);
            pro3 = new ProDetailAdpater(this, 0, items3);
            rvItem3.setAdapter(pro3);

            LinearLayoutManager manager3 = new LinearLayoutManager(this);
            manager3.setOrientation(LinearLayoutManager.VERTICAL);
            rvItem4.setLayoutManager(manager3);
            pro4 = new ProDetailAdpater(this, 0, items4);
            rvItem4.setAdapter(pro4);
        } else {
            rvItem1.setVisibility(View.GONE);
            rvItem2.setVisibility(View.GONE);
            rvItem3.setVisibility(View.GONE);
            rvItem4.setVisibility(View.GONE);
            tvSec1.setVisibility(View.VISIBLE);
            tvSec2.setVisibility(View.VISIBLE);
            tvSec3.setVisibility(View.VISIBLE);
            tvSec4.setVisibility(View.VISIBLE);
        }

        //航班
        tvTouCheng.setText("头程情况" + flightInfo.getTheFirstFlight().getRoute());

        tvFlyNumber.setText(flightInfo.getTheFirstFlight().getDetailData().get(0).getFlightNumber());
        tvYunGong.setText(flightInfo.getTheFirstFlight().getDetailData().get(0).getTransportation());
        tvFlyTime.setText(flightInfo.getTheFirstFlight().getDetailData().get(0).getStartDate());
        tvArriveTime.setText(flightInfo.getTheFirstFlight().getDetailData().get(0).getArrivalDate());
        tvHangBan.setText(flightInfo.getTheFirstFlight().getDetailData().get(0).getFlightShift());
        tvJiXing.setText(flightInfo.getTheFirstFlight().getDetailData().get(0).getAircraftType());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

}
