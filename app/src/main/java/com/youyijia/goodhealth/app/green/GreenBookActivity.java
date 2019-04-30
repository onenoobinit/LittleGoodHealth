package com.youyijia.goodhealth.app.green;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.adress.GreenInformationActivity;
import com.youyijia.goodhealth.entity.GreenPriceInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenBookActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_lv_introduction)
    TextView tvLvIntroduction;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_select_city)
    TextView tvSelectCity;
    @BindView(R.id.tv_select_service)
    TextView tvSelectService;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_select_hospital)
    TextView tvSelectHospital;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_remove)
    ImageView ivRemove;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.ll_add_content)
    LinearLayout llAddContent;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.tv_whole)
    TextView tvWhole;
    @BindView(R.id.tv_youhui)
    TextView tvYouhui;
    @BindView(R.id.tv_shiji)
    TextView tvShiji;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_select_city)
    RelativeLayout rlSelectCity;
    @BindView(R.id.rl_select_service)
    RelativeLayout rlSelectService;
    @BindView(R.id.rl_select_hostipal)
    RelativeLayout rlSelectHostipal;
    @BindView(R.id.tv_type)
    TextView tvType;
    private String url;
    private String city;
    private String id;
    private String service;
    private String serviceId;
    private String hosId;
    private String medicalPersonId;
    private int status = 0;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)) {
            url = "https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/31bf02d3-32b7-4c1e-8bba-2ee3319e742b.png?height=1180&width=750";
        }

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(GreenBookActivity.this)
                .load(url)
                .apply(options)
                .into(ivContent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_book;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }


    @OnClick({R.id.tv_lv_introduction, R.id.rl_select_city, R.id.rl_select_service, R.id.rl_select_hostipal, R.id.tv_add, R.id.iv_remove, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_lv_introduction:
                startActivity(new Intent(GreenBookActivity.this, GreenInstrctionActivity.class));
                break;
            case R.id.rl_select_city://选择城市
                startActivityForResult(new Intent(GreenBookActivity.this, GreenCityActivity.class), 1111);
                break;
            case R.id.rl_select_service://选择服务:
                if ("选择城市类型".equals(tvSelectCity.getText().toString().trim())) {
                    ToastUtil.show(GreenBookActivity.this, "请选择城市类型！");
                    return;
                } else if (!TextUtils.isEmpty(id)) {
                    Intent intent = new Intent(GreenBookActivity.this, GreenSeviceActivity.class);
                    intent.putExtra("id", id);
                    startActivityForResult(intent, 1113);
                }
                break;
            case R.id.rl_select_hostipal://选择医院
                if ("选择城市类型".equals(tvSelectCity.getText().toString().trim())) {
                    ToastUtil.show(GreenBookActivity.this, "请选择城市类型！");
                    return;
                } else if ("选择服务类型".equals(tvSelectService.getText().toString().trim())) {
                    ToastUtil.show(GreenBookActivity.this, "选择服务类型！");
                    return;
                } else {
                    Intent intent = new Intent(GreenBookActivity.this, GreenHostipalActivity.class);
                    intent.putExtra("serviceId", serviceId);
                    startActivityForResult(intent, 1114);
                }
                break;
            case R.id.tv_add:
                Intent intent = new Intent(GreenBookActivity.this, GreenInformationActivity.class);
                intent.putExtra("green", "1");
                startActivityForResult(intent, 1115);
                break;
            case R.id.iv_remove:
                tvAdd.setVisibility(View.VISIBLE);
                llAddContent.setVisibility(View.GONE);
                status = 0;
                break;
            case R.id.tv_submit:
                if ("选择城市类型".equals(tvSelectCity.getText().toString().trim())) {
                    ToastUtil.show(GreenBookActivity.this, "请选择城市类型！");
                    return;
                } else if ("选择服务类型".equals(tvSelectService.getText().toString().trim())) {
                    ToastUtil.show(GreenBookActivity.this, "选择服务类型！");
                    return;
                } else if ("请输入联系方式".equals(etPhone.getText().toString().trim())) {
                    ToastUtil.show(GreenBookActivity.this, "请输入联系方式！");
                    return;
                } else if ("选择医院".equals(tvSelectHospital.getText().toString().trim())) {
                    ToastUtil.show(GreenBookActivity.this, "选择医院！");
                    return;
                } else if (status == 0) {
                    ToastUtil.show(GreenBookActivity.this, "请添加就医人！");
                    return;
                } else {
                    startOrder();
                }
                break;
        }
    }

    private void startOrder() {
        Intent intent = new Intent(GreenBookActivity.this, GreenOrderActivity.class);
        intent.putExtra("serviceName",tvSelectService.getText().toString().trim());
        intent.putExtra("totalPrice",tvWhole.getText().toString().trim());
        intent.putExtra("youhuiPrice",tvYouhui.getText().toString().trim());
        intent.putExtra("shijiPrice",tvShiji.getText().toString().trim());
        intent.putExtra("cityServiceId",serviceId);
        intent.putExtra("medicalPersonId",medicalPersonId);
        intent.putExtra("linkPhone",etPhone.getText().toString().trim());
        intent.putExtra("note",etContent.getText().toString().trim());
        intent.putExtra("hospital",tvSelectHospital.getText().toString().trim());
        intent.putExtra("hospitalId",hosId);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && resultCode == RESULT_OK) {
            city = data.getStringExtra("city");
            id = data.getStringExtra("id");
            tvSelectCity.setText(city);
        }

        if (requestCode == 1113 && resultCode == RESULT_OK) {
            service = data.getStringExtra("service");
            serviceId = data.getStringExtra("serviceId");
            tvSelectService.setText(service);
        }

        if (requestCode == 1114 && resultCode == RESULT_OK) {
            String hospital = data.getStringExtra("hospital");
            hosId = data.getStringExtra("hosId");
            tvSelectHospital.setText(hospital);
            if (status == 1) {
                getPrice();
            }
        }

        if (requestCode == 1115 && resultCode == RESULT_OK) {
            tvAdd.setVisibility(View.GONE);
            llAddContent.setVisibility(View.VISIBLE);
            status = 1;
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String type = data.getStringExtra("type");
            String card = data.getStringExtra("card");
            medicalPersonId = data.getStringExtra("medicalPersonId");
            tvName.setText(name);
            tvPhone.setText(phone);
            tvType.setText(type);
            tvId.setText(card);
            if (!"选择医院".equals(tvSelectHospital.getText().toString().trim())) {
                getPrice();
            }
        }
    }

    private void getPrice() {
        params.clear();
        params.put("cityServiceId", serviceId);
        params.put("medicalPersonId", medicalPersonId);
        RetrofitManager.getInstance().create(CommonService.class)
                .getGreenPrice(params)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            GreenPriceInfo greenPriceInfo = gson.fromJson(baseEntity.getData(), GreenPriceInfo.class);
                            tvWhole.setText(greenPriceInfo.getTotalPay() + "");
                            tvYouhui.setText(greenPriceInfo.getDiscountMoney() + "");
                            tvShiji.setText(greenPriceInfo.getFinalPay() + "");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tvAdd.setVisibility(View.VISIBLE);
        llAddContent.setVisibility(View.GONE);
        status = 0;
    }

}
