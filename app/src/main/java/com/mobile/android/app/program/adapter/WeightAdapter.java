package com.mobile.android.app.program.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<String> datas;
    private final String active;
    private int selectPostion = 0;


    public WeightAdapter(Context context, List<String> weights, String active) {
        this.mContext = context;
        this.datas = weights;
        this.active = active;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_weight, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position == 0) {
            holder.tv_weight_kg.setText("100kg");
        } else if (position == 1) {
            holder.tv_weight_kg.setText("300kg");
        } else if (position == 2) {
            holder.tv_weight_kg.setText("500kg");
        } else if (position == 3) {
            holder.tv_weight_kg.setText("1000kg");
        } else if (position == 4) {
            holder.tv_weight_kg.setText("3000kg");
        } else if (position == 5) {
            holder.tv_weight_kg.setText("5000kg");
        }
        if ("0.00".equals(datas.get(position))) {
            holder.tv_weight_shu.setText("无报价");
        } else {
            holder.tv_weight_shu.setText(datas.get(position));
        }

        if ("100".equals(active)) {
            selectPostion = 0;
        } else if ("300".equals(active)) {
            selectPostion = 1;
        } else if ("500".equals(active)) {
            selectPostion = 2;
        } else if ("1000".equals(active)) {
            selectPostion = 3;
        } else if ("3000".equals(active)) {
            selectPostion = 4;
        } else if ("5000".equals(active)) {
            selectPostion = 5;
        }


        if (selectPostion == position) {
            holder.all_item_weight.setBackgroundResource(R.drawable.bg_item_weight);
            holder.tv_weight_kg.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
            holder.tv_weight_shu.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        } else {
            holder.all_item_weight.setBackgroundResource(R.drawable.bg_item_weight_no);
            holder.tv_weight_kg.setTextSize(TypedValue.COMPLEX_UNIT_PX, 35);
            holder.tv_weight_shu.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final AutoLinearLayout all_item_weight;
        private final TextView tv_weight_kg;
        private final TextView tv_weight_shu;


        public MyViewHolder(View itemView) {
            super(itemView);
            all_item_weight = itemView.findViewById(R.id.all_item_weight);
            tv_weight_kg = itemView.findViewById(R.id.tv_weight_kg);
            tv_weight_shu = itemView.findViewById(R.id.tv_weight_shu);
        }
    }
}
