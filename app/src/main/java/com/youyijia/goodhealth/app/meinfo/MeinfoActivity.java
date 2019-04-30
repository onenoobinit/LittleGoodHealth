package com.youyijia.goodhealth.app.meinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.youyijia.goodhealth.GoodHealthApp;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.company.CompanyActivity;
import com.youyijia.goodhealth.entity.SetUserPost;
import com.youyijia.goodhealth.entity.User;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.goodhealth.widgets.GlideCircleTransform;
import com.youyijia.goodhealth.widgets.dialog.SexSeclecDialog;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;
import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MeinfoActivity extends BaseActivity {

    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.rl_set_icon)
    RelativeLayout rlSetIcon;
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.arl_set_name)
    RelativeLayout arlSetName;
    @BindView(R.id.tv_set_sex)
    TextView tvSetSex;
    @BindView(R.id.arl_set_sex)
    RelativeLayout arlSetSex;
    @BindView(R.id.tv_set_brithday)
    TextView tvSetBrithday;
    @BindView(R.id.arl_set_brithday)
    RelativeLayout arlSetBrithday;
    @BindView(R.id.tv_set_address_company)
    TextView tvSetAddressCompany;
    @BindView(R.id.arl_set_company_address)
    RelativeLayout arlSetCompanyAddress;
    @BindView(R.id.tv_set_company)
    TextView tvSetCompany;
    @BindView(R.id.arl_set_company)
    RelativeLayout arlSetCompany;
    private SexSeclecDialog sexSeclecDialog;
    private String gender;
    private RequestOptions mRequestOptions = new RequestOptions().placeholder(R.mipmap.img_boy_weixuanzhong)
            .error(R.mipmap.img_boy_weixuanzhong)
            .transform(new GlideCircleTransform());
    private RequestBody requestBody;
    private MultipartBody.Part mBody;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        tvSetName.setText(GoodHealthApp.getInstance().getUser().getNickName());
        tvSetSex.setText(GoodHealthApp.getInstance().getUser().getGender().getText());
        tvSetBrithday.setText(GoodHealthApp.getInstance().getUser().getBirthday());
        String companyName = GoodHealthApp.getInstance().getUser().getCompanyName();
        if (companyName == null || TextUtils.isEmpty(companyName)) {
            tvSetCompany.setText("");
        } else {
            tvSetCompany.setText(companyName);
        }

        Glide.with(MeinfoActivity.this)
                .load(GoodHealthApp.getInstance().getUser().getPortrait())
                .apply(new RequestOptions().transform(new GlideCircleTransform()).error(R.mipmap.img_boy_weixuanzhong))
                .into(ivUser);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_meinfo;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(view -> finish());
    }


    @OnClick({R.id.rl_set_icon, R.id.arl_set_name, R.id.arl_set_sex, R.id.arl_set_brithday, R.id.arl_set_company_address, R.id.arl_set_company})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_set_icon://设置头像
                openGallery();
                break;
            case R.id.arl_set_name://设置昵称
                startActivityForResult(new Intent(MeinfoActivity.this, SetNewNameActivity.class), 1118);
                break;
            case R.id.arl_set_sex://设置性别
                if (sexSeclecDialog == null) {
                    sexSeclecDialog = new SexSeclecDialog(MeinfoActivity.this) {
                        @Override
                        public void setSex(String sex) {
                            if ("男".equals(sex)) {
                                gender = "MALE";
                                getsex();
                            } else if ("女".equals(sex)) {
                                gender = "FEMALE";
                                getsex();
                            }
                        }
                    };
                }
                sexSeclecDialog.show();
                break;
            case R.id.arl_set_brithday://设置出生日期
                showTimeDialog(tvSetBrithday);
                break;
            case R.id.arl_set_company_address://设置公司地址
                break;
            case R.id.arl_set_company://设置企业
                if (TextUtils.isEmpty(GoodHealthApp.getInstance().getUser().getCompanyName())) {
                    startActivity(new Intent(MeinfoActivity.this, CompanyActivity.class));
                }
                break;
        }
    }

    /**
     * 打开相册
     */
    private void openGallery() {
        PictureSelector.create(MeinfoActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_QQ_style)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .previewVideo(false)
                .enablePreviewAudio(false)
                .isCamera(true)
                .isZoomAnim(true)
                .enableCrop(true)
                .compress(true)
                .synOrAsy(true)
                .glideOverride(160, 160)
                .withAspectRatio(1, 1)
                .hideBottomControls(true)
                .isGif(false)
                .freeStyleCropEnabled(true)
                .circleDimmedLayer(false)
                .showCropFrame(true)
                .showCropGrid(true)
                .openClickSound(false)
                .isDragFrame(false)
                .minimumCompressSize(100)
                .rotateEnabled(false)
                .scaleEnabled(true)
                //结果回调onActivityResult code
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1118 && resultCode == RESULT_OK) {
            tvSetName.setText(GoodHealthApp.getInstance().getUser().getNickName());
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    final List<LocalMedia> mediaList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (int i = 0; i < mediaList.size(); i++) {
                        final LocalMedia localMedia = mediaList.get(i);
                        String filePath = "";
                        if (localMedia.isCompressed()) {
                            filePath = localMedia.getCompressPath();
                        }
                        Glide.with(this).load(filePath).apply(mRequestOptions).into(ivUser);
                        File file = new File(filePath);
                        requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                        mBody = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
                    }
                    modifyHeadImg();
                    break;
                default:
                    break;
            }
        }
    }

    private void modifyHeadImg() {
        RetrofitManager.getInstance().create(CommonService.class)
                .upLoad(mBody)
                .compose(this.bindToLifecycle())
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 300, 300))
                .compose(RxSchedulers.io_main())
                .compose(this.newWorkAvailable())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            String imgurl = baseEntity.getData();
                            ToastUtil.show(MeinfoActivity.this,"头像上传成功！");
                            getChagePicture(imgurl);
                        } else {
                            ToastUtil.show(MeinfoActivity.this, baseEntity.getMessage());
                        }
                    }

                  /*  @Override
                    protected void onHandleSuccess(ModifyHeadBean s) {
                        if (s.getSuccess() == 1) {
                            String url = s.getUrl();
                            final User user = SPUtil.getObject(MainActivity.this, Constant.USER, User.class);
                            user.setPortrait(url);
                            SPUtil.setObject(MainActivity.this, Constant.USER, user);
                            L.i("imageUpload", user.getToken());
                            ToastUtil.show(MainActivity.this, "修改成功");
                            //清空图片缓存
                            new Handler().postDelayed(() -> PictureFileUtils.deleteCacheDirFile(MainActivity.this), 500);
                        }
                    }*/
                });
    }

    private void getChagePicture(String imgurl) {
        SetUserPost loginPost = new SetUserPost();
        SetUserPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setPortrait(imgurl);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .setUserInfo(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            User user = GoodHealthApp.getInstance().getUser();
                            user.setPortrait(imgurl);
                            GoodHealthApp.getInstance().setUser(user, true);
                        } else {
                            ToastUtil.show(MeinfoActivity.this, baseEntity.getMessage());
                        }
                    }
                });
    }

    private void getsex() {
        SetUserPost loginPost = new SetUserPost();
        SetUserPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setGender(gender);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .setUserInfo(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            if ("MALE".equals(gender)) {
                                User user = GoodHealthApp.getInstance().getUser();
                                user.getGender().setText("男");
                                GoodHealthApp.getInstance().setUser(user, true);
                                tvSetSex.setText("男");
                            } else if ("FEMALE".equals(gender)) {
                                GoodHealthApp.getInstance().getUser().getGender().setText("女");
                                tvSetSex.setText("女");
                            }
                            sexSeclecDialog.dismiss();
                        } else {
                            ToastUtil.show(MeinfoActivity.this, baseEntity.getMessage());
                            sexSeclecDialog.dismiss();
                        }
                    }
                });
    }


    /**
     * 显示时间选择框
     *
     * @param time
     */
    private void showTimeDialog(TextView time) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1920, 1, 1);//设置起始年份
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 1, 1);//设置结束年份
        TimePickerView pvTime = new TimePickerBuilder(MeinfoActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                getchageDate(getTime(date));
            }
        })
                .setRangDate(startDate, endDate)
                .setTitleText("请选择日期")
                .setDate(selectedDate)
                .setTitleColor(ContextCompat.getColor(MeinfoActivity.this, R.color.c_4EB2FF))
                .setCancelColor(ContextCompat.getColor(MeinfoActivity.this, R.color.c_4EB2FF))
                .setSubmitColor(ContextCompat.getColor(MeinfoActivity.this, R.color.c_4EB2FF))
                //默认设置为年月日时分秒
                .setLabel("年", "月", "日", "时", "分", "秒")
                // 默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pvTime.show();
    }

    private void getchageDate(String time) {
        SetUserPost loginPost = new SetUserPost();
        SetUserPost.PostInfoBean postInfoBean = loginPost.new PostInfoBean();
        postInfoBean.setBirthday(time);

        loginPost.setPostInfoBean(postInfoBean);
        String s = gson.toJson(postInfoBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s);
        RetrofitManager.getInstance().create(CommonService.class)
                .setUserInfo(requestBody)
                .throttleFirst(1, TimeUnit.SECONDS)
                .retryWhen(new RetryWhenNetworkException(2, 500, 500))
                .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onHandleSuccess(BaseEntity baseEntity) {
                        if ("200".equals(baseEntity.getCode())) {
                            User user = GoodHealthApp.getInstance().getUser();
                            user.setBirthday(time);
                            GoodHealthApp.getInstance().setUser(user, true);
                            tvSetBrithday.setText(time);
                        } else {
                            ToastUtil.show(MeinfoActivity.this, baseEntity.getMessage());
                            sexSeclecDialog.dismiss();
                        }
                    }
                });
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
