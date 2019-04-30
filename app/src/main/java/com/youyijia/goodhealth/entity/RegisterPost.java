package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/16.
 */
public class RegisterPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        private String phoneNumber;


        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        private String authCode;
        private String password;
        private String gender;
        private String birthday;

        public String getCompanyRegisterCode() {
            return companyRegisterCode;
        }

        public void setCompanyRegisterCode(String companyRegisterCode) {
            this.companyRegisterCode = companyRegisterCode;
        }

        private String companyRegisterCode;
        private String authId;

        public String getAuthId() {
            return authId;
        }

        public void setAuthId(String authId) {
            this.authId = authId;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        private String accessToken;

    }
}
