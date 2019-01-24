package com.mobile.android.app.submit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.SubmitInfo;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/19.
 */
public abstract class ContactHistoryAdapter extends RecyclerView.Adapter<ContactHistoryAdapter.MyViewHolder> {
    private final Context mContext;
    private List<SubmitInfo.OrderBillBean.ContactInfoBean> datas;

    public ContactHistoryAdapter(Context context, List<SubmitInfo.OrderBillBean.ContactInfoBean> data) {
        this.mContext = context;
        this.datas = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_contact_name.setText(datas.get(position).getName());
        holder.tv_contact_mobile.setText("电话：" + datas.get(position).getTel());
        holder.tv_contact_qq.setText("QQ：" + datas.get(position).getQq());
        holder.tv_contact_mail.setText("邮箱：" + datas.get(position).getEmail());
        holder.tv_contact_content.setText("备注：" + datas.get(position).getRemark());
        holder.all_contact_item.setOnClickListener(view -> {
            setOnItemClik(position);
        });


    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_contact_name;
        private final TextView tv_contact_mobile;
        private final TextView tv_contact_qq;
        private final TextView tv_contact_mail;
        private final TextView tv_contact_content;
        private final AutoLinearLayout all_contact_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_contact_name = itemView.findViewById(R.id.tv_contact_name);
            tv_contact_mobile = itemView.findViewById(R.id.tv_contact_mobile);
            tv_contact_qq = itemView.findViewById(R.id.tv_contact_qq);
            tv_contact_mail = itemView.findViewById(R.id.tv_contact_mail);
            tv_contact_content = itemView.findViewById(R.id.tv_contact_content);
            all_contact_item = itemView.findViewById(R.id.all_contact_item);
        }
    }

    public abstract void setOnItemClik(int postion);
}
