package com.youyijia.hyoukalibrary.base;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * 描述:
 * -
 * 工程:
 * #0000    Tian Xiao    创建日期: 2016-07-08 11:50
 */
public abstract class BaseRecyclerViewHolder extends ViewHolder {
    public BaseRecyclerViewHolder(View mview) {
        super(mview);
        //自动布局
        AutoUtils.autoSize(mview);
    }
}
