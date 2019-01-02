package com.mobile.android.app.Memorandum;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobile.android.R;
import com.mobile.android.entity.MemoRandomListBean;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/21
 * @describe
 */

public class MemoRandumListAdapter extends BaseQuickAdapter<MemoRandomListBean, BaseViewHolder> {


    public MemoRandumListAdapter(int layoutResId, @Nullable List<MemoRandomListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemoRandomListBean item) {
        helper.setText(R.id.tv_memorandum_date, item.getRemark_time());
        helper.setText(R.id.tv_memorandum_content, item.getRemark_txt());
    }
}
