package com.youyijia.goodhealth.app.web;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ShopDetailInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/24.
 */
public abstract class GuigeAdapter extends RecyclerView.Adapter<GuigeAdapter.MyViewHolder> {
    private final Context mContext;
    private List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean> datas;
    private GuigeContentAdapter gridEndAdapter;
    public static int fristSelection = -1;

    public GuigeAdapter(Context context, List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_guige, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.tv_case.setText(datas.get(position));
        fristSelection = position;
        holder.tv_type_name.setText(datas.get(position).getAttrname());
        List<ShopDetailInfo.SpecificationsBeanX.SpecificationsBean.AttrvalueBean> attrvalue = datas.get(position).getAttrvalue();

        holder.rv_content.setLayoutManager(new GridLayoutManager(mContext, 6));
        gridEndAdapter = new GuigeContentAdapter(mContext, attrvalue, position);
        holder.rv_content.setAdapter(gridEndAdapter);
        gridEndAdapter.setOnPopupItemClickListener(new GuigeContentAdapter.PopupItemClickListener() {
            @Override
            public void popupItemClick(int secpostion, int onepostion) {
                GuigeContentAdapter.selectPostion = secpostion;
                fristSelection = onepostion;
                setOnFristClick(fristSelection, secpostion);
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_type_name;
        private final RecyclerView rv_content;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_type_name = itemView.findViewById(R.id.tv_type_name);
            rv_content = itemView.findViewById(R.id.rv_content);
        }
    }

    public abstract void setOnFristClick(int position, int secPostion);
}
