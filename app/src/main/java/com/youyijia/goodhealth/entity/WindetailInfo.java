package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/17.
 */
public class WindetailInfo {

    /**
     * articleLike : {"articleId":23,"articleLikeId":"5b85154012d708244dfc4432","articleLikeState":{"code":2,"name":"DISLIKE","text":"未点赞"},"articleType":{"code":1,"name":"HEALTH_DIRECTION","text":"健康风向"},"customerIdSet":[],"likeCounts":0}
     * articleStatus : {"code":0,"name":"NORMAL","text":"正常"}
     * author : 官方
     * carouselStatus : {"code":1030,"name":"DISABLED","text":"未轮播"}
     * commdityList : []
     * commentCount : 5
     * companyId : -1
     * createDate : 2018-09-19 17:15:44
     * directionId : 23
     * introduction : 益生菌与人体微生态息息相关，肠道微生态菌群失衡会引起身材臃肿、胀气、脸色暗黄、口气大等问题。
     * mainBody :
     * pageviews : 534
     * position : {"code":2,"name":"HOMEPAGE","text":"首页"}
     * projectIds : []
     * title : 微生态研究中心专家沈博士讲益生菌与人体微生态息息相关的故事
     * titleImgList : ["https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/622d0691-bc8c-4b28-8658-e96a499131f4.png?height=601&width=972"]
     * titleImgUrl1 : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/622d0691-bc8c-4b28-8658-e96a499131f4.png?height=601&width=972
     */

    private ArticleLikeBean articleLike;
    private ArticleStatusBean articleStatus;
    private String author;
    private CarouselStatusBean carouselStatus;
    private String commentCount;
    private int companyId;
    private String createDate;
    private String directionId;
    private String introduction;
    private String mainBody;
    private String pageviews;
    private PositionBean position;
    private String title;
    private String titleImgUrl1;
    private List<?> commdityList;
    private List<?> projectIds;
    private List<String> titleImgList;

    public ArticleLikeBean getArticleLike() {
        return articleLike;
    }

    public void setArticleLike(ArticleLikeBean articleLike) {
        this.articleLike = articleLike;
    }

    public ArticleStatusBean getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(ArticleStatusBean articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public CarouselStatusBean getCarouselStatus() {
        return carouselStatus;
    }

    public void setCarouselStatus(CarouselStatusBean carouselStatus) {
        this.carouselStatus = carouselStatus;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDirectionId() {
        return directionId;
    }

    public void setDirectionId(String directionId) {
        this.directionId = directionId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMainBody() {
        return mainBody;
    }

    public void setMainBody(String mainBody) {
        this.mainBody = mainBody;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleImgUrl1() {
        return titleImgUrl1;
    }

    public void setTitleImgUrl1(String titleImgUrl1) {
        this.titleImgUrl1 = titleImgUrl1;
    }

    public List<?> getCommdityList() {
        return commdityList;
    }

    public void setCommdityList(List<?> commdityList) {
        this.commdityList = commdityList;
    }

    public List<?> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<?> projectIds) {
        this.projectIds = projectIds;
    }

    public List<String> getTitleImgList() {
        return titleImgList;
    }

    public void setTitleImgList(List<String> titleImgList) {
        this.titleImgList = titleImgList;
    }

    public static class ArticleLikeBean {
        /**
         * articleId : 23
         * articleLikeId : 5b85154012d708244dfc4432
         * articleLikeState : {"code":2,"name":"DISLIKE","text":"未点赞"}
         * articleType : {"code":1,"name":"HEALTH_DIRECTION","text":"健康风向"}
         * customerIdSet : []
         * likeCounts : 0
         */

        private int articleId;
        private String articleLikeId;
        private ArticleLikeStateBean articleLikeState;
        private ArticleTypeBean articleType;
        private int likeCounts;
        private List<?> customerIdSet;

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getArticleLikeId() {
            return articleLikeId;
        }

        public void setArticleLikeId(String articleLikeId) {
            this.articleLikeId = articleLikeId;
        }

        public ArticleLikeStateBean getArticleLikeState() {
            return articleLikeState;
        }

        public void setArticleLikeState(ArticleLikeStateBean articleLikeState) {
            this.articleLikeState = articleLikeState;
        }

        public ArticleTypeBean getArticleType() {
            return articleType;
        }

        public void setArticleType(ArticleTypeBean articleType) {
            this.articleType = articleType;
        }

        public int getLikeCounts() {
            return likeCounts;
        }

        public void setLikeCounts(int likeCounts) {
            this.likeCounts = likeCounts;
        }

        public List<?> getCustomerIdSet() {
            return customerIdSet;
        }

        public void setCustomerIdSet(List<?> customerIdSet) {
            this.customerIdSet = customerIdSet;
        }

        public static class ArticleLikeStateBean {
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
    }

    public static class ArticleStatusBean {
        /**
         * code : 0
         * name : NORMAL
         * text : 正常
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

    public static class CarouselStatusBean {
        /**
         * code : 1030
         * name : DISABLED
         * text : 未轮播
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

    public static class PositionBean {
        /**
         * code : 2
         * name : HOMEPAGE
         * text : 首页
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
}
