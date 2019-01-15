package com.mobile.android.entity;

/**
 * Created by wangqiang on 2019/1/11.
 */
public class MeMarkInfo {
    /**
     * orderBillInfo : {"pendingComment":"11","pendingFlight":"152","pendingOperation":"763","pendingPayment":"4","pendingReview":"912"}
     */

    private OrderBillInfoBean orderBillInfo;

    public OrderBillInfoBean getOrderBillInfo() {
        return orderBillInfo;
    }

    public void setOrderBillInfo(OrderBillInfoBean orderBillInfo) {
        this.orderBillInfo = orderBillInfo;
    }

    public static class OrderBillInfoBean {
        /**
         * pendingComment : 11
         * pendingFlight : 152
         * pendingOperation : 763
         * pendingPayment : 4
         * pendingReview : 912
         */

        private String pendingComment;
        private String pendingFlight;
        private String pendingOperation;
        private String pendingPayment;
        private String pendingReview;

        public String getPendingComment() {
            return pendingComment;
        }

        public void setPendingComment(String pendingComment) {
            this.pendingComment = pendingComment;
        }

        public String getPendingFlight() {
            return pendingFlight;
        }

        public void setPendingFlight(String pendingFlight) {
            this.pendingFlight = pendingFlight;
        }

        public String getPendingOperation() {
            return pendingOperation;
        }

        public void setPendingOperation(String pendingOperation) {
            this.pendingOperation = pendingOperation;
        }

        public String getPendingPayment() {
            return pendingPayment;
        }

        public void setPendingPayment(String pendingPayment) {
            this.pendingPayment = pendingPayment;
        }

        public String getPendingReview() {
            return pendingReview;
        }

        public void setPendingReview(String pendingReview) {
            this.pendingReview = pendingReview;
        }
    }
}
