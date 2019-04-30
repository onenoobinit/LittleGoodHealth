package com.youyijia.goodhealth.app.register;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.PhoneCheckPost;
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

/**
 * Created by wangqiang on 2019/1/3.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.rl_login_top)
    RelativeLayout rlLoginTop;
    @BindView(R.id.tv_geren)
    TextView tvGeren;
    @BindView(R.id.v_left)
    View vLeft;
    @BindView(R.id.ll_geren)
    LinearLayout llGeren;
    @BindView(R.id.tv_qiye)
    TextView tvQiye;
    @BindView(R.id.v_right)
    View vRight;
    @BindView(R.id.ll_qiye)
    LinearLayout llQiye;
    @BindView(R.id.et_register_number)
    EditText etRegisterNumber;
    @BindView(R.id.ll_register_phone)
    LinearLayout llRegisterPhone;
    @BindView(R.id.cd_name)
    CardView cdName;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.ll_register_code)
    LinearLayout llRegisterCode;
    @BindView(R.id.cd_code)
    CardView cdCode;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.cb_register_pw)
    CheckBox cb_register_pw;
    @BindView(R.id.ll_register_password)
    LinearLayout llRegisterPassword;
    @BindView(R.id.cd_password)
    CardView cdPassword;
    @BindView(R.id.et_register_qy_code)
    EditText etRegisterQyCode;
    @BindView(R.id.ll_register_qy_code)
    LinearLayout llRegisterQyCode;
    @BindView(R.id.cd_qiye_code)
    CardView cdQiyeCode;
    @BindView(R.id.tv_register_sure)
    TextView tvRegisterSure;
    @BindView(R.id.cd_login)
    CardView cdLogin;
    @BindView(R.id.tv_have_user)
    TextView tvHaveUser;
    private CountDownTimer timer;
    private String modile;
    private int type;
    private String authId;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);

        authId = getIntent().getStringExtra("authId");
        cb_register_pw.setOnCheckedChangeListener((button, isCheck) -> {
            if (isCheck) {
                etRegisterPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etRegisterPassword.setSelection(etRegisterPassword.getText().toString().length());
            } else {
                etRegisterPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etRegisterPassword.setSelection(etRegisterPassword.getText().toString().length());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initToolBar() {

    }

    @OnClick({R.id.iv_login_back, R.id.ll_geren, R.id.ll_qiye, R.id.tv_verify, R.id.tv_register_sure, R.id.tv_have_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back://返回
                finish();
                break;
            case R.id.ll_geren://企业
                type = 0;
                tvGeren.setTextColor(Color.parseColor("#5AAEFF"));
                vLeft.setVisibility(View.VISIBLE);
                tvQiye.setTextColor(Color.parseColor("#4A4A4A"));
                vRight.setVisibility(View.INVISIBLE);
                cdQiyeCode.setVisibility(View.VISIBLE);

                etRegisterNumber.setText("");
                etRegisterCode.setText("");
                etRegisterPassword.setText("");
                etRegisterQyCode.setText("");
                break;
            case R.id.ll_qiye://个人:
                type = 1;
                tvGeren.setTextColor(Color.parseColor("#4A4A4A"));
                vLeft.setVisibility(View.INVISIBLE);
                tvQiye.setTextColor(Color.parseColor("#5AAEFF"));
                vRight.setVisibility(View.VISIBLE);
                cdQiyeCode.setVisibility(View.GONE);

                etRegisterNumber.setText("");
                etRegisterCode.setText("");
                etRegisterPassword.setText("");
                break;
            case R.id.tv_verify://发送验证码
                modile = etRegisterNumber.getText().toString().trim();
                if (Validator.isMobile(modile)) {
                    getPhoneCode();//验证手机号是否注册

                    VerifyUtils();//验证计时
                } else {
                    ToastUtil.show(RegisterActivity.this, "手机格式不正确！");
                }
                break;
            case R.id.tv_register_sure://下一步
                modile = etRegisterNumber.getText().toString().trim();
                String code = etRegisterCode.getText().toString().trim();
                String password = etRegisterPassword.getText().toString().trim();
                String qycode = etRegisterQyCode.getText().toString().trim();
                if ("请输入大陆11位手机号码".equals(modile) || TextUtils.isEmpty(modile)) {
                    ToastUtil.show(RegisterActivity.this, "请输入大陆11位手机号码");
                    return;
                } else if (!Validator.isMobile(modile)) {
                    ToastUtil.show(RegisterActivity.this, "手机号格式不正确");
                    return;
                } else if ("请输入验证码".equals(code) || TextUtils.isEmpty(code)) {
                    ToastUtil.show(RegisterActivity.this, "请输入验证码");
                    return;
                } else if (!Validator.isCode(code)) {
                    ToastUtil.show(RegisterActivity.this, "验证码格式不正确");
                    return;
                } else if ("密码6到16位字母或数字".equals(password) || TextUtils.isEmpty(password)) {
                    ToastUtil.show(RegisterActivity.this, "请输入密码");
                    return;
                } else if (!Validator.isPassword(password)) {
                    ToastUtil.show(RegisterActivity.this, "密码格式不正确");
                    return;
                } else if (type == 0) {
                    if ("请输入所在企业发放的服务卡卡号".equals(qycode) || TextUtils.isEmpty(qycode)) {
                        ToastUtil.show(RegisterActivity.this, "请输入所在企业发放的服务卡卡号");
                        return;
                    } else if (!Validator.isQYCode(qycode)) {
                        ToastUtil.show(RegisterActivity.this, "企业发放的服务卡卡号格式不正确");
                        return;
                    } else {
                        getcheck();
                    }
                } else if (type == 1) {
                    getcheck();
                }
                break;
            case R.id.tv_have_user://已有账号
                finish();
                break;
            default:
                break;
        }
    }

    private void getcheck() {
        PhoneCheckPost loginPost = new PhoneCheckPost();
        PhoneCheckPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setPhoneNumber(etRegisterNumber.getText().toString().trim());
        postInfoBean.setAuthCode(etRegisterCode.getText().toString().trim());

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .getPhoneCheck(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            Intent intent = new Intent(RegisterActivity.this, SelectSexDateActivity.class);
                            intent.putExtra("phone", etRegisterNumber.getText().toString().trim());
                            intent.putExtra("code", etRegisterCode.getText().toString().trim());
                            intent.putExtra("password", etRegisterPassword.getText().toString().trim());
                            intent.putExtra("qycode", etRegisterQyCode.getText().toString().trim());
                            intent.putExtra("type", type);
                            intent.putExtra("authId", authId);
//                            startActivityForResult(intent, 1117);
                            startActivity(intent);
                        } else {
                            ToastUtil.show(RegisterActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1117 && resultCode == RESULT_OK) {
            finish();
        }
    }*/

    private void getPhoneCode() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getPhoneCode(modile)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            ToastUtil.show(RegisterActivity.this, "验证码已发送");
                        } else if ("2101".equals(baseEntity.getCode())) {
                            ToastUtil.show(RegisterActivity.this, "距离上次发送短信不到1分钟");
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();
                    }
                });
    }

    private void showPromptDialog(String s) {
        new AlertDialog.Builder(this)
                .setMessage(s)
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    if ("注册成功！".equals(s)) {
                        finish();
                    }
                }).show();
    }

    private void VerifyUtils() {
        timer = new CountDownTimer(60000, 1000) {

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                tvVerify.setClickable(false); //设置不可点击
                tvVerify.setText("重新发送(" + l / 1000 + ")");  //设置倒计时时间
                tvVerify.setBackgroundResource(R.drawable.bg_code_gray);
            }

            @Override
            public void onFinish() {
                tvVerify.setText("重新发送");
                tvVerify.setClickable(true);//重新获得点击
                tvVerify.setBackgroundResource(R.drawable.tv_code_blue);
            }
        }.start();
    }
}
