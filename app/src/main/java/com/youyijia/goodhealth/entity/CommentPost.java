package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class CommentPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        private String orderId;
        private String evaluateLevel;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getEvaluateLevel() {
            return evaluateLevel;
        }

        public void setEvaluateLevel(String evaluateLevel) {
            this.evaluateLevel = evaluateLevel;
        }

        public String getEvaluateContent() {
            return evaluateContent;
        }

        public void setEvaluateContent(String evaluateContent) {
            this.evaluateContent = evaluateContent;
        }

        private String evaluateContent;

    }
}
