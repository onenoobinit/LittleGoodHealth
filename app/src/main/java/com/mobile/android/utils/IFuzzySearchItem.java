package com.mobile.android.utils;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/15.
 */
public interface IFuzzySearchItem {
    /**
     * 获取item原始字符串
     *
     * @return 原始item字符串
     */
    String getSourceKey();

    /**
     * 获取item模糊字符串,item对应的拼音 江西省->["jiang", "xi", "sheng"]
     *
     * @return 模糊item字符串
     */
    List<String> getFuzzyKey();
}
