package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/31.
 */
public class OrderBillTypeInfo {
    /**
     * bookingData : {"feeWeight":"","pieces":"1","proportion":"333","volume":"0.300","weight":"100"}
     * bookingFee : {"pieces":"100.00","tips":"","total":"1249.00","unitPrice":"12.49"}
     * confirmTypeGroup : {"freightFeeType":"0","intoCabinType":"0","makeBillType":"0"}
     * customsClearanceWeight :
     * freightFeePlan : 1
     * intoCabinDataList : [{"date":"2019.01.11-17:28","goodsInfo":[{"boxStatus":"","high":"30","length":"100","packageMethod":"","pieces":"1","width":"100"}],"number":"956DSGC19010377","pieces":"1","volume":"0.300","weight":"200"}]
     * intoCabinFee : {"pieces":"100.00","tips":"","total":"1249.00","unitPrice":"12.49"}
     * intoCabinTotal : {"feeWeight":"","pieces":"1","proportion":"667","volume":"0.300","weight":"200"}
     * minCustomsClearanceWeight : 90
     * optimizationFee : {"pieces":"100.00","tips":"","total":"1249.00","unitPrice":"12.49"}
     * productType : 0
     * transportType : 0
     */

    private BookingDataBean bookingData;
    private BookingFeeBean bookingFee;
    private ConfirmTypeGroupBean confirmTypeGroup;
    private String customsClearanceWeight;
    private String freightFeePlan;
    private IntoCabinFeeBean intoCabinFee;
    private IntoCabinTotalBean intoCabinTotal;
    private String minCustomsClearanceWeight;
    private OptimizationFeeBean optimizationFee;
    private String productType;
    private String transportType;
    private List<IntoCabinDataListBean> intoCabinDataList;

    public BookingDataBean getBookingData() {
        return bookingData;
    }

    public void setBookingData(BookingDataBean bookingData) {
        this.bookingData = bookingData;
    }

    public BookingFeeBean getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(BookingFeeBean bookingFee) {
        this.bookingFee = bookingFee;
    }

    public ConfirmTypeGroupBean getConfirmTypeGroup() {
        return confirmTypeGroup;
    }

    public void setConfirmTypeGroup(ConfirmTypeGroupBean confirmTypeGroup) {
        this.confirmTypeGroup = confirmTypeGroup;
    }

    public String getCustomsClearanceWeight() {
        return customsClearanceWeight;
    }

    public void setCustomsClearanceWeight(String customsClearanceWeight) {
        this.customsClearanceWeight = customsClearanceWeight;
    }

    public String getFreightFeePlan() {
        return freightFeePlan;
    }

    public void setFreightFeePlan(String freightFeePlan) {
        this.freightFeePlan = freightFeePlan;
    }

    public IntoCabinFeeBean getIntoCabinFee() {
        return intoCabinFee;
    }

    public void setIntoCabinFee(IntoCabinFeeBean intoCabinFee) {
        this.intoCabinFee = intoCabinFee;
    }

    public IntoCabinTotalBean getIntoCabinTotal() {
        return intoCabinTotal;
    }

    public void setIntoCabinTotal(IntoCabinTotalBean intoCabinTotal) {
        this.intoCabinTotal = intoCabinTotal;
    }

    public String getMinCustomsClearanceWeight() {
        return minCustomsClearanceWeight;
    }

    public void setMinCustomsClearanceWeight(String minCustomsClearanceWeight) {
        this.minCustomsClearanceWeight = minCustomsClearanceWeight;
    }

    public OptimizationFeeBean getOptimizationFee() {
        return optimizationFee;
    }

