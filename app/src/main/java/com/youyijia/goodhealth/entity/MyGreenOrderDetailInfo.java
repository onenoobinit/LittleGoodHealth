package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/29.
 */
public class MyGreenOrderDetailInfo {
    /**
     * actualProductAmountTotal : 0.01
     * commodityCount : 1
     * createDate : 2019-04-25
     * customerId : 154
     * customerType : {"code":2,"name":"COMPANY_CUSTOMER","text":"企业用户"}
     * extendsInformation : 复旦大学附属华东医院
     * logisticsFee : 0.0
     * note :
     * orderAmountTotal : 0.01
     * orderId : 958
     * orderItems : [{"commodityName":"上海第二诊疗建议（单科室代诊）","commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"costUnitPrice":0.01,"imageUrl":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/68280690-5b18-4194-9617-cd262732dcf3.png","itemId":679,"number":1,"orderId":958,"presentUnitPrice":0.01,"subtotal":0.01}]
     * orderStatus : {"code":1030,"name":"NON_RECIEVE","text":"待收货"}
     * orderType : {"code":1010,"name":"LVTONG","text":"绿通订单"}
     * originProductAmountTotal : 0.01
     * outTradeNo : 2019042522001446701039865010
     * payMethod : {"code":1010,"name":"ALIPAY","text":"支付宝"}
     * payTime : 2019-04-25
     * reducedPrice : 0.0
     * serialNumber : L042512363
     */

    private double actualProductAmountTotal;
    private int commodityCount;
    private String createDate;
    private int customerId;
    private CustomerTypeBean customerType;
    private String extendsInformation;
    private double logisticsFee;
    private String note;
    private double orderAmountTotal;
    private int orderId;
    private OrderStatusBean orderStatus;
    private OrderTypeBean orderType;
    private double originProductAmountTotal;
    private String outTradeNo;
    private PayMethodBean payMethod;
    private String payTime;
    private double reducedPrice;
    private String serialNumber;
    private List<OrderItemsBean> orderItems;

    public double getActualProductAmountTotal() {
        return actualProductAmountTotal;
    }

    public void setActualProductAmountTotal(double actualProductAmountTotal) {
        this.actualProductAmountTotal = actualProductAmountTotal;
    }

    public int getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(int commodityCount) {
        this.commodityCount = commodityCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CustomerTypeBean getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeBean customerType) {
        this.customerType = customerType;
    }

    public String getExtendsInformation() {
        return extendsInformation;
    }

    public void setExtendsInformation(String extendsInformation) {
        this.extendsInformation = extendsInformation;
    }

    public double getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(double logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(double orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderStatusBean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusBean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderTypeBean getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderTypeBean orderType) {
        this.orderType = orderType;
    }

    public double getOriginProductAmountTotal() {
        return originProductAmountTotal;
    }

    public void setOriginProductAmountTotal(double originProductAmountTotal) {
        this.originProductAmountTotal = originProductAmountTotal;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public PayMethodBean getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethodBean payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public double getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(double reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class CustomerTypeBean {
        /**
         * code : 2
         * name : COMPANY_CUSTOMER
         * text : 企业用户
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

    public static class OrderStatusBean {
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

    public static class OrderTypeBean {
        /**
         * code : 1010
         * name : LVTONG
         * text : 绿通订单
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

    public static class PayMethodBean {
        /**
         * code : 1010
         * name : ALIPAY
         * text : 支付宝
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

    public static class OrderItemsBean {
        /**
         * commodityName : 上海第二诊疗建议（单科室代诊）
         * commodityStatus : {"code":1010,"name":"PUTAWAY","text":"上架"}
         * costUnitPrice : 0.01
         * imageUrl : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/68280690-5b18-4194-9617-cd262732dcf3.png
         * itemId : 679
         * number : 1
         * orderId : 958
         * presentUnitPrice : 0.01
         * subtotal : 0.01
         */

        private String commodityName;
        private CommodityStatusBean commodityStatus;
        private double costUnitPrice;
        private String imageUrl;
        private int itemId;
        private int number;
        private int orderId;
        private double presentUnitPrice;
        private double subtotal;

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public CommodityStatusBean getCommodityStatus() {
            return commodityStatus;
        }

        public void setCommodityStatus(CommodityStatusBean commodityStatus) {
            this.commodityStatus = commodityStatus;
        }

        public double getCostUnitPrice() {
            return costUnitPrice;
        }

        public void setCostUnitPrice(double costUnitPrice) {
            this.costUnitPrice = costUnitPrice;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public double getPresentUnitPrice() {
            return presentUnitPrice;
        }

        public void setPresentUnitPrice(double presentUnitPrice) {
            this.presentUnitPrice = presentUnitPrice;
        }

        public double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(double subtotal) {
            this.subtotal = subtotal;
        }

        public static class CommodityStatusBean {
            /**
             * code : 1010
             * name : PUTAWAY
             * text : 上架
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
