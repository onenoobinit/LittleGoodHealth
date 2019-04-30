package com.youyijia.goodhealth.app.body;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.BodyZiXunInfo;
import com.youyijia.goodhealth.widgets.GlideCircleTransform;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wangqiang on 2019/4/15.
 */
public abstract class BodyzxAdapter extends RecyclerView.Adapter<BodyzxAdapter.MyViewHolder> {

    private final Context mContext;
    private ArrayList<BodyZiXunInfo> datas;
    private int type;

    public BodyzxAdapter(Context context, ArrayList<BodyZiXunInfo> complains) {
        this.mContext = context;
        this.datas = complains;
    }

    public void setData(ArrayList<BodyZiXunInfo> complains) {
        this.datas = complains;
    }

    public ArrayList<BodyZiXunInfo> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_heart_body, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iv_heart_item.setOnClickListener(v -> {
            setOnItemClick(position);
        });

        RequestOptions options = new RequestOptions().transform(new GlideCircleTransform());
        Glide.with(mContext)
                .load(datas.get(position).getHeadPortrait())
                .apply(options)
                .into(holder.iv_heart_item);
        holder.tv_heart_item_name.setText(datas.get(position).getName());
        holder.tv_heart_item_zz.setText(datas.get(position).getDoctorTitle());
        holder.tv_heart_item_marjor.setText(datas.get(position).getResearchDirection());
        holder.tv_heart_item_zx.setOnClickListener(v -> {
            setOnTellClick();
        });
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView iv_heart_item;
        private final TextView tv_heart_item_name;
        private final TextView tv_heart_item_zz;
        private final TextView tv_heart_item_zx;
        private final TextView tv_heart_item_marjor;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_heart_item = itemView.findViewById(R.id.iv_heart_item);
            tv_heart_item_name = itemView.findViewById(R.id.tv_heart_item_name);
            tv_heart_item_zz = itemView.findViewById(R.id.tv_heart_item_zz);
            tv_heart_item_zx = itemView.findViewById(R.id.tv_heart_item_zx);
            tv_heart_item_marjor = itemView.findViewById(R.id.tv_heart_item_marjor);

        }
    }

    public abstract void setOnItemClick(int position);
    public abstract void setOnTellClick();
}
