package com.youyijia.goodhealth.app.program;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.login.LoginActivity;
import com.youyijia.goodhealth.app.program.adapter.DetailDateAdapter;
import com.youyijia.goodhealth.app.program.adapter.EndSeclectAdapter;
import com.youyijia.goodhealth.app.program.adapter.GridEndAdapter;
import com.youyijia.goodhealth.app.program.adapter.ProportionAdapter;
import com.youyijia.goodhealth.app.program.adapter.WeightAdapter;
import com.youyijia.goodhealth.app.submit.SubmitActivity;
import com.youyijia.goodhealth.entity.ProDateInfo;
import com.youyijia.goodhealth.entity.ProductDetailInfo;
import com.youyijia.goodhealth.entity.StartInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.IndexDialog;
import com.youyijia.goodhealth.widgets.dialog.MyDialog;
import com.youyijia.goodhealth.widgets.dialog.ProgramDetailDialog;
import com.youyijia.goodhealth.widgets.dialog.ProgramDetailNexDialog;
import com.youyijia.goodhealth.widgets.dialog.TargetDialog;
import com.youyijia.goodhealth.widgets.pop.AddPopWindow;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.SPUtil;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class ProgramDetailActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_start_py)
    TextView tvStartPy;
    @BindView(R.id.tv_start_hy)
    TextView tvStartHy;
    @BindView(R.id.tv_end_py)
    TextView tvEndPy;
    @BindView(R.id.tv_end_hy)
    TextView tvEndHy;
    @BindView(R.id.all_set_line)
    AutoLinearLayout allSetLine;
    @BindView(R.id.tv_just)
    TextView tvJust;
    @BindView(R.id.tv_prepare)
    TextView tvPrepare;
    @BindView(R.id.tv_tray)
    TextView tvTray;
    @BindView(R.id.tv_bulk)
    TextView tvBulk;
    @BindView(R.id.all_program_select)
    AutoLinearLayout allProgramSelect;
    @BindView(R.id.all_program_dialog)
    AutoLinearLayout allProgramDialog;
    @BindView(R.id.all_left)
    AutoLinearLayout allLeft;
    @BindView(R.id.tv_start_end)
    TextView tvStartEnd;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    @BindView(R.id.tv_detail_start)
    TextView tvDetailStart;
    @BindView(R.id.tv_detail_end)
    TextView tvDetailEnd;
    @BindView(R.id.rv_weight)
    RecyclerView rvWeight;
    @BindView(R.id.rv_proportion)
    RecyclerView rvProportion;
    @BindView(R.id.tv_yugu)
    TextView tvYugu;
    @BindView(R.id.tv_detail_money)
    TextView tvDetailMoney;
    @BindView(R.id.tv_detail_next)
    TextView tvDetailNext;
    @BindView(R.id.tv_head_number)
    TextView tvHeadNumber;
    @BindView(R.id.tv_head_weight)
    TextView tvHeadWeight;
    @BindView(R.id.tv_head_vol)
    TextView tvHeadVol;
    @BindView(R.id.tv_head_portion)
    TextView tvHeadPortion;
    @BindView(R.id.iv_pro_company)
    ImageView ivProCompany;
    @BindView(R.id.tv_pro_company)
    TextView tvProCompany;
    @BindView(R.id.tv_item_order_date)
    TextView tvItemOrderDate;
    @BindView(R.id.tv_detail_zy)
    TextView tvDetailZy;
    @BindView(R.id.tv_detail_zd)
    TextView tvDetailZd;
    @BindView(R.id.tv_detail_bc)
    TextView tvDetailBc;
    @BindView(R.id.all_detail_trans)
    AutoLinearLayout allDetailTrans;
    private ArrayList<String> dates = new ArrayList();
    private DetailDateAdapter dateAdapter;
    private WeightAdapter weightAdapter;
    private ProportionAdapter proportionAdapter;
    private TargetDialog targetDialog;
    private String TOKEN;
    private IndexDialog indexDialog;
    private AddPopWindow addPopWindow;
    private List<StartInfo.StartPortBean> startList;
    private ArrayList<String> starts = new ArrayList<String>();
    private String nameC;
    private String book = "0";
    private String packget = "0";
    private String DATE = "";
    private ProDateInfo proDateInfo;
    private List<ProDateInfo.ProductDateListBean> productDateList;
    private MyDialog myDialog;
    private ProductDetailInfo productDetailInfo;
    private String productDate;
    private String destination;
    private String goodNumber;
    private String goodWeight;
    private String goodVolume;
    private String bookingPosition;
    private String packageWay;
    private String proportion;
    private String productNo;
    private String start;
    private String end;
    private int proport;
    private List<ProductDetailInfo.PortDataBean.DestinationBean> destinationList;
    private ProgramDetailDialog programDetailDialog;
    private String[] splitDate;
    private List<String> weights = new ArrayList<>();
    private List<ProductDetailInfo.ProportionListBean.ProportionBean> proportion1;
    private int proportType = 0;
    private String changeProport = "";
    private ProgramDetailNexDialog programDetailNexDialog;
    private String priceCheckId;
    private String quotedPriceId;
    private int overSpace;
    private String errData;
    private String weightSize;
    private String throwingGoodsLimit;
    private String suit;
    private String realTotal = "";
    private String mFrist = "0";
    private String airline;
    private String endHy;
    private String imageUrl;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        TOKEN = GoodHealthApp.getUser().getToken();
        startList = SPUtil.getObject(ProgramDetailActivity.this, "startList", List.class);
        starts.clear();
        for (int i = 0; i < startList.size(); i++) {
            starts.add(startList.get(i).getPort());
        }

        //获取上一页数据
        productNo = getIntent().getStringExtra("productNo");
        productDate = getIntent().getStringExtra("productDate");
        destination = getIntent().getStringExtra("startEnd");
        goodNumber = getIntent().getStringExtra("goodNumber");
        goodWeight = getIntent().getStringExtra("goodWeight");
        goodVolume = getIntent().getStringExtra("goodVolume");
        book = getIntent().getStringExtra("bookingPosition");
        packget = getIntent().getStringExtra("packageWay");
        proportion = getIntent().getStringExtra("proportion");
        endHy = getIntent().getStringExtra("endHy");
        imageUrl = getIntent().getStringExtra("imageUrl");
        tvEndHy.setText(endHy);

        if ("0".equals(book)) {
            initSelectColor(tvJust, tvPrepare, 1);
            book = "0";
        }
        if ("1".equals(book)) {
            initSelectColor(tvJust, tvPrepare, 2);
            book = "1";
        }

        if ("0".equals(packget)) {
            initSelectColor(tvTray, tvBulk, 1);
            packget = "0";
        }

        if ("1".equals(packget)) {
            initSelectColor(tvTray, tvBulk, 2);
            packget = "1";
        }
        splitDate = productDate.split("-");
        tvItemOrderDate.setText(splitDate[1] + "月" + splitDate[2] + "日");
        if (destination.contains("-")) {
            String[] split = destination.split("-");
            start = split[0];
            end = split[1];
            tvStartPy.setText(start);
            if (split.length == 2) {
                tvEndPy.setText(end);
            } else if (split.length == 3) {
                tvEndPy.setText(split[2]);
            }
        }
        List<StartInfo.StartPortBean> startList = SPUtil.getObject(ProgramDetailActivity.this, "startList", List.class);
        for (int i = 0; i < startList.size(); i++) {
            if (start.equals(startList.get(i).getPort())) {
                tvStartHy.setText(startList.get(i).getNameC());
            }
        }


        if (TextUtils.isEmpty(goodNumber)) {
            tvHeadNumber.setText("填入");
        } else {
            tvHeadNumber.setText(goodNumber);
        }
        if (TextUtils.isEmpty(goodWeight)) {
            tvHeadWeight.setText("填入");
        } else {
            tvHeadWeight.setText(goodWeight);
        }
        if (TextUtils.isEmpty(goodVolume)) {
            tvHeadVol.setText("填入");
        } else {
            tvHeadVol.setText(goodVolume);
        }

        if (TextUtils.isEmpty(proportion)) {
            tvHeadPortion.setText("- -");
            proportType = 0;
            changeProport = "";
        } else if (proportion.contains(":")) {
            proportType = 1;
            String[] split1 = proportion.split(":");
            changeProport = split1[1];
            tvHeadPortion.setText(proportion);
        }

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(this)
                .load(imageUrl)
                .apply(options)
                .into(ivProCompany);
        //获取日期信息
        getDateInfo();

    }


    private void getDetail() {
        params.clear();
        params.put("act", "getProductsDetailData");
        params.put("productNo", productNo);
//        params.put("startPort", "");
        params.put("productDate", productDate);
        params.put("destination", tvEndPy.getText().toString().trim());
        if ("填入".equals(tvHeadNumber.getText().toString().trim())) {
            params.put("goodNumber", "");
        } else {
            params.put("goodNumber", tvHeadNumber.getText().toString().trim());
        }
        if ("填入".equals(tvHeadWeight.getText().toString().trim())) {
            params.put("goodWeight", "");
        } else {
            params.put("goodWeight", tvHeadWeight.getText().toString().trim());
        }
        if ("填入".equals(tvHeadVol.getText().toString().trim())) {
            params.put("goodVolume", "");
        } else {
            params.put("goodVolume", tvHeadVol.getText().toString().trim());
        }
        if ("- -".equals(tvHeadPortion.getText().toString().trim())) {
            params.put("proportion", "");
        } else {
            params.put("proportion", changeProport);
        }
        params.put("bookingPosition", book);
        params.put("packageWay", packget);


        L.i("详情参数", params.toString());
        RetrofitManager.getInstance().create(CommonService.class)
                .getProdetail(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getData())) {
                            ToastUtil.show(ProgramDetailActivity.this, baseEntity.getData());
                            return;
                        } else {
                            productDetailInfo = gson.fromJson(baseEntity.getSuccess(), ProductDetailInfo.class);
                            initData();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                        if (myDialog != null) {
                            myDialog.dismissDialog();
                        }
                    }
                });
    }

    private void initData() {
        ProductDetailInfo.TransferPriceBean transferPrice = productDetailInfo.getTransferPrice();
        if ("1".equals(transferPrice.getTransferType())) {
            allDetailTrans.setVisibility(View.VISIBLE);
            String unitPrice = transferPrice.getUnitPrice();
            double dUnitPrice = Double.parseDouble(unitPrice);
            int transWight = Integer.parseInt(transferPrice.getChargeableWeight());

            if (transWight == 0) {
                tvDetailZy.setText("¥ " + unitPrice + "/kg * 0kg = 0.00元（按计费重量计算）");
            } else {
                tvDetailZy.setText("¥" + unitPrice + "/kg*" + transWight + "kg=" + (dUnitPrice * transWight) + "元" + "（按计费重量计算）");
            }
            tvDetailZd.setText(transferPrice.getMinPrice() + "元");
            tvDetailBc.setText(transferPrice.getDifferencePrice() + "元");
        } else {
            allDetailTrans.setVisibility(View.GONE);
        }

        if (!"0.00".equals(productDetailInfo.getTotalPrice().getTotal())) {
            tvDetailMoney.setText(productDetailInfo.getTotalPrice().getTotal());
        } else {
            tvDetailMoney.setText("-.-");
        }
        tvProCompany.setText(productDetailInfo.getProductDetail().getProductName());
        tvStartEnd.setText(productDetailInfo.getProductDetail().getStartPort() + "-" + productDetailInfo.getProductDetail().getDirectPoint());
        tvShopName.setText(productDetailInfo.getProductDetail().getSupplierName());
        tvDetailStart.setText(productDetailInfo.getPortData().getTransit());
        destinationList = productDetailInfo.getPortData().getDestination();


        if ("0".equals(mFrist)) {
            realTotal = productDetailInfo.getTotalPrice().getTotal();
        }
        priceCheckId = productDetailInfo.getTotalPrice().getPriceCheckId();
        quotedPriceId = productDetailInfo.getTotalPrice().getQuotedPriceId();
        airline = productDetailInfo.getProductDetail().getAirline();

        throwingGoodsLimit = productDetailInfo.getTotalPrice().getThrowingGoodsLimit();


        //重量等级
        ProductDetailInfo.WeightPriceBean.WeightBean weight = productDetailInfo.getWeightPrice().getWeight();
        String active = productDetailInfo.getWeightPrice().getActive();
        if ("100".equals(active)) {
            weightSize = productDetailInfo.getWeightPrice().getWeight().getW100();
        } else if ("300".equals(active)) {
            weightSize = productDetailInfo.getWeightPrice().getWeight().getW300();
        } else if ("500".equals(active)) {
            weightSize = productDetailInfo.getWeightPrice().getWeight().getW500();
        } else if ("1000".equals(active)) {
            weightSize = productDetailInfo.getWeightPrice().getWeight().getW1000();
        } else if ("3000".equals(active)) {
            weightSize = productDetailInfo.getWeightPrice().getWeight().getW3000();
        } else if ("5000".equals(active)) {
            weightSize = productDetailInfo.getWeightPrice().getWeight().getW5000();
        }
        weights.clear();
        weights.add(weight.getW100());
        weights.add(weight.getW300());
        weights.add(weight.getW500());
        weights.add(weight.getW1000());
        weights.add(weight.getW3000());
        weights.add(weight.getW5000());
        LinearLayoutManager weightmanager = new LinearLayoutManager(this);
        weightmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvWeight.setLayoutManager(weightmanager);
        weightAdapter = new WeightAdapter(ProgramDetailActivity.this, weights, active);
        rvWeight.setAdapter(weightAdapter);

        //比重
        String active1 = productDetailInfo.getProportionList().getActive();
        if ("0".equals(mFrist)) {
            ProportionAdapter.active = active1;
        }

        proportion1 = productDetailInfo.getProportionList().getProportion();
        for (int i = 0; i < proportion1.size(); i++) {
            if (active1.equals(proportion1.get(i).getData())) {
                if ("0".equals(mFrist)) {
                    ProportionAdapter.selectPostion = i;
                }
            }
        }
        LinearLayoutManager portionAdapter = new LinearLayoutManager(this);
        portionAdapter.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvProportion.setLayoutManager(portionAdapter);
        proportionAdapter = new ProportionAdapter(ProgramDetailActivity.this, proportion1, proportType) {
            @Override
            public void setOnItemClick(String proportion, String active) {
                mFrist = "1";
                changeProport = proportion;
                getDateInfo();
            }
        };
        rvProportion.setAdapter(proportionAdapter);
    }

    private void getDateInfo() {
        if (myDialog == null) {
            myDialog = new MyDialog(this);
        }
        myDialog.showDialog();
        params.clear();
        params.put("act", "getProductDateListData");
        params.put("productNo", productNo);
        params.put("destination", tvEndPy.getText().toString().trim());
        if ("填入".equals(tvHeadNumber.getText().toString().trim())) {
            params.put("goodNumber", "");
        } else {
            params.put("goodNumber", tvHeadNumber.getText().toString().trim());
        }
        if ("填入".equals(tvHeadWeight.getText().toString().trim())) {
            params.put("goodWeight", "");
        } else {
            params.put("goodWeight", tvHeadWeight.getText().toString().trim());
        }
        if ("填入".equals(tvHeadVol.getText().toString().trim())) {
            params.put("goodVolume", "");
        } else {
            params.put("goodVolume", tvHeadVol.getText().toString().trim());
        }
        if ("- -".equals(tvHeadPortion.getText().toString().trim())) {
            params.put("proportion", "");
        } else {
            params.put("proportion", changeProport);
        }
        params.put("bookingPosition", book);
        params.put("packageWay", packget);


        L.i("方案选择参数", params.toString());
        RetrofitManager.getInstance().create(CommonService.class)
                .getDateinfo(TOKEN, params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (!TextUtils.isEmpty(baseEntity.getData())) {
                            ToastUtil.show(ProgramDetailActivity.this, baseEntity.getData());
                            return;
                        } else {
                            proDateInfo = gson.fromJson(baseEntity.getSuccess(), ProDateInfo.class);
                            productDateList = proDateInfo.getProductDateList();
                            initDate();
                            //获取产品详情
                            getDetail();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }

    private void initDate() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.scrollToPositionWithOffset(DetailDateAdapter.selectPostion, 100);//adapter刷新时自动回到顶部
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetail.setLayoutManager(manager);
        rvDetail.setFocusableInTouchMode(false);
        rvDetail.setHasFixedSize(true);
        if ("0".equals(mFrist)) {
            if (!TextUtils.isEmpty(productDateList.get(0).getSpace().getOverSpace())) {
                overSpace = Integer.parseInt(productDateList.get(0).getSpace().getOverSpace());
            } else {
                overSpace = 0;
            }
        }
        dateAdapter = new DetailDateAdapter(ProgramDetailActivity.this, productDateList, book) {
            @Override
            public void setOnItemClickListener(String position, int postion, int overspace) {
                productDate = position;
                DetailDateAdapter.selectPostion = postion;
                splitDate = productDate.split("-");
                tvItemOrderDate.setText(splitDate[1] + "月" + splitDate[2] + "日");
                overSpace = overspace;
                errData = productDateList.get(postion).getErrData();
                if ("0".equals(book)) {
                    suit = productDateList.get(postion).getSpace().getSuit();
                } else if ("1".equals(book)) {
                    suit = productDateList.get(postion).getSpace().getReadiness();
                }
                getDateInfo();
            }
        };
        rvDetail.setAdapter(dateAdapter);

        /*rvDetail.post(new Runnable() {
            @Override
            public void run() {
                rvDetail.scrollToPosition(DetailDateAdapter.selectPostion);
            }
        });*/
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_program_detail;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.all_set_line, R.id.tv_just, R.id.tv_prepare, R.id.tv_tray, R.id.tv_bulk, R.id.tv_detail, R.id.tv_detail_end, R.id.tv_detail_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_set_line://头部
                if (programDetailDialog == null) {
                    initNewDialog();
                }
                programDetailDialog.setStart(start);
                programDetailDialog.setPort(tvEndPy.getText().toString().trim());
                if (!"填入".equals(tvHeadNumber.getText().toString().trim())) {
                    programDetailDialog.setNumber(tvHeadNumber.getText().toString().trim());
                } else {
                    programDetailDialog.setNumber("");
                }

                if (!"填入".equals(tvHeadWeight.getText().toString().trim())) {
                    programDetailDialog.setWeight(tvHeadWeight.getText().toString().trim());
                } else {
                    programDetailDialog.setWeight("");
                }

                if (!"填入".equals(tvHeadVol.getText().toString().trim())) {
                    programDetailDialog.setVol(tvHeadVol.getText().toString().trim());
                } else {
                    programDetailDialog.setVol("");
                }
                programDetailDialog.show();
                break;
            case R.id.tv_just:
                initSelectColor(tvJust, tvPrepare, 1);
                book = "0";
                getDateInfo();
                break;
            case R.id.tv_prepare:
                initSelectColor(tvJust, tvPrepare, 2);
                book = "1";
                getDateInfo();
                break;
            case R.id.tv_tray:
                initSelectColor(tvTray, tvBulk, 1);
                packget = "0";
                getDateInfo();
                break;
            case R.id.tv_bulk:
                packget = "1";
                initSelectColor(tvTray, tvBulk, 2);
                getDateInfo();
                break;
            case R.id.tv_detail://详情
                Intent intent = new Intent(ProgramDetailActivity.this, ProductDetailActivity.class);
                intent.putExtra("productNo", productNo);
                intent.putExtra("productDate", productDate);
                intent.putExtra("destination", tvEndPy.getText().toString().trim());
                if ("填入".equals(tvHeadNumber.getText().toString().trim())) {
                    intent.putExtra("goodNumber", "");
                } else {
                    intent.putExtra("goodNumber", tvHeadNumber.getText().toString().trim());
                }
                if ("填入".equals(tvHeadWeight.getText().toString().trim())) {
                    intent.putExtra("goodWeight", "");
                } else {
                    intent.putExtra("goodWeight", tvHeadWeight.getText().toString().trim());
                }
                if ("填入".equals(tvHeadVol.getText().toString().trim())) {
                    intent.putExtra("goodVolume", "");
                } else {
                    intent.putExtra("goodVolume", tvHeadVol.getText().toString().trim());
                }
                if ("- -".equals(tvHeadPortion.getText().toString().trim())) {
                    intent.putExtra("proportion", "");
                } else {
                    String[] split = tvHeadPortion.getText().toString().trim().split(":");
                    intent.putExtra("proportion", split[1]);
                }
                intent.putExtra("bookingPosition", book);
                intent.putExtra("packageWay", packget);
                startActivity(intent);
                break;
            case R.id.tv_detail_end://目的港
                targetDialog = new TargetDialog(this, destinationList, tvEndPy.getText().toString().trim()) {
                    @Override
                    public void setOnTargetClick(String target, String hy) {
                        TargetDialog.portLog = 1;
                        tvDetailEnd.setText("目的港：" + target);
                        tvEndPy.setText(target);
                        if (hy.contains(",")) {
                            String[] split = hy.split(",");
                            tvEndHy.setText(split[0]);
                        } else if (hy.contains("，")) {
                            String[] split = hy.split("，");
                            tvEndHy.setText(split[0]);
                        } else {
                            tvEndHy.setText(hy);
                        }

                        dismiss();
                        getDateInfo();
                    }
                };
                targetDialog.show();
                break;
            case R.id.tv_detail_next://下一步
                if ("填入".equals(tvHeadNumber.getText().toString().trim()) && "填入".equals(tvHeadWeight.getText().toString().trim()) && "填入".equals(tvHeadVol.getText().toString().trim())) {
                    if (programDetailDialog == null) {
                        initNewDialog();
                    }
                    programDetailDialog.setStart(start);
                    programDetailDialog.setPort(end);
                    if (!"填入".equals(tvHeadNumber.getText().toString().trim())) {
                        programDetailDialog.setNumber(tvHeadNumber.getText().toString().trim());
                    } else {
                        programDetailDialog.setNumber("");
                    }

                    if (!"填入".equals(tvHeadWeight.getText().toString().trim())) {
                        programDetailDialog.setWeight(tvHeadWeight.getText().toString().trim());
                    } else {
                        programDetailDialog.setWeight("");
                    }

                    if (!"填入".equals(tvHeadVol.getText().toString().trim())) {
                        programDetailDialog.setVol(tvHeadVol.getText().toString().trim());
                    } else {
                        programDetailDialog.setVol("");
                    }
                    programDetailDialog.show();
                    return;
                } else if (Integer.parseInt(tvHeadWeight.getText().toString().trim()) > overSpace) {
                    showPromptDialog("您的订舱重量大于剩余舱位重量，无法订舱");
                    return;
                } else if ("0.00".equals(weightSize)) {
                    showPromptDialog("当前重量无报价，无法订舱！");
                    return;
                } else if ("0".equals(throwingGoodsLimit)) {
                    showPromptDialog("该产品不支持当前比重订舱！");
                    return;
                } else if ("100".equals(suit)) {
                    showPromptDialog("该产品当前日期已无剩余舱位！");
                    return;
                } else if ("-.-".equals(tvDetailMoney.getText().toString().trim())) {
                    showPromptDialog("该产品没有当前重量报价，无法订舱！");
                } else {
                    if (programDetailNexDialog == null) {
                        programDetailNexDialog = new ProgramDetailNexDialog(this) {
                            @Override
                            public void setOnSureClick() {
                                if (TextUtils.isEmpty(TOKEN)) {
                                    Intent intent1 = new Intent(ProgramDetailActivity.this, LoginActivity.class);
                                    intent1.putExtra("detail", 1);
                                    startActivityForResult(intent1, 2);
                                } else {
                                    Intent intent1 = new Intent(ProgramDetailActivity.this, SubmitActivity.class);
                                    intent1.putExtra("productNo", productNo);
                                    intent1.putExtra("startPort", tvStartPy.getText().toString().trim());
                                    intent1.putExtra("productDate", productDate);
                                    intent1.putExtra("destination", tvEndPy.getText().toString().trim());
                                    intent1.putExtra("goodNumber", tvHeadNumber.getText().toString().trim());
                                    intent1.putExtra("goodWeight", tvHeadWeight.getText().toString().trim());
                                    intent1.putExtra("goodVolume", tvHeadVol.getText().toString().trim());
                                    intent1.putExtra("bookingPosition", book);
                                    intent1.putExtra("packageWay", packget);
                                    intent1.putExtra("quotedPriceId", quotedPriceId);
                                    intent1.putExtra("priceCheckId", priceCheckId);
                                    intent1.putExtra("proportion", tvHeadPortion.getText().toString().trim());
                                    intent1.putExtra("realTotal", realTotal);
                                    intent1.putExtra("airline", airline);
                                    startActivity(intent1);
                                }
                                dismiss();
                            }
                        };
                    }
                    programDetailNexDialog.show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
//                TOKEN = GoodHealthApp.getUser().getToken();
                break;
        }
    }

    private void initNewDialog() {
        programDetailDialog = new ProgramDetailDialog(ProgramDetailActivity.this) {
            @Override
            public void sureClick(TextView tv1, TextView tv2, EditText et_show1, EditText et_show2, EditText et_show3, TextView tv3) {
                String trim1 = et_show1.getText().toString().trim();
                String trim2 = et_show2.getText().toString().trim();
                String trim3 = et_show3.getText().toString().trim();
                if (TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("件数、重量、体积参数错误");
                    return;
                } else if (!TextUtils.isEmpty(trim1) && TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("件数、重量、体积参数错误");
                    return;
                } else if (!TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && TextUtils.isEmpty(trim3)) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("件数、重量、体积参数错误");
                    return;
                } else if (!TextUtils.isEmpty(trim1) && TextUtils.isEmpty(trim2) && TextUtils.isEmpty(trim3)) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("件数、重量、体积参数错误");
                    return;
                } else if (TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && TextUtils.isEmpty(trim3)) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("件数、重量、体积参数错误");
                    return;
                } else if (TextUtils.isEmpty(trim1) && TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("件数、重量、体积参数错误");
                    return;
                } else if ("0".equals(et_show1.getText().toString().trim()) || "0".equals(et_show3.getText().toString().trim())
                        || "0.0".equals(et_show1.getText().toString().trim()) || "0.0".equals(et_show3.getText().toString().trim())) {
                    tv3.setVisibility(View.VISIBLE);
                    tv3.setText("件数、体积不可以为0");
                    return;
                } else if (!TextUtils.isEmpty(trim1) && !TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
                    int intWeight = Integer.parseInt(trim2);
                    if (intWeight < 100) {
                        tv3.setVisibility(View.VISIBLE);
                        tv3.setText("重量不得少于100kg");
                        return;
                    } else {
                        tvStartPy.setText(tv1.getText().toString().trim());
                        tvEndPy.setText(tv2.getText().toString().trim());

                        if (!TextUtils.isEmpty(et_show1.getText().toString().trim())) {
                            tvHeadNumber.setText(et_show1.getText().toString().trim());
                        } else {
                            tvHeadNumber.setText("填入");
                        }

                        if (!TextUtils.isEmpty(et_show2.getText().toString().trim())) {
                            tvHeadWeight.setText(et_show2.getText().toString().trim());
                        } else {
                            tvHeadWeight.setText("填入");
                        }

                        if (!TextUtils.isEmpty(et_show3.getText().toString().trim())) {
                            tvHeadVol.setText(et_show3.getText().toString().trim());
                        } else {
                            tvHeadVol.setText("填入");
                        }

                        if (!"填入".equals(tvHeadWeight.getText().toString().trim()) && !"填入".equals(tvHeadVol.getText().toString().trim())) {
                            double v1 = Double.parseDouble(tvHeadWeight.getText().toString().trim());
                            double v2 = Double.parseDouble(tvHeadVol.getText().toString().trim());
                            tvHeadPortion.setText("1:" + (int) (v1 / v2));
                            proportType = 1;
                            changeProport = (int) (v1 / v2) + "";
                        } else {
                            tvHeadPortion.setText("- -");
                            proportType = 0;
                            changeProport = "";
                        }
                        tv3.setVisibility(View.INVISIBLE);
                        tv3.setText("");
                        dismiss();
                        mFrist = "0";
                        getDateInfo();
                    }
                } else {
                    tvStartPy.setText(tv1.getText().toString().trim());
                    tvEndPy.setText(tv2.getText().toString().trim());
                    if (!TextUtils.isEmpty(et_show1.getText().toString().trim())) {
                        tvHeadNumber.setText(et_show1.getText().toString().trim());
                    } else {
                        tvHeadNumber.setText("填入");
                    }

                    if (!TextUtils.isEmpty(et_show2.getText().toString().trim())) {
                        tvHeadWeight.setText(et_show2.getText().toString().trim());
                    } else {
                        tvHeadWeight.setText("填入");
                    }

                    if (!TextUtils.isEmpty(et_show3.getText().toString().trim())) {
                        tvHeadVol.setText(et_show3.getText().toString().trim());
                    } else {
                        tvHeadVol.setText("填入");
                    }

                    if (!"填入".equals(tvHeadWeight.getText().toString().trim()) && !"填入".equals(tvHeadVol.getText().toString().trim())) {
                        double v1 = Double.parseDouble(tvHeadWeight.getText().toString().trim());
                        double v2 = Double.parseDouble(tvHeadVol.getText().toString().trim());
                        tvHeadPortion.setText("1:" + (int) (v1 / v2));
                        proportType = 1;
                        changeProport = (int) (v1 / v2) + "";
                    } else {
                        tvHeadPortion.setText("- -");
                        proportType = 0;
                        changeProport = "";
                    }
                    tv3.setVisibility(View.INVISIBLE);
                    tv3.setText("");
                    dismiss();
                    mFrist = "0";
                    getDateInfo();
                }
            }
        };
    }

    private void initSelectColor(TextView tv1, TextView tv2, int i) {
        if (i == 1) {
            tv1.setBackgroundResource(R.drawable.bg_tv_just);
            tv1.setTextColor(Color.parseColor("#ffffff"));
            tv2.setBackgroundResource(R.drawable.bg_tv_back);
            tv2.setTextColor(Color.parseColor("#00A7F7"));
        } else if (i == 2) {
            tv1.setBackgroundResource(R.drawable.bg_tv_back);
            tv1.setTextColor(Color.parseColor("#00A7F7"));
            tv2.setBackgroundResource(R.drawable.bg_tv_just);
            tv2.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void showPromptDialog(String s) {
        new AlertDialog.Builder(this)
                .setMessage(s)
                .setPositiveButton("确定", null).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (programDetailDialog != null) {
            programDetailDialog.dismiss();
        }

        if (programDetailNexDialog != null) {
            programDetailNexDialog.dismiss();
        }
        if (targetDialog != null) {
            targetDialog.dismiss();
        }
        DetailDateAdapter.selectPostion = 0;
        EndSeclectAdapter.selectPostion = 0;
        GridEndAdapter.selectPostion = 0;
        TargetDialog.portLog = 0;
    }
}
