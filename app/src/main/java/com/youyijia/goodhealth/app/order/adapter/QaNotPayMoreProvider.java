package com.youyijia.goodhealth.app.order.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.annotation.ItemProviderTag;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.app.jknews.QaType;
import com.youyijia.goodhealth.app.pay.PayTypeActivity;
import com.youyijia.goodhealth.entity.OrderInfo;

import java.util.ArrayList;

/**
 * Created by wangqiang on 2019/4/29.
 */

@ItemProviderTag(
        viewType = QaType.QAMorePIC,
        layout = R.layout.adapter_whole
)
public class QaNotPayMoreProvider extends BaseItemProvider<OrderInfo, BaseViewHolder> {
    private ArrayList<OrderInfo.OrderItemsBean> itemdatas = new ArrayList<>();

    @Override
    public void convert(BaseViewHolder helper, OrderInfo data, int position) {
        TextView tv_order_right = helper.getView(R.id.tv_order_right);
        TextView tv_order_left = helper.getView(R.id.tv_order_left);
        tv_order_left.setText("取消订单");
        tv_order_right.setText("立即支付");
        helper.addOnClickListener(R.id.tv_order_left);
        helper.addOnClickListener(R.id.ll_more_item);
        tv_order_right.setOnClickListener(v -> {
            String orderId = data.getOrderId() + "";
            Intent intent = new Intent(mContext, PayTypeActivity.class);
            intent.putExtra("orderId", orderId);
            mContext.startActivity(intent);
        });
        helper.setText(R.id.tv_shop_stauts, data.getOrderStatus().getText());
        helper.setText(R.id.tv_order_item_total_number, "共" + data.getCommodityCount() + "件商品");
        helper.setText(R.id.tv_order_item_total_price, "¥" + data.getOrderAmountTotal());
        helper.setText(R.id.tv_order_item_yf, "(含运费：¥" + data.getLogisticsFee() + ")");

        itemdatas.clear();
        itemdatas.addAll(data.getOrderItems());
        RecyclerView rv_order_item = helper.getView(R.id.rv_order_item);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_order_item.setLayoutManager(layoutManager);
        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(mContext, itemdatas, 1);
        rv_order_item.setAdapter(orderItemAdapter);
    }

    @Override
    public void onClick(BaseViewHolder helper, OrderInfo data, int position) {

    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, OrderInfo data, int position) {
        return false;
    }
}
