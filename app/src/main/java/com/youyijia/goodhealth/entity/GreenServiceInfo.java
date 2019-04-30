package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class GreenServiceInfo {
    /**
     * cityServices : [{"cityName":"上海","id":57,"medicalCityId":1,"medicalServiceId":18,"price":3700,"serviceStatus":{"code":1,"name":"DISABLED","text":"正常"},"serviceTypeInformation":"","serviceTypeInformationImg":{"height":"350","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/8e46853f-e521-49d3-ba5a-0b47d819f9b7.png?height=350&width=750","width":"750"},"serviceTypeName":"第二诊疗建议（单科室代诊）"},{"cityName":"上海","id":64,"medicalCityId":1,"medicalServiceId":19,"price":6800,"serviceStatus":{"code":1,"name":"DISABLED","text":"正常"},"serviceTypeInformation":"","serviceTypeInformationImg":{"height":"400","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/b5ed6d87-dfd1-4fc4-bd1a-0eaf1b077e39.png?height=400&width=750","width":"750"},"serviceTypeName":"第二诊疗建议（多科室代诊）"},{"cityName":"上海","id":77,"medicalCityId":1,"medicalServiceId":20,"price":4800,"serviceStatus":{"code":1,"name":"DISABLED","text":"正常"},"serviceTypeInformation":"","serviceTypeInformationImg":{"height":"400","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/90d8a279-f7fd-4368-aa3a-63fbc997540b.png?height=400&width=750","width":"750"},"serviceTypeName":"品质看诊（含全程陪诊）"},{"cityName":"上海","id":78,"medicalCityId":1,"medicalServiceId":21,"price":7000,"serviceStatus":{"code":1,"name":"DISABLED","text":"正常"},"serviceTypeInformation":"","serviceTypeInformationImg":{"height":"440","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/37c3457d-4289-4bd9-9957-352df7ec0092.png?height=440&width=750","width":"750"},"serviceTypeName":"品质看诊/床位协调/手术安排（普通专家）"},{"cityName":"上海","id":85,"medicalCityId":1,"medicalServiceId":24,"price":11000,"serviceStatus":{"code":1,"name":"DISABLED","text":"正常"},"serviceTypeInformation":"","serviceTypeInformationImg":{"height":"440","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ce37c62f-bcdd-4201-8e6a-8b960598e3b6.png?height=440&width=750","width":"750"},"serviceTypeName":"品质看诊/床位协调/手术安排（指定医院/医生/知名专家）"}]
     * id : 1
     * name : 上海
     * note : this is a city
     */

    private int id;
    private String name;
    private String note;
    private List<CityServicesBean> cityServices;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<CityServicesBean> getCityServices() {
        return cityServices;
    }

    public void setCityServices(List<CityServicesBean> cityServices) {
        this.cityServices = cityServices;
    }

    public static class CityServicesBean {
        /**
         * cityName : 上海
         * id : 57
         * medicalCityId : 1
         * medicalServiceId : 18
         * price : 3700.0
         * serviceStatus : {"code":1,"name":"DISABLED","text":"正常"}
         * serviceTypeInformation :
         * serviceTypeInformationImg : {"height":"350","url":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/8e46853f-e521-49d3-ba5a-0b47d819f9b7.png?height=350&width=750","width":"750"}
         * serviceTypeName : 第二诊疗建议（单科室代诊）
         */

        private String cityName;
        private String id;
        private String medicalCityId;
        private String medicalServiceId;
        private double price;
        private ServiceStatusBean serviceStatus;
        private String serviceTypeInformation;
        private ServiceTypeInformationImgBean serviceTypeInformationImg;
        private String serviceTypeName;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMedicalCityId() {
            return medicalCityId;
        }

        public void setMedicalCityId(String medicalCityId) {
            this.medicalCityId = medicalCityId;
        }

        public String getMedicalServiceId() {
            return medicalServiceId;
        }

        public void setMedicalServiceId(String medicalServiceId) {
            this.medicalServiceId = medicalServiceId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public ServiceStatusBean getServiceStatus() {
            return serviceStatus;
        }

        public void setServiceStatus(ServiceStatusBean serviceStatus) {
            this.serviceStatus = serviceStatus;
        }

        public String getServiceTypeInformation() {
            return serviceTypeInformation;
        }

        public void setServiceTypeInformation(String serviceTypeInformation) {
            this.serviceTypeInformation = serviceTypeInformation;
        }

        public ServiceTypeInformationImgBean getServiceTypeInformationImg() {
            return serviceTypeInformationImg;
        }

        public void setServiceTypeInformationImg(ServiceTypeInformationImgBean serviceTypeInformationImg) {
            this.serviceTypeInformationImg = serviceTypeInformationImg;
        }

        public String getServiceTypeName() {
            return serviceTypeName;
        }

        public void setServiceTypeName(String serviceTypeName) {
            this.serviceTypeName = serviceTypeName;
        }

        public static class ServiceStatusBean {
            /**
             * code : 1
             * name : DISABLED
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

        public static class ServiceTypeInformationImgBean {
            /**
             * height : 350
             * url : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/8e46853f-e521-49d3-ba5a-0b47d819f9b7.png?height=350&width=750
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
    }
}
