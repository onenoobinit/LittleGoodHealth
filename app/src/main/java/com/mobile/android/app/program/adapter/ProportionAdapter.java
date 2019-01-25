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
import com.mobile.android.entity.ProductDetailInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/8.
 */
public abstract class ProportionAdapter extends RecyclerView.Adapter<ProportionAdapter.MyViewHolder> {
    private final Context mContext;
    private final int type;
    private List<ProductDetailInfo.ProportionListBean.ProportionBean> datas;
    public static int selectPostion = 0;
    public static String active = "";


    public ProportionAdapter(Context context, List<ProductDetailInfo.ProportionListBean.ProportionBean> proportion1,
                             int type) {
        this.mContext = context;
        this.datas = proportion1;
        this.type = type;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_proportion, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_item_proportion.setText("1:" + datas.get(position).getData());

        if (type == 1 && active.equals(datas.get(position).getData())) {
            holder.tv_proprot_select.setVisibility(View.VISIBLE);
        } else {
            holder.tv_proprot_select.setVisibility(View.GONE);
        }
        holder.tv_item_proportion.setOnClickListener(view -> {
            selectPostion = position;
            setOnItemClick(datas.get(position).getData(), active);
            notifyDataSetChanged();
        });

        if (selectPostion == position) {
            holder.tv_item_proportion.setBackgroundResource(R.drawable.bg_item_proportion);
            holder.tv_item_proportion.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.tv_item_proportion.setBackgroundResource(R.drawable.bg_item_proportion_no);
            holder.tv_item_proportion.setTextColor(Color.parseColor("#9b9b9b"));
        }


    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_item_proportion;
        private final TextView tv_proprot_select;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_proportion = itemView.findViewById(R.id.tv_item_proportion);
            tv_proprot_select = itemView.findViewById(R.id.tv_proprot_select);
        }
    }

    public abstract void setOnItemClick(String proportion, String active);
}
