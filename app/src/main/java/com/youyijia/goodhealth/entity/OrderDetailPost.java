package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/24.
 */
public class OrderDetailPost {
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

        public String getItemNumber() {
            return itemNumber;
        }

        public void setItemNumber(String itemNumber) {
            this.itemNumber = itemNumber;
        }

        private String commodityCount;
        private String itemNumber;

    }
}
