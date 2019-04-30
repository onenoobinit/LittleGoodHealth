package com.youyijia.goodhealth.app.program.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ProgramSelectInfo;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/21.
 */
public abstract class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private final Context mContext;
    private final String book;//0正装 1备装
    private List<ProgramSelectInfo.ProductCardListBean> datas;
    private int selectPostion = 0;
    private int overSpace;
    private int reoverSpace;

    public TestAdapter(Context context, List<ProgramSelectInfo.ProductCardListBean> data, String book) {
        this.mContext = context;
        this.datas = data;
        this.book = book;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_right_small_sort, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String airline = datas.get(position).getAirline();

        holder.tv_select_look.setOnClickListener(view -> {
            selectPostion = position;
            seOnItemClick(position, airline);
        });

        holder.all_select_item.setOnClickListener(view -> {
            selectPostion = position;
            seOnItemClick(position, airline);
        });

        if (!TextUtils.isEmpty(datas.get(position).getTotalPrice())) {
            holder.all_total.setVisibility(View.VISIBLE);
            holder.arl_look.setVisibility(View.GONE);
            holder.tv_total_yg.setText(datas.get(position).getTotalPrice());
            holder.pv_select.setMax(100);
            holder.tv_select_price.setText("单价：" + datas.get(position).getSpaceData().getPrice() + "/KG");
            holder.pv_select.setProgress(Integer.parseInt(datas.get(position).getSpaceData().getSpace()));
            overSpace = Integer.parseInt(datas.get(position).getSpaceData().getOverSpace());
            reoverSpace = Integer.parseInt(datas.get(position).getSpaceData().getReOverSpace());

            if ("0".equals(book)) {
                if (overSpace < 101 && reoverSpace < 100) {
                    holder.pv_select.setProgress(100);
                    holder.tv_select_over.setText("爆仓");
                } else {
                    if (overSpace < 101 && reoverSpace > 100) {
                        holder.tv_select_over.setText("正装无位备装可定");
                        holder.pv_select.setProgress(100);
                    } else {
                        holder.tv_select_over.setText("剩余舱位" + (100 - Integer.parseInt(datas.get(position).getSpaceData().getSpace())) + "%");
                        holder.pv_select.setProgress(Integer.parseInt(datas.get(position).getSpaceData().getSpace()));
                    }
                }
            } else if ("1".equals(book)) {
                if (overSpace < 101 && reoverSpace < 100) {
                    holder.pv_select.setProgress(100);
                    holder.tv_select_over.setText("爆仓");
                } else {
                    if (overSpace > 100 && reoverSpace < 101) {
                        holder.tv_select_over.setText("备装无位正装可定");
                        holder.pv_select.setProgress(100);
                    } else {
                        holder.tv_select_over.setText("剩余舱位" + (100 - Integer.parseInt(datas.get(position).getSpaceData().getReSpace())) + "%");
                        holder.pv_select.setProgress(Integer.parseInt(datas.get(position).getSpaceData().getReSpace()));
                    }
                }
            }
        } else {
            holder.all_total.setVisibility(View.GONE);
            holder.arl_look.setVisibility(View.VISIBLE);

            int overspace = Integer.parseInt(datas.get(position).getSuit().getOverSpace());
            int overspace1 = Integer.parseInt(datas.get(position).getReadiness().getOverSpace());

            if ("0".equals(book)) {
                holder.pv_select.setProgress(Integer.parseInt(datas.get(position).getSuit().getSpace()));
                holder.tv_select_price.setText("单价：" + datas.get(position).getSuit().getRealPrice() + "/KG");

                if (overspace < 101 && overspace1 < 101) {
                    holder.tv_select_over.setText("爆仓");
                } else if (overspace < 101 && overspace1 > 100) {
                    holder.tv_select_over.setText("正装无位备装可定");
                } else {
                    holder.tv_select_over.setText("剩余舱位" + (100 - Integer.parseInt(datas.get(position).getSuit().getSpace())) + "%");
                }
            } else if ("1".equals(book)) {
                holder.pv_select.setProgress(Integer.parseInt(datas.get(position).getReadiness().getSpace()));
                holder.tv_select_price.setText("单价：" + datas.get(position).getReadiness().getRealPrice() + "/KG");

                if (overspace < 101 && overspace1 < 101) {
                    holder.tv_select_over.setText("爆仓");
                } else if (overspace > 100 && overspace1 < 101) {
                    holder.tv_select_over.setText("备装无位正装可定");
                } else {
                    holder.tv_select_over.setText("剩余舱位" + (100 - Integer.parseInt(datas.get(position).getReadiness().getSpace())) + "%");
                }
            }
        }


        holder.tv_select_name.setText(datas.get(position).getStartPort());
        holder.tv_select_shop.setText(datas.get(position).getProductIcons().getSupplierName());

        holder.tv_select_company.setText(datas.get(position).getProductName());
        holder.tv_sale_count.setText("销量； " + datas.get(position).getSalesCount());
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
        private final TextView tv_sale_count;
        private final ProgressBar pv_select;
        private final AutoLinearLayout all_select_item;
        private final AutoLinearLayout all_total;
        private final TextView tv_total_yg;
        private final AutoRelativeLayout arl_look;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_select_name = itemView.findViewById(R.id.tv_select_name);
            tv_select_company = itemView.findViewById(R.id.tv_select_company);
            tv_select_price = itemView.findViewById(R.id.tv_select_price);
            tv_select_shop = itemView.findViewById(R.id.tv_select_shop);
            tv_select_over = itemView.findViewById(R.id.tv_select_over);
            tv_select_look = itemView.findViewById(R.id.tv_select_look);
            pv_select = itemView.findViewById(R.id.pv_select);
            tv_sale_count = itemView.findViewById(R.id.tv_sale_count);
            all_select_item = itemView.findViewById(R.id.all_select_item);
            all_total = itemView.findViewById(R.id.all_total);
            tv_total_yg = itemView.findViewById(R.id.tv_total_yg);
            arl_look = itemView.findViewById(R.id.arl_look);
        }
    }

    public abstract void seOnItemClick(int selectPostion, String airline);
}
