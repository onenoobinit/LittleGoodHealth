package com.youyijia.goodhealth.app.center;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.MicDiscritionInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/10.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {
    private final Context mContext;
    private ArrayList<MicDiscritionInfo.AllBean> datas;

    public VideoListAdapter(Context context, ArrayList<MicDiscritionInfo.AllBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    public void setData(ArrayList<MicDiscritionInfo.AllBean> datas) {
        this.datas = datas;
    }

    public ArrayList<MicDiscritionInfo.AllBean> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_video, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(datas.get(i).getLectureImg())
                .apply(options)
                .into(myViewHolder.iv_video_item);
        myViewHolder.tv_video_title.setText(datas.get(i).getLectureName());
        myViewHolder.tv_video_date.setText(datas.get(i).getLectureDate());
        myViewHolder.rl_miccenter_item.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MicDiscritionActivity.class);
            intent.putExtra("doctorId", datas.get(i).getDoctorId());
            intent.putExtra("id", datas.get(i).getId());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_video_item;
        private final TextView tv_video_title;
        private final TextView tv_video_date;
        private final RelativeLayout rl_miccenter_item;


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_video_item = itemView.findViewById(R.id.iv_video_item);
            tv_video_title = itemView.findViewById(R.id.tv_video_title);
            tv_video_date = itemView.findViewById(R.id.tv_video_date);
            rl_miccenter_item = itemView.findViewById(R.id.rl_miccenter_item);
        }
    }
}
