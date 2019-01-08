package com.mobile.android.app.program.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/8.
 */
public class ProportionAdapter extends RecyclerView.Adapter<ProportionAdapter.MyViewHolder> {
    private final Context mContext;
    private ArrayList<String> datas;
    private int selectPostion = 0;

    public ProportionAdapter(Context context, ArrayList<String> data) {
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
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_proportion, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_item_proportion.setOnClickListener(view -> {
            selectPostion = position;
            notifyDataSetChanged();
        });

        if (selectPostion == position) {
            holder.tv_item_proportion.setBackgroundResource(R.drawable.bg_item_proportion);
            holder.tv_item_proportion.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.tv_item_proportion.setBackgroundResource(R.drawable.bg_item_proportion_no);
            holder.tv_item_proportion.setTextColor(Color.parseColor("#F5A623"));
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_item_proportion;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_proportion = itemView.findViewById(R.id.tv_item_proportion);
        }
    }
}
