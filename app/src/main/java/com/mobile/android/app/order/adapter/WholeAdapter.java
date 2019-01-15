package com.mobile.android.app.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.android.R;
import com.mobile.android.entity.OrderInfo;
import com.mobile.hyoukalibrary.glide.CircleTransform;
import com.mobile.hyoukalibrary.utils.DateUtil;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wangqiang on 2019/1/4.
 */
public class WholeAdapter extends RecyclerView.Adapter<WholeAdapter.MyViewHolder> {
    private final Context mContext;
    private ArrayList<OrderInfo.OrderBillListInfoBean> datas;
    private int type;

    public WholeAdapter(Context context, int i, ArrayList<OrderInfo.OrderBillListInfoBean> complains) {
        this.mContext = context;
        this.type = i;
        this.datas = complains;
    }

    public void setData(ArrayList<OrderInfo.OrderBillListInfoBean> complains) {
        this.datas = complains;
    }

    public ArrayList<OrderInfo.OrderBillListInfoBean> getData() {
        return datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_whole, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(datas.get(position).getAirlineImg())
                .transform(new CircleTransform(mContext))
                .into(holder.iv_item_icon);

        if (type == 0) {
            holder.tv_order_stauts.setVisibility(View.GONE);
            holder.tv_order_shenhe.setVisibility(View.GONE);
        }

        if (type == 1) {
            holder.tv_order_shenhe.setVisibility(View.VISIBLE);
            holder.tv_order_stauts.setVisibility(View.GONE);
            if ("1".equals(datas.get(position).getReviewType())) {
                holder.tv_order_shenhe.setText("订单审核中");
            } else if ("1".equals(datas.get(position).getReviewType())) {
                holder.tv_order_shenhe.setText("改期审核");
            } else if ("1".equals(datas.get(position).getReviewType())) {
                holder.tv_order_shenhe.setText("取消审核");
            }
        }

        if (type == 2) {
            holder.tv_order_shenhe.setVisibility(View.GONE);
            holder.tv_order_stauts.setVisibility(View.VISIBLE);
            if ("1".equals(datas.get(position).getOperating().getIntoCabinConfirm())) {
                holder.tv_order_stauts.setText("需确认入库数据");
            } else if ("1".equals(datas.get(position).getOperating().getFreightFeeConfirm())) {
                holder.tv_order_stauts.setText("需确认优化运费数据");
            } else if ("1".equals(datas.get(position).getOperating().getMakeBillConfirm())) {
                holder.tv_order_stauts.setText("需确认制单数据");
            } else if ("1".equals(datas.get(position).getOperating().getMakeBill())) {
                holder.tv_order_stauts.setText("等待主分单制作");
            } else if ("1".equals(datas.get(position).getOperating().getFeeConfirm())) {
                holder.tv_order_stauts.setText("等待确认运费");
            }
        }

        if (type == 3) {
            holder.tv_order_shenhe.setVisibility(View.GONE);
            holder.tv_order_stauts.setVisibility(View.VISIBLE);
            holder.tv_order_stauts.setText("待付款");
        }

        if (type == 4) {
            holder.tv_order_shenhe.setVisibility(View.GONE);
            holder.tv_order_stauts.setVisibility(View.VISIBLE);
            holder.tv_order_stauts.setText("待评论");
        }

        if ("1".equals(datas.get(position).getOperating().getIntoCabinConfirm())) {
            holder.tv_item_pieces.setText(datas.get(position).getGoodsData().getBookingData().getPieces() + "PCS");
            holder.tv_item_weight.setText(datas.get(position).getGoodsData().getBookingData().getWeight() + "KG");
            holder.tv_item_vol.setText(datas.get(position).getGoodsData().getBookingData().getWeight() + "CBM");
            holder.tv_item_propor.setText("1:" + datas.get(position).getGoodsData().getBookingData().getProportion());
        } else if ("1".equals(datas.get(position).getOperating().getFreightFeeConfirm())) {
            holder.tv_item_pieces.setText(datas.get(position).getGoodsData().getIntoCabin().getPieces() + "PCS");
            holder.tv_item_weight.setText(datas.get(position).getGoodsData().getIntoCabin().getWeight() + "KG");
            holder.tv_item_vol.setText(datas.get(position).getGoodsData().getIntoCabin().getWeight() + "CBM");
            holder.tv_item_propor.setText("1:" + datas.get(position).getGoodsData().getIntoCabin().getProportion());
        } else {
            holder.tv_item_pieces.setText(datas.get(position).getGoodsData().getPrintList().getPieces() + "PCS");
            holder.tv_item_weight.setText(datas.get(position).getGoodsData().getPrintList().getWeight() + "KG");
            holder.tv_item_vol.setText(datas.get(position).getGoodsData().getPrintList().getWeight() + "CBM");
            holder.tv_item_propor.setText("1:" + datas.get(position).getGoodsData().getPrintList().getProportion());
        }

        if ("1".equals(datas.get(position).getInsuranceIcons().getGoodsExtension().getBuyType())) {
            holder.tvItemInsurance.setVisibility(View.VISIBLE);
        } else {
            holder.tvItemInsurance.setVisibility(View.GONE);
        }

        holder.tvStartPy.setText(datas.get(position).getStartPort());
        holder.tvStartHy.setText(datas.get(position).getStartPortCN());
        holder.tvEndPy.setText(datas.get(position).getEndPort());
        holder.tvEndHy.setText(datas.get(position).getEndPortCN());
        if (TextUtils.isEmpty(datas.get(position).getDrCode())) {
            holder.tvOrderNumnber.setText("运单号: 无");
        } else {
            holder.tvOrderNumnber.setText("运单号: " + datas.get(position).getDrCode());
        }


        String dateToString = DateUtil.getDateToString(Long.parseLong(datas.get(position).getCreationDate()));
        holder.tvOrderDate.setText(dateToString);


        String shippingDate = datas.get(position).getShippingDate();
        String itemdate = shippingDate.substring(5);
        holder.tvItemOrderDate.setText(itemdate);
        holder.tvItemMoney.setText("预计费用¥  " + datas.get(position).getPrice() + ".00");
        holder.tv_shop_name.setText(datas.get(position).getSupplierName());
    }


    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvOrderDate;
        private final TextView tvOrderNumnber;
        private final TextView tvItemOrderDate;
        private final TextView tvStartPy;
        private final TextView tvStartHy;
        private final TextView tvEndHy;
        private final TextView tvEndPy;
        private final TextView tvItemInsurance;
        private final TextView tvItemMoney;
        private final TextView tv_order_stauts;
        private final TextView tv_order_shenhe;
        private final TextView tv_shop_name;
        private final TextView tv_item_pieces;
        private final TextView tv_item_weight;
        private final TextView tv_item_vol;
        private final TextView tv_item_propor;
        private final CircleImageView iv_item_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrderDate = itemView.findViewById(R.id.tv_order_date);
            tvOrderNumnber = itemView.findViewById(R.id.tv_order_numnber);
            tvItemOrderDate = itemView.findViewById(R.id.tv_item_order_date);
            tvStartPy = itemView.findViewById(R.id.tv_start_py);
            tvStartHy = itemView.findViewById(R.id.tv_start_hy);
            tvEndHy = itemView.findViewById(R.id.tv_end_hy);
            tvEndPy = itemView.findViewById(R.id.tv_end_py);
            tvItemInsurance = itemView.findViewById(R.id.tv_item_Insurance);
            tvItemMoney = itemView.findViewById(R.id.tv_item_money);
            tv_order_stauts = itemView.findViewById(R.id.tv_order_stauts);
            tv_order_shenhe = itemView.findViewById(R.id.tv_order_shenhe);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            tv_item_pieces = itemView.findViewById(R.id.tv_item_pieces);
            tv_item_weight = itemView.findViewById(R.id.tv_item_weight);
            tv_item_vol = itemView.findViewById(R.id.tv_item_vol);
            tv_item_propor = itemView.findViewById(R.id.tv_item_propor);
            iv_item_icon = itemView.findViewById(R.id.iv_item_icon);
        }
    }
}
