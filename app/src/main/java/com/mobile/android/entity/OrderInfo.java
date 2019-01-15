package com.mobile.android.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/11.
 */
public class OrderInfo {
    /**
     * allCount : 3383
     * orderBillListInfo : [{"airlineImg":"http://admin.3j-mall.com/aWCargoWebJMPic.png","creationDate":"1547183432000","drCode":"","endPort":"AMS","endPortCN":"阿姆斯特丹，荷兰 (Netherlands)首都，欧洲","goodsData":{"bookingData":{"pieces":"1","proportion":"333","volume":"0.300","weight":"100"},"intoCabin":{"pieces":"0","proportion":"0","volume":"0.000","weight":"0"},"printList":{"pieces":"0","proportion":"0","volume":"0.000","weight":"0"}},"insuranceIcons":{"goodsExtension":{"buyType":"0","payType":"0"}},"operating":{"feeConfirm":"1","freightFeeConfirm":"0","intoCabinConfirm":"0","makeBill":"1","makeBillConfirm":"0"},"orderBillCode":"DSGC19010361","price":"500","reviewType":"0","shippingDate":"2019-01-14","startPort":"PVG","startPortCN":"上海(浦东)","supplierId":"","supplierName":"久茂国际"}]
     */

    private String allCount;
    private List<OrderBillListInfoBean> orderBillListInfo;

    public String getAllCount() {
        return allCount;
    }

    public void setAllCount(String allCount) {
        this.allCount = allCount;
    }

    public List<OrderBillListInfoBean> getOrderBillListInfo() {
        return orderBillListInfo;
    }

    public void setOrderBillListInfo(List<OrderBillListInfoBean> orderBillListInfo) {
        this.orderBillListInfo = orderBillListInfo;
    }

    public static class OrderBillListInfoBean {
        /**
         * airlineImg : http://admin.3j-mall.com/aWCargoWebJMPic.png
         * creationDate : 1547183432000
         * drCode :
         * endPort : AMS
         * endPortCN : 阿姆斯特丹，荷兰 (Netherlands)首都，欧洲
         * goodsData : {"bookingData":{"pieces":"1","proportion":"333","volume":"0.300","weight":"100"},"intoCabin":{"pieces":"0","proportion":"0","volume":"0.000","weight":"0"},"printList":{"pieces":"0","proportion":"0","volume":"0.000","weight":"0"}}
         * insuranceIcons : {"goodsExtension":{"buyType":"0","payType":"0"}}
         * operating : {"feeConfirm":"1","freightFeeConfirm":"0","intoCabinConfirm":"0","makeBill":"1","makeBillConfirm":"0"}
         * orderBillCode : DSGC19010361
         * price : 500
         * reviewType : 0
         * shippingDate : 2019-01-14
         * startPort : PVG
         * startPortCN : 上海(浦东)
         * supplierId :
         * supplierName : 久茂国际
         */

        private String airlineImg;
        private String creationDate;
        private String drCode;
        private String endPort;
        private String endPortCN;
        private GoodsDataBean goodsData;
        private InsuranceIconsBean insuranceIcons;
        private OperatingBean operating;
        private String orderBillCode;
        private String price;
        private String reviewType;
        private String shippingDate;
        private String startPort;
        private String startPortCN;
        private String supplierId;
        private String supplierName;

        public String getAirlineImg() {
            return airlineImg;
        }

