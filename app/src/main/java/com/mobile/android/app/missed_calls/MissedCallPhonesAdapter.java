package com.mobile.android.app.missed_calls;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mobile.android.R;
import com.mobile.android.entity.MissedCallsBean;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/17
 * @describe
 */

public class MissedCallPhonesAdapter extends BaseQuickAdapter<MissedCallsBean,BaseViewHolder>{

    public MissedCallPhonesAdapter(int layoutResId, @Nullable List<MissedCallsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MissedCallsBean item) {
        helper.setText(R.id.tv_owner_name,item.getName());
        helper.setText(R.id.tv_owner_id,"(ID号: "+item.getBaoming_id()+")");
        helper.setText(R.id.tv_ld_time,item.getCall_time());
        helper.setText(R.id.tv_latest_qd_time,"您最近一次去电时间: "+item.getLast_time());
        helper.addOnClickListener(R.id.all_call_phone);
    }
}
