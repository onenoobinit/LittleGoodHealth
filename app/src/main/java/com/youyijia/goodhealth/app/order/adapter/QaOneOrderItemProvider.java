package com.youyijia.goodhealth.app.order.adapter;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.jknews.QaType;
import com.youyijia.goodhealth.app.order.MyTraceActivity;
import com.youyijia.goodhealth.app.pay.PayTypeActivity;
import com.youyijia.goodhealth.entity.OrderInfo;

/**
 * Created by wangqiang on 2019/4/28.
 */

@ItemProviderTag(
        viewType = QaType.QAONEPIC,
        layout = R.layout.adapter_exmine
)
public class QaOneOrderItemProvider extends BaseItemProvider<OrderInfo, BaseViewHolder> {
    @Override
    public void convert(BaseViewHolder helper, OrderInfo data, int position) {
        TextView tv_order_left = helper.getView(R.id.tv_order_left);
        TextView tv_order_right = helper.getView(R.id.tv_order_right);
        RelativeLayout rl_bottom = helper.getView(R.id.rl_bottom);
        if ("绿通订单".equals(data.getOrderType().getText())) {
            helper.setText(R.id.tv_shop_name, "绿通服务");
        } else if ("商品订单".equals(data.getOrderType().getText())) {
            helper.setText(R.id.tv_shop_name, "优选商城");
        }
        if ("待支付".equals(data.getOrderStatus().getText())) {
            rl_bottom.setVisibility(View.VISIBLE);
            tv_order_left.setVisibility(View.VISIBLE);
            tv_order_right.setVisibility(View.VISIBLE);
            tv_order_left.setText("取消订单");
            tv_order_right.setText("立即支付");

            helper.addOnClickListener(R.id.tv_order_left);
            tv_order_right.setOnClickListener(v -> {
                String orderId = data.getOrderId() + "";
                Intent intent = new Intent(mContext, PayTypeActivity.class);
                intent.putExtra("orderId", orderId);
                mContext.startActivity(intent);
            });
        } else if ("待发货".equals(data.getOrderStatus().getText())) {
            rl_bottom.setVisibility(View.GONE);
        } else if ("待收货".equals(data.getOrderStatus().getText())) {
            rl_bottom.setVisibility(View.VISIBLE);
            tv_order_left.setVisibility(View.VISIBLE);
            tv_order_left.setText("查看物流");
            tv_order_right.setVisibility(View.GONE);
            tv_order_left.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, MyTraceActivity.class);
                intent.putExtra("orderId", data.getOrderId() + "");
                mContext.startActivity(intent);
            });
        } else if ("待评价".equals(data.getOrderStatus().getText())) {
            rl_bottom.setVisibility(View.VISIBLE);
            tv_order_left.setVisibility(View.GONE);
            tv_order_right.setVisibility(View.VISIBLE);
            tv_order_right.setText("评价");
            helper.addOnClickListener(R.id.tv_order_right);
        } else if ("已关闭".equals(data.getOrderStatus().getText())) {
            rl_bottom.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_shop_stauts, data.getOrderStatus().getText());
        helper.setText(R.id.tv_order_item_total_number, "共" + data.getCommodityCount() + "件商品");
        helper.setText(R.id.tv_order_item_total_price, "¥" + data.getOrderAmountTotal());
        helper.setText(R.id.tv_order_item_yf, "(含运费：¥" + data.getLogisticsFee() + ")");
        ImageView iv_order_item = helper.getView(R.id.iv_order_item);
        TextView tv_order_out_price = helper.getView(R.id.tv_order_out_price);
        tv_order_out_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().skipMemoryCache(true).placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon);
        Glide.with(mContext)
                .load(data.getOrderItems().get(0).getImageUrl())
                .apply(options)
                .into(iv_order_item);
        helper.setText(R.id.tv_order_whole_name, data.getOrderItems().get(0).getCommodityName());
        helper.setText(R.id.tv_order_real_price, "¥" + data.getOrderItems().get(0).getPresentUnitPrice());
        helper.setText(R.id.tv_order_out_price, "¥" + data.getOrderItems().get(0).getCostUnitPrice());
        helper.setText(R.id.tv_order_shop_number, "×" + data.getOrderItems().get(0).getNumber());
        helper.setText(R.id.tv_order_real_price, "¥" + data.getOrderItems().get(0).getPresentUnitPrice());
        helper.addOnClickListener(R.id.ll_order_item);

    }

    @Override
    public void onClick(BaseViewHolder helper, OrderInfo data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, OrderInfo data, int position) {
        return false;
    }
}
