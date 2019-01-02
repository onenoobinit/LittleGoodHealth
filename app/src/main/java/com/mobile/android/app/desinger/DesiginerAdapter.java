package com.mobile.android.app.desinger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.DesiginerSearchInfo;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * 设计师查询adapter
 * Created by wangqiang on 2018/5/21.
 */
public class DesiginerAdapter extends RecyclerView.Adapter<DesiginerAdapter.MyViewHolder> {

    private final Context mContext;
    private List<DesiginerSearchInfo> data;
    private int type;//2.装修1.建材

    public DesiginerAdapter(Context context) {
        this.mContext = context;
        /*this.data = desiginerSearchInfos;
        this.type = dType;*/
    }

    @NonNull
    @Override
    public DesiginerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_desiginer, null));
    }

    @Override
    public void onBindViewHolder(@NonNull DesiginerAdapter.MyViewHolder holder, int position) {
        if (type == 1) {//建材
            holder.tv_desiginer_name.setText(data.get(position).getFirm_name());
            holder.tv_desiginer_pnumber.setText(data.get(position).getMobile());
            holder.tv_comoany_name.setText("建材公司：");
            holder.all_desiginer_item.setOnClickListener(view -> {
                if (listener!=null) {
                    listener.OnListener(position);
                }
            });
        } else if (type == 2) {//装修
            holder.tv_desiginer_name.setText(data.get(position).getFirm_name());
            holder.tv_desiginer_pnumber.setText(data.get(position).getMobile());
            holder.tv_comoany_name.setText("装修公司：");
            holder.all_desiginer_item.setOnClickListener(view -> {
                if (listener!=null) {
                    listener.OnListener(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<DesiginerSearchInfo> desiginerSearchInfos, int dType) {
        this.type = dType;
        this.data =desiginerSearchInfos;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_desiginer_name;
        private final TextView tv_desiginer_pnumber;
        private final AutoLinearLayout all_desiginer_item;
        private final TextView tv_comoany_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_desiginer_name = itemView.findViewById(R.id.tv_desiginer_name);
            tv_desiginer_pnumber = itemView.findViewById(R.id.tv_desiginer_pnumber);
            all_desiginer_item = itemView.findViewById(R.id.all_desiginer_item);
            tv_comoany_name = itemView.findViewById(R.id.tv_comoany_name);
        }
    }

    public interface  onListener{
        void OnListener(int position);
    }

    private onListener listener;

    public void setListener( onListener listener){
        this.listener = listener;
    }

}
