package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/28.
 */
public class ShopOrderIdPost {
    private PostInfoBean postInfoBean;

    public PostInfoBean getPostInfoBean() {
        return postInfoBean;
    }

    public void setPostInfoBean(PostInfoBean postInfoBean) {
        this.postInfoBean = postInfoBean;
    }

    public class PostInfoBean {
        public String getReceiveAddressId() {
            return receiveAddressId;
        }

        public void setReceiveAddressId(String receiveAddressId) {
            this.receiveAddressId = receiveAddressId;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getNeedInvoice() {
            return needInvoice;
        }

        public void setNeedInvoice(String needInvoice) {
            this.needInvoice = needInvoice;
        }

        public List<OrderItemsBean> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItemsBean> orderItems) {
            this.orderItems = orderItems;
        }

        private String receiveAddressId;
        private String note;
        private String needInvoice;
        private List<OrderItemsBean> orderItems;
        public class OrderItemsBean {
            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommoditySpecificationNo() {
                return commoditySpecificationNo;
            }

            public void setCommoditySpecificationNo(String commoditySpecificationNo) {
                this.commoditySpecificationNo = commoditySpecificationNo;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            private int commodityId;
            private String commoditySpecificationNo;
            private String number;

        }
    }
}
