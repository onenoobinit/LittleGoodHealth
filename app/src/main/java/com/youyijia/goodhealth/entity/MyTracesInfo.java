package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/29.
 */
public class MyTracesInfo {
    /**
     * logisticCode : 3834044962032
     * shipperCode : YD
     * shipperName : 韵达速递
     * traces : [{"acceptStation":"【上海市】  已签收 : 由水电箱上面 代签收，如有问题联系蒋亚强(13482812926)","acceptTime":"2019-04-26 12:49:18","goodsStatus":{"code":1040,"name":"SIGN","text":"签收"}},{"acceptStation":"【上海市】  上海松江区北部公司 派件员 蒋亚强(13482812926)正在为您派送","acceptTime":"2019-04-26 07:06:40","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【上海市】 已到达  上海松江区北部公司 马上为您派送","acceptTime":"2019-04-26 04:01:14","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【上海市】 已离开  上海分拨中心 发往 上海松江区北部公司","acceptTime":"2019-04-26 03:29:53","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【上海市】 已到达  上海分拨中心","acceptTime":"2019-04-26 03:29:03","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【上海市】 已到达  上海分拨中心","acceptTime":"2019-04-26 02:56:57","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【金华市】 已离开  浙江义乌分拨中心 发往 上海分拨中心","acceptTime":"2019-04-25 22:04:53","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【金华市】 已到达  浙江义乌分拨中心","acceptTime":"2019-04-25 22:00:09","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【金华市】 已离开  浙江义乌佛堂公司 发往 上海分拨中心","acceptTime":"2019-04-25 19:27:19","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"【金华市】  浙江义乌佛堂公司 已揽收","acceptTime":"2019-04-25 19:22:40","goodsStatus":{"code":1030,"name":"TRANSPORT","text":"运输"}},{"acceptStation":"您的订单开始处理","acceptTime":"2018-08-30 14:23:53","goodsStatus":{"code":1010,"name":"DEAL","text":"处理"},"id":1,"logisiticsId":2}]
     */

    private String logisticCode;
    private String shipperCode;
    private String shipperName;
    private List<TracesBean> traces;

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public List<TracesBean> getTraces() {
        return traces;
    }

    public void setTraces(List<TracesBean> traces) {
        this.traces = traces;
    }

    public static class TracesBean {
        /**
         * acceptStation : 【上海市】  已签收 : 由水电箱上面 代签收，如有问题联系蒋亚强(13482812926)
         * acceptTime : 2019-04-26 12:49:18
         * goodsStatus : {"code":1040,"name":"SIGN","text":"签收"}
         * id : 1
         * logisiticsId : 2
         */

        private String acceptStation;
        private String acceptTime;
        private GoodsStatusBean goodsStatus;
        private int id;
        private int logisiticsId;

        public String getAcceptStation() {
            return acceptStation;
        }

        public void setAcceptStation(String acceptStation) {
            this.acceptStation = acceptStation;
        }

        public String getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            this.acceptTime = acceptTime;
        }

        public GoodsStatusBean getGoodsStatus() {
            return goodsStatus;
        }

        public void setGoodsStatus(GoodsStatusBean goodsStatus) {
            this.goodsStatus = goodsStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLogisiticsId() {
            return logisiticsId;
        }

        public void setLogisiticsId(int logisiticsId) {
            this.logisiticsId = logisiticsId;
        }

        public static class GoodsStatusBean {
            /**
             * code : 1040
             * name : SIGN
             * text : 签收
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
}
