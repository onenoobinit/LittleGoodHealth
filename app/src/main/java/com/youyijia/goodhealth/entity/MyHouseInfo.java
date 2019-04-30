package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/24.
 */
public class MyHouseInfo {
    /**
     * cabinDetail : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/950838c2-331b-423e-ae68-c5c824da031c.png?height=9600&width=750
     * cabinServiceList : [{"cabinId":67,"id":265,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ea30ff90-00ad-4ab5-9667-56978e02c8da.png?height="},{"cabinId":67,"id":266,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/add21fc6-82ae-4fa4-b1b6-c930ca4d6d9d.png?height="},{"cabinId":67,"id":267,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/c73169a2-22a2-49b1-8bc8-1145ebc030ad.png?height="},{"cabinId":67,"id":268,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/95c95496-fda5-40d1-949f-d4b64ceb9c42.png?height="},{"cabinId":67,"id":269,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/183961bf-dbde-40d7-9908-08c1fe2c62cd.png?height="},{"cabinId":67,"id":270,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/6c2a0926-b358-488a-a897-4d3cc87517fa.png?height="},{"cabinId":67,"id":271,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/8a1ef6a3-024d-4117-9097-a86a9de04377.png?height="},{"cabinId":67,"id":272,"serviceImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/26fc782e-f9ec-48f9-a283-5b742d263d84.png?height="}]
     * healthCabinLogo : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/a970eb08-eaec-46e3-870a-9e0aa972686b.png?height=
     * healthCabinName : 建设银行东莞分行
     * innerImgList : [{"height":"840","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/0d4afa84-8814-4900-8358-07e440b9a616.jpg?height=840&width=1200","width":"1200"},{"height":"140","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/6c2a0926-b358-488a-a897-4d3cc87517fa.png?height=","width":"200"},{"height":"140","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/8a1ef6a3-024d-4117-9097-a86a9de04377.png?height=","width":"200"},{"height":"140","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/26fc782e-f9ec-48f9-a283-5b742d263d84.png?height=","width":"200"}]
     * nurseList : [{"adress":"广东省东莞市南城区体育路5号健升大厦10楼建设银行健康小屋","cabinId":67,"id":71,"nurseCode":"034419001","nurseImg":"","nurseIntroduction":"","nurseName":"黄晓琴","nursePhone":"15817573381","nursePosition":{"code":1010,"name":"NURSE","text":"护士"},"nurseStatus":{"code":1020,"name":"NO_BIND","text":"未绑定用户"},"priority":1},{"cabinId":67,"id":73,"nurseCode":"018819009901115","nurseImg":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/015daf56-5e3d-47a3-83b2-e850b21092fd.jpg?height=640&width=640","nurseIntroduction":"测试一下新增","nurseName":"xjs66","nursePhone":"13152235587","nursePosition":{"code":1010,"name":"NURSE","text":"护士"},"nurseStatus":{"code":1030,"name":"BINDED","text":"已绑定用户"}}]
     */

    private String cabinDetail;
    private String healthCabinLogo;
    private String healthCabinName;
    private List<CabinServiceListBean> cabinServiceList;
    private List<InnerImgListBean> innerImgList;
    private List<NurseListBean> nurseList;

    public String getCabinDetail() {
        return cabinDetail;
    }

    public void setCabinDetail(String cabinDetail) {
        this.cabinDetail = cabinDetail;
    }

    public String getHealthCabinLogo() {
        return healthCabinLogo;
    }

    public void setHealthCabinLogo(String healthCabinLogo) {
        this.healthCabinLogo = healthCabinLogo;
    }

    public String getHealthCabinName() {
        return healthCabinName;
    }

    public void setHealthCabinName(String healthCabinName) {
        this.healthCabinName = healthCabinName;
    }

    public List<CabinServiceListBean> getCabinServiceList() {
        return cabinServiceList;
    }

    public void setCabinServiceList(List<CabinServiceListBean> cabinServiceList) {
        this.cabinServiceList = cabinServiceList;
    }

    public List<InnerImgListBean> getInnerImgList() {
        return innerImgList;
    }

    public void setInnerImgList(List<InnerImgListBean> innerImgList) {
        this.innerImgList = innerImgList;
    }

    public List<NurseListBean> getNurseList() {
        return nurseList;
    }

    public void setNurseList(List<NurseListBean> nurseList) {
        this.nurseList = nurseList;
    }

    public static class CabinServiceListBean {
        /**
         * cabinId : 67
         * id : 265
         * serviceImg : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ea30ff90-00ad-4ab5-9667-56978e02c8da.png?height=
         */

        private int cabinId;
        private int id;
        private String serviceImg;

        public int getCabinId() {
            return cabinId;
        }

        public void setCabinId(int cabinId) {
            this.cabinId = cabinId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(String serviceImg) {
            this.serviceImg = serviceImg;
        }
    }

    public static class InnerImgListBean {
        /**
         * height : 840
         * url : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/0d4afa84-8814-4900-8358-07e440b9a616.jpg?height=840&width=1200
         * width : 1200
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

    public static class NurseListBean {
        /**
         * adress : 广东省东莞市南城区体育路5号健升大厦10楼建设银行健康小屋
         * cabinId : 67
         * id : 71
         * nurseCode : 034419001
         * nurseImg :
         * nurseIntroduction :
         * nurseName : 黄晓琴
         * nursePhone : 15817573381
         * nursePosition : {"code":1010,"name":"NURSE","text":"护士"}
         * nurseStatus : {"code":1020,"name":"NO_BIND","text":"未绑定用户"}
         * priority : 1
         */

        private String adress;
        private int cabinId;
        private int id;
        private String nurseCode;
        private String nurseImg;
        private String nurseIntroduction;
        private String nurseName;
        private String nursePhone;
        private NursePositionBean nursePosition;
        private NurseStatusBean nurseStatus;
        private int priority;

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public int getCabinId() {
            return cabinId;
        }

        public void setCabinId(int cabinId) {
            this.cabinId = cabinId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNurseCode() {
            return nurseCode;
        }

        public void setNurseCode(String nurseCode) {
            this.nurseCode = nurseCode;
        }

        public String getNurseImg() {
            return nurseImg;
        }

        public void setNurseImg(String nurseImg) {
            this.nurseImg = nurseImg;
        }

        public String getNurseIntroduction() {
            return nurseIntroduction;
        }

        public void setNurseIntroduction(String nurseIntroduction) {
            this.nurseIntroduction = nurseIntroduction;
        }

        public String getNurseName() {
            return nurseName;
        }

        public void setNurseName(String nurseName) {
            this.nurseName = nurseName;
        }

        public String getNursePhone() {
            return nursePhone;
        }

        public void setNursePhone(String nursePhone) {
            this.nursePhone = nursePhone;
        }

        public NursePositionBean getNursePosition() {
            return nursePosition;
        }

        public void setNursePosition(NursePositionBean nursePosition) {
            this.nursePosition = nursePosition;
        }

        public NurseStatusBean getNurseStatus() {
            return nurseStatus;
        }

        public void setNurseStatus(NurseStatusBean nurseStatus) {
            this.nurseStatus = nurseStatus;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public static class NursePositionBean {
            /**
             * code : 1010
             * name : NURSE
             * text : 护士
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

        public static class NurseStatusBean {
            /**
             * code : 1020
             * name : NO_BIND
             * text : 未绑定用户
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
