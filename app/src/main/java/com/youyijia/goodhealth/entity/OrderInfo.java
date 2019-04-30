package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/11.
 */
public class OrderInfo {


    /**
     * actualProductAmountTotal : 3700.0
     * commodityCount : 1
     * createDate : 2019-04-19
     * customerId : 2165
     * customerType : {"code":2,"name":"COMPANY_CUSTOMER","text":"企业用户"}
     * logisticsFee : 0.0
     * note :
     * orderAmountTotal : 3700.0
     * orderId : 1953
     * orderItems : [{"commodityName":"上海第二诊疗建议（单科室代诊）","commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"costUnitPrice":3700,"imageUrl":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/68280690-5b18-4194-9617-cd262732dcf3.png","itemId":1827,"number":1,"orderId":1953,"presentUnitPrice":3700,"subtotal":3700}]
     * orderStatus : {"code":1010,"name":"NON_PAYMENT","text":"待支付"}
     * orderType : {"code":1010,"name":"LVTONG","text":"绿通订单"}
     * originProductAmountTotal : 3700.0
     * reducedPrice : 0.0
     * serialNumber : L041912230
     * address : 上海市市辖区闵行区浦江镇
     * amountPayable : 3548.0
     * needInvoice : {"code":1,"name":"NO","text":"不需要"}
     * recipientsName : 小黑
     * recipientsTelephone : 15665290937
     */

    private double actualProductAmountTotal;
    private int commodityCount;
    private String createDate;
    private int customerId;
    private CustomerTypeBean customerType;
    private double logisticsFee;
    private String note;
    private double orderAmountTotal;
    private int orderId;
    private OrderStatusBean orderStatus;
    private OrderTypeBean orderType;
    private double originProductAmountTotal;
    private double reducedPrice;
    private String serialNumber;
    private String address;
    private double amountPayable;
    private NeedInvoiceBean needInvoice;
    private String recipientsName;
    private String recipientsTelephone;
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

    public NeedInvoiceBean getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(NeedInvoiceBean needInvoice) {
        this.needInvoice = needInvoice;
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

    public static class NeedInvoiceBean {
        /**
         * code : 1
         * name : NO
         * text : 不需要
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
         * costUnitPrice : 3700.0
         * imageUrl : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/68280690-5b18-4194-9617-cd262732dcf3.png
         * itemId : 1827
         * number : 1
         * orderId : 1953
         * presentUnitPrice : 3700.0
         * subtotal : 3700.0
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

        public String getCommoditySpecificationInfo() {
            return commoditySpecificationInfo;
        }

        public void setCommoditySpecificationInfo(String commoditySpecificationInfo) {
            this.commoditySpecificationInfo = commoditySpecificationInfo;
        }

        public String getCommoditySpecificationNo() {
            return commoditySpecificationNo;
        }

        public void setCommoditySpecificationNo(String commoditySpecificationNo) {
            this.commoditySpecificationNo = commoditySpecificationNo;
        }

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        private String commoditySpecificationInfo;
        private String commoditySpecificationNo;
        private String commodityId;

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
