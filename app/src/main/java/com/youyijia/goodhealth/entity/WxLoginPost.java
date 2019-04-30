package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/25.
 */
public class WxLoginPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public String getWxCode() {
            return wxCode;
        }

        public void setWxCode(String wxCode) {
            this.wxCode = wxCode;
        }

        private String wxCode;


    }
}
