package com.youyijia.goodhealth.app.green;

import android.content.Context;
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
import com.youyijia.goodhealth.entity.GreenServiceInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/15.
 */
public abstract class GreenServiceAdapter extends RecyclerView.Adapter<GreenServiceAdapter.MyViewHolder> {


    private final Context mContext;
    private ArrayList<GreenServiceInfo.CityServicesBean> datas;
    public static int selectPostion = -1;
    private boolean isFrist = true;

    public GreenServiceAdapter(Context context, ArrayList<GreenServiceInfo.CityServicesBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_service, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(datas.get(i).getServiceTypeInformationImg().getUrl())
                .apply(options)
                .into(myViewHolder.iv_service_item);
        myViewHolder.rl_city.setOnClickListener(view -> {
            if (isFrist == true) {
                myViewHolder.iv_service_item.setVisibility(View.VISIBLE);
            } else if (isFrist == false) {
                myViewHolder.iv_service_item.setVisibility(View.GONE);
            }

            isFrist = !isFrist;
        });

        myViewHolder.rl_iv_select.setOnClickListener(v -> {
            selectPostion = i;
            setonClick(selectPostion);
            notifyDataSetChanged();
        });

        if (selectPostion == i) {
            myViewHolder.iv_service.setImageResource(R.mipmap.choseblue);
        } else {
            myViewHolder.iv_service.setImageResource(R.mipmap.close_blue_circle);
        }

        myViewHolder.tv_service_name.setText(datas.get(i).getServiceTypeName());
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_service_name;
        private final ImageView iv_service;
        private final RelativeLayout rl_city;
        private final ImageView iv_service_item;
        private final RelativeLayout rl_iv_select;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_service_name = itemView.findViewById(R.id.tv_service_name);
            iv_service = itemView.findViewById(R.id.iv_service);
            rl_city = itemView.findViewById(R.id.rl_city);
            iv_service_item = itemView.findViewById(R.id.iv_service_item);
            rl_iv_select = itemView.findViewById(R.id.rl_iv_select);
        }
    }

    public abstract void setonClick(int positionj);
}
