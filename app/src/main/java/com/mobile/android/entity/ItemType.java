package com.mobile.android.entity;

import android.support.annotation.IntDef;

/**
 * Created by wangqiang on 2019/1/17.
 */
@IntDef({ItemType.BIG_SORT, ItemType.SMALL_SORT})
public @interface ItemType {
    int BIG_SORT = 0;
    int SMALL_SORT = 1;
}
