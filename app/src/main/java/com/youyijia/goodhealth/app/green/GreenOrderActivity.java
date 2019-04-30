package com.youyijia.goodhealth.app.green;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.pay.PayTypeActivity;
import com.youyijia.goodhealth.entity.PutgreenOrderPost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GreenOrderActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_green_order_time)
    TextView tvGreenOrderTime;
    @BindView(R.id.iv_order_item)
    ImageView ivOrderItem;
    @BindView(R.id.tv_order_whole_name)
    TextView tvOrderWholeName;
    @BindView(R.id.tv_order_whole_style)
    TextView tvOrderWholeStyle;
    @BindView(R.id.tv_order_real_price)
    TextView tvOrderRealPrice;
    @BindView(R.id.tv_order_out_price)
    TextView tvOrderOutPrice;
    @BindView(R.id.tv_order_shop_number)
    TextView tvOrderShopNumber;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_green_price_total)
    TextView tvGreenPriceTotal;
    @BindView(R.id.tv_green_price_yh)
    TextView tvGreenPriceYh;
    @BindView(R.id.tv_green_price_yf)
    TextView tvGreenPriceYf;
    @BindView(R.id.tv_price_green)
    TextView tvPriceGreen;
    @BindView(R.id.tv_submit_green)
    TextView tvSubmitGreen;
    private String serviceName;
    private String totalPrice;
    private String youhuiPrice;
    private String shijiPrice;
    private String cityServiceId;
    private String medicalPersonId;
    private String linkPhone;
    private String note;
    private String hospital;
    private String hospitalId;
    private String date;
    private String orderId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        logiToolBar.setNavigationOnClickListener(v -> finish());

        serviceName = getIntent().getStringExtra("serviceName");
        totalPrice = getIntent().getStringExtra("totalPrice");
        youhuiPrice = getIntent().getStringExtra("youhuiPrice");
        shijiPrice = getIntent().getStringExtra("shijiPrice");
        cityServiceId = getIntent().getStringExtra("cityServiceId");
        medicalPersonId = getIntent().getStringExtra("medicalPersonId");
        linkPhone = getIntent().getStringExtra("linkPhone");
        note = getIntent().getStringExtra("note");
        hospital = getIntent().getStringExtra("hospital");
        hospitalId = getIntent().getStringExtra("hospitalId");

        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        tvGreenOrderTime.setText("下单时间：" + date);
        tvOrderWholeName.setText("商品名：绿色通道");
        tvOrderWholeStyle.setText("商品类型:" + serviceName);
        tvOrderRealPrice.setText("¥" + shijiPrice);
        tvOrderOutPrice.setText("¥" + totalPrice);
        tvOrderOutPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        etContent.setText(note);
        tvGreenPriceTotal.setText("¥" + shijiPrice);
        tvGreenPriceYh.setText("¥" + youhuiPrice);
        tvGreenPriceYf.setText("¥0");
        tvPriceGreen.setText("¥" + shijiPrice);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_green_order;
    }

    @Override
    public void initToolBar() {

    }


    @OnClick(R.id.tv_submit_green)
    public void onClick() {
        getdata();
    }

    private void getdata() {
        PutgreenOrderPost loginPost = new PutgreenOrderPost();
        PutgreenOrderPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setCityServiceId(cityServiceId);
        postInfoBean.setMedicalPersonId(medicalPersonId);
        postInfoBean.setLinkPhone(linkPhone);
        postInfoBean.setNote(note);
        postInfoBean.setHospital(hospital);
        postInfoBean.setHospitalId(hospitalId);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .putGreenOrder(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            orderId = gson.fromJson(baseEntity.getData(), String.class);
                            Intent intent = new Intent(GreenOrderActivity.this, PayTypeActivity.class);
                            intent.putExtra("orderId",orderId);
                            startActivity(intent);
                        } else {
                            ToastUtil.show(GreenOrderActivity.this, baseEntity.getMessage());
                        }
                    }

                });
    }

}
