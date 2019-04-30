package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/17.
 */
public class ForgetPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        private String phoneNumber;
        private String authCode;
        private String password;

    }
}
