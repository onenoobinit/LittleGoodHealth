package com.youyijia.goodhealth.app.program.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.utils.DateUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/7.
 */
public abstract class DateAdapter extends RecyclerView.Adapter<DateAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<String> datas;
    private int selectPostion = 0;

    public DateAdapter(Context context, ArrayList<String> data) {
        this.mContext = context;
        this.datas = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_date, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String week = DateUtils.getWeek(datas.get(position));
        String substring = datas.get(position).substring(5);
        holder.tv_item_date.setText(substring);
        holder.tv_item_week.setText(week);
        holder.all_item_date.setOnClickListener(view -> {
            selectPostion = position;
            notifyDataSetChanged();
            setOnItemDate(datas.get(position));
        });

        if (selectPostion == position) {
            holder.all_item_date.setBackgroundColor(Color.parseColor("#00A7F7"));
            holder.tv_item_date.setTextColor(Color.parseColor("#ffffff"));
            holder.tv_item_week.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.all_item_date.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv_item_date.setTextColor(Color.parseColor("#4A4A4A"));
            holder.tv_item_week.setTextColor(Color.parseColor("#4A4A4A"));
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final AutoLinearLayout all_item_date;
        private final TextView tv_item_date;
        private final TextView tv_item_week;


        public MyViewHolder(View itemView) {
            super(itemView);
            all_item_date = itemView.findViewById(R.id.all_item_date);
            tv_item_date = itemView.findViewById(R.id.tv_item_date);
            tv_item_week = itemView.findViewById(R.id.tv_item_week);

        }
    }

    public abstract void setOnItemDate(String date);
}