    public void setOptimizationFee(OptimizationFeeBean optimizationFee) {
        this.optimizationFee = optimizationFee;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public List<IntoCabinDataListBean> getIntoCabinDataList() {
        return intoCabinDataList;
    }

    public void setIntoCabinDataList(List<IntoCabinDataListBean> intoCabinDataList) {
        this.intoCabinDataList = intoCabinDataList;
    }

    public static class BookingDataBean {
        /**
         * feeWeight :
         * pieces : 1
         * proportion : 333
         * volume : 0.300
         * weight : 100
         */

        private String feeWeight;
        private String pieces;
        private String proportion;
        private String volume;
        private String weight;

        public String getFeeWeight() {
            return feeWeight;
        }

        public void setFeeWeight(String feeWeight) {
            this.feeWeight = feeWeight;
        }

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

    public static class BookingFeeBean {
        /**
         * pieces : 100.00
         * tips :
         * total : 1249.00
         * unitPrice : 12.49
         */

        private String pieces;
        private String tips;
        private String total;
        private String unitPrice;

        public String getPieces() {
            return pieces;
        }

        public void setPieces(String pieces) {
            this.pieces = pieces;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }
    }

    public static class ConfirmTypeGroupBean {
        /**
         * freightFeeType : 0
         * intoCabinType : 0
         * makeBillType : 0
         */

        private String freightFeeType;
        private String intoCabinType;
        private String makeBillType;

        public String getFreightFeeType() {
            return freightFeeType;
        }

        public void setFreightFeeType(String freightFeeType) {
            this.freightFeeType = freightFeeType;
        }

        public String getIntoCabinType() {
            return intoCabinType;
        }

        public void setIntoCabinType(String intoCabinType) {
            this.intoCabinType = intoCabinType;
        }

        public String getMakeBillType() {
            return makeBillType;
        }

        public void setMakeBillType(String makeBillType) {
            this.makeBillType = makeBillType;
        }
    }

    public static class IntoCabinFeeBean {
        /**
         * pieces : 100.00
         * tips :
         * total : 1249.00
         * unitPrice : 12.49
         */

        private String pieces;
        private String tips;
        private String total;
        private String unitPrice;

        public String getPieces() {
            return pieces;
        }

        public void setPieces(String pieces) {
            this.pieces = pieces;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }
    }

    public static class IntoCabinTotalBean {
        /**
         * feeWeight :
         * pieces : 1
         * proportion : 667
         * volume : 0.300
         * weight : 200
         */

        private String feeWeight;
        private String pieces;
        private String proportion;
        private String volume;
        private String weight;

        public String getFeeWeight() {
            return feeWeight;
        }

        public void setFeeWeight(String feeWeight) {
            this.feeWeight = feeWeight;
        }

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

    public static class OptimizationFeeBean {
        /**
         * pieces : 100.00
         * tips :
         * total : 1249.00
         * unitPrice : 12.49
         */

        private String pieces;
        private String tips;
        private String total;
        private String unitPrice;

        public String getPieces() {
            return pieces;
        }

        public void setPieces(String pieces) {
            this.pieces = pieces;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }
    }

    public static class IntoCabinDataListBean {
        /**
         * date : 2019.01.11-17:28
         * goodsInfo : [{"boxStatus":"","high":"30","length":"100","packageMethod":"","pieces":"1","width":"100"}]
         * number : 956DSGC19010377
         * pieces : 1
         * volume : 0.300
         * weight : 200
         */

        private String date;
        private String number;
        private String pieces;
        private String volume;
        private String weight;
        private List<GoodsInfoBean> goodsInfo;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPieces() {
            return pieces;
        }

        public void setPieces(String pieces) {
            this.pieces = pieces;
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

        public List<GoodsInfoBean> getGoodsInfo() {
            return goodsInfo;
        }

        public void setGoodsInfo(List<GoodsInfoBean> goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        public static class GoodsInfoBean {
            /**
             * boxStatus :
             * high : 30
             * length : 100
             * packageMethod :
             * pieces : 1
             * width : 100
             */

            private String boxStatus;
            private String high;
            private String length;
            private String packageMethod;
            private String pieces;
            private String width;

            public String getBoxStatus() {
                return boxStatus;
            }

            public void setBoxStatus(String boxStatus) {
                this.boxStatus = boxStatus;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getPackageMethod() {
                return packageMethod;
            }

            public void setPackageMethod(String packageMethod) {
                this.packageMethod = packageMethod;
            }

            public String getPieces() {
                return pieces;
            }

            public void setPieces(String pieces) {
                this.pieces = pieces;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }
        }
    }
}
