package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/17.
 */
public class ZanPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        private String commentId;

    }
}
