package com.youyijia.goodhealth.app.order;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.MyTracesInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class MyTraceAdapter extends RecyclerView.Adapter<MyTraceAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<MyTracesInfo.TracesBean> datas;

    public MyTraceAdapter(Context context, ArrayList<MyTracesInfo.TracesBean> complains) {
        this.mContext = context;
        this.datas = complains;
    }

    public void setData(ArrayList<MyTracesInfo.TracesBean> complains) {
        this.datas = complains;
    }

    public ArrayList<MyTracesInfo.TracesBean> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_my_trace, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (position == 0) {
            holder.tv_left_top.setVisibility(View.GONE);
        } else {
            holder.tv_left_top.setVisibility(View.VISIBLE);
        }
        if (position == datas.size() - 1) {
            holder.tv_left_bottom.setVisibility(View.GONE);
            holder.v_line_bottom.setVisibility(View.GONE);
        } else {
            holder.tv_left_bottom.setVisibility(View.VISIBLE);
            holder.v_line_bottom.setVisibility(View.VISIBLE);
        }

        if (datas.get(position).getGoodsStatus().getCode() == 1040) {
            holder.iv_trace_item.setImageResource(R.mipmap.choseblue);
            holder.tv_title_trace.setTextColor(Color.parseColor("#4a4a4a"));
            holder.tv_time_trace.setTextColor(Color.parseColor("#4a4a4a"));
        } else {
            holder.iv_trace_item.setImageResource(R.mipmap.close_blue_circle);
            holder.tv_title_trace.setTextColor(Color.parseColor("#aaaaaa"));
            holder.tv_time_trace.setTextColor(Color.parseColor("#aaaaaa"));
        }

        holder.tv_title_trace.setText(datas.get(position).getAcceptStation());
        holder.tv_time_trace.setText(datas.get(position).getAcceptTime());
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_left_top;
        private final ImageView iv_trace_item;
        private final TextView tv_left_bottom;
        private final TextView tv_title_trace;
        private final TextView tv_time_trace;
        private final View v_line_bottom;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_left_top = itemView.findViewById(R.id.tv_left_top);
            iv_trace_item = itemView.findViewById(R.id.iv_trace_item);
            tv_left_bottom = itemView.findViewById(R.id.tv_left_bottom);
            tv_title_trace = itemView.findViewById(R.id.tv_title_trace);
            tv_time_trace = itemView.findViewById(R.id.tv_time_trace);
            v_line_bottom = itemView.findViewById(R.id.v_line_bottom);

        }
    }

}
