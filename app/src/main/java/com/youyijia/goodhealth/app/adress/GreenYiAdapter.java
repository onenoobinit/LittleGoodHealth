package com.youyijia.goodhealth.app.adress;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.GreenManInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/5.
 */
public abstract class GreenYiAdapter extends RecyclerView.Adapter<GreenYiAdapter.MyViewHolder> {

    private final Context mContext;
    private final String green;
    private ArrayList<GreenManInfo> datas;
    private int selectPostion = -1;

    public GreenYiAdapter(Context context, ArrayList<GreenManInfo> complains, String green) {
        this.mContext = context;
        this.datas = complains;
        this.green = green;
    }

    public void setData(ArrayList<GreenManInfo> complains) {
        this.datas = complains;
    }

    public ArrayList<GreenManInfo> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_green_yi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if ("1".equals(green)) {
            holder.ll_left.setVisibility(View.VISIBLE);
        } else {
            holder.ll_left.setVisibility(View.GONE);
        }

        holder.tv_green_name.setText(datas.get(position).getName());
        holder.tv_green_type.setText(datas.get(position).getRelation().getText());
        holder.tv_green_phone.setText(datas.get(position).getTelephone());
        holder.tv_green_card.setText(datas.get(position).getIdCardType().getText());
        holder.tv_green_id.setText(datas.get(position).getIdCardNo());

        holder.ll_left.setOnClickListener(v -> {
            selectPostion = position;
            setOnclick(datas.get(position).getName(), datas.get(position).getTelephone(), datas.get(position).getIdCardType().getText()
                    , datas.get(position).getIdCardNo(), datas.get(position).getId());
            notifyDataSetChanged();
        });

        if (selectPostion == position) {
            holder.iv_left.setImageResource(R.mipmap.choseblue);
        } else {
            holder.iv_left.setImageResource(R.mipmap.close_blue_circle);
        }

        holder.rl_edit.setOnClickListener(v -> {
            setEdit(position);
        });
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_green_type;
        private final TextView tv_green_name;
        private final TextView tv_green_phone;
        private final TextView tv_green_card;
        private final TextView tv_green_id;
        private final ImageView iv_green_edit;
        private final LinearLayout ll_left;
        private final RelativeLayout rl_edit;
        private final ImageView iv_left;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_green_type = itemView.findViewById(R.id.tv_green_type);
            tv_green_name = itemView.findViewById(R.id.tv_green_name);
            tv_green_phone = itemView.findViewById(R.id.tv_green_phone);
            tv_green_card = itemView.findViewById(R.id.tv_green_card);
            tv_green_id = itemView.findViewById(R.id.tv_green_id);
            iv_green_edit = itemView.findViewById(R.id.iv_green_edit);
            ll_left = itemView.findViewById(R.id.ll_left);
            rl_edit = itemView.findViewById(R.id.rl_edit);
            iv_left = itemView.findViewById(R.id.iv_left);

        }
    }

    public abstract void setOnclick(String name, String phone, String type, String card, String medicalPersonId);

    public abstract void setEdit(int position);
}
