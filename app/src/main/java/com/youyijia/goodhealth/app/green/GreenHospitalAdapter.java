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

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.GreenHospitalInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/15.
 */
public abstract class GreenHospitalAdapter extends RecyclerView.Adapter<GreenHospitalAdapter.MyViewHolder> {


    private final Context mContext;
    private ArrayList<GreenHospitalInfo> datas;
    public static int selectPostion = -1;

    public GreenHospitalAdapter(Context context, ArrayList<GreenHospitalInfo> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_city, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.rl_city.setOnClickListener(view -> {
            selectPostion = i;
            setonClick(selectPostion);
            notifyDataSetChanged();
        });

        if (selectPostion == i) {
            myViewHolder.iv_city.setImageResource(R.mipmap.choseblue);
        } else {
            myViewHolder.iv_city.setImageResource(R.mipmap.close_blue_circle);
        }

        myViewHolder.tv_city_name.setText(datas.get(i).getHospitalName());
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_city_name;
        private final ImageView iv_city;
        private final RelativeLayout rl_city;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_city_name = itemView.findViewById(R.id.tv_city_name);
            iv_city = itemView.findViewById(R.id.iv_city);
            rl_city = itemView.findViewById(R.id.rl_city);
        }
    }

    public abstract void setonClick(int position);
}
