package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/17.
 */
public class SortBean {
    public int bigSortId;
    public String bigSortName;
    public boolean isSelected;

    public List<ProgramSelectInfo.AirlineListBean> list;
    public static class ListBean {
        public int smallSortId;
        public String smallSortName;
    }
}
