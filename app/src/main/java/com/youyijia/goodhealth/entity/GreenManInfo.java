package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class GreenManInfo {
    /**
     * customerId : 2165
     * id : 316
     * idCardNo : 370406199011230077
     * idCardType : {"code":1010,"name":"IDCARD","text":"身份证"}
     * name : 小黑
     * relation : {"code":1010,"name":"STAFF","text":"员工"}
     * telephone : 15665290937
     */

    private int customerId;
    private String id;
    private String idCardNo;
    private IdCardTypeBean idCardType;
    private String name;
    private RelationBean relation;
    private String telephone;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public IdCardTypeBean getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(IdCardTypeBean idCardType) {
        this.idCardType = idCardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RelationBean getRelation() {
        return relation;
    }

    public void setRelation(RelationBean relation) {
        this.relation = relation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public static class IdCardTypeBean {
        /**
         * code : 1010
         * name : IDCARD
         * text : 身份证
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

    public static class RelationBean {
        /**
         * code : 1010
         * name : STAFF
         * text : 员工
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
