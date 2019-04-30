package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/29.
 */
public class MyShopOrderInfo {

    /**
     * actualProductAmountTotal : 380.0
     * address : 广东省东莞市开始对话或大或小好多好多好多
     * amountPayable : 380.0
     * commodityCount : 2
     * createDate : 2019-03-05
     * customerId : 154
     * logisticsFee : 0.0
     * note :
     * orderAmountTotal : 380.0
     * orderId : 855
     * orderItems : [{"commodityId":39,"commodityName":"测试一","commoditySpecificationInfo":"颜色:黑/大小:100ml/","commoditySpecificationNo":1,"commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"costUnitPrice":200,"itemId":659,"number":1,"orderId":855,"presentUnitPrice":150,"subtotal":150},{"commodityId":40,"commodityName":"我不会上架的","commoditySpecificationInfo":"颜色:黑/大小:100/","commoditySpecificationNo":4,"commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"costUnitPrice":230,"itemId":660,"number":1,"orderId":855,"presentUnitPrice":230,"subtotal":230}]
     * orderStatus : {"code":1010,"name":"NON_PAYMENT","text":"待支付"}
     * orderType : {"code":1020,"name":"COMMON","text":"商品订单"}
     * originProductAmountTotal : 380.0
     * recipientsName : 刘晶晶
     * recipientsTelephone : 13023091335
     * reducedPrice : 0.0
     * serialNumber : S030523126
     */

    private double actualProductAmountTotal;
    private String address;
    private double amountPayable;
    private int commodityCount;
    private String createDate;
    private int customerId;
    private double logisticsFee;
    private String note;
    private double orderAmountTotal;
    private int orderId;
    private OrderStatusBean orderStatus;
    private OrderTypeBean orderType;
    private double originProductAmountTotal;
    private String recipientsName;
    private String recipientsTelephone;
    private double reducedPrice;
    private String serialNumber;
    private List<OrderItemsBean> orderItems;

    public PayMethodBean getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethodBean payMethod) {
        this.payMethod = payMethod;
    }

    private PayMethodBean payMethod;

    public double getActualProductAmountTotal() {
        return actualProductAmountTotal;
    }

    public void setActualProductAmountTotal(double actualProductAmountTotal) {
        this.actualProductAmountTotal = actualProductAmountTotal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(double amountPayable) {
        this.amountPayable = amountPayable;
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

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }

    public String getRecipientsTelephone() {
        return recipientsTelephone;
    }

    public void setRecipientsTelephone(String recipientsTelephone) {
        this.recipientsTelephone = recipientsTelephone;
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

    public static class OrderStatusBean {
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

    public static class OrderTypeBean {
        /**
         * code : 1020
         * name : COMMON
         * text : 商品订单
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
         * code : 1020
         * name : COMMON
         * text : 商品订单
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
         * commodityId : 39
         * commodityName : 测试一
         * commoditySpecificationInfo : 颜色:黑/大小:100ml/
         * commoditySpecificationNo : 1
         * commodityStatus : {"code":1010,"name":"PUTAWAY","text":"上架"}
         * costUnitPrice : 200.0
         * itemId : 659
         * number : 1
         * orderId : 855
         * presentUnitPrice : 150.0
         * subtotal : 150.0
         */

        private int commodityId;
        private String commodityName;
        private String commoditySpecificationInfo;
        private int commoditySpecificationNo;
        private CommodityStatusBean commodityStatus;
        private double costUnitPrice;
        private int itemId;
        private int number;
        private int orderId;
        private double presentUnitPrice;
        private double subtotal;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        private String imageUrl;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getCommoditySpecificationInfo() {
            return commoditySpecificationInfo;
        }

        public void setCommoditySpecificationInfo(String commoditySpecificationInfo) {
            this.commoditySpecificationInfo = commoditySpecificationInfo;
        }

        public int getCommoditySpecificationNo() {
            return commoditySpecificationNo;
        }

        public void setCommoditySpecificationNo(int commoditySpecificationNo) {
            this.commoditySpecificationNo = commoditySpecificationNo;
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
