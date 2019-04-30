package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/18.
 */
public class AddressListInfo {
    /**
     * address : 浦江镇
     * addressStatus : {"code":1010,"name":"DEFAULT","text":"默认"}
     * cityId : 310100
     * cityName : 市辖区
     * countryId : 310112
     * countryName : 闵行区
     * customerId : 2165
     * id : 596
     * phone : 15665290937
     * provinceId : 310000
     * provinceName : 上海市
     * receiver : 小黑
     */

    private String address;
    private AddressStatusBean addressStatus;
    private String cityId;
    private String cityName;
    private String countryId;
    private String countryName;
    private int customerId;
    private int id;
    private String phone;
    private String provinceId;
    private String provinceName;
    private String receiver;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AddressStatusBean getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(AddressStatusBean addressStatus) {
        this.addressStatus = addressStatus;
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public static class AddressStatusBean {
        /**
         * code : 1010
         * name : DEFAULT
         * text : 默认
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
