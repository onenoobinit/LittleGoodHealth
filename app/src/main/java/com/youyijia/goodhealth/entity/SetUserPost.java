package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/18.
 */
public class SetUserPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        private String nickName;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        private String gender;
        private String birthday;
        private String companyAddress;

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        private String portrait;
    }
}
