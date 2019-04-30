package com.youyijia.goodhealth.entity;

/**
 * @author chenliangzhi
 * @date 2018/5/28
 * @describe
 */

public class TodayBwBean {

    /**
     *  remark_id  : 2
     * remark_txt : 噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜噜
     */

    private String remark_id;
    private String remark_txt;

    public String getRemark_id() {
        return remark_id;
    }

    public void setRemark_id(String remark_id) {
        this.remark_id = remark_id;
    }

    public String getRemark_txt() {
        return remark_txt;
    }

    public void setRemark_txt(String remark_txt) {
        this.remark_txt = remark_txt;
    }

    @Override
    public String toString() {
        return "TodayBwBean{" +
                "remark_id='" + remark_id + '\'' +
                ", remark_txt='" + remark_txt + '\'' +
                '}';
    }
}
