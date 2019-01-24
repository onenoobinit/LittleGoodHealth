package com.mobile.android.app.program.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
public abstract class EndSeclectAdapter extends RecyclerView.Adapter<EndSeclectAdapter.MyViewHolder> {
    private final Context mContext;
    private List<ProductDetailInfo.PortDataBean.DestinationBean> datas;
    private GridEndAdapter gridEndAdapter;
    public static int selectPostion = 0;

    public EndSeclectAdapter(Context context, List<ProductDetailInfo.PortDataBean.DestinationBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_end_seclect, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.tv_case.setText(datas.get(position));
        holder.tv_end_name.setText(datas.get(position).getArea());
        List<ProductDetailInfo.PortDataBean.DestinationBean.PortListBean> portList = datas.get(position).getPortList();

        holder.rv_end.setLayoutManager(new GridLayoutManager(mContext, 4));
        gridEndAdapter = new GridEndAdapter(mContext, portList, position);
        holder.rv_end.setAdapter(gridEndAdapter);
        gridEndAdapter.setOnPopupItemClickListener(new GridEndAdapter.PopupItemClickListener() {
            @Override
            public void popupItemClick(int secpostion, int onepostion) {
                GridEndAdapter.selectPostion = secpostion;
//                System.out.println("AAAAA1" + secpostion);
                selectPostion = onepostion;
                setOnFristClick(position, secpostion);
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_end_name;
        private final RecyclerView rv_end;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_end_name = itemView.findViewById(R.id.tv_end_name);
            rv_end = itemView.findViewById(R.id.rv_end);
        }
    }

    public abstract void setOnFristClick(int position, int secPostion);
}
