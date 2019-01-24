package com.mobile.android.app.program.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.ProDateInfo;
import com.mobile.android.utils.DateUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/8.
 */
public abstract class DetailDateAdapter extends RecyclerView.Adapter<DetailDateAdapter.MyViewHolder> {
    private final Context mContext;
    private final String book;//0是正装   1是备装
    private List<ProDateInfo.ProductDateListBean> datas;
    public static int selectPostion = 0;
    private int overSpace;
    private double book1;
    private Double book2;

    public DetailDateAdapter(Context context, List<ProDateInfo.ProductDateListBean> data, String book) {
        this.mContext = context;
        this.datas = data;
        this.book = book;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_date_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String week = DateUtils.getWeek(datas.get(position).getDate());
        String substring = datas.get(position).getDate().substring(5);
        holder.tv_item_date.setText(substring);
        holder.tv_item_week.setText(week);


        if (!TextUtils.isEmpty(datas.get(position).getErrData())) {
            holder.tv_weight_number.setVisibility(View.INVISIBLE);
            holder.tv_item_price.setText(datas.get(position).getErrData());
            if ("无报价".equals(datas.get(position).getErrData())) {
                holder.all_boot.setBackgroundResource(R.mipmap.time);
                holder.all_boot.getBackground().setAlpha(204);
            } else if ("无航班".equals(datas.get(position).getErrData())) {
                holder.all_boot.setBackgroundResource(R.mipmap.no_flag);
            }
            holder.all_item_date.setClickable(false);
        } else if (TextUtils.isEmpty(datas.get(position).getErrData())) {
            holder.all_item_date.setClickable(true);
            holder.tv_weight_number.setVisibility(View.VISIBLE);
            holder.tv_item_price.setText("¥" + datas.get(position).getPrice());
            holder.iv_date_select.setVisibility(View.GONE);
            overSpace = Integer.parseInt(datas.get(position).getSpace().getOverSpace());
            if (overSpace < 100) {
                holder.all_boot.setBackgroundResource(R.mipmap.blow_up);
                holder.tv_weight_number.setText("爆仓");
                holder.tv_weight_number.setTextColor(Color.parseColor("#D0021B"));
                holder.tv_item_week.setTextColor(Color.parseColor("#ffffff"));
                holder.tv_item_date.setTextColor(Color.parseColor("#ffffff"));
            } else {
                holder.iv_date_select.setVisibility(View.VISIBLE);
                holder.tv_item_week.setTextColor(Color.parseColor("#575757"));
                holder.tv_item_date.setTextColor(Color.parseColor("#575757"));
                if (overSpace >= 10 && overSpace < 1000) {
                    holder.all_boot.setBackgroundResource(R.mipmap.time);
                    holder.tv_weight_number.setTextColor(Color.parseColor("#575757"));
                    holder.tv_weight_number.setText(overSpace + "");
                } else if (overSpace > 1000) {
                    int number = overSpace / 1000;
                    if (number > 3) {
                        holder.tv_weight_number.setText("3吨可定");
                    } else {
                        holder.all_boot.setBackgroundResource(R.mipmap.time);
                        holder.tv_weight_number.setTextColor(Color.parseColor("#575757"));
                        holder.tv_weight_number.setText(number + "吨可定");
                    }
                }

                if ("0".equals(book)) {
                    book1 = Double.parseDouble(datas.get(position).getSpace().getSuit());
                } else if ("1".equals(book)) {
                    book1 = Double.parseDouble(datas.get(position).getSpace().getReadiness());
                }

                book2 = book1 / 100;
//                book2 = 0.1 * (position + 1);
                ViewGroup.LayoutParams layoutParams = holder.iv_date_select.getLayoutParams();
                int measuredHeight = holder.all_bg_item.getMeasuredHeight();
                layoutParams.height = (int) (measuredHeight * book2);
                holder.iv_date_select.setLayoutParams(layoutParams);
            }

            holder.all_item_date.setOnClickListener(view -> {
                selectPostion = position;
                setOnItemClickListener(datas.get(selectPostion).getDate(), position, overSpace);
                notifyDataSetChanged();
            });
        }


        if (selectPostion == position) {
            holder.all_bg_item.setBackgroundResource(R.drawable.bg_item_date);
        } else {
            holder.all_bg_item.setBackgroundResource(R.drawable.bg_item_date_no);
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final AutoLinearLayout all_item_date;
        private final TextView tv_item_date;
        private final TextView tv_item_week;
        private final AutoRelativeLayout all_bg_item;
        private final TextView tv_weight_number;
        private final TextView tv_item_price;
        private final AutoRelativeLayout all_boot;
        private final ImageView iv_date_select;


        public MyViewHolder(View itemView) {
            super(itemView);
            all_item_date = itemView.findViewById(R.id.all_item_date);
            tv_item_date = itemView.findViewById(R.id.tv_item_date);
            tv_item_week = itemView.findViewById(R.id.tv_item_week);
            all_bg_item = itemView.findViewById(R.id.all_bg_item);
            tv_weight_number = itemView.findViewById(R.id.tv_weight_number);
            tv_item_price = itemView.findViewById(R.id.tv_item_price);
            all_boot = itemView.findViewById(R.id.all_boot);
            iv_date_select = itemView.findViewById(R.id.iv_date_select);

        }
    }

    public abstract void setOnItemClickListener(String position, int postion, int overSpace);
}
