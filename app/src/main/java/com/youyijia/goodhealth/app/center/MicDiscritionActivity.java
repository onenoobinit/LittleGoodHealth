package com.youyijia.goodhealth.app.center;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.Constants;
import com.youyijia.goodhealth.MainActivity;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.web.CommonWebActivity;
import com.youyijia.goodhealth.entity.MicDiscritionInfo;
import com.youyijia.goodhealth.retrofit.RetrofitManager;
import com.youyijia.goodhealth.retrofit.RetryWhenNetworkException;
import com.youyijia.goodhealth.retrofit.RxSchedulers;
import com.youyijia.goodhealth.retrofit.api.CommonService;
import com.youyijia.hyoukalibrary.base.BaseActivity;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.base.BaseObserver;
import com.youyijia.hyoukalibrary.utils.SPUtil;
import com.youyijia.hyoukalibrary.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MicDiscritionActivity extends BaseActivity {

    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.logi_tool_bar)
    Toolbar logiToolBar;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_his_more)
    TextView tvHisMore;
    @BindView(R.id.rl_his_video)
    RelativeLayout rlHisVideo;
    @BindView(R.id.rv_his_video)
    RecyclerView rvHisVideo;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rl_video)
    RelativeLayout rlVideo;
    @BindView(R.id.rv_video)
    RecyclerView rvVideo;
    @BindView(R.id.tv_tearcher)
    TextView tvTearcher;
    @BindView(R.id.tv_teacher_dis)
    TextView tvTeacherDis;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_go_jt)
    TextView tvGoJt;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    private ArrayList<MicDiscritionInfo.ListBean> hisDatas = new ArrayList<>();
    private ArrayList<MicDiscritionInfo.AllBean> videoDatas = new ArrayList<>();
    private HisVideoAdapter hisVideoAdapter;
    private VideoListAdapter videoListAdapter;
    private String doctorId;
    private String id;
    private MicDiscritionInfo micDiscritionInfo;


    @Override

    protected void initViews(Bundle savedInstanceState) {
        StatusBarCompat.setTranslucentForImageView(this, 0, null);
        ButterKnife.bind(this);
        tvGoJt.setBackgroundColor(Color.parseColor("#4EB2FF"));
        doctorId = getIntent().getStringExtra("doctorId");
        id = getIntent().getStringExtra("id");


        LinearLayoutManager manager = new LinearLayoutManager(MicDiscritionActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvHisVideo.setLayoutManager(manager);
        hisVideoAdapter = new HisVideoAdapter(MicDiscritionActivity.this, hisDatas);
        rvHisVideo.setAdapter(hisVideoAdapter);

        LinearLayoutManager managervideo = new LinearLayoutManager(MicDiscritionActivity.this);
        managervideo.setOrientation(LinearLayoutManager.VERTICAL);
        rvVideo.setLayoutManager(managervideo);
        videoListAdapter = new VideoListAdapter(MicDiscritionActivity.this, videoDatas);
        rvVideo.setAdapter(videoListAdapter);

        getData();
    }

    private void getData() {
        params.clear();
        params.put("doctorId", doctorId);
        if (!TextUtils.isEmpty(id)) {
            params.put("id", id);
        }
        RetrofitManager.getInstance().create(CommonService.class)
                .getDetailVideo(params)
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
                            micDiscritionInfo = gson.fromJson(baseEntity.getData(), MicDiscritionInfo.class);
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
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(MicDiscritionActivity.this)
                .load(micDiscritionInfo.getDetail().getLectureImg())
                .apply(options)
                .into(ivTop);
        tvTopTitle.setText(micDiscritionInfo.getDetail().getLectureName());
        tvTitle.setText(micDiscritionInfo.getDetail().getLectureName());
        tvDate.setText("时间：" + micDiscritionInfo.getDetail().getLectureDate());
        tvTearcher.setText("主讲人：" + micDiscritionInfo.getDetail().getLectureSpeaker());

        String left1 = "讲师简介：" + Html.fromHtml(micDiscritionInfo.getDetail().getLectureDoctorIntroduction());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(left1);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#4A4A4A"));
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTeacherDis.setText(spannableStringBuilder);

        String left2 = "直播概述：" + Html.fromHtml(micDiscritionInfo.getDetail().getLectureIntroduction());
        SpannableStringBuilder builder = new SpannableStringBuilder(left2);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#4A4A4A"));
        builder.setSpan(colorSpan, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvContent.setText(builder);

        List<MicDiscritionInfo.AllBean> all = micDiscritionInfo.getAll();
        List<MicDiscritionInfo.ListBean> list = micDiscritionInfo.getList();
        videoDatas.addAll(all);
        hisDatas.addAll(list);
        refreshData(hisDatas, false);
        refreshData1(videoDatas, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mic_discrition;
    }

    @Override
    public void initToolBar() {
        logiToolBar.setNavigationOnClickListener(v -> finish());
    }


    @OnClick({R.id.iv_center, R.id.tv_his_more, R.id.tv_more, R.id.tv_go_jt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_center://右侧图标
                startActivity(new Intent(MicDiscritionActivity.this, MainActivity.class));
                break;
            case R.id.tv_his_more://他的更多
                Intent intent = new Intent(MicDiscritionActivity.this, HisVideoActivity.class);
                intent.putExtra("doctorId", micDiscritionInfo.getDetail().getDoctorId());
                intent.putExtra("id", micDiscritionInfo.getDetail().getId());
                startActivity(intent);
                break;
            case R.id.tv_more://更多
                startActivity(new Intent(MicDiscritionActivity.this, VideoListActivity.class));
                break;
            case R.id.tv_go_jt:
                String token = (String) SPUtil.get(MicDiscritionActivity.this, "TOKEN", String.class);
                String url = Constants.URL_DETAIL_VIDEO + "roomId=" + micDiscritionInfo.getDetail().getRoomid() + "&token=" + token;
                Intent intent1 = new Intent(MicDiscritionActivity.this, CommonWebActivity.class);
                intent1.putExtra("url", url);
                intent1.putExtra("title", micDiscritionInfo.getDetail().getLectureName());
                startWebActivity(url, intent1);
                break;
            default:
                break;
        }
    }

    private void refreshData(ArrayList<MicDiscritionInfo.ListBean> datas, boolean b) {
        int size = hisVideoAdapter.getData().size();
        if (!b) {
            hisVideoAdapter.notifyItemRangeRemoved(0, hisVideoAdapter.getData().size());
        }
        hisVideoAdapter.setData(datas);
        if (b) {
            rvHisVideo.getAdapter().notifyItemInserted(size);
        } else {
            rvHisVideo.getAdapter().notifyDataSetChanged();
        }
    }

    private void refreshData1(ArrayList<MicDiscritionInfo.AllBean> datas, boolean b) {
        int size = videoListAdapter.getData().size();
        if (!b) {
            videoListAdapter.notifyItemRangeRemoved(0, videoListAdapter.getData().size());
        }
        videoListAdapter.setData(datas);
        if (b) {
            rvVideo.getAdapter().notifyItemInserted(size);
        } else {
            rvVideo.getAdapter().notifyDataSetChanged();
        }
    }

}
