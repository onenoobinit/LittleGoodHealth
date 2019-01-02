package com.mobile.android.app.build_material_feedback;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobile.android.R;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/18
 * @describe
 */

public  class ListSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public ListSelectAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_type, item);
       helper.addOnClickListener(R.id.tv_type);
    }

}
