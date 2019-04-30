package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/20.
 */
public class AddToCartPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        private String commodityId;

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityCount() {
            return commodityCount;
        }

        public void setCommodityCount(String commodityCount) {
            this.commodityCount = commodityCount;
        }

        public String getCommoditySpecificationNo() {
            return commoditySpecificationNo;
        }

        public void setCommoditySpecificationNo(String commoditySpecificationNo) {
            this.commoditySpecificationNo = commoditySpecificationNo;
        }

        private String commodityCount;
        private String commoditySpecificationNo;

    }
}
