package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/19.
 */
public class PutgreenOrderPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        private String cityServiceId;

        public String getCityServiceId() {
            return cityServiceId;
        }

        public void setCityServiceId(String cityServiceId) {
            this.cityServiceId = cityServiceId;
        }

        public String getMedicalPersonId() {
            return medicalPersonId;
        }

        public void setMedicalPersonId(String medicalPersonId) {
            this.medicalPersonId = medicalPersonId;
        }

        public String getLinkPhone() {
            return linkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            this.linkPhone = linkPhone;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public String getHospitalId() {
            return hospitalId;
        }

        public void setHospitalId(String hospitalId) {
            this.hospitalId = hospitalId;
        }

        private String medicalPersonId;
        private String linkPhone;
        private String note;
        private String hospital;
        private String hospitalId;

    }
}
