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
 * Created by wangqiang on 2019/1/18.
 */
public class GridEndAdapter extends RecyclerView.Adapter<GridEndAdapter.MyViewHolder> {

    private final Context mContext;
    private final int onePostion;
    private List<ProductDetailInfo.PortDataBean.DestinationBean.PortListBean> datas;
    private int type;
    public static int selectPostion = 0;


    public GridEndAdapter(Context mContext, List<ProductDetailInfo.PortDataBean.DestinationBean.PortListBean> portList, int onePostion) {
        this.mContext = mContext;
        this.datas = portList;
        this.onePostion = onePostion;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.end_dialog_show, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_end_dialog_item.setText(datas.get(position).getPort());
        holder.tv_end_dialog_item.setOnClickListener(view -> {
            if (popupItemClickListener != null) {
                popupItemClickListener.popupItemClick(position, onePostion);
            }

        });

        if (selectPostion == position && EndSeclectAdapter.selectPostion == onePostion) {
            System.out.println("AAAAA" + selectPostion + "," + EndSeclectAdapter.selectPostion + "," + onePostion);
            holder.tv_end_dialog_item.setBackgroundResource(R.drawable.bg_tv_just);
            holder.tv_end_dialog_item.setTextColor(Color.parseColor("#ffffff"));
        } else {
            holder.tv_end_dialog_item.setBackgroundResource(R.drawable.bg_tv_target);
            holder.tv_end_dialog_item.setTextColor(Color.parseColor("#9B9B9B"));
        }
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_end_dialog_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_end_dialog_item = itemView.findViewById(R.id.tv_end_dialog_item);

        }
    }


//    public abstract void setOnSecondClick(int position);

    //设置回调方法，在popupwindow条目点击事件中调用，传递值给activity刷新数据
    public interface PopupItemClickListener {
        void popupItemClick(int postion, int onepostion);
    }


    private PopupItemClickListener popupItemClickListener;

    public void setOnPopupItemClickListener(PopupItemClickListener popupItemClickListener) {
        this.popupItemClickListener = popupItemClickListener;
    }

}