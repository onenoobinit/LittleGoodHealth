package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/17.
 */
public class CommentListInfo {
    /**
     * articleCommentLikeState : {"code":2,"name":"DISLIKE","text":"未点赞"}
     * articleId : 23
     * articleType : {"code":1,"name":"HEALTH_DIRECTION","text":"健康风向"}
     * commentContent : 快乐
     * commentId : 5bbf227a12d70839c3797d2c
     * commentItemList : []
     * commentTime : 2018-10-11 18:14:18
     * customerId : 1203
     * customerName : 优医家7
     * customerPortrait : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/733e072c-41a1-4c19-8303-54cbd117f2a1.jpeg?height=2208&width=1242
     * gender : {"code":1020,"name":"FEMALE","text":"女"}
     * likeCounts : 1
     * likePersonSet : [{"customerId":58,"customerName":"猫猫","customerPortrait":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/f484eded-af08-4087-8c91-253e797f9e89.jpeg"}]
     */

    private ArticleCommentLikeStateBean articleCommentLikeState;
    private int articleId;
    private ArticleTypeBean articleType;
    private String commentContent;
    private String commentId;
    private String commentTime;
    private int customerId;
    private String customerName;
    private String customerPortrait;
    private GenderBean gender;
    private int likeCounts;
    private List<?> commentItemList;
    private List<LikePersonSetBean> likePersonSet;

    public ArticleCommentLikeStateBean getArticleCommentLikeState() {
        return articleCommentLikeState;
    }

    public void setArticleCommentLikeState(ArticleCommentLikeStateBean articleCommentLikeState) {
        this.articleCommentLikeState = articleCommentLikeState;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public ArticleTypeBean getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleTypeBean articleType) {
        this.articleType = articleType;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPortrait() {
        return customerPortrait;
    }

    public void setCustomerPortrait(String customerPortrait) {
        this.customerPortrait = customerPortrait;
    }

    public GenderBean getGender() {
        return gender;
    }

    public void setGender(GenderBean gender) {
        this.gender = gender;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public List<?> getCommentItemList() {
        return commentItemList;
    }

    public void setCommentItemList(List<?> commentItemList) {
        this.commentItemList = commentItemList;
    }

    public List<LikePersonSetBean> getLikePersonSet() {
        return likePersonSet;
    }

    public void setLikePersonSet(List<LikePersonSetBean> likePersonSet) {
        this.likePersonSet = likePersonSet;
    }

    public static class ArticleCommentLikeStateBean {
        /**
         * code : 2
         * name : DISLIKE
         * text : 未点赞
         */

        private int code;
        private String name;
        private String text;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class ArticleTypeBean {
        /**
         * code : 1
         * name : HEALTH_DIRECTION
         * text : 健康风向
         */

        private int code;
        private String name;
        private String text;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class GenderBean {
        /**
         * code : 1020
         * name : FEMALE
         * text : 女
         */

        private int code;
        private String name;
        private String text;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class LikePersonSetBean {
        /**
         * customerId : 58
         * customerName : 猫猫
         * customerPortrait : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/f484eded-af08-4087-8c91-253e797f9e89.jpeg
         */

        private int customerId;
        private String customerName;
        private String customerPortrait;

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPortrait() {
            return customerPortrait;
        }

        public void setCustomerPortrait(String customerPortrait) {
            this.customerPortrait = customerPortrait;
        }
    }
}
