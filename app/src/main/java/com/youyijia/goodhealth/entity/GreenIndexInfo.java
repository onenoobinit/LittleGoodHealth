package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/12.
 */
public class GreenIndexInfo {
    /**
     * bannerList : [{"advertStatus":{"code":0,"name":"ENABLED","text":"未知"},"banner":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/43c21040-5443-48de-8b8e-a36f61cae74e.png?height=300&width=750","bannerAim":"","bannerName":"绿色通道","bannerType":{"code":1070,"name":"GREEN_CHANNEL_INTRODUCTION","text":"绿通首页介绍"},"id":10268,"launchPlatform":{"code":1030,"name":"ALL","text":"all"},"position":{"code":1040,"name":"GREEN_CHANNEL","text":"绿通说明"},"priority":1,"projectId":0,"projectName":"个人端通用版"}]
     * introductionImg : {"height":"1850","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/c9373a9e-9f90-4f34-8442-8559ef7bec2d.png?height=1850&width=750","width":"750"}
     * processImg : {"height":"1180","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/31bf02d3-32b7-4c1e-8bba-2ee3319e742b.png?height=1180&width=750","width":"750"}
     */

    private IntroductionImgBean introductionImg;
    private ProcessImgBean processImg;
    private List<BannerListBean> bannerList;

    public IntroductionImgBean getIntroductionImg() {
        return introductionImg;
    }

    public void setIntroductionImg(IntroductionImgBean introductionImg) {
        this.introductionImg = introductionImg;
    }

    public ProcessImgBean getProcessImg() {
        return processImg;
    }

    public void setProcessImg(ProcessImgBean processImg) {
        this.processImg = processImg;
    }

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public static class IntroductionImgBean {
        /**
         * height : 1850
         * url : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/c9373a9e-9f90-4f34-8442-8559ef7bec2d.png?height=1850&width=750
         * width : 750
         */

        private String height;
        private String url;
        private String width;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }

    public static class ProcessImgBean {
        /**
         * height : 1180
         * url : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/31bf02d3-32b7-4c1e-8bba-2ee3319e742b.png?height=1180&width=750
         * width : 750
         */

        private String height;
        private String url;
        private String width;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }

    public static class BannerListBean {
        /**
         * advertStatus : {"code":0,"name":"ENABLED","text":"未知"}
         * banner : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/43c21040-5443-48de-8b8e-a36f61cae74e.png?height=300&width=750
         * bannerAim :
         * bannerName : 绿色通道
         * bannerType : {"code":1070,"name":"GREEN_CHANNEL_INTRODUCTION","text":"绿通首页介绍"}
         * id : 10268
         * launchPlatform : {"code":1030,"name":"ALL","text":"all"}
         * position : {"code":1040,"name":"GREEN_CHANNEL","text":"绿通说明"}
         * priority : 1
         * projectId : 0
         * projectName : 个人端通用版
         */

        private AdvertStatusBean advertStatus;
        private String banner;
        private String bannerAim;
        private String bannerName;
        private BannerTypeBean bannerType;
        private int id;
        private LaunchPlatformBean launchPlatform;
        private PositionBean position;
        private int priority;
        private int projectId;
        private String projectName;

        public AdvertStatusBean getAdvertStatus() {
            return advertStatus;
        }

        public void setAdvertStatus(AdvertStatusBean advertStatus) {
            this.advertStatus = advertStatus;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getBannerAim() {
            return bannerAim;
        }

        public void setBannerAim(String bannerAim) {
            this.bannerAim = bannerAim;
        }

        public String getBannerName() {
            return bannerName;
        }

        public void setBannerName(String bannerName) {
            this.bannerName = bannerName;
        }

        public BannerTypeBean getBannerType() {
            return bannerType;
        }

        public void setBannerType(BannerTypeBean bannerType) {
            this.bannerType = bannerType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public LaunchPlatformBean getLaunchPlatform() {
            return launchPlatform;
        }

        public void setLaunchPlatform(LaunchPlatformBean launchPlatform) {
            this.launchPlatform = launchPlatform;
        }

        public PositionBean getPosition() {
            return position;
        }

        public void setPosition(PositionBean position) {
            this.position = position;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public static class AdvertStatusBean {
            /**
             * code : 0
             * name : ENABLED
             * text : 未知
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

        public static class BannerTypeBean {
            /**
             * code : 1070
             * name : GREEN_CHANNEL_INTRODUCTION
             * text : 绿通首页介绍
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

        public static class LaunchPlatformBean {
            /**
             * code : 1030
             * name : ALL
             * text : all
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
             * code : 1040
             * name : GREEN_CHANNEL
             * text : 绿通说明
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
}
