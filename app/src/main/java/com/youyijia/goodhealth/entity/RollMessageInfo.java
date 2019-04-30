package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/9.
 */
public class RollMessageInfo {
    /**
     * articleId : 24
     * articleSort : {"code":1010,"name":"HEALTH_DIRECTION","text":"健康风向"}
     * title : 优医家健康小屋服务中的企业员工突发重疾，健康助理快速响应，妥善处理
     * flag : {"code":1010,"name":"LATEST","text":"最新"}
     */

    private String articleId;
    private ArticleSortBean articleSort;
    private String title;
    private FlagBean flag;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public ArticleSortBean getArticleSort() {
        return articleSort;
    }

    public void setArticleSort(ArticleSortBean articleSort) {
        this.articleSort = articleSort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FlagBean getFlag() {
        return flag;
    }

    public void setFlag(FlagBean flag) {
        this.flag = flag;
    }

    public static class ArticleSortBean {
        /**
         * code : 1010
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

    public static class FlagBean {
        /**
         * code : 1010
         * name : LATEST
         * text : 最新
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
