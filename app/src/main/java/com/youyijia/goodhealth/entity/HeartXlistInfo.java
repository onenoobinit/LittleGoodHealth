package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class HeartXlistInfo {
    /**
     * doctorId : 14
     * doctorTitle : 国家二级心理咨询师
     * doctorType : {"code":1010,"name":"PSYCHOLOGIST","text":"优医家特聘心理咨询师"}
     * gender : {"code":1010,"name":"MALE","text":"男"}
     * headPortrait : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/63c34ed0-1154-4af3-8cfb-77ead2f33395.png
     * introduction : 注册催眠师
     * name : 甄海飚
     * researchDirection : 职场人际关系与沟通、压力与情绪疏导、自我成长等。
     * researchDirectionControlArray : [[0,9],[10,7],[18,4]]
     * researchDirectionSynopsis : 职场人际关系与沟通、压力与情绪疏导、自我成长
     */

    private int doctorId;
    private String doctorTitle;
    private DoctorTypeBean doctorType;
    private GenderBean gender;
    private String headPortrait;
    private String introduction;
    private String name;
    private String researchDirection;
    private String researchDirectionSynopsis;
    private List<List<Integer>> researchDirectionControlArray;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public DoctorTypeBean getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorTypeBean doctorType) {
        this.doctorType = doctorType;
    }

    public GenderBean getGender() {
        return gender;
    }

    public void setGender(GenderBean gender) {
        this.gender = gender;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResearchDirection() {
        return researchDirection;
    }

    public void setResearchDirection(String researchDirection) {
        this.researchDirection = researchDirection;
    }

    public String getResearchDirectionSynopsis() {
        return researchDirectionSynopsis;
    }

    public void setResearchDirectionSynopsis(String researchDirectionSynopsis) {
        this.researchDirectionSynopsis = researchDirectionSynopsis;
    }

    public List<List<Integer>> getResearchDirectionControlArray() {
        return researchDirectionControlArray;
    }

    public void setResearchDirectionControlArray(List<List<Integer>> researchDirectionControlArray) {
        this.researchDirectionControlArray = researchDirectionControlArray;
    }

    public static class DoctorTypeBean {
        /**
         * code : 1010
         * name : PSYCHOLOGIST
         * text : 优医家特聘心理咨询师
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

    public static class GenderBean {
        /**
         * code : 1010
         * name : MALE
         * text : 男
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
