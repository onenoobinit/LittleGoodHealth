package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/10.
 */
public class DoctorInfo {

    /**
     * channelId : 1
     * doctorHeadimg : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/b7d57068-2f67-4e43-be79-edd003825085.png
     * doctorIntroduction : 上海市第十人民医院普外科副主任医师， 医学博士，中华预防医学会微生态分会青年委员，上海预防医学会微生态专业组委员兼秘书，中华医学会肠内与肠外营养分会营养与微生态协作组成员，中国微生态学杂志特约编辑
     * doctorIntroductionBr : 上海市第十人民医院普外科副主任医师<br/>医学博士<br/>中华预防医学会微生态分会青年委员<br/>上海预防医学会微生态专业组委员兼秘书<br/>中华医学会肠内与肠外营养分会营养与微生态协作组成员<br/>中国微生态学杂志特约编辑
     * doctorName : 沈通一
     * doctorTitle : 副主任医师
     * id : 1
     * lectureCount : 2
     * lectureStatus : {"code":1,"name":"UNDERWAY","text":"在线咨询进行中"}
     */

    private int channelId;
    private String doctorHeadimg;
    private String doctorIntroduction;
    private String doctorIntroductionBr;
    private String doctorName;
    private String doctorTitle;
    private String id;
    private int lectureCount;
    private LectureStatusBean lectureStatus;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getDoctorHeadimg() {
        return doctorHeadimg;
    }

    public void setDoctorHeadimg(String doctorHeadimg) {
        this.doctorHeadimg = doctorHeadimg;
    }

    public String getDoctorIntroduction() {
        return doctorIntroduction;
    }

    public void setDoctorIntroduction(String doctorIntroduction) {
        this.doctorIntroduction = doctorIntroduction;
    }

    public String getDoctorIntroductionBr() {
        return doctorIntroductionBr;
    }

    public void setDoctorIntroductionBr(String doctorIntroductionBr) {
        this.doctorIntroductionBr = doctorIntroductionBr;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLectureCount() {
        return lectureCount;
    }

    public void setLectureCount(int lectureCount) {
        this.lectureCount = lectureCount;
    }

    public LectureStatusBean getLectureStatus() {
        return lectureStatus;
    }

    public void setLectureStatus(LectureStatusBean lectureStatus) {
        this.lectureStatus = lectureStatus;
    }

    public static class LectureStatusBean {
        /**
         * code : 1
         * name : UNDERWAY
         * text : 在线咨询进行中
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
