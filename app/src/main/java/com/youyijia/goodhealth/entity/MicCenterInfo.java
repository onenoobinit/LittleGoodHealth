package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/10.
 */
public class MicCenterInfo {
    /**
     * banner : [{"advertStatus":{"code":0,"name":"ENABLED","text":"未知"},"banner":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/b149519b-d4cc-4f53-965e-bd881cbb323e.png","bannerAim":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/4e619213-1b15-4acb-8c4e-c54a3fc6c8cb.png","bannerName":"微生态中心","bannerType":{"code":1010,"name":"LONG_IMAGE","text":"长图"},"channelId":1,"id":1,"priority":1},{"advertStatus":{"code":0,"name":"ENABLED","text":"未知"},"banner":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/47994fda-44b6-4d50-a0ef-3cef7e4784b2.png","bannerAim":"9","bannerName":"益生菌","bannerType":{"code":1040,"name":"COMMONDITY","text":"商品"},"channelId":1,"id":3,"priority":2},{"advertStatus":{"code":0,"name":"ENABLED","text":"未知"},"banner":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/797cb99c-4056-4437-9b9b-d3952cbfe501.jpg","bannerAim":"1","bannerName":"十院专家0距离，肠道微生态健康在线咨询","bannerType":{"code":1060,"name":"LECTURE_ROOM","text":"在线讲堂"},"channelId":1,"id":2,"priority":3}]
     * doctor : [{"channelId":1,"doctorField":"","doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/b7d57068-2f67-4e43-be79-edd003825085.png","doctorIntroduction":"上海市第十人民医院普外科副主任医师， 医学博士，中华预防医学会微生态分会青年委员，上海预防医学会微生态专业组委员兼秘书，中华医学会肠内与肠外营养分会营养与微生态协作组成员，中国微生态学杂志特约编辑","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"沈通一","doctorTitle":"副主任医师","id":1,"lectureCount":3,"lectureStatus":{"code":1,"name":"UNDERWAY","text":"在线咨询进行中"}},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/a059e81b-aca4-4669-8b99-942dc1844a22.png","doctorIntroduction":"同济大学附属第十人民医院兼职教授，南京总医院副院长，南京大学临床学院副院长、主任医师、教授、博士生导师，著名普外科学专家，肠外瘘治疗的创始人，临床营养支持的奠基人","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"黎介寿","doctorTitle":"中国工程院院士、中心顾问","id":4,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/b1382099-1d1c-4654-bd69-7202aae89629.png","doctorIntroduction":"同济大学附属第十人民医院兼职教授，南京军区南京总医院普通外科主任医师，解放军普通外科研究所副所长，第二军医大学和南京大学、医学院教授、博士生导师","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"李宁","doctorTitle":"中心名誉主任","id":5,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/243a6fa6-e5b0-46b1-aea4-f255ac6f82b7.png","doctorIntroduction":"同济大学附属第十人民医院院长、教授、 博士生导师，国家千百万人才工程、国家教育部新世纪优秀人才，上海市领军人才，中华医学会肠外肠内营养学分会/预防医学会微生态学专委会常务委员，上海市预防医学会微生态专业委员会主任委员，上海医学会医学会肠外肠内营养分会候任主任委员，上海市大肠癌专业委员会副组长，《中华临床营养杂志》等杂志副主编，开展微生态治疗研究近20年","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"秦环龙","doctorTitle":"中心主任","id":7,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/cfec3624-39dc-4b58-ab4a-a45f522d03aa.png","doctorIntroduction":"同济大学附属皮肤病医院副主任医师","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"丁杨峰","doctorTitle":"核心专家成员","id":2,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/a3bb34bb-5132-4e78-8dfb-58980106d384.png","doctorIntroduction":"同济大学附属第十人民医院主任医师、副教授、硕士生导师，同济大学附属第十人民医院儿科主任，同济大学医学院医学一系儿科教研室主任","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"谷丽","doctorTitle":"核心专家成员","id":3,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ac9f5a8a-5ee6-4dc9-8f69-38dc5408ed0b.png","doctorIntroduction":"同济大学附属第十人民医院主任医师、教授、博士生导师，担任同济大学附属第十人民医院消化内科主任，比利时魯汶大学医学博士学位，美国哈佛大学和康涅狄格州立大学医学院消化系疾病博士后研究员，现为比利时胃肠病学会，美国胃肠病学会和美国免疫学会会员，中华医学会消化病学分会中青年委员和炎症性肠病学科组委员","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"刘占举","doctorTitle":"核心专家成员","id":6,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/8c4fd828-1b80-4adf-a7bc-7edabc5db758.png","doctorIntroduction":"同济大学附属第十人民医院主任医师、教授、博士生导师，同济大学附属第十人民医院内分泌科主任","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"曲伸","doctorTitle":"核心专家成员","id":8,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/a166e963-ad08-4d6f-b893-83079864077c.png","doctorIntroduction":"同济大学附属第十人民医院主任医师、教授、博士生导师，同济大学附属第十人民医院心内科主任","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"徐亚伟","doctorTitle":"核心专家成员","id":9,"lectureCount":0},{"channelId":1,"doctorHeadimg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/79c2be6f-1069-4dbb-a421-365c3e6b92d7.png","doctorIntroduction":"同济大学附属第十人民医院主任医师、教授、博士生导师，同济大学腹部疑难病诊治中心主任","doctorIntroductionBr":"上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑","doctorName":"尹路","doctorTitle":"核心专家成员","id":10,"lectureCount":0}]
     * doctorPageinfo : {"limit":10,"offset":0,"pageNum":1,"totalCount":10,"totalPage":1}
     */

