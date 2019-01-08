package com.mobile.android.app.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class WholeAdapter extends RecyclerView.Adapter<WholeAdapter.MyViewHolder> {
    private final Context mContext;
    private ArrayList<String> datas;
    private int type;

    public WholeAdapter(Context context, int i, ArrayList<String> complains) {
        this.mContext = context;
        this.type = i;
        this.datas = complains;
    }

    public void setData(ArrayList<String> complains) {
        this.datas = complains;
    }

    public ArrayList<String> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_whole, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (type == 0) {
            holder.tv_order_stauts.setVisibility(View.GONE);
            holder.tv_order_shenhe.setVisibility(View.GONE);
        }

        if (type == 1) {
            holder.tv_order_shenhe.setVisibility(View.VISIBLE);
            holder.tv_order_shenhe.setText("订单审核中");
            holder.tv_order_stauts.setVisibility(View.GONE);
        }

        if (type == 2) {
            holder.tv_order_shenhe.setVisibility(View.GONE);
            holder.tv_order_stauts.setVisibility(View.VISIBLE);
            holder.tv_order_shenhe.setText("需确认优化运费数据");
        }

        if (type == 3) {
            holder.tv_order_shenhe.setVisibility(View.GONE);
            holder.tv_order_stauts.setVisibility(View.VISIBLE);
            holder.tv_order_shenhe.setText("待付款");
        }

        if (type == 4) {
            holder.tv_order_shenhe.setVisibility(View.GONE);
            holder.tv_order_stauts.setVisibility(View.VISIBLE);
            holder.tv_order_shenhe.setText("待评论");
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvOrderDate;
        private final TextView tvOrderNumnber;
        private final TextView tvItemOrderDate;
        private final TextView tvStartPy;
        private final TextView tvStartHy;
        private final TextView tvEndHy;
        private final TextView tvEndPy;
        private final TextView tvItemInsurance;
        private final TextView tvItemMoney;
        private final TextView tv_order_stauts;
        private final TextView tv_order_shenhe;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrderDate = itemView.findViewById(R.id.tv_order_date);
            tvOrderNumnber = itemView.findViewById(R.id.tv_order_numnber);
            tvItemOrderDate = itemView.findViewById(R.id.tv_item_order_date);
            tvStartPy = itemView.findViewById(R.id.tv_start_py);
            tvStartHy = itemView.findViewById(R.id.tv_start_hy);
            tvEndHy = itemView.findViewById(R.id.tv_end_hy);
            tvEndPy = itemView.findViewById(R.id.tv_end_py);
            tvItemInsurance = itemView.findViewById(R.id.tv_item_Insurance);
            tvItemMoney = itemView.findViewById(R.id.tv_item_money);
            tv_order_stauts = itemView.findViewById(R.id.tv_order_stauts);
            tv_order_shenhe = itemView.findViewById(R.id.tv_order_shenhe);
        }
    }
}
