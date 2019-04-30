package com.youyijia.goodhealth.app.program.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ProgramSelectInfo;
import com.youyijia.goodhealth.utils.SimpleRecyclerAdapter;
import com.youyijia.goodhealth.utils.SimpleViewHolder;

/**
 * Created by wangqiang on 2019/1/17.
 */
public class RightBigSortViewHolder extends SimpleViewHolder<ProgramSelectInfo.ProductCardListBean> {
    TextView tvTitle;

    public RightBigSortViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<ProgramSelectInfo.ProductCardListBean> adapter) {
        super(itemView, adapter);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    }

    @Override
    protected void refreshView(ProgramSelectInfo.ProductCardListBean data) {
//        tvTitle.setText(data.name);
    }
}
