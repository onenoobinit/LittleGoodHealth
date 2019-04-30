package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/1/11.
 */
public class MeMarkInfo {

    /**
     * nonEvaluated : {"code":1040,"name":"NON_EVALUATED","text":"待评价"}
     * nonEvaluatedNum : 0
     * nonPayMent : {"code":1010,"name":"NON_PAYMENT","text":"待支付"}
     * nonPayMentNum : 1
     * nonReceive : {"code":1030,"name":"NON_RECIEVE","text":"待收货"}
     * nonReceiveNum : 0
     * nonSend : {"code":1020,"name":"NON_SEND","text":"待发货"}
     * nonSendNum : 0
     * shoppingCartStatus : no
     */

    private NonEvaluatedBean nonEvaluated;
    private String nonEvaluatedNum;
    private NonPayMentBean nonPayMent;
    private String nonPayMentNum;
    private NonReceiveBean nonReceive;
    private String nonReceiveNum;
    private NonSendBean nonSend;
    private String nonSendNum;
    private String shoppingCartStatus;

    public NonEvaluatedBean getNonEvaluated() {
        return nonEvaluated;
    }

    public void setNonEvaluated(NonEvaluatedBean nonEvaluated) {
        this.nonEvaluated = nonEvaluated;
    }

    public String getNonEvaluatedNum() {
        return nonEvaluatedNum;
    }

    public void setNonEvaluatedNum(String nonEvaluatedNum) {
        this.nonEvaluatedNum = nonEvaluatedNum;
    }

    public NonPayMentBean getNonPayMent() {
        return nonPayMent;
    }

    public void setNonPayMent(NonPayMentBean nonPayMent) {
        this.nonPayMent = nonPayMent;
    }

    public String getNonPayMentNum() {
        return nonPayMentNum;
    }

    public void setNonPayMentNum(String nonPayMentNum) {
        this.nonPayMentNum = nonPayMentNum;
    }

    public NonReceiveBean getNonReceive() {
        return nonReceive;
    }

    public void setNonReceive(NonReceiveBean nonReceive) {
        this.nonReceive = nonReceive;
    }

    public String getNonReceiveNum() {
        return nonReceiveNum;
    }

    public void setNonReceiveNum(String nonReceiveNum) {
        this.nonReceiveNum = nonReceiveNum;
    }

    public NonSendBean getNonSend() {
        return nonSend;
    }

    public void setNonSend(NonSendBean nonSend) {
        this.nonSend = nonSend;
    }

    public String getNonSendNum() {
        return nonSendNum;
    }

    public void setNonSendNum(String nonSendNum) {
        this.nonSendNum = nonSendNum;
    }

    public String getShoppingCartStatus() {
        return shoppingCartStatus;
    }

    public void setShoppingCartStatus(String shoppingCartStatus) {
        this.shoppingCartStatus = shoppingCartStatus;
    }

    public static class NonEvaluatedBean {
        /**
         * code : 1040
         * name : NON_EVALUATED
         * text : 待评价
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

    public static class NonPayMentBean {
        /**
         * code : 1010
         * name : NON_PAYMENT
         * text : 待支付
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

    public static class NonReceiveBean {
        /**
         * code : 1030
         * name : NON_RECIEVE
         * text : 待收货
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

    public static class NonSendBean {
        /**
         * code : 1020
         * name : NON_SEND
         * text : 待发货
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
