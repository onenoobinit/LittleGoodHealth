package com.mobile.android.app.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.MyViewHolder> {
    private final Context mContext;
    private ArrayList<String> datas;
    private int type;

    public OperationAdapter(Context context, int i, ArrayList<String> complains) {
        this.mContext = context;
        this.type = i;
        this.datas = complains;
    }

    public void setData(ArrayList<String> complains) {
        this.datas = complains;
    }

    public ArrayList<String> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_operation, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       /* if ("已解决".equals(datas.get(position).getStatus_name())) {
            holder.tv_ap_state.setBackgroundResource(R.drawable.tv_ap_green);
            holder.tv_ap_state.setText("已解决");
            holder.tv_ap_state.setTextColor(Color.parseColor("#50c3ba"));
        } else if ("待办".equals(datas.get(position).getStatus_name())) {
            holder.tv_ap_state.setBackgroundResource(R.drawable.tv_ap_red);
            holder.tv_ap_state.setText("待办");
            holder.tv_ap_state.setTextColor(Color.parseColor("#FF0000"));
        } else if ("处理中".equals(datas.get(position).getStatus_name())) {
            holder.tv_ap_state.setBackgroundResource(R.drawable.tv_ap_oriange);
            holder.tv_ap_state.setText("处理中");
            holder.tv_ap_state.setTextColor(Color.parseColor("#fbaa36"));
        }
        holder.tv_ap_number.setText(datas.get(position).getComplain_id());
        holder.tv_ap_name.setText(datas.get(position).getOne_class_name());
        holder.tv_ap_title.setText(datas.get(position).getTwo_class_name());
        holder.tv_ap_describe.setText(datas.get(position).getContent());
        holder.tv_ap_call.setText(datas.get(position).getComplain_from());
        holder.tv_ap_time.setText(datas.get(position).getAdd_time());
        holder.all_ap_item.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ComplainDetailActivity.class);
            intent.putExtra("complain_id", datas.get(position).getComplain_id());
            mContext.startActivity(intent);
        });*/
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvOrderDate;
        private final TextView tvOrderNumnber;
        private final TextView tvItemOrderDate;
        private final TextView tvStartPy;
        private final TextView tvStartHy;
        private final TextView tvEndHy;
        private final TextView tvEndPy;
        private final TextView tvItemInsurance;
        private final TextView tvItemMoney;
        private final TextView tvOrderDownStauts;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrderDate = itemView.findViewById(R.id.tv_order_date);
            tvOrderNumnber = itemView.findViewById(R.id.tv_order_numnber);
            tvItemOrderDate = itemView.findViewById(R.id.tv_item_order_date);
            tvStartPy = itemView.findViewById(R.id.tv_start_py);
            tvStartHy = itemView.findViewById(R.id.tv_start_hy);
            tvEndHy = itemView.findViewById(R.id.tv_end_hy);
            tvEndPy = itemView.findViewById(R.id.tv_end_py);
            tvItemInsurance = itemView.findViewById(R.id.tv_item_Insurance);
            tvItemMoney = itemView.findViewById(R.id.tv_item_money);
            tvOrderDownStauts = itemView.findViewById(R.id.tv_order_down_stauts);
        }
    }
}
