package com.mobile.android.app.submit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.SubmitInfo;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by wangqiang on 2019/1/24.
 */
public abstract class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MyViewHolder> {
    private final Context mContext;
    private final SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan1Bean data1;
    private final SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan2Bean data2;
    private final SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan3Bean data3;
    private final String valid;
    private String payoutRatio;
    private String costRatio;
    private String insuranceFee;
    public static int selectPostion = 3;

    public ShowAdapter(Context context, SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan1Bean data1
            , SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan2Bean data2, SubmitInfo.GoodsExtensionInsuranceBean.PlanListBean.Plan3Bean data3
            , String valid) {
        this.mContext = context;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.valid = valid;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_show, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position == 0) {
            holder.tv_banner_style.setText("方案一");
        } else if (position == 1) {
            holder.tv_banner_style.setText("方案二");
        } else if (position == 2) {
            holder.tv_banner_style.setText("方案三");
        }
        if (position == 0 && data1 != null) {
            payoutRatio = data1.getPayoutRatio();
            costRatio = data1.getCostRatio();
            insuranceFee = data1.getInsuranceFee();
        }
        if (position == 1 && data2 != null) {
            payoutRatio = data2.getPayoutRatio();
            costRatio = data2.getCostRatio();
            insuranceFee = data2.getInsuranceFee();
        }

        if (position == 2 && data3 != null) {
            payoutRatio = data3.getPayoutRatio();
            costRatio = data3.getCostRatio();
            insuranceFee = data3.getInsuranceFee();
        }

        holder.tv_banner_proport.setText(payoutRatio);
        holder.tv_banner_fl.setText(costRatio);
        holder.tv_banner_bf.setText(insuranceFee);

        if ("0".equals(valid)) {
            holder.all_item_banner.setClickable(false);
        } else if ("1".equals(valid)) {
            holder.all_item_banner.setClickable(true);
            holder.all_item_banner.setOnClickListener(view1 -> {
                onItemClick(position);
                selectPostion = position;
            });

            if (selectPostion == position) {
                holder.all_item_banner.setBackgroundResource(R.drawable.bg_banner_chek);
                holder.iv_banner_select.setVisibility(View.VISIBLE);
            } else {
                holder.all_item_banner.setBackgroundResource(R.drawable.bg_banner);
                holder.iv_banner_select.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_banner_style;
        private final ImageView iv_banner_select;
        private final TextView tv_banner_proport;
        private final TextView tv_banner_fl;
        private final TextView tv_banner_bf;
        private final AutoLinearLayout all_item_banner;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_banner_style = itemView.findViewById(R.id.tv_banner_style);
            iv_banner_select = itemView.findViewById(R.id.iv_banner_select);
            tv_banner_proport = itemView.findViewById(R.id.tv_banner_proport);
            tv_banner_fl = itemView.findViewById(R.id.tv_banner_fl);
            tv_banner_bf = itemView.findViewById(R.id.tv_banner_bf);
            all_item_banner = itemView.findViewById(R.id.all_item_banner);
        }
    }
    public abstract void onItemClick(int position);
}
