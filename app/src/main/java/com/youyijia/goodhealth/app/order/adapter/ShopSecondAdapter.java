package com.youyijia.goodhealth.app.order.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.MyShopOrderInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/22.
 */
public class ShopSecondAdapter extends RecyclerView.Adapter<ShopSecondAdapter.MyViewHolder> {
    private final Context mContext;
    private final int type;
    private ArrayList<MyShopOrderInfo.OrderItemsBean> datas;

    public ShopSecondAdapter(Context context, ArrayList<MyShopOrderInfo.OrderItemsBean> datas, int type) {
        this.mContext = context;
        this.datas = datas;
        this.type = type;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_order_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(datas.get(position).getImageUrl())
                .apply(options)
                .into(holder.iv_order_item);

        holder.tv_order_whole_name.setText(datas.get(position).getCommodityName());
        holder.tv_order_real_price.setText("¥" + datas.get(position).getPresentUnitPrice());
        holder.tv_order_out_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_order_out_price.setText("¥" + datas.get(position).getCostUnitPrice());
        holder.tv_order_shop_number.setText("×" + datas.get(position).getNumber());
        if (type == 1) {
            holder.tv_order_whole_style.setText(datas.get(position).getCommoditySpecificationInfo());
        }

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_order_item;
        private final TextView tv_order_whole_name;
        private final TextView tv_order_whole_style;
        private final TextView tv_order_real_price;
        private final TextView tv_order_out_price;
        private final TextView tv_order_shop_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_order_item = itemView.findViewById(R.id.iv_order_item);
            tv_order_whole_name = itemView.findViewById(R.id.tv_order_whole_name);
            tv_order_whole_style = itemView.findViewById(R.id.tv_order_whole_style);
            tv_order_real_price = itemView.findViewById(R.id.tv_order_real_price);
            tv_order_out_price = itemView.findViewById(R.id.tv_order_out_price);
            tv_order_shop_number = itemView.findViewById(R.id.tv_order_shop_number);

        }
    }
}
