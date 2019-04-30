package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class AddjiuyiPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        private String name;
        private String relation;
        private String telephone;

        public String getIdCardType() {
            return idCardType;
        }

        public void setIdCardType(String idCardType) {
            this.idCardType = idCardType;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }

        private String idCardType;
        private String idCardNo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

    }
}
