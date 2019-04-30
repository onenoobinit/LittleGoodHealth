package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class WxbandPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String code;

    }
}
