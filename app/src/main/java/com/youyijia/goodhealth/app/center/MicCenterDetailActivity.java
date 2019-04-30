package com.youyijia.goodhealth.app.center;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.DoctorInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.dialog.MicTishiDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MicCenterDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_miccenter_item)
    CircleImageView ivMiccenterItem;
    @BindView(R.id.tv_miccenter_name)
    TextView tvMiccenterName;
    @BindView(R.id.tv_miccenter_marjor)
    TextView tvMiccenterMarjor;
    @BindView(R.id.rl_miccenter_item)
    RelativeLayout rlMiccenterItem;
    @BindView(R.id.ll_zxzz)
    LinearLayout llZxzz;
    @BindView(R.id.ll_twzz)
    LinearLayout llTwzz;
    @BindView(R.id.ll_zxgt)
    LinearLayout llZxgt;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_zxjt)
    ImageView ivZxjt;
    @BindView(R.id.iv_twzx)
    ImageView ivTwzx;
    @BindView(R.id.iv_zxgt)
    ImageView ivZxgt;
    private String id;
    private DoctorInfo doctorInfo;
    private String name;
    private MicTishiDialog micTishiDialog;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        tvTitle.setText(name + "医生");
        getDetail();
    }

    private void getDetail() {
        RetrofitManager.getInstance().create(CommonService.class)
                .getDoctor(id)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if (TextUtils.isEmpty(baseEntity.getData())) {
//                            ToastUtil.show(mContext, baseEntity.getData());
                            return;
                        } else {
                            doctorInfo = gson.fromJson(baseEntity.getData(), DoctorInfo.class);
                            initData();
                        }
                    }

                    @Override
                    protected void onFinally() {
                        super.onFinally();

                    }
                });
    }

    private void initData() {
        DoctorInfo.LectureStatusBean lectureStatus = doctorInfo.getLectureStatus();
        if (lectureStatus != null && "在线咨询进行中".equals(lectureStatus.getText())) {
            ivZxjt.setImageResource(R.mipmap.ic_doctor_zaixianjiangzuo);
        } else {
            ivZxjt.setImageResource(R.mipmap.ic_doctor_zaixianjiangzuo1);
        }

        tvMiccenterName.setText(doctorInfo.getDoctorName());
        tvMiccenterMarjor.setText(doctorInfo.getDoctorTitle());

        Glide.with(this)
                .load(doctorInfo.getDoctorHeadimg())
                .into(ivMiccenterItem);
        tvContent.setText(Html.fromHtml(doctorInfo.getDoctorIntroductionBr()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mic_center_detail;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }


    @OnClick({R.id.ll_zxzz, R.id.ll_twzz, R.id.ll_zxgt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_zxzz://在线讲堂
                Intent intent = new Intent(MicCenterDetailActivity.this, MicDiscritionActivity.class);
                intent.putExtra("doctorId", doctorInfo.getId());
                intent.putExtra("id", "");
                startActivity(intent);
                break;
            case R.id.ll_twzz://图文资讯
                if (micTishiDialog == null) {
                    micTishiDialog = new MicTishiDialog(MicCenterDetailActivity.this,"暂不开发");
                }
                micTishiDialog.show();
                break;
            case R.id.ll_zxgt://在线沟通
                if (micTishiDialog == null) {
                    micTishiDialog = new MicTishiDialog(MicCenterDetailActivity.this,"暂不开发");
                }
                micTishiDialog.show();
                break;
        }
    }

}
