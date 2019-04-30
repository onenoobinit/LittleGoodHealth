package com.youyijia.goodhealth.entity;

/**
 * @author chenliangzhi
 * @date 2018/5/17
 * @describe
 */

public class MissedCallsBean {

    /**
     * id : 2
     * baoming_id : 5
     * call_time : 2017-11-06 10:53:50
     * name : 张先生（业主父亲）
     * last_time : 2017-11-06 10:53:50
     * type : missed
     */

    private String id;
    private String baoming_id;
    private String call_time;
    private String name;
    private String last_time;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaoming_id() {
        return baoming_id;
    }

    public void setBaoming_id(String baoming_id) {
        this.baoming_id = baoming_id;
    }

    public String getCall_time() {
        return call_time;
    }

    public void setCall_time(String call_time) {
        this.call_time = call_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MissedCallsBean{" +
                "id='" + id + '\'' +
                ", baoming_id='" + baoming_id + '\'' +
                ", call_time='" + call_time + '\'' +
                ", name='" + name + '\'' +
                ", last_time='" + last_time + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
