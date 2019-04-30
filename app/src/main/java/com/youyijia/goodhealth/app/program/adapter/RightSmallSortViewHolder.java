package com.youyijia.goodhealth.app.program.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.youyijia.goodhealth.entity.ProgramSelectInfo;
import com.youyijia.goodhealth.utils.SimpleRecyclerAdapter;
import com.youyijia.goodhealth.utils.SimpleViewHolder;

/**
 * Created by wangqiang on 2019/1/17.
 */
public class RightSmallSortViewHolder extends SimpleViewHolder<ProgramSelectInfo.ProductCardListBean> {
    private TextView textView;

    public RightSmallSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<ProgramSelectInfo.ProductCardListBean> adapter) {
        super(itemView, adapter);
//        textView = itemView.findViewById(R.id.tv_small);
    }

    @Override
    protected void refreshView(ProgramSelectInfo.ProductCardListBean data) {
//        textView.setText(data.name);
    }
}
