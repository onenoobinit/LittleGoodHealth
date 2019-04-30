package com.youyijia.goodhealth.app.order.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.OrderInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/4.
 */
public abstract class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<OrderInfo> datas;

    public CommentAdapter(Context context, ArrayList<OrderInfo> complains) {
        this.mContext = context;
        this.datas = complains;
    }

    public void setData(ArrayList<OrderInfo> complains) {
        this.datas = complains;
    }

    public ArrayList<OrderInfo> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_exmine, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if ("绿通订单".equals(datas.get(position).getOrderType().getText())) {
            holder.tv_shop_name.setText("绿通服务");
        } else if ("商品订单".equals(datas.get(position).getOrderType().getText())) {
            holder.tv_shop_name.setText("优选商城");
            holder.tv_order_whole_style.setText(datas.get(position).getOrderItems().get(0).getCommoditySpecificationInfo());
        }


        holder.tv_shop_stauts.setText(datas.get(position).getOrderStatus().getText());
        holder.tv_order_item_total_number.setText("共" + datas.get(position).getCommodityCount() + "件商品");
        holder.tv_order_item_total_price.setText("¥" + datas.get(position).getOrderAmountTotal());
        holder.tv_order_item_yf.setText("(含运费：¥" + datas.get(position).getLogisticsFee() + ")");

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(datas.get(position).getOrderItems().get(0).getImageUrl())
                .apply(options)
                .into(holder.iv_order_item);

        holder.tv_order_whole_name.setText(datas.get(position).getOrderItems().get(0).getCommodityName());
        holder.tv_order_real_price.setText("¥" + datas.get(position).getOrderItems().get(0).getPresentUnitPrice());
        holder.tv_order_out_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_order_out_price.setText("¥" + datas.get(position).getOrderItems().get(0).getCostUnitPrice());
        holder.tv_order_shop_number.setText("×" + datas.get(position).getOrderItems().get(0).getNumber());
        holder.tv_order_left.setText("删除订单");
        holder.tv_order_right.setText("评价");
        holder.tv_order_left.setOnClickListener(v -> {
            setOnDelete(position);
        });

        holder.tv_order_right.setOnClickListener(v -> {
            setOnPj(position);
        });
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_shop_name;
        private final TextView tv_shop_stauts;
        private final TextView tv_order_item_yf;
        private final TextView tv_order_item_total_price;
        private final TextView tv_order_item_total_number;
        private final TextView tv_order_left;
        private final TextView tv_order_right;
        private final ImageView iv_order_item;
        private final TextView tv_order_whole_name;
        private final TextView tv_order_whole_style;
        private final TextView tv_order_real_price;
        private final TextView tv_order_out_price;
        private final TextView tv_order_shop_number;
        private final RelativeLayout rl_bottom;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            tv_shop_stauts = itemView.findViewById(R.id.tv_shop_stauts);
            tv_order_item_yf = itemView.findViewById(R.id.tv_order_item_yf);
            tv_order_item_total_price = itemView.findViewById(R.id.tv_order_item_total_price);
            tv_order_item_total_number = itemView.findViewById(R.id.tv_order_item_total_number);
            tv_order_left = itemView.findViewById(R.id.tv_order_left);
            tv_order_right = itemView.findViewById(R.id.tv_order_right);
            iv_order_item = itemView.findViewById(R.id.iv_order_item);
            tv_order_whole_name = itemView.findViewById(R.id.tv_order_whole_name);
            tv_order_whole_style = itemView.findViewById(R.id.tv_order_whole_style);
            tv_order_real_price = itemView.findViewById(R.id.tv_order_real_price);
            tv_order_out_price = itemView.findViewById(R.id.tv_order_out_price);
            tv_order_shop_number = itemView.findViewById(R.id.tv_order_shop_number);
            rl_bottom = itemView.findViewById(R.id.rl_bottom);
        }
    }

    public abstract void setOnDelete(int position);

    public abstract void setOnPj(int position);
}
