package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/18.
 */
public class ProDateInfo {
    private List<ProductDateListBean> productDateList;

    public List<ProductDateListBean> getProductDateList() {
        return productDateList;
    }

    public void setProductDateList(List<ProductDateListBean> productDateList) {
        this.productDateList = productDateList;
    }

    public static class ProductDateListBean {
        /**
         * activity : {"code":"","sign":"","type":"0"}
         * date : 2019-01-20
         * errData :
         * price : 11.00
         * space : {"overSpace":"5000","readiness":"0","suit":"0"}
         */

        private ActivityBean activity;
        private String date;
        private String errData;
        private String price;
        private SpaceBean space;

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getErrData() {
            return errData;
        }

        public void setErrData(String errData) {
            this.errData = errData;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public SpaceBean getSpace() {
            return space;
        }

        public void setSpace(SpaceBean space) {
            this.space = space;
        }

        public static class ActivityBean {
            /**
             * code :
             * sign :
             * type : 0
             */

            private String code;
            private String sign;
            private String type;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class SpaceBean {
            /**
             * overSpace : 5000
             * readiness : 0
             * suit : 0
             */

            private String overSpace;
            private String readiness;
            private String suit;

            public String getOverSpace() {
                return overSpace;
            }

            public void setOverSpace(String overSpace) {
                this.overSpace = overSpace;
            }

            public String getReadiness() {
                return readiness;
            }

            public void setReadiness(String readiness) {
                this.readiness = readiness;
            }

            public String getSuit() {
                return suit;
            }

            public void setSuit(String suit) {
                this.suit = suit;
            }
        }
    }
}
