package com.mobile.android.app.program.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.mobile.android.R;
import com.mobile.android.entity.ProgramSelectInfo;
import com.mobile.android.utils.SimpleRecyclerAdapter;
import com.mobile.android.utils.SimpleViewHolder;

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
