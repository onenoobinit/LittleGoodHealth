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

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<String> datas;
    private int selectPostion = 0;

    public WeightAdapter(Context context, ArrayList<String> data) {
        this.mContext = context;
        this.datas = data;
    }

    public void setData(ArrayList<String> data) {
        this.datas = data;
    }

    public ArrayList<String> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_weight, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.all_item_weight.setOnClickListener(view -> {
            selectPostion = position;
            notifyDataSetChanged();
        });

        if (selectPostion == position) {
            holder.all_item_weight.setBackgroundResource(R.drawable.bg_item_weight);
            holder.tv_weight_kg.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
            holder.tv_weight_shu.setTextSize(TypedValue.COMPLEX_UNIT_PX, 52);
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
