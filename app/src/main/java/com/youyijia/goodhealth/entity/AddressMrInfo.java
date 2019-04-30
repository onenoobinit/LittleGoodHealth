package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/18.
 */
public class AddressMrInfo {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;
    }
}
