package com.mobile.android.app.program.adapter;

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
 * Created by wangqiang on 2019/1/17.
 */
public class ProDetailAdpater extends RecyclerView.Adapter<ProDetailAdpater.MyViewHolder> {

    private final Context mContext;
    private List<String> datas;
    private int type;

    public ProDetailAdpater(Context context, int i, List<String> complains) {
        this.mContext = context;
        this.type = i;
        this.datas = complains;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_pro_detail, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_case.setText(datas.get(position));
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_case;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_case = itemView.findViewById(R.id.tv_case);

        }
    }
}
