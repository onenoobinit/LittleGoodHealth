package com.youyijia.goodhealth.app.program;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.program.adapter.FlyHeadAdapter;
import com.youyijia.goodhealth.app.program.adapter.FlySecondAdapter;
import com.youyijia.goodhealth.app.program.adapter.ProDetailAdpater;
import com.youyijia.goodhealth.entity.ProductDetailInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
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
    @BindView(R.id.arl_have_data)
    AutoLinearLayout arlHaveData;
    @BindView(R.id.arl_no_data)
    AutoRelativeLayout arlNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rv_head)
    RecyclerView rvHead;
    @BindView(R.id.all_second)
    AutoLinearLayout allSecond;
    @BindView(R.id.tv_second_py)
    TextView tvSecondPy;
    @BindView(R.id.rv_second)
    RecyclerView rvSecond;
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
    private List<ProductDetailInfo.ProductAttachInfoBean.FlightInfoBean.TheFirstFlightBean.DetailDataBean> detailData = new ArrayList<>();
    private FlyHeadAdapter flyHeadAdapter;
    private List<ProductDetailInfo.ProductAttachInfoBean.FlightInfoBean.TheSecondaryFlightBean.DetailDataBeanX> detailData1 = new ArrayList<>();
    private String productNo;
    private String productDate;
    private String destination;
    private String goodNumber;
    private String goodWeight;
    private String goodVolume;
    private String bookingPosition;
    private String packageWay;
    private String proportion;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        TOKEN = GoodHealthApp.getUser().getToken();

        productNo = getIntent().getStringExtra("productNo");
        productDate = getIntent().getStringExtra("productDate");
        destination = getIntent().getStringExtra("destination");
        goodNumber = getIntent().getStringExtra("goodNumber");
        goodWeight = getIntent().getStringExtra("goodWeight");
        goodVolume = getIntent().getStringExtra("goodVolume");
        bookingPosition = getIntent().getStringExtra("bookingPosition");
        packageWay = getIntent().getStringExtra("packageWay");
        proportion = getIntent().getStringExtra("proportion");
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
        params.put("productNo", productNo);
//        params.put("startPort", "");
        params.put("productDate", productDate);
        params.put("destination", destination);
        params.put("goodNumber", goodNumber);
        params.put("goodWeight", goodWeight);
        params.put("goodVolume", goodVolume);
        params.put("bookingPosition", bookingPosition);
        params.put("packageWay", packageWay);
        params.put("proportion", proportion);


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
                        if (!TextUtils.isEmpty(baseEntity.getData())) {
                            ToastUtil.show(ProductDetailActivity.this, baseEntity.getData());
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
        detailData = flightInfo.getTheFirstFlight().getDetailData();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHead.setLayoutManager(manager);
        flyHeadAdapter = new FlyHeadAdapter(this, this.detailData);
        rvHead.setAdapter(flyHeadAdapter);

        if (flightInfo.getTheSecondaryFlight() != null) {
            detailData1 = flightInfo.getTheSecondaryFlight().getDetailData();

            if (detailData1 != null && detailData1.size() > 0) {
                allSecond.setVisibility(View.VISIBLE);
                tvSecondPy.setText(flightInfo.getTheSecondaryFlight().getRoute());

                LinearLayoutManager managersecond = new LinearLayoutManager(this);
                managersecond.setOrientation(LinearLayoutManager.HORIZONTAL);
                rvSecond.setLayoutManager(managersecond);
                FlySecondAdapter flySecondAdapter = new FlySecondAdapter(this, this.detailData1);
                rvSecond.setAdapter(flySecondAdapter);
            }
        } else {
            allSecond.setVisibility(View.GONE);
        }

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
