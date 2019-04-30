package com.youyijia.goodhealth.app.jknews.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.youyijia.goodhealth.app.jknews.QaType;
import com.youyijia.goodhealth.entity.JkNewsInfo;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/12.
 */
public class TotalAdapter extends MultipleItemRvAdapter<JkNewsInfo, BaseViewHolder> {
    public TotalAdapter(@Nullable List<JkNewsInfo> data) {
        super(data);
        finishInitialize();
    }

    @Override
    protected int getViewType(JkNewsInfo jkNewsInfo) {
        int size = jkNewsInfo.getTitleImgList().size();
        if (size == 1) {
            return QaType.QAONEPIC;
        } else if (size == 3) {
            return QaType.QAMorePIC;
        }
        return 1;
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(new QaOnePicItemProvider());
        mProviderDelegate.registerProvider(new QaMorePicItemProvider());
    }
}
