package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/1/25.
 */
public class SubmitSuccessInfo {
    /**
     * manualReview : 1
     * orderBillCode : DSGC19010476
     * reviewMobile : 15936089960
     * msgContent :
     */

    private String manualReview;
    private String orderBillCode;
    private String reviewMobile;
    private String msgContent;

    public String getManualReview() {
        return manualReview;
    }

    public void setManualReview(String manualReview) {
        this.manualReview = manualReview;
    }

    public String getOrderBillCode() {
        return orderBillCode;
    }

    public void setOrderBillCode(String orderBillCode) {
        this.orderBillCode = orderBillCode;
    }

    public String getReviewMobile() {
        return reviewMobile;
    }

    public void setReviewMobile(String reviewMobile) {
        this.reviewMobile = reviewMobile;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