    private DoctorPageinfoBean doctorPageinfo;
    private List<BannerBean> banner;
    private List<DoctorBean> doctor;

    public DoctorPageinfoBean getDoctorPageinfo() {
        return doctorPageinfo;
    }

    public void setDoctorPageinfo(DoctorPageinfoBean doctorPageinfo) {
        this.doctorPageinfo = doctorPageinfo;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<DoctorBean> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<DoctorBean> doctor) {
        this.doctor = doctor;
    }

    public static class DoctorPageinfoBean {
        /**
         * limit : 10
         * offset : 0
         * pageNum : 1
         * totalCount : 10
         * totalPage : 1
         */

        private int limit;
        private int offset;
        private int pageNum;
        private int totalCount;
        private int totalPage;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }
    }

    public static class BannerBean {
        /**
         * advertStatus : {"code":0,"name":"ENABLED","text":"未知"}
         * banner : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/b149519b-d4cc-4f53-965e-bd881cbb323e.png
         * bannerAim : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/4e619213-1b15-4acb-8c4e-c54a3fc6c8cb.png
         * bannerName : 微生态中心
         * bannerType : {"code":1010,"name":"LONG_IMAGE","text":"长图"}
         * channelId : 1
         * id : 1
         * priority : 1
         */

        private AdvertStatusBean advertStatus;
        private String banner;
        private String bannerAim;
        private String bannerName;
        private BannerTypeBean bannerType;
        private int channelId;
        private int id;
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

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }

    public static class DoctorBean {
        /**
         * channelId : 1
         * doctorField :
         * doctorHeadimg : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/b7d57068-2f67-4e43-be79-edd003825085.png
         * doctorIntroduction : 上海市第十人民医院普外科副主任医师， 医学博士，中华预防医学会微生态分会青年委员，上海预防医学会微生态专业组委员兼秘书，中华医学会肠内与肠外营养分会营养与微生态协作组成员，中国微生态学杂志特约编辑
         * doctorIntroductionBr : 上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑
         * doctorName : 沈通一
         * doctorTitle : 副主任医师
         * id : 1
         * lectureCount : 3
         * lectureStatus : {"code":1,"name":"UNDERWAY","text":"在线咨询进行中"}
         */

        private int channelId;
        private String doctorField;
        private String doctorHeadimg;
        private String doctorIntroduction;
        private String doctorIntroductionBr;
        private String doctorName;
        private String doctorTitle;
        private String id;
        private String lectureCount;
        private LectureStatusBean lectureStatus;

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public String getDoctorField() {
            return doctorField;
        }

        public void setDoctorField(String doctorField) {
            this.doctorField = doctorField;
        }

        public String getDoctorHeadimg() {
            return doctorHeadimg;
        }

        public void setDoctorHeadimg(String doctorHeadimg) {
            this.doctorHeadimg = doctorHeadimg;
        }

        public String getDoctorIntroduction() {
            return doctorIntroduction;
        }

        public void setDoctorIntroduction(String doctorIntroduction) {
            this.doctorIntroduction = doctorIntroduction;
        }

        public String getDoctorIntroductionBr() {
            return doctorIntroductionBr;
        }

        public void setDoctorIntroductionBr(String doctorIntroductionBr) {
            this.doctorIntroductionBr = doctorIntroductionBr;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDoctorTitle() {
            return doctorTitle;
        }

        public void setDoctorTitle(String doctorTitle) {
            this.doctorTitle = doctorTitle;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLectureCount() {
            return lectureCount;
        }

        public void setLectureCount(String lectureCount) {
            this.lectureCount = lectureCount;
        }

        public LectureStatusBean getLectureStatus() {
            return lectureStatus;
        }

        public void setLectureStatus(LectureStatusBean lectureStatus) {
            this.lectureStatus = lectureStatus;
        }

        public static class LectureStatusBean {
            /**
             * code : 1
             * name : UNDERWAY
             * text : 在线咨询进行中
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
