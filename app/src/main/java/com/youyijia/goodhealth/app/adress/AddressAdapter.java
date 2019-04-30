package com.youyijia.goodhealth.app.adress;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.AddressListInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/5.
 */
public abstract class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    private final Context mContext;
    private final String type;
    private ArrayList<AddressListInfo> datas;
    public static int selectPostion = -1;

    public AddressAdapter(Context context, ArrayList<AddressListInfo> complains, String type) {
        this.mContext = context;
        this.datas = complains;
        this.type = type;
    }

    public void setData(ArrayList<AddressListInfo> complains) {
        this.datas = complains;
    }

    public ArrayList<AddressListInfo> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_address, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_address_name.setText(datas.get(position).getReceiver());
        holder.tv_address_phone.setText(datas.get(position).getPhone());
        holder.tv_address_detail.setText(datas.get(position).getAddress());
        holder.ll_address_remove.setOnClickListener(view -> {
            remove(position);
        });

        if ("DEFAULT".equals(datas.get(position).getAddressStatus().getName())) {
            selectPostion = position;
        }

        holder.ll_address_edit.setOnClickListener(view -> {
            edit(position);
        });

        holder.ll_address_moren.setOnClickListener(view -> {
            setMoren(position);
            selectPostion = position;
        });

        if (selectPostion == position) {
            holder.tv_address_moren.setTextColor(Color.parseColor("#63BBFF"));
            holder.iv_moren.setImageResource(R.mipmap.choseblue);
        } else {
            holder.tv_address_moren.setTextColor(Color.parseColor("#4A4A4A"));
            holder.iv_moren.setImageResource(R.mipmap.close_blue_circle);
        }


        if ("1".equals(type)) {
            holder.ll_address_item.setOnClickListener(v -> {
                setOnclickItem(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_address_name;
        private final TextView tv_address_phone;
        private final TextView tv_address_detail;
        private final LinearLayout ll_address_remove;
        private final LinearLayout ll_address_edit;
        private final LinearLayout ll_address_moren;
        private final TextView tv_address_moren;
        private final ImageView iv_moren;
        private final LinearLayout ll_address_item;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_address_name = itemView.findViewById(R.id.tv_address_name);
            tv_address_phone = itemView.findViewById(R.id.tv_address_phone);
            tv_address_detail = itemView.findViewById(R.id.tv_address_detail);
            ll_address_remove = itemView.findViewById(R.id.ll_address_remove);
            ll_address_edit = itemView.findViewById(R.id.ll_address_edit);
            ll_address_moren = itemView.findViewById(R.id.ll_address_moren);
            tv_address_moren = itemView.findViewById(R.id.tv_address_moren);
            iv_moren = itemView.findViewById(R.id.iv_moren);
            ll_address_item = itemView.findViewById(R.id.ll_address_item);
        }
    }

    public abstract void setMoren(int position);

    public abstract void remove(int position);

    public abstract void edit(int position);

    public abstract void setOnclickItem(int position);
}
