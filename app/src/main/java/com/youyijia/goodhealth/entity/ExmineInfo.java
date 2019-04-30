package com.youyijia.goodhealth.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangqiang on 2019/4/22.
 */
public class ExmineInfo implements Serializable {
    /**
     * actualProductAmountTotal : 220.0
     * address : 上海市市辖区闵行区江月路1000米
     * amountPayable : 220.0
     * commodityCount : 1
     * createDate : 2019-04-22
     * customerId : 2165
     * logisticsFee : 0.0
     * needInvoice : {"code":1,"name":"NO","text":"不需要"}
     * note :
     * orderAmountTotal : 220.0
     * orderId : 1955
     * orderItems : [{"commodityId":14,"commodityName":"膳食纤维","commoditySpecificationInfo":"规格:默认/","commoditySpecificationNo":1,"commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"costUnitPrice":220,"imageUrl":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/462d6090-284e-4649-b263-038a954424ef.png","itemId":1829,"number":1,"orderId":1955,"presentUnitPrice":220,"subtotal":220}]
     * orderStatus : {"code":1010,"name":"NON_PAYMENT","text":"待支付"}
     * orderType : {"code":1020,"name":"COMMON","text":"商品订单"}
     * originProductAmountTotal : 220.0
     * recipientsName : 尼古拉斯
     * recipientsTelephone : 17601379998
     * reducedPrice : 0.0
     * serialNumber : S042235598
     * customerType : {"code":2,"name":"COMPANY_CUSTOMER","text":"企业用户"}
     */

    private double actualProductAmountTotal;
    private String address;
    private double amountPayable;
    private int commodityCount;
    private String createDate;
    private int customerId;
    private double logisticsFee;
    private NeedInvoiceBean needInvoice;
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
    private CustomerTypeBean customerType;
    private List<OrderItemsBean> orderItems;

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

    public NeedInvoiceBean getNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(NeedInvoiceBean needInvoice) {
        this.needInvoice = needInvoice;
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

    public CustomerTypeBean getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypeBean customerType) {
        this.customerType = customerType;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class NeedInvoiceBean implements Serializable{
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

    public static class OrderStatusBean implements Serializable{
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

    public static class OrderTypeBean implements Serializable{
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

    public static class CustomerTypeBean implements Serializable{
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

    public static class OrderItemsBean implements Serializable{
        /**
         * commodityId : 14
         * commodityName : 膳食纤维
         * commoditySpecificationInfo : 规格:默认/
         * commoditySpecificationNo : 1
         * commodityStatus : {"code":1010,"name":"PUTAWAY","text":"上架"}
         * costUnitPrice : 220.0
         * imageUrl : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/462d6090-284e-4649-b263-038a954424ef.png
         * itemId : 1829
         * number : 1
         * orderId : 1955
         * presentUnitPrice : 220.0
         * subtotal : 220.0
         */

        private int commodityId;
        private String commodityName;
        private String commoditySpecificationInfo;
        private int commoditySpecificationNo;
        private CommodityStatusBean commodityStatus;
        private double costUnitPrice;
        private String imageUrl;
        private int itemId;
        private int number;
        private int orderId;
        private double presentUnitPrice;
        private double subtotal;

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

        public static class CommodityStatusBean implements Serializable{
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
