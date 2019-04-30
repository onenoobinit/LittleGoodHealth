package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class BodyZiXunInfo {

    /**
     * doctorId : 2
     * doctorTitle : 主治医师
     * doctorType : {"code":1020,"name":"PHYSIOLOGY_DOCTOR","text":"优医家特聘全职专科医生"}
     * expertiseField : 外科医生
     * gender : {"code":1020,"name":"FEMALE","text":"女"}
     * headPortrait : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/afc12576-e84a-4c73-9e83-05e7b399aa73.png
     * introduction : 1985年毕业于上海交大医学院。相继于上海市东医院、中山医院、瑞金医院工作近20年时间，从事外科手术以及妇科手术工作等。
     * name : 周敏
     * researchDirection : 体检报告解读、妇科常见病，如：阴道炎、盆腔炎、宫颈炎、妇科肿瘤的诊断与治疗。内外科各种常见病、多发病的诊断与治疗。尤其对高血压病、脑血管病、呼吸系统疾病等有丰富诊疗经验。
     * researchDirectionControlArray : [[0,6],[7,5]]
     * researchDirectionSynopsis : 体检报告解读、妇科常见病，阴道炎、盆腔炎、宫颈炎、妇科肿瘤、高血压病、脑血管病、呼吸系统疾病
     */

    private int doctorId;
    private String doctorTitle;
    private DoctorTypeBean doctorType;
    private String expertiseField;
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

    public String getExpertiseField() {
        return expertiseField;
    }

    public void setExpertiseField(String expertiseField) {
        this.expertiseField = expertiseField;
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
         * code : 1020
         * name : PHYSIOLOGY_DOCTOR
         * text : 优医家特聘全职专科医生
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
         * code : 1020
         * name : FEMALE
         * text : 女
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
