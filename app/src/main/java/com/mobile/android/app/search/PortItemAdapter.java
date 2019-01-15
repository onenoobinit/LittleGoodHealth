package com.mobile.android.app.search;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.PortInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/14.
 */
public class PortItemAdapter extends RecyclerView.Adapter<PortItemAdapter.MyViewHolder> {
    private final Context mContext;
    private ArrayList<PortInfo.DestinationListBean> datas;
    private int selectPostion = 0;


    public PortItemAdapter(Context context, ArrayList<PortInfo.DestinationListBean> data) {
        this.mContext = context;
        this.datas = data;
    }

    public void setData(ArrayList<PortInfo.DestinationListBean> data) {
        this.datas = data;
    }

    public ArrayList<PortInfo.DestinationListBean> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_port_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_item_port.setText(datas.get(position).getGroup());
        holder.tv_item_port.setOnClickListener(view -> {
            selectPostion = position;
            if (contentItemClickListener != null) {
                contentItemClickListener.contentItemClick(selectPostion);
            }
            notifyDataSetChanged();
        });

        if (selectPostion == position) {
            holder.tv_item_port.setTextColor(Color.parseColor("#00A7F7"));
        } else {
            holder.tv_item_port.setTextColor(Color.parseColor("#575757"));
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_item_port;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_item_port = itemView.findViewById(R.id.tv_item_port);

        }
    }

    //设置回调方法，在popupwindow条目点击事件中调用，传递值给activity刷新数据
    public interface ContentItemClickListener {
        void contentItemClick(int postion);
    }

    private ContentItemClickListener contentItemClickListener;

    public void setOnContentItemClickListener(ContentItemClickListener contentItemClickListener) {
        this.contentItemClickListener = contentItemClickListener;
    }
}
