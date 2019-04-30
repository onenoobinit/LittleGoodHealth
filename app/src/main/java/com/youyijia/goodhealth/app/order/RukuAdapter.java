package com.youyijia.goodhealth.app.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.OrderBillTypeInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/31.
 */
public class RukuAdapter extends RecyclerView.Adapter<RukuAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<OrderBillTypeInfo.IntoCabinDataListBean> datas;


    public RukuAdapter(Context context, List<OrderBillTypeInfo.IntoCabinDataListBean> weights) {
        this.mContext = context;
        this.datas = weights;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_ruku, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_order_number.setText(datas.get(position).getNumber());
        holder.tv_order_time.setText(datas.get(position).getDate());
        holder.tv_order_js.setText(datas.get(position).getPieces());
        holder.tv_order_weight.setText(datas.get(position).getWeight());
        holder.tv_order_vol.setText(datas.get(position).getVolume());
//        holder.tv_order_size.setText(datas.get(position).getGoodsInfo().get());
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_order_number;
        private final TextView tv_order_time;
        private final TextView tv_order_js;
        private final TextView tv_order_weight;
        private final TextView tv_order_vol;
        private final TextView tv_order_size;
        private final TextView tv_order_style;
        private final TextView tv_order_qk;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_order_number = itemView.findViewById(R.id.tv_order_number);
            tv_order_time = itemView.findViewById(R.id.tv_order_time);
            tv_order_js = itemView.findViewById(R.id.tv_order_js);
            tv_order_weight = itemView.findViewById(R.id.tv_order_weight);
            tv_order_vol = itemView.findViewById(R.id.tv_order_vol);
            tv_order_size = itemView.findViewById(R.id.tv_order_size);
            tv_order_style = itemView.findViewById(R.id.tv_order_style);
            tv_order_qk = itemView.findViewById(R.id.tv_order_qk);
        }
    }
}
