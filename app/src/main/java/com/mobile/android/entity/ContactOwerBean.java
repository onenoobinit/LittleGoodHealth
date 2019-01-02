package com.mobile.android.entity;

/**
 * @author chenliangzhi
 * @date 2018/5/16
 * @describe
 */

public class ContactOwerBean {

    /**
     * name : chenhuijing-187
     * baoming_id : 447718
     * is_main : 1
     * type : user
     */

    private String name;
    private String baoming_id;
    private int is_main;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaoming_id() {
        return baoming_id;
    }

    public void setBaoming_id(String baoming_id) {
        this.baoming_id = baoming_id;
    }

    public int getIs_main() {
        return is_main;
    }

    public void setIs_main(int is_main) {
        this.is_main = is_main;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContactOwerBean{" +
                "name='" + name + '\'' +
                ", baoming_id='" + baoming_id + '\'' +
                ", is_main=" + is_main +
                ", type='" + type + '\'' +
                '}';
    }
}
