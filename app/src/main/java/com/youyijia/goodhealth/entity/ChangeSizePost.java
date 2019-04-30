package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/23.
 */
public class ChangeSizePost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        private String itemId;

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getCommodityCount() {
            return commodityCount;
        }

        public void setCommodityCount(String commodityCount) {
            this.commodityCount = commodityCount;
        }

        private String commodityCount;

    }
}
