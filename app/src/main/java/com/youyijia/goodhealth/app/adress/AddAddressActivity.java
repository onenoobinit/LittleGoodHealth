package com.youyijia.goodhealth.app.adress;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.AddAreaPost;
import com.youyijia.goodhealth.entity.SanjiInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.utils.GetJsonDataUtil;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.L;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.utils.Validator;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.tv_save_address)
    TextView tvSaveAddress;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.tv_add_address_select)
    TextView tvAddAddressSelect;
    @BindView(R.id.ll_add_address_select)
    LinearLayout llAddAddressSelect;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.et_address_name)
    EditText etAddressName;
    @BindView(R.id.et_address_phone)
    EditText etAddressPhone;
    @BindView(R.id.cb_add_address)
    CheckBox cbAddAddress;
    @BindView(R.id.iv_address_right)
    ImageView ivAddressRight;
    private List<SanjiInfo> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Codes = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Codes = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private static boolean isLoaded = false;
    private String tx;
    private String provinceId;
    private String cityId;
    private String counntryId;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
//                        Toast.makeText(AddAddressActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
//                    Toast.makeText(AddAddressActivity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
//                    Toast.makeText(AddAddressActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private String type;
    private String id;
    private String receiver;
    private String phone;
    private String provinceName;
    private String cityName;
    private String countryName;
    private String addressStatus;
    private String addressDetail;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        initJsonData();
        logiToolBar.setNavigationOnClickListener(v -> finish());
        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        receiver = getIntent().getStringExtra("receiver");
        phone = getIntent().getStringExtra("phone");
        provinceId = getIntent().getStringExtra("provinceId");
        provinceName = getIntent().getStringExtra("provinceName");
        cityId = getIntent().getStringExtra("cityId");
        cityName = getIntent().getStringExtra("cityName");
        counntryId = getIntent().getStringExtra("counntryId");
        countryName = getIntent().getStringExtra("countryName");
        addressStatus = getIntent().getStringExtra("addressStatus");
        addressDetail = getIntent().getStringExtra("addressDetail");
        if (TextUtils.isEmpty(provinceName) || TextUtils.isEmpty(cityName) || TextUtils.isEmpty(countryName)) {
            tvAddAddressSelect.setText("请选择");
        } else {
            tvAddAddressSelect.setText(provinceName + cityName + countryName);
        }
        etAddressDetail.setText(addressDetail);
        etAddressName.setText(receiver);
        etAddressPhone.setText(phone);

        if ("NO_DEFAULT".equals(addressStatus)) {
            cbAddAddress.setChecked(false);
        } else if ("DEFAULT".equals(addressStatus)) {
            cbAddAddress.setChecked(true);
        }


        cbAddAddress.setOnCheckedChangeListener((button, isCheck) -> {
            if (isCheck) {
                addressStatus = "DEFAULT";
            } else {
                addressStatus = "NO_DEFAULT";
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initToolBar() {

    }


    public ArrayList<SanjiInfo> parseData(String result) {//Gson 解析
        ArrayList<SanjiInfo> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                SanjiInfo entity = gson.fromJson(data.optJSONObject(i).toString(), SanjiInfo.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }


    @OnClick({R.id.tv_save_address, R.id.ll_add_address_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save_address:
                if ("请选择".equals(tvAddAddressSelect.getText().toString().trim())) {
                    ToastUtil.show(AddAddressActivity.this, "请选择省市区！");
                    return;
                } else if ("详细地址".equals(etAddressDetail.getText().toString().trim()) || TextUtils.isEmpty(etAddressDetail.getText().toString().trim())) {
                    ToastUtil.show(AddAddressActivity.this, "请输入详细地址！");
                    return;
                } else if ("收货人姓名".equals(etAddressName.getText().toString().trim()) || TextUtils.isEmpty(etAddressName.getText().toString().trim())) {
                    ToastUtil.show(AddAddressActivity.this, "请输入收货人姓名！");
                    return;
                } else if ("联系方式".equals(etAddressPhone.getText().toString().trim()) || TextUtils.isEmpty(etAddressPhone.getText().toString().trim())) {
                    ToastUtil.show(AddAddressActivity.this, "请输入联系方式！");
                    return;
                } else if (!Validator.isMobile(etAddressPhone.getText().toString().trim())) {
                    ToastUtil.show(AddAddressActivity.this, "手机格式不正确！");
                    return;
                } else {
                    if ("0".equals(type)) {
                        getData();
                    } else if ("1".equals(type)) {
                        getEdit();
                    }
                }
                break;
            case R.id.ll_add_address_select:
                if (isLoaded) {
                    showPickerView();
                } else {
                    Toast.makeText(AddAddressActivity.this, "请点击重试", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void getEdit() {
        receiver = etAddressName.getText().toString().trim();
        phone = etAddressPhone.getText().toString().trim();
        addressDetail = etAddressDetail.getText().toString();
        AddAreaPost loginPost = new AddAreaPost();
        AddAreaPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setId(id);
        postInfoBean.setReceiver(receiver);
        postInfoBean.setPhone(phone);
        postInfoBean.setProvinceId(provinceId);
        postInfoBean.setProvinceName(provinceName);
        postInfoBean.setCityId(cityId);
        postInfoBean.setCityName(cityName);
        postInfoBean.setCounntryId(counntryId);
        postInfoBean.setCountryName(countryName);
        postInfoBean.setAddress(addressDetail);
        postInfoBean.setAddressStatus(addressStatus);


        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .changArea(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(AddAddressActivity.this, "修改地址成功！");
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            ToastUtil.show(AddAddressActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }

    private void getData() {
        receiver = etAddressName.getText().toString().trim();
        phone = etAddressPhone.getText().toString().trim();
        addressDetail = etAddressDetail.getText().toString();
        AddAreaPost loginPost = new AddAreaPost();
        AddAreaPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setReceiver(receiver);
        postInfoBean.setPhone(phone);
        postInfoBean.setProvinceId(provinceId);
        postInfoBean.setProvinceName(provinceName);
        postInfoBean.setCityId(cityId);
        postInfoBean.setCityName(cityName);
        postInfoBean.setCounntryId(counntryId);
        postInfoBean.setCountryName(countryName);
        postInfoBean.setAddress(addressDetail);
        postInfoBean.setAddressStatus(addressStatus);


        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .addArea(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(AddAddressActivity.this, "添加成功！");
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            ToastUtil.show(AddAddressActivity.this, baseEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }


    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";
                provinceName = opt1tx;
                provinceId = options1Items.get(options1).getValue();

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";
                cityName = opt2tx;
                cityId = options2Codes.get(options1).get(options2);

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";
                countryName = opt3tx;
                counntryId = options3Codes.get(options1).get(options2).get(options3);


                if (opt1tx.equals(opt2tx)) {
                    tx = opt1tx + "-" + opt3tx;
                } else {
                    tx = opt1tx + "-" + opt2tx + "-" + opt3tx;
                }
                tvAddAddressSelect.setText(tx);
                L.i("AAAA", provinceId + "," + cityId + "," + counntryId);
                ivAddressRight.setVisibility(View.GONE);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "area.json");//获取assets目录下的json文件数据

        ArrayList<SanjiInfo> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<String> cityListCode = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            ArrayList<ArrayList<String>> province_AreaListCodes = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getChildren().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getChildren().get(c).getName();
                String cityCode = jsonBean.get(i).getChildren().get(c).getValue();
                cityList.add(cityName);//添加城市
                cityListCode.add(cityCode);//添加城市
                ArrayList<SanjiInfo.ChildrenBeanX.ChildrenBean> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getChildren().get(c).getChildren());
                ArrayList<String> newdatas = new ArrayList<>();
                ArrayList<String> newdatasCodes = new ArrayList<>();
                for (int x = 0; x < city_AreaList.size(); x++) {
                    newdatas.add(city_AreaList.get(x).getName());
                    newdatasCodes.add(city_AreaList.get(x).getValue());
                }
                province_AreaList.add(newdatas);//添加该省所有地区数据
                province_AreaListCodes.add(newdatasCodes);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);
            options2Codes.add(cityListCode);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
            options3Codes.add(province_AreaListCodes);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

}
