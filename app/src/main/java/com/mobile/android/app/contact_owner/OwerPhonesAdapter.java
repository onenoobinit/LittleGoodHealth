package com.mobile.android.app.contact_owner;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobile.android.R;
import com.mobile.android.entity.ContactOwerBean;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/16
 * @describe
 */

public class OwerPhonesAdapter extends BaseQuickAdapter<ContactOwerBean, BaseViewHolder> {
    public OwerPhonesAdapter(int layoutResId, @Nullable List<ContactOwerBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactOwerBean item) {
        helper.setText(R.id.tv_owner_id, "ID号: " + item.getBaoming_id() + "");
        final int position = helper.getAdapterPosition();
        if (position == 0) {
            helper.setText(R.id.tv_call_name, "电话A");
        } else {
            helper.setText(R.id.tv_call_name, "电话B");
        }
        helper.addOnClickListener(R.id.all_call_phone);
    }
}
