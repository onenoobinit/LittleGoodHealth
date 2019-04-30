package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class GreenHospitalInfo {
    /**
     * city : 上海
     * cityServiceId : 57
     * hospitalName : 复旦大学附属华东医院
     * id : 10372
     */

    private String city;
    private int cityServiceId;
    private String hospitalName;
    private int id;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityServiceId() {
        return cityServiceId;
    }

    public void setCityServiceId(int cityServiceId) {
        this.cityServiceId = cityServiceId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
