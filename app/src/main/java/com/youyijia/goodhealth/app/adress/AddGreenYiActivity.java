package com.youyijia.goodhealth.app.adress;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.AddjiuyiPost;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;
import com.youyijia.hyoukalibrary.utils.Validator;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddGreenYiActivity extends BaseActivity {
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rb_yg)
    RadioButton rbYg;
    @BindView(R.id.rb_ygjs)
    RadioButton rbYgjs;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.tv_add_green_name)
    TextView tvAddGreenName;
    @BindView(R.id.et_add_green_name)
    EditText etAddGreenName;
    @BindView(R.id.tv_add_green_phone)
    TextView tvAddGreenPhone;
    @BindView(R.id.et_add_green_phone)
    EditText etAddGreenPhone;
    @BindView(R.id.rb_shenfen)
    RadioButton rbShenfen;
    @BindView(R.id.rb_junren)
    RadioButton rbJunren;
    @BindView(R.id.rb_huzhao)
    RadioButton rbHuzhao;
    @BindView(R.id.rg_zhengjian)
    RadioGroup rgZhengjian;
    @BindView(R.id.tv_add_green_id)
    TextView tvAddGreenId;
    @BindView(R.id.et_add_green_id)
    EditText etAddGreenId;
    @BindView(R.id.tv_green_save)
    TextView tvGreenSave;
    private int checkedRadioButtonId;
    private int checkedRadioButtonId1;
    private String relation;
    private String idCardType;
    private String saveType;
    private String id;
    private String name;
    private String telephone;
    private String jiuyiId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        tvGreenSave.setBackgroundColor(Color.parseColor("#4EB2FF"));
        String type = getIntent().getStringExtra("type");
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        String cardType = getIntent().getStringExtra("cardType");
        String id = getIntent().getStringExtra("id");
        jiuyiId = getIntent().getStringExtra("jiuyiId");
        saveType = getIntent().getStringExtra("saveType");
        etAddGreenName.setText(name);
        etAddGreenPhone.setText(phone);
        etAddGreenId.setText(id);
        if ("员工".equals(type)) {
            rbYg.setChecked(true);
        } else if ("员工家属".equals(type)) {
            rbYgjs.setChecked(true);
        }

        if ("身份证".equals(cardType)) {
            rbShenfen.setChecked(true);
        } else if ("军官证".equals(cardType)) {
            rbJunren.setChecked(true);
        } else if ("护照".equals(cardType)) {
            rbHuzhao.setChecked(true);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_green_yi;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }

    @OnClick(R.id.tv_green_save)
    public void onClick() {
        checkedRadioButtonId = rgType.getCheckedRadioButtonId();
        checkedRadioButtonId1 = rgZhengjian.getCheckedRadioButtonId();
        name = etAddGreenName.getText().toString().trim();
        if (checkedRadioButtonId == rbYg.getId()) {
            relation = "STAFF";
        } else if (checkedRadioButtonId == rbYgjs.getId()) {
            relation = "FAMILY";
        }
        telephone = etAddGreenPhone.getText().toString().trim();
        if (checkedRadioButtonId1 == rbShenfen.getId()) {
            idCardType = "IDCARD";
        } else if (checkedRadioButtonId1 == rbJunren.getId()) {
            idCardType = "CERTIFICATE_OF_OFFICERS";
        } else if (checkedRadioButtonId1 == rbHuzhao.getId()) {
            idCardType = "PASSPORT";
        }
        id = etAddGreenId.getText().toString().trim();
        if (checkedRadioButtonId != rbYg.getId() && checkedRadioButtonId != rbYgjs.getId()) {
            ToastUtil.show(AddGreenYiActivity.this, "请选择就医人类型！");
            return;
        } else if ("请输入您的姓名".equals(etAddGreenName.getText().toString().trim()) || TextUtils.isEmpty(etAddGreenName.getText().toString().trim())) {
            ToastUtil.show(AddGreenYiActivity.this, "请输入您的姓名！");
            return;
        } else if ("请输入您的手机号".equals(etAddGreenPhone.getText().toString().trim())) {
            ToastUtil.show(AddGreenYiActivity.this, "请输入您的手机号！");
            return;
        } else if (checkedRadioButtonId1 != rbShenfen.getId() && checkedRadioButtonId1 != rbJunren.getId() && checkedRadioButtonId1 != rbHuzhao.getId()) {
            ToastUtil.show(AddGreenYiActivity.this, "请选择证件类型！");
            return;
        } else if ("请输入您的证件号码".equals(etAddGreenId.getText().toString().trim()) || TextUtils.isEmpty(etAddGreenId.getText().toString().trim())) {
            ToastUtil.show(AddGreenYiActivity.this, "请输入您的证件号码！");
        } else if (checkedRadioButtonId1 == rbShenfen.getId() && !Validator.isIDCard(etAddGreenId.getText().toString().trim())) {
            ToastUtil.show(AddGreenYiActivity.this, "身份证格式不正确！");
        } else if (checkedRadioButtonId1 == rbJunren.getId() && !Validator.isJunGuan(etAddGreenId.getText().toString().trim())) {
            ToastUtil.show(AddGreenYiActivity.this, "军官证格式不正确！");
        } else if (checkedRadioButtonId1 == rbHuzhao.getId() && !Validator.isHuZhao(etAddGreenId.getText().toString().trim())) {
            ToastUtil.show(AddGreenYiActivity.this, "护照格式不正确！");
        } else if ("0".equals(saveType)) {
            getData();
        } else if ("1".equals(saveType)) {
            getsaveData();
        }
    }

    private void getsaveData() {
        AddjiuyiPost loginPost = new AddjiuyiPost();
        AddjiuyiPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setId(jiuyiId);
        postInfoBean.setName(name);
        postInfoBean.setRelation(relation);
        postInfoBean.setTelephone(telephone);
        postInfoBean.setIdCardType(idCardType);
        postInfoBean.setIdCardNo(id);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getChangejiuyi(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            setResult(RESULT_OK);
                            finish();
                        } else {

                        }
                    }
                });
    }

    private void getData() {

        AddjiuyiPost loginPost = new AddjiuyiPost();
        AddjiuyiPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setName(name);
        postInfoBean.setRelation(relation);
        postInfoBean.setTelephone(telephone);
        postInfoBean.setIdCardType(idCardType);
        postInfoBean.setIdCardNo(id);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getAddjiuyi(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            setResult(RESULT_OK);
                            finish();
                        } else {

                        }
                    }
                });
    }
}
