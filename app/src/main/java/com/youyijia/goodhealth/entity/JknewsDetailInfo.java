package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/17.
 */
public class JknewsDetailInfo {
    /**
     * articleLike : {"articleId":1,"articleLikeId":"5b42c8d212d7080367e45f82","articleLikeState":{"code":2,"name":"DISLIKE","text":"未点赞"},"articleType":{"code":2,"name":"HEALTH_INFORMATION","text":"健康资讯"},"customerIdSet":[70],"likeCounts":1}
     * articleStatus : {"code":0,"name":"NORMAL","text":"正常"}
     * author : 官方
     * carouselStatus : {"code":1030,"name":"DISABLED","text":"未轮播"}
     * commdityList : []
     * commentCount : 48
     * createDate : 2018-05-22 15:37:05
     * informationId : 1
     * introduction : 2018年3月8日，全球最大的美容微整平台新氧APP和南方周末联合发布《2018中国女性自信报告》，这是首次对国民个体自信情况进行的量化调研报告，揭示了中美民众，特别是女性，在自信表现上的巨大差异，引人深思。
     * label : {"code":4,"name":"FEMALE","text":"女性"}
     * mainBody :
     * mainBodyUrl :
     * pageviews : 818
     * position : {"code":2,"name":"HOMEPAGE","text":"首页"}
     * title : 中国女性习惯性地低估自己，为什么？
     * titleImgList : ["https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/7e574725-0ea1-4b54-8cf2-7520d3d2fdf3.png"]
     * titleImgUrl1 : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/7e574725-0ea1-4b54-8cf2-7520d3d2fdf3.png
     */

    private ArticleLikeBean articleLike;
    private ArticleStatusBean articleStatus;
    private String author;
    private CarouselStatusBean carouselStatus;
    private String commentCount;
    private String createDate;
    private String informationId;
    private String introduction;
    private LabelBean label;
    private String mainBody;
    private String mainBodyUrl;
    private String pageviews;
    private PositionBean position;
    private String title;
    private String titleImgUrl1;
    private List<?> commdityList;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public LabelBean getLabel() {
        return label;
    }

    public void setLabel(LabelBean label) {
        this.label = label;
    }

    public String getMainBody() {
        return mainBody;
    }

    public void setMainBody(String mainBody) {
        this.mainBody = mainBody;
    }

    public String getMainBodyUrl() {
        return mainBodyUrl;
    }

    public void setMainBodyUrl(String mainBodyUrl) {
        this.mainBodyUrl = mainBodyUrl;
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

    public List<String> getTitleImgList() {
        return titleImgList;
    }

    public void setTitleImgList(List<String> titleImgList) {
        this.titleImgList = titleImgList;
    }

    public static class ArticleLikeBean {
        /**
         * articleId : 1
         * articleLikeId : 5b42c8d212d7080367e45f82
         * articleLikeState : {"code":2,"name":"DISLIKE","text":"未点赞"}
         * articleType : {"code":2,"name":"HEALTH_INFORMATION","text":"健康资讯"}
         * customerIdSet : [70]
         * likeCounts : 1
         */

        private int articleId;
        private String articleLikeId;
        private ArticleLikeStateBean articleLikeState;
        private ArticleTypeBean articleType;
        private int likeCounts;
        private List<Integer> customerIdSet;

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

        public List<Integer> getCustomerIdSet() {
            return customerIdSet;
        }

        public void setCustomerIdSet(List<Integer> customerIdSet) {
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
             * code : 2
             * name : HEALTH_INFORMATION
             * text : 健康资讯
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

    public static class LabelBean {
        /**
         * code : 4
         * name : FEMALE
         * text : 女性
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
