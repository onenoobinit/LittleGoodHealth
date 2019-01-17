package com.mobile.android.utils;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mobile.android.R;
import com.mobile.android.entity.ProgramSelectInfo;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * Created by wangqiang on 2019/1/17.
 */
public class LeftViewHolder extends SimpleViewHolder<ProgramSelectInfo.AirlineListBean> {
    /**
     * tvName显示大类名称，view是显示被选中的黄色标记
     */
    private TextView tvName;
    private ImageView iv_left_icon;
    private final AutoLinearLayout all_slide_left;

    public LeftViewHolder(View itemView, @Nullable SimpleRecyclerAdapter<ProgramSelectInfo.AirlineListBean> adapter) {
        super(itemView, adapter);
        all_slide_left = itemView.findViewById(R.id.all_slide_left);
        tvName = itemView.findViewById(R.id.tv_left_name);
        iv_left_icon = itemView.findViewById(R.id.iv_left_icon);
    }

    @Override
    protected void refreshView(ProgramSelectInfo.AirlineListBean data) {
        Glide.with(getContext())
                .load(data.getImgLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .skipMemoryCache(true)
                .dontTransform()
                .placeholder(R.mipmap.zz_zxal_mrbj_icon)
                .error(R.mipmap.zz_zxal_mrbj_icon)
                .into(iv_left_icon);
        tvName.setText(data.getNameAbbr());
        //item点击后背景的变化
        if (data.isSelected) {
            all_slide_left.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            all_slide_left.setBackgroundColor(Color.parseColor("#F3F2F1"));
        }
    }
}
