package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/12.
 */
public class AllVideoInfo {

    /**
     * id : 2
     * doctorId : 1
     * lectureName : 十院专家0距离，肠道微生态健康在线咨询 (第二期)
     * lectureImg : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/38ff80b0-1032-4f02-ab58-b4887b32e473.jpg?height=460&width=750
     * lectureDate : 2018-10-16 14:00
     * roomid : 5bbef92a5c0d0b391691c485
     * lectureSpeaker : 沈通一 博士
     * lectureDoctorIntroduction : 副主任医师沈通一<br/>中华预防医学会微生态学分会青年委员<br/>上海市预防医学微生态学组委员及秘书<br/>中国微生态学杂志特约编委<br/>中华医学会肠外肠内营养学分会肠道微生态与临床营养协作组成员<br/>目前主持省部级及横向课题3项，拥有国家发明专利2项。在国内国外期刊发表专业论文20篇,其中SCI收录6篇。先后获得教育部科技进步一等奖、上海市技术发明三等奖等<br/>2015年国家卫计委公派英国曼彻施特皇家医院普外科学习
     * lectureIntroduction : 超过90%的中国人存在肠道健康问题，优医家微生态健康管理中心来帮您！<br/>人体肠道内有各种各样的细菌，对人体发挥的作用各不相同。其中有些对人体有益，为有益菌，如双歧杆菌、乳酸杆菌等，有些对人体有害，为有害菌，如肠球菌等,还有些利害兼具（如大肠杆菌）。正常情况下，有益菌占有优势，各菌群相对保持稳定，不对人体产生危害引起疾病。而一旦某些因素（如抗生素、食物中的腐败菌、放化疗等）破坏肠道菌群结构，导致肠内有益菌减少，有害菌增多，则出现菌群失衡现象，最常见的就是腹泻、便秘等肠道症状了。所以健康的肠道菌群很重要。<br/>本次特别邀请上海市第十人民医院肠胃外科副主任医师、微生态诊疗中心特需门诊专家沈统一博士在线咨询<br/><br/>活动规则<br/>本次在线咨询为公益活动，不收取任何费用；活动采取自主预约形式，名额有限先到先得；<br/>1.为了保护您的隐私请使用昵称注册登录APP；<br/>2.本次在线咨询采用在留言互动讨论的形式<br/>3.咨询全过程公开，咨询者可以将问题发上互动区，沈通一博士按互动区的提问顺序公开回答问题；<br/>4.如有咨询者在互动问答时间内发生未完成咨询的情况，咨询名额将顺延至下次在线咨询活动时，并享有优先咨询权。
     * lecturePosition : {"code":0,"name":"NORMAL","text":"普通"}
     * hasSubscribed : null
     * lectureStatus : {"code":1,"name":"UNDERWAY","text":"在线咨询进行中"}
     * lectureIntroductionImg : null
     */

    private String id;
    private String doctorId;
    private String lectureName;
    private String lectureImg;
    private String lectureDate;
    private String roomid;
    private String lectureSpeaker;
    private String lectureDoctorIntroduction;
    private String lectureIntroduction;
    private LecturePositionBean lecturePosition;
    private Object hasSubscribed;
    private LectureStatusBean lectureStatus;
    private Object lectureIntroductionImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureImg() {
        return lectureImg;
    }

    public void setLectureImg(String lectureImg) {
        this.lectureImg = lectureImg;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(String lectureDate) {
        this.lectureDate = lectureDate;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getLectureSpeaker() {
        return lectureSpeaker;
    }

    public void setLectureSpeaker(String lectureSpeaker) {
        this.lectureSpeaker = lectureSpeaker;
    }

    public String getLectureDoctorIntroduction() {
        return lectureDoctorIntroduction;
    }

    public void setLectureDoctorIntroduction(String lectureDoctorIntroduction) {
        this.lectureDoctorIntroduction = lectureDoctorIntroduction;
    }

    public String getLectureIntroduction() {
        return lectureIntroduction;
    }

    public void setLectureIntroduction(String lectureIntroduction) {
        this.lectureIntroduction = lectureIntroduction;
    }

    public LecturePositionBean getLecturePosition() {
        return lecturePosition;
    }

    public void setLecturePosition(LecturePositionBean lecturePosition) {
        this.lecturePosition = lecturePosition;
    }

    public Object getHasSubscribed() {
        return hasSubscribed;
    }

    public void setHasSubscribed(Object hasSubscribed) {
        this.hasSubscribed = hasSubscribed;
    }

    public LectureStatusBean getLectureStatus() {
        return lectureStatus;
    }

    public void setLectureStatus(LectureStatusBean lectureStatus) {
        this.lectureStatus = lectureStatus;
    }

    public Object getLectureIntroductionImg() {
        return lectureIntroductionImg;
    }

    public void setLectureIntroductionImg(Object lectureIntroductionImg) {
        this.lectureIntroductionImg = lectureIntroductionImg;
    }

    public static class LecturePositionBean {
        /**
         * code : 0
         * name : NORMAL
         * text : 普通
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
