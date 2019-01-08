package com.mobile.android.app.program.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.utils.DateUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class DetailDateAdapter extends RecyclerView.Adapter<DetailDateAdapter.MyViewHolder> {
    private final Context mContext;
    private ArrayList<String> datas;
    private int selectPostion = 0;

    public DetailDateAdapter(Context context, ArrayList<String> data) {
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
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_date_detail, parent, false));
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
        });

        if (selectPostion == position) {
            holder.all_bg_item.setBackgroundResource(R.drawable.bg_item_date);
        } else {
            holder.all_bg_item.setBackgroundResource(R.drawable.bg_item_date_no);
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
        private final AutoLinearLayout all_bg_item;


        public MyViewHolder(View itemView) {
            super(itemView);
            all_item_date = itemView.findViewById(R.id.all_item_date);
            tv_item_date = itemView.findViewById(R.id.tv_item_date);
            tv_item_week = itemView.findViewById(R.id.tv_item_week);
            all_bg_item = itemView.findViewById(R.id.all_bg_item);

        }
    }
}
