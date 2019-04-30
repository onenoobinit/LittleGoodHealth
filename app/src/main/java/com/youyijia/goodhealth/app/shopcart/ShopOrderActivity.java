package com.youyijia.goodhealth.app.shopcart;

import android.content.Intent;
import android.graphics.Paint;
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
import com.google.gson.reflect.TypeToken;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.adress.AdressActivity;
import com.youyijia.goodhealth.app.pay.PayTypeActivity;
import com.youyijia.goodhealth.entity.AddressListInfo;
import com.youyijia.goodhealth.entity.ShopOrderIdPost;
import com.youyijia.goodhealth.entity.ShopOrderPriceInfo;
import com.youyijia.goodhealth.entity.ShopOrderPricePost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.KeyBoardShowListener;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ShopOrderActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.ll_add_address)
    LinearLayout llAddAddress;
    @BindView(R.id.rl_add_no)
    RelativeLayout rlAddNo;
    @BindView(R.id.iv_address_left)
    ImageView ivAddressLeft;
    @BindView(R.id.tv_address_name)
    TextView tvAddressName;
    @BindView(R.id.tv_adddress_phone)
    TextView tvAdddressPhone;
    @BindView(R.id.tv_address_detail)
    TextView tvAddressDetail;
    @BindView(R.id.rl_add_have)
    RelativeLayout rlAddHave;
    @BindView(R.id.tv_shop_order_type)
    TextView tvShopOrderType;
    @BindView(R.id.iv_shop_order)
    ImageView ivShopOrder;
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
    @BindView(R.id.tv_reduce)
    TextView tvReduce;
    @BindView(R.id.tv_num)
    EditText tvNum;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_send_type)
    TextView tvSendType;
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
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    private String commodityId;
    private String commoditySpecificationNo;
    private String name;
    private String imageurl;
    private String attached;
    private String sendType;
    private String originalPrice;
    private String presentPrice;
    private int nums;
    private BigDecimal price;
    private BigDecimal bigDecimal;
    private ArrayList<AddressListInfo> addresss = new ArrayList();
    private int areaStauts = -1;
    private BigDecimal original;
    private BigDecimal subtract;
    private String commodityCount;
    private String itemNumber;
    private ShopOrderPriceInfo shopOrderPriceInfo;
    private String receiveAddressId = "";
    private List<ShopOrderIdPost.PostInfoBean.OrderItemsBean> orderItemsBeans = new ArrayList<>();

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        commodityId = getIntent().getStringExtra("commodityId");
        commodityCount = getIntent().getStringExtra("commodityCount");
        itemNumber = getIntent().getStringExtra("itemNumber");
        nums = 1;
        getAddress();
        getData();

        new KeyBoardShowListener(ShopOrderActivity.this).setKeyboardListener(new KeyBoardShowListener.OnKeyboardVisibilityListener() {
            @Override
            public void onVisibilityChanged(boolean visible) {
                if (visible) { //软键盘已弹出
                } else { //软键盘未弹出
                    if ("0".equals(tvNum.getText().toString().trim())) {
                        tvNum.setText("1");
                        L.i("AAAA", tvNum.getText().toString().trim());
                        commodityCount = tvNum.getText().toString().trim();
                        getData();
                    } else {
                        tvNum.setText(tvNum.getText().toString().trim());
                        L.i("AAAA", tvNum.getText().toString().trim());
                        commodityCount = tvNum.getText().toString().trim();
                        getData();
                    }
                }
            }
        }, ShopOrderActivity.this);
    }

    private void getData() {
        ShopOrderPricePost loginPost = new ShopOrderPricePost();
        ShopOrderPricePost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setCommodityId(commodityId);
        postInfoBean.setCommodityCount(commodityCount);
        postInfoBean.setItemNumber(itemNumber);

        loginPost.setPostInfoBean(postInfoBean);
        String s1 = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s1);

        RetrofitManager.getInstance().create(CommonService.class)
                .getShopPrice(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            shopOrderPriceInfo = gson.fromJson(baseEntity.getData(), ShopOrderPriceInfo.class);
                            initPrice();
                        } else {
                            ToastUtil.show(ShopOrderActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initPrice() {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(ShopOrderActivity.this)
                .load(shopOrderPriceInfo.getCommodity().getCoverImage())
                .apply(options)
                .into(ivShopOrder);
        tvOrderWholeName.setText(shopOrderPriceInfo.getCommodity().getName());
        ShopOrderPriceInfo.CommodityItemSelectedBean.AttachedBeanX attached = shopOrderPriceInfo.getCommodityItemSelected().getAttached();
        tvOrderWholeStyle.setText("\"" + attached.get大小() + ";" + attached.get颜色() + "\"");
        tvOrderRealPrice.setText("¥" + shopOrderPriceInfo.getCommodityItemSelected().getPresentPrice());
        tvOrderOutPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvOrderOutPrice.setText("¥" + shopOrderPriceInfo.getCommodityItemSelected().getOriginalPrice());
        tvOrderShopNumber.setText("×" + shopOrderPriceInfo.getCommodityCount());
        tvSendType.setText(shopOrderPriceInfo.getLogisticsMode());
        tvGreenPriceTotal.setText("¥" + shopOrderPriceInfo.getOriginProductAmountTotal());
        tvGreenPriceYh.setText("¥" + shopOrderPriceInfo.getReducedPrice());
        tvGreenPriceYf.setText("¥" + shopOrderPriceInfo.getLogisticsFee());
        tvPriceGreen.setText(shopOrderPriceInfo.getAmountPayable() + "");
    }

    private void getAddress() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getAddressList()
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            List<AddressListInfo> datas = gson.fromJson(baseEntity.getData(), new TypeToken<List<AddressListInfo>>() {
                            }.getType());
                            addresss.clear();
                            addresss.addAll(datas);
                            initAddress();

                        } else {
                            ToastUtil.show(ShopOrderActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void initAddress() {
        if (addresss.size() == 0 || addresss == null) {
            rlAddNo.setVisibility(View.VISIBLE);
            rlAddHave.setVisibility(View.GONE);
        } else if (addresss.size() > 0) {
            for (int i = 0; i < addresss.size(); i++) {
                String name = addresss.get(i).getAddressStatus().getName();
                if ("DEFAULT".equals(name)) {
                    areaStauts = i;
                }
            }

            if (areaStauts == -1) {
                rlAddNo.setVisibility(View.VISIBLE);
                rlAddHave.setVisibility(View.GONE);
            } else {
                rlAddHave.setVisibility(View.VISIBLE);
                rlAddNo.setVisibility(View.GONE);

                tvAddressName.setText(addresss.get(areaStauts).getReceiver());
                String address = addresss.get(areaStauts).getProvinceName() + addresss.get(areaStauts).getCityName()
                        + addresss.get(areaStauts).getCountryName() + addresss.get(areaStauts).getAddress();
                tvAdddressPhone.setText(addresss.get(areaStauts).getPhone());
                tvAddressDetail.setText(address);
                receiveAddressId = addresss.get(areaStauts).getId() + "";
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_order;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }

    @OnClick({R.id.rl_add_no, R.id.rl_add_have, R.id.tv_reduce, R.id.tv_add, R.id.tv_submit_green})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_add_no://添加收货地址
                Intent intent = new Intent(ShopOrderActivity.this, AdressActivity.class);
                intent.putExtra("type", "1");
                startActivityForResult(intent, 1123);
                break;
            case R.id.rl_add_have://修改收货地址
                Intent intent1 = new Intent(ShopOrderActivity.this, AdressActivity.class);
                intent1.putExtra("type", "1");
                startActivityForResult(intent1, 1124);
                break;
            case R.id.tv_reduce://减少
                nums = Integer.parseInt(tvNum.getText().toString().trim());
                if (nums == 1) {
                    tvNum.setText("1");
                    commodityCount = tvNum.getText().toString().trim();
                    getData();
                } else {
                    nums = nums - 1;
                    tvNum.setText(nums + "");
                    commodityCount = tvNum.getText().toString().trim();
                    getData();
                }
                break;
            case R.id.tv_add://增加
                nums = Integer.parseInt(tvNum.getText().toString().trim());
                tvNum.setText((nums + 1) + "");
                commodityCount = tvNum.getText().toString().trim();
                getData();
                break;
            case R.id.tv_submit_green://提交订单
                if (TextUtils.isEmpty(receiveAddressId)) {
                    ToastUtil.show(ShopOrderActivity.this, "请添加收货地址！");
                    return;
                } else {
                    getPay();
                }
                break;
        }
    }

    private void getPay() {

        ShopOrderIdPost loginPost = new ShopOrderIdPost();
        ShopOrderIdPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        ShopOrderIdPost.PostInfoBean.OrderItemsBean orderItemsBean = postInfoBean.new OrderItemsBean();
        postInfoBean.setReceiveAddressId(receiveAddressId);
        if (TextUtils.isEmpty(etContent.getText().toString().trim())) {
            postInfoBean.setNote(etContent.getText().toString().trim());
        }
//        postInfoBean.setProvinceId(provinceId);
        orderItemsBean.setCommodityId(shopOrderPriceInfo.getCommodityId());
        orderItemsBean.setNumber(commodityCount);
        orderItemsBean.setCommoditySpecificationNo(commoditySpecificationNo);
        orderItemsBeans.clear();
        orderItemsBeans.add(orderItemsBean);
        postInfoBean.setOrderItems(orderItemsBeans);


        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getOrderId(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            Intent intent = new Intent(ShopOrderActivity.this, PayTypeActivity.class);
                            intent.putExtra("orderId", baseEntity.getData());
                            startActivity(intent);
                        } else {
                            ToastUtil.show(ShopOrderActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1123 && resultCode == RESULT_OK) {
            String mname = data.getStringExtra("name");
            String mphone = data.getStringExtra("phone");
            String marea = data.getStringExtra("area");
            receiveAddressId = data.getStringExtra("receiveAddressId");

            rlAddNo.setVisibility(View.GONE);
            rlAddHave.setVisibility(View.VISIBLE);
            tvAddressName.setText(mname);
            tvAdddressPhone.setText(mphone);
            tvAddressDetail.setText(marea);
        }

        if (requestCode == 1124 && resultCode == RESULT_OK) {
            String mname = data.getStringExtra("name");
            String mphone = data.getStringExtra("phone");
            String marea = data.getStringExtra("area");
            receiveAddressId = data.getStringExtra("receiveAddressId");

            rlAddNo.setVisibility(View.GONE);
            rlAddHave.setVisibility(View.VISIBLE);
            tvAddressName.setText(mname);
            tvAdddressPhone.setText(mphone);
            tvAddressDetail.setText(marea);
        }
    }

}
