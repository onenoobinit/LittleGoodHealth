package com.mobile.android.app.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.PortInfo;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/14.
 */
public abstract class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {
    private final Context mContext;
    public static int canClick = 0;
    private ArrayList<PortInfo.DestinationListBean.DataListBeanX> datas;
    private int selectPostion = -1;


    public ContentAdapter(Context context, ArrayList<PortInfo.DestinationListBean.DataListBeanX> data) {
        this.mContext = context;
        this.datas = data;
    }


    public void setDataContent(ArrayList<PortInfo.DestinationListBean.DataListBeanX> data) {
        this.datas = data;
    }

    public ArrayList<PortInfo.DestinationListBean.DataListBeanX> getDataContent() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_content, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String cityNameC = datas.get(position).getCityNameC();
        if (cityNameC.contains("，")) {
            String[] temp = null;
            temp = cityNameC.split("，");
            holder.tv_item_content.setText(temp[0]);
        } else if (cityNameC.contains(",")) {
            String substring = cityNameC.substring(0, cityNameC.indexOf(","));
            holder.tv_item_content.setText(substring);
        } else {
            holder.tv_item_content.setText(cityNameC);
        }

        if (canClick == 0) {
            holder.tv_item_content.setClickable(true);
            holder.tv_item_content.setOnClickListener(view -> {
                selectPostion = position;
                setOnItemCont(holder.tv_item_content.getText().toString().trim());
            });
        } else {
            holder.tv_item_content.setClickable(false);
        }


       /* if (selectPostion == position) {
            holder.all_content.setBackgroundColor(Color.parseColor("#F3F2F1"));
        } else {
            holder.all_content.setBackgroundColor(Color.parseColor("#ffffff"));
        }*/
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_item_content;
        private final AutoLinearLayout all_content;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_content = itemView.findViewById(R.id.tv_item_content);
            all_content = itemView.findViewById(R.id.all_content);
        }
    }

    public abstract void setOnItemCont(String name);
}
