package com.youyijia.goodhealth.app.program.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.youyijia.goodhealth.R;
import com.youyijia.goodhealth.entity.ItemType;
import com.youyijia.goodhealth.entity.ProgramSelectInfo;
import com.youyijia.goodhealth.utils.SimpleRecyclerAdapter;
import com.youyijia.goodhealth.utils.SimpleViewHolder;

/**
 * Created by wangqiang on 2019/1/17.
 */
public class RightAdapter extends SimpleRecyclerAdapter<ProgramSelectInfo.ProductCardListBean> {
    @Override
    public SimpleViewHolder<ProgramSelectInfo.ProductCardListBean> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.BIG_SORT) {
            return new RightBigSortViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_right_big_sort, parent, false), this);
        } else {
            return new RightSmallSortViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_right_small_sort, parent, false), this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mListData.get(position).viewType;
    }
}
