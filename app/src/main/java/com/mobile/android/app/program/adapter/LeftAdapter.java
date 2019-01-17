package com.mobile.android.app.program.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mobile.android.R;
import com.mobile.android.entity.ProgramSelectInfo;
import com.mobile.android.utils.LeftViewHolder;
import com.mobile.android.utils.SimpleRecyclerAdapter;
import com.mobile.android.utils.SimpleViewHolder;

/**
 * Created by wangqiang on 2019/1/17.
 */
public class LeftAdapter extends SimpleRecyclerAdapter<ProgramSelectInfo.AirlineListBean> {

    private int mSelectedPosition;

    @Override
    public SimpleViewHolder<ProgramSelectInfo.AirlineListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeftViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_search_sort_left, parent, false), this);
    }

    public void setSelectedPosition(int position) {
        mListData.get(mSelectedPosition).isSelected = false;
        notifyItemChanged(mSelectedPosition);
        mListData.get(position).isSelected = true;
        notifyItemChanged(position);
        mSelectedPosition = position;
    }
}
