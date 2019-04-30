package com.youyijia.goodhealth.app.search;

import com.youyijia.goodhealth.utils.IAZItem;
import com.youyijia.goodhealth.utils.IFuzzySearchItem;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/15.
 */
public class ItemEntity implements IAZItem, IFuzzySearchItem {
    private String       mValue;
    private String       mSortLetters;
    private List<String> mFuzzySearchKey;

    public ItemEntity(String value, String sortLetters, List<String> fuzzySearchKey) {
        mValue = value;
        mSortLetters = sortLetters;
        mFuzzySearchKey = fuzzySearchKey;
    }

    public String getValue() {
        return mValue;
    }

    @Override
    public String getSortLetters() {
        return mSortLetters;
    }

    @Override
    public String getSourceKey() {
        return mValue;
    }

    @Override
    public List<String> getFuzzyKey() {
        return mFuzzySearchKey;
    }
}
