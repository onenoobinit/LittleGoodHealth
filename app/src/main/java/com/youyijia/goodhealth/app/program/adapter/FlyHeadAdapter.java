package com.youyijia.goodhealth.app.program.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ProductDetailInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/18.
 */
public class FlyHeadAdapter extends RecyclerView.Adapter<FlyHeadAdapter.MyViewHolder> {

    private final Context mContext;
    private List<ProductDetailInfo.ProductAttachInfoBean.FlightInfoBean.TheFirstFlightBean.DetailDataBean> datas;


    public FlyHeadAdapter(Context context, List<ProductDetailInfo.ProductAttachInfoBean.FlightInfoBean.TheFirstFlightBean.DetailDataBean> complains) {
        this.mContext = context;
        this.datas = complains;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_fly_head, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_fly_number.setText(datas.get(position).getFlightNumber());
        holder.tv_yun_gong.setText(datas.get(position).getTransportation());
        holder.tv_fly_time.setText(datas.get(position).getStartDate());
        String arrivalDate = datas.get(position).getArrivalDate();
        if (arrivalDate.contains("&")) {
            String[] split = arrivalDate.split("&");
            holder.tv_arrive_time.setText(split[0]);
        } else {
            holder.tv_arrive_time.setText(arrivalDate);
        }
        holder.tv_hang_ban.setText(datas.get(position).getFlightShift());
        holder.tv_ji_xing.setText(datas.get(position).getAircraftType());
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_fly_number;
        private final TextView tv_yun_gong;
        private final TextView tv_fly_time;
        private final TextView tv_arrive_time;
        private final TextView tv_hang_ban;
        private final TextView tv_ji_xing;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_fly_number = itemView.findViewById(R.id.tv_fly_number);
            tv_yun_gong = itemView.findViewById(R.id.tv_yun_gong);
            tv_fly_time = itemView.findViewById(R.id.tv_fly_time);
            tv_arrive_time = itemView.findViewById(R.id.tv_arrive_time);
            tv_hang_ban = itemView.findViewById(R.id.tv_hang_ban);
            tv_ji_xing = itemView.findViewById(R.id.tv_ji_xing);
        }
    }
}
