package com.youyijia.goodhealth.entity;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * @author chenliangzhi
 * @date 2018/2/24
 * @describe 用户信息
 */

public class User implements Serializable {

    private String token;
    private String msg;
    private Boolean isLogined;
    /**
     * id : 2165
     * phone : null
     * portrait : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/7d458580-ef5c-47cd-86f0-13ffc3b2f4c2.jpeg?height=1200&width=1200
     * nickName : 小黑
     * gender : {"code":1010,"name":"MALE","text":"男"}
     * birthday : 1990-11-23
     * companyName : null
     * projectNameDisplay : null
     * welcomeMessage : null
     */

    private String id;
    private String phone;
    private String portrait;
    private String nickName;
    private GenderBean gender;
    private CustomerTypeBean customerType;
    private String birthday;
    private String companyName;
    private String projectNameDisplay;
    private String welcomeMessage;

    public String getProjectId() {
        if (TextUtils.isEmpty(projectId)) {
            return "";
        }
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUnionid() {
        if (TextUtils.isEmpty(unionid)) {
            return "";
        }
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    private String projectId;
    private String unionid;

    public Boolean isLogined() {
        if (isLogined == null) {
            return false;
        }
        return isLogined;
    }

    public void setLogined(Boolean logined) {
        isLogined = logined;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        if (TextUtils.isEmpty(id)) {
            return "";
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        if (TextUtils.isEmpty(phone)) {
            return "";
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortrait() {
        if (TextUtils.isEmpty(portrait)) {
            return "";
        }
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        if (TextUtils.isEmpty(nickName)) {
            return "";
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public GenderBean getGender() {
        return gender;
    }

    public void setGender(GenderBean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        if (TextUtils.isEmpty(birthday)) {
            return "";
        }
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompanyName() {
        if (TextUtils.isEmpty(companyName)) {
            return "";
        }
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectNameDisplay() {
        if (TextUtils.isEmpty(projectNameDisplay)) {
            return "";
        }
        return projectNameDisplay;
    }

    public void setProjectNameDisplay(String projectNameDisplay) {
        this.projectNameDisplay = projectNameDisplay;
    }

    public String getWelcomeMessage() {
        if (TextUtils.isEmpty(welcomeMessage)) {
            return "";
        }
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public static class GenderBean implements Serializable{
        /**
         * code : 1010
         * name : MALE
         * text : 男
         */

        private String code;
        private String name;
        private String text;

        public String getCode() {
            if (TextUtils.isEmpty(code)) {
                return "";
            }
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            if (TextUtils.isEmpty(name)) {
                return "";
            }
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            if (TextUtils.isEmpty(text)) {
                return "";
            }
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class CustomerTypeBean implements Serializable{
        /**
         * code : 1010
         * name : MALE
         * text : 男
         */

        private String code;
        private String name;
        private String text;

        public String getCode() {
            if (TextUtils.isEmpty(code)) {
                return "";
            }
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            if (TextUtils.isEmpty(name)) {
                return "";
            }
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            if (TextUtils.isEmpty(text)) {
                return "";
            }
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
