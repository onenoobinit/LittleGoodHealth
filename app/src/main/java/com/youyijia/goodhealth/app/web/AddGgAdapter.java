package com.youyijia.goodhealth.app.web;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ShopDetailInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/28.
 */
public class AddGgAdapter extends RecyclerView.Adapter<AddGgAdapter.MyViewHolder> {

    private final Context mContext;
    private List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean.AttrvalueBean> datas;
    public static int selectPostion = -1;


    public AddGgAdapter(Context mContext, List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean.AttrvalueBean> portList) {
        this.mContext = mContext;
        this.datas = portList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_guige_content, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.cb_gg_item.setText(datas.get(position).getInfoName());
        holder.cb_gg_item.setOnCheckedChangeListener((button, isCheck) -> {
            if (isCheck) {
                /*holder.cb_gg_item.setBackgroundResource(R.drawable.bg_gg_check);
                holder.cb_gg_item.setTextColor(Color.parseColor("#FFFFFF"));*/
                selectPostion = position;
            } else {
                selectPostion = -1;
                holder.cb_gg_item.setBackgroundResource(R.drawable.bg_gg_uncheck);
                holder.cb_gg_item.setTextColor(Color.parseColor("#aaaaaa"));
            }

            notifyDataSetChanged();
        });

        if (selectPostion == position) {
            holder.cb_gg_item.setBackgroundResource(R.drawable.bg_gg_check);
            holder.cb_gg_item.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.cb_gg_item.setBackgroundResource(R.drawable.bg_gg_uncheck);
            holder.cb_gg_item.setTextColor(Color.parseColor("#aaaaaa"));
        }
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cb_gg_item;
//        private final TextView tv_guige_content_item;

        public MyViewHolder(View itemView) {
            super(itemView);
//            tv_guige_content_item = itemView.findViewById(R.id.tv_guige_content_item);
            cb_gg_item = itemView.findViewById(R.id.cb_gg_item);

        }
    }
}
