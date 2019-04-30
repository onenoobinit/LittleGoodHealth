package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/16.
 */
public class FeedBackPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        private String content;
        private String phoneNumber;

    }
}
