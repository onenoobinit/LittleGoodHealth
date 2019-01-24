package com.mobile.android.app.submit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/22.
 */
public class ShowTipsAdapter extends RecyclerView.Adapter<ShowTipsAdapter.MyViewHolder> {
    private final Context mContext;
    private List<String> datas;

    public ShowTipsAdapter(Context context, List<String> complains) {
        this.mContext = context;
        this.datas = complains;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_show_tips, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_show_tips.setText(datas.get(position));
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_show_tips;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_show_tips = itemView.findViewById(R.id.tv_show_tips);

        }
    }
}