        public void setAirlineImg(String airlineImg) {
            this.airlineImg = airlineImg;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public String getDrCode() {
            return drCode;
        }

        public void setDrCode(String drCode) {
            this.drCode = drCode;
        }

        public String getEndPort() {
            return endPort;
        }

        public void setEndPort(String endPort) {
            this.endPort = endPort;
        }

        public String getEndPortCN() {
            return endPortCN;
        }

        public void setEndPortCN(String endPortCN) {
            this.endPortCN = endPortCN;
        }

        public GoodsDataBean getGoodsData() {
            return goodsData;
        }

        public void setGoodsData(GoodsDataBean goodsData) {
            this.goodsData = goodsData;
        }

        public InsuranceIconsBean getInsuranceIcons() {
            return insuranceIcons;
        }

        public void setInsuranceIcons(InsuranceIconsBean insuranceIcons) {
            this.insuranceIcons = insuranceIcons;
        }

        public OperatingBean getOperating() {
            return operating;
        }

        public void setOperating(OperatingBean operating) {
            this.operating = operating;
        }

        public String getOrderBillCode() {
            return orderBillCode;
        }

        public void setOrderBillCode(String orderBillCode) {
            this.orderBillCode = orderBillCode;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getReviewType() {
            return reviewType;
        }

        public void setReviewType(String reviewType) {
            this.reviewType = reviewType;
        }

        public String getShippingDate() {
            return shippingDate;
        }

        public void setShippingDate(String shippingDate) {
            this.shippingDate = shippingDate;
        }

        public String getStartPort() {
            return startPort;
        }

        public void setStartPort(String startPort) {
            this.startPort = startPort;
        }

        public String getStartPortCN() {
            return startPortCN;
        }

        public void setStartPortCN(String startPortCN) {
            this.startPortCN = startPortCN;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public static class GoodsDataBean {
            /**
             * bookingData : {"pieces":"1","proportion":"333","volume":"0.300","weight":"100"}
             * intoCabin : {"pieces":"0","proportion":"0","volume":"0.000","weight":"0"}
             * printList : {"pieces":"0","proportion":"0","volume":"0.000","weight":"0"}
             */

            private BookingDataBean bookingData;
            private IntoCabinBean intoCabin;
            private PrintListBean printList;

            public BookingDataBean getBookingData() {
                return bookingData;
            }

            public void setBookingData(BookingDataBean bookingData) {
                this.bookingData = bookingData;
            }

            public IntoCabinBean getIntoCabin() {
                return intoCabin;
            }

            public void setIntoCabin(IntoCabinBean intoCabin) {
                this.intoCabin = intoCabin;
            }

            public PrintListBean getPrintList() {
                return printList;
            }

            public void setPrintList(PrintListBean printList) {
                this.printList = printList;
            }

            public static class BookingDataBean {
                /**
                 * pieces : 1
                 * proportion : 333
                 * volume : 0.300
                 * weight : 100
                 */

                private String pieces;
                private String proportion;
                private String volume;
                private String weight;

                public String getPieces() {
                    return pieces;
                }

                public void setPieces(String pieces) {
                    this.pieces = pieces;
                }

                public String getProportion() {
                    return proportion;
                }

                public void setProportion(String proportion) {
                    this.proportion = proportion;
                }

                public String getVolume() {
                    return volume;
                }

                public void setVolume(String volume) {
                    this.volume = volume;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }
            }

            public static class IntoCabinBean {
                /**
                 * pieces : 0
                 * proportion : 0
                 * volume : 0.000
                 * weight : 0
                 */

                private String pieces;
                private String proportion;
                private String volume;
                private String weight;

                public String getPieces() {
                    return pieces;
                }

                public void setPieces(String pieces) {
                    this.pieces = pieces;
                }

                public String getProportion() {
                    return proportion;
                }

                public void setProportion(String proportion) {
                    this.proportion = proportion;
                }

                public String getVolume() {
                    return volume;
                }

                public void setVolume(String volume) {
                    this.volume = volume;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }
            }

            public static class PrintListBean {
                /**
                 * pieces : 0
                 * proportion : 0
                 * volume : 0.000
                 * weight : 0
                 */

                private String pieces;
                private String proportion;
                private String volume;
                private String weight;

                public String getPieces() {
                    return pieces;
                }

                public void setPieces(String pieces) {
                    this.pieces = pieces;
                }

                public String getProportion() {
                    return proportion;
                }

                public void setProportion(String proportion) {
                    this.proportion = proportion;
                }

                public String getVolume() {
                    return volume;
                }

                public void setVolume(String volume) {
                    this.volume = volume;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }
            }
        }

        public static class InsuranceIconsBean {
            /**
             * goodsExtension : {"buyType":"0","payType":"0"}
             */

            private GoodsExtensionBean goodsExtension;

            public GoodsExtensionBean getGoodsExtension() {
                return goodsExtension;
            }

            public void setGoodsExtension(GoodsExtensionBean goodsExtension) {
                this.goodsExtension = goodsExtension;
            }

            public static class GoodsExtensionBean {
                /**
                 * buyType : 0
                 * payType : 0
                 */

                private String buyType;
                private String payType;

                public String getBuyType() {
                    return buyType;
                }

                public void setBuyType(String buyType) {
                    this.buyType = buyType;
                }

                public String getPayType() {
                    return payType;
                }

                public void setPayType(String payType) {
                    this.payType = payType;
                }
            }
        }

        public static class OperatingBean {
            /**
             * feeConfirm : 1
             * freightFeeConfirm : 0
             * intoCabinConfirm : 0
             * makeBill : 1
             * makeBillConfirm : 0
             */

            private String feeConfirm;
            private String freightFeeConfirm;
            private String intoCabinConfirm;
            private String makeBill;
            private String makeBillConfirm;

            public String getFeeConfirm() {
                return feeConfirm;
            }

            public void setFeeConfirm(String feeConfirm) {
                this.feeConfirm = feeConfirm;
            }

            public String getFreightFeeConfirm() {
                return freightFeeConfirm;
            }

            public void setFreightFeeConfirm(String freightFeeConfirm) {
                this.freightFeeConfirm = freightFeeConfirm;
            }

            public String getIntoCabinConfirm() {
                return intoCabinConfirm;
            }

            public void setIntoCabinConfirm(String intoCabinConfirm) {
                this.intoCabinConfirm = intoCabinConfirm;
            }

            public String getMakeBill() {
                return makeBill;
            }

            public void setMakeBill(String makeBill) {
                this.makeBill = makeBill;
            }

            public String getMakeBillConfirm() {
                return makeBillConfirm;
            }

            public void setMakeBillConfirm(String makeBillConfirm) {
                this.makeBillConfirm = makeBillConfirm;
            }
        }
    }
}
