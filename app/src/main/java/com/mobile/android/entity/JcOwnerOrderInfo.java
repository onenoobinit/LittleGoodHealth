package com.mobile.android.entity;

import java.util.List;

/**
 * @author chenliangzhi
 * @date 2018/5/29
 * @describe
 */

public class JcOwnerOrderInfo {


    /**
     * list : [{"allot_id":35,"appoint_measure_time":1516325400000}]
     * is_fwsc : 0
     */

    private String is_fwsc;
    private List<ListBean> list;

    public String getIs_fwsc() {
        return is_fwsc;
    }

    public void setIs_fwsc(String is_fwsc) {
        this.is_fwsc = is_fwsc;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * allot_id : 35
         * appoint_measure_time : 1516325400000
         */

        private int allot_id;
        private long appoint_measure_time;

        public int getAllot_id() {
            return allot_id;
        }

        public void setAllot_id(int allot_id) {
            this.allot_id = allot_id;
        }

        public long getAppoint_measure_time() {
            return appoint_measure_time;
        }

        public void setAppoint_measure_time(long appoint_measure_time) {
            this.appoint_measure_time = appoint_measure_time;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "allot_id=" + allot_id +
                    ", appoint_measure_time=" + appoint_measure_time +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JcOwnerOrderInfo{" +
                "is_fwsc='" + is_fwsc + '\'' +
                ", list=" + list +
                '}';
    }
}
