package com.mobile.android.app.submit;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.SubmitInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/23.
 */
public abstract class FjAdapter extends RecyclerView.Adapter<FjAdapter.MyViewHolder> {
    private final Context mContext;
    private List<SubmitInfo.AttachServiceBean> datas;

    public FjAdapter(Context context, List<SubmitInfo.AttachServiceBean> complains) {
        this.mContext = context;
        this.datas = complains;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_fj, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if ("1".equals(datas.get(0).getValid())) {
            holder.iv_fj.setVisibility(View.GONE);
            holder.cb_bg.setVisibility(View.VISIBLE);
            holder.tv_fj.setText(datas.get(0).getName());
            holder.tv_fj_number.setText(datas.get(0).getPrice());
            if ("1".equals(datas.get(0).getSelected())) {
                holder.cb_bg.setChecked(true);
                holder.tv_fj.setBackgroundResource(R.drawable.bg_rb_select);
                holder.tv_fj.setTextColor(Color.parseColor("#ffffff"));
            } else {
                holder.cb_bg.setChecked(false);
                holder.tv_fj.setBackgroundResource(R.drawable.bg_tv_cb);
                holder.tv_fj.setTextColor(Color.parseColor("#575757"));
            }
        } else {
            holder.iv_fj.setVisibility(View.VISIBLE);
            holder.cb_bg.setVisibility(View.GONE);
        }

        String id = datas.get(position).getId();
        holder.cb_bg.setOnCheckedChangeListener((button, isCheck) -> {
            if (isCheck) {
                holder.tv_fj.setBackgroundResource(R.drawable.bg_rb_select);
                holder.tv_fj.setTextColor(Color.parseColor("#ffffff"));
                setOnCheckClik(true, id);
            } else {
                holder.tv_fj.setBackgroundResource(R.drawable.bg_tv_cb);
                holder.tv_fj.setTextColor(Color.parseColor("#575757"));
                setOnCheckClik(false, "");
            }
        });


    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_fj;
        private final CheckBox cb_bg;
        private final TextView tv_fj;
        private final TextView tv_fj_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_fj = itemView.findViewById(R.id.iv_fj);
            cb_bg = itemView.findViewById(R.id.cb_bg);
            tv_fj = itemView.findViewById(R.id.tv_fj);
            tv_fj_number = itemView.findViewById(R.id.tv_fj_number);

        }
    }

    public abstract void setOnCheckClik(boolean isCheck, String checkId);
}
