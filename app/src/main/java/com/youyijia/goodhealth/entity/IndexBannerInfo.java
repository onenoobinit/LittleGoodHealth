package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/9.
 */
public class IndexBannerInfo {

    /**
     * advertStatus : {"code":0,"name":"ENABLED","text":"未知"}
     * banner : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/705c3d43-2942-44d5-b354-3111be071503.jpg?height=293&width=512
     * bannerAim : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ad5b0519-3789-454b-99f7-0d49ce44e080.jpg?height=566&width=1000
     * bannerName : 13
     * bannerType : {"code":1010,"name":"LONG_IMAGE","text":"长图"}
     * id : 104
     * launchPlatform : {"code":1030,"name":"ALL","text":"all"}
     * position : {"code":1010,"name":"HEAD","text":"首页"}
     * priority : 1
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
         * code : 1010
         * name : LONG_IMAGE
         * text : 长图
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
         * code : 1010
         * name : HEAD
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
