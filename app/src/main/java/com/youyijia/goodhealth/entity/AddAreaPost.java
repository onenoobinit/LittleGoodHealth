package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/21.
 */
public class AddAreaPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        private String receiver;
        private String phone;
        private String provinceId;
        private String provinceName;
        private String cityId;

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCounntryId() {
            return counntryId;
        }

        public void setCounntryId(String counntryId) {
            this.counntryId = counntryId;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddressStatus() {
            return addressStatus;
        }

        public void setAddressStatus(String addressStatus) {
            this.addressStatus = addressStatus;
        }

        private String cityName;
        private String counntryId;
        private String countryName;
        private String address;
        private String addressStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

    }
}
