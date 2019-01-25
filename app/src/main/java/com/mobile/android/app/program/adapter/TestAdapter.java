package com.mobile.android.app.program.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.ProgramSelectInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/21.
 */
public abstract class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private final Context mContext;
    private List<ProgramSelectInfo.ProductCardListBean> datas;
    private int selectPostion = 0;

    public TestAdapter(Context context, List<ProgramSelectInfo.ProductCardListBean> data) {
        this.mContext = context;
        this.datas = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_right_small_sort, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_select_look.setOnClickListener(view -> {
            selectPostion = position;
            seOnItemClick(position);
        });
        holder.tv_select_name.setText(datas.get(position).getStartPort());
        holder.tv_select_price.setText("单价：" + datas.get(position).getSuit().getRealPrice() + "/KG");
        holder.tv_select_shop.setText(datas.get(position).getProductIcons().getSupplierName());
        holder.tv_select_over.setText("剩余舱位" + (100 - Integer.parseInt(datas.get(position).getSuit().getSpace())) + "%");
        holder.tv_select_company.setText(datas.get(position).getProductName());
//        holder.pv_select.setProgress(datas.get(position).getSuit().getSpace());
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_select_name;
        private final TextView tv_select_company;
        private final TextView tv_select_price;
        private final TextView tv_select_shop;
        private final TextView tv_select_over;
        private final TextView tv_select_look;
        private final ProgressBar pv_select;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_select_name = itemView.findViewById(R.id.tv_select_name);
            tv_select_company = itemView.findViewById(R.id.tv_select_company);
            tv_select_price = itemView.findViewById(R.id.tv_select_price);
            tv_select_shop = itemView.findViewById(R.id.tv_select_shop);
            tv_select_over = itemView.findViewById(R.id.tv_select_over);
            tv_select_look = itemView.findViewById(R.id.tv_select_look);
            pv_select = itemView.findViewById(R.id.pv_select);
        }
    }

    public abstract void seOnItemClick(int selectPostion);
}
