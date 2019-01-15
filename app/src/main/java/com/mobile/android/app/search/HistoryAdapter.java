package com.mobile.android.app.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by wangqiang on 2019/1/14.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private final Context mContext;
    private List<String> datas;


    public HistoryAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.datas = data;
    }

    public void setData(List<String> data) {
        this.datas = data;
    }

    public List<String> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Collections.reverse(datas);
        holder.tv_his_item.setText(datas.get(position));
        holder.tv_his_item.setOnClickListener(view -> {
        });

    }

    @Override
    public int getItemCount() {
        if (datas.size() > 5) {
            return 5;
        } else {
            return datas != null ? datas.size() : 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_his_item;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_his_item = itemView.findViewById(R.id.tv_his_item);

        }
    }
}
