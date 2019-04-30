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
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.MicCenterInfo;
import com.youyijia.goodhealth.widgets.GlideCircleTransform;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/9.
 */
public class MicCenterAdapter extends RecyclerView.Adapter<MicCenterAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<MicCenterInfo.DoctorBean> datas;

    public MicCenterAdapter(Context context, ArrayList<MicCenterInfo.DoctorBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    public void setData(ArrayList<MicCenterInfo.DoctorBean> datas) {
        this.datas = datas;
    }

    public ArrayList<MicCenterInfo.DoctorBean> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_mic_center, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (i == 0) {
            myViewHolder.rl_miccenter_item.setBackgroundResource(R.drawable.ll_frist);
        } else {
            myViewHolder.rl_miccenter_item.setBackgroundResource(R.drawable.ll_click);
        }


        MicCenterInfo.DoctorBean.LectureStatusBean lectureStatus = datas.get(i).getLectureStatus();
        if (lectureStatus != null && "在线咨询进行中".equals(lectureStatus.getText())) {
            myViewHolder.tv_miccenter_zx.setVisibility(View.VISIBLE);
        }else {
            myViewHolder.tv_miccenter_zx.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(datas.get(i).getDoctorHeadimg())
                .apply(new RequestOptions().transform(new GlideCircleTransform()))
                .into(myViewHolder.iv_miccenter_item);
        myViewHolder.tv_miccenter_name.setText(datas.get(i).getDoctorName());
        myViewHolder.tv_miccenter_marjor.setText(datas.get(i).getDoctorTitle());
        myViewHolder.tv_miccenter_content.setText(datas.get(i).getDoctorIntroduction());


        myViewHolder.rl_miccenter_item.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MicCenterDetailActivity.class);
            intent.putExtra("id", datas.get(i).getId());
            System.out.println("SSSS" + datas.get(i).getId());
            intent.putExtra("name", datas.get(i).getDoctorName());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_miccenter_item;
        private final RelativeLayout rl_miccenter_item;
        private final TextView tv_miccenter_name;
        private final TextView tv_miccenter_marjor;
        private final TextView tv_miccenter_content;
        private final ImageView tv_miccenter_zx;


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_miccenter_item = itemView.findViewById(R.id.iv_miccenter_item);
            rl_miccenter_item = itemView.findViewById(R.id.rl_miccenter_item);
            tv_miccenter_name = itemView.findViewById(R.id.tv_miccenter_name);
            tv_miccenter_marjor = itemView.findViewById(R.id.tv_miccenter_marjor);
            tv_miccenter_content = itemView.findViewById(R.id.tv_miccenter_content);
            tv_miccenter_zx = itemView.findViewById(R.id.tv_miccenter_zx);
        }
    }
}
