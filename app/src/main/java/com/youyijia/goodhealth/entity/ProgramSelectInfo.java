package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/16.
 */
public class ProgramSelectInfo {

    /**
     * airlineList : [{"imgLink":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180814/180814190011_19_航司卡片画板-51.png","name":"荷兰皇家航空公司","nameAbbr":"KL"},{"imgLink":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180814/180814183906_47_航司卡片LH.png","name":"LUFTHANSA CARGO AG","nameAbbr":"LH"},{"imgLink":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180814/180814185935_74_航司卡片画板-1.png","name":"AIR FRANCE","nameAbbr":"AF"}]
     * productCardList : [{"airline":"KL","area":["europe"],"burstType":"0","comment":"0","evaluation":"0","productDate":"2019-01-18","productIcons":{"crowdfunding":"0","goodsExtensionInsurance":"0","limitedSale":"0","newProduct":"0","popularity":"0","productLogo":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180820/180820094925_13_久茂logo.png","productType":"0","protectionBusinesses":"0","protectionHeart":"1","rushShopping":"0","shopSwitch":"0","supplierId":"","supplierName":"久茂国际"},"productName":"安心保-KL上海重板","productNo":"4801","readiness":{"overSpace":"1","realPrice":"14.00","space":"100"},"salesCount":"0","sort":{"integrated":"2","price":"4","sales":"5"},"startPort":"PVG-AMS","suit":{"overSpace":"10000","realPrice":"14.00","space":"0"}},{"airline":"KL","area":["europe"],"burstType":"0","comment":"1","evaluation":"5","productDate":"2019-01-18","productIcons":{"crowdfunding":"0","goodsExtensionInsurance":"0","limitedSale":"0","newProduct":"0","popularity":"0","productLogo":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180816/180816145948_83_久茂logo.png","productType":"0","protectionBusinesses":"0","protectionHeart":"0","rushShopping":"0","shopSwitch":"0","supplierId":"","supplierName":"久茂国际"},"productName":"KL-上海普货","productNo":"4769","readiness":{"overSpace":"2000","realPrice":"14.39","space":"0"},"salesCount":"1","sort":{"integrated":"1","price":"5","sales":"4"},"startPort":"PVG-AMS","suit":{"overSpace":"4000","realPrice":"14.59","space":"0"}},{"airline":"AF","area":["europe"],"burstType":"1","comment":"2","evaluation":"3","productDate":"2019-01-18","productIcons":{"crowdfunding":"0","goodsExtensionInsurance":"0","limitedSale":"0","newProduct":"0","popularity":"0","productLogo":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180820/180820095451_69_久茂logo.png","productType":"0","protectionBusinesses":"0","protectionHeart":"0","rushShopping":"0","shopSwitch":"0","supplierId":"","supplierName":"久茂国际"},"productName":"AF-欧洲重板","productNo":"5177","readiness":{"overSpace":"0","realPrice":"12.59","space":"100"},"salesCount":"24","sort":{"integrated":"5","price":"3","sales":"2"},"startPort":"PVG-CDG-AMS","suit":{"overSpace":"0","realPrice":"12.89","space":"100"}},{"airline":"KL","area":["europe"],"burstType":"0","comment":"26","evaluation":"5","productDate":"2019-01-18","productIcons":{"crowdfunding":"0","goodsExtensionInsurance":"1","limitedSale":"0","newProduct":"0","popularity":"0","productLogo":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180816/180816150407_58_久茂logo.png","productType":"0","protectionBusinesses":"0","protectionHeart":"0","rushShopping":"0","shopSwitch":"0","supplierId":"","supplierName":"久茂国际"},"productName":"KL-上海重板","productNo":"4778","readiness":{"overSpace":"3000","realPrice":"10.19","space":"0"},"salesCount":"172","sort":{"integrated":"4","price":"1","sales":"1"},"startPort":"PVG-AMS","suit":{"overSpace":"19900","realPrice":"10.59","space":"1"}},{"airline":"LH","area":["europe"],"burstType":"0","comment":"9","evaluation":"5","productDate":"2019-01-18","productIcons":{"crowdfunding":"0","goodsExtensionInsurance":"0","limitedSale":"0","newProduct":"0","popularity":"0","productLogo":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180820/180820094747_41_久茂logo.png","productType":"0","protectionBusinesses":"0","protectionHeart":"0","rushShopping":"0","shopSwitch":"0","supplierId":"","supplierName":"久茂国际"},"productName":"LH-上海重板","productNo":"4786","readiness":{"overSpace":"1000","realPrice":"11.99","space":"0"},"salesCount":"2","sort":{"integrated":"3","price":"2","sales":"3"},"startPort":"PVG-FRA-AMS","suit":{"overSpace":"5600","realPrice":"11.99","space":"0"}}]
     * productCardType : 0
     */

    private String productCardType;
    private List<AirlineListBean> airlineList;
    private List<ProductCardListBean> productCardList;

    public String getProductCardType() {
        return productCardType;
    }

    public void setProductCardType(String productCardType) {
        this.productCardType = productCardType;
    }

    public List<AirlineListBean> getAirlineList() {
        return airlineList;
    }

    public void setAirlineList(List<AirlineListBean> airlineList) {
        this.airlineList = airlineList;
    }

    public List<ProductCardListBean> getProductCardList() {
        return productCardList;
    }

    public void setProductCardList(List<ProductCardListBean> productCardList) {
        this.productCardList = productCardList;
    }

    public static class AirlineListBean {
        /**
         * imgLink : http://admin.3j-mall.com/aWCargoWebJMPic/0000/180814/180814190011_19_航司卡片画板-51.png
         * name : 荷兰皇家航空公司
         * nameAbbr : KL
         */

        private String imgLink;
        private String name;
        private String nameAbbr;
        public boolean isSelected;
        public int bigSortId;
        public String bigSortName;

        public String getImgLink() {
            return imgLink;
        }

        public void setImgLink(String imgLink) {
            this.imgLink = imgLink;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameAbbr() {
            return nameAbbr;
        }

        public void setNameAbbr(String nameAbbr) {
            this.nameAbbr = nameAbbr;
        }
    }

    public static class ProductCardListBean {
        /**
         * airline : KL
         * area : ["europe"]
         * burstType : 0
         * comment : 0
         * evaluation : 0
         * productDate : 2019-01-18
         * productIcons : {"crowdfunding":"0","goodsExtensionInsurance":"0","limitedSale":"0","newProduct":"0","popularity":"0","productLogo":"http://admin.3j-mall.com/aWCargoWebJMPic/0000/180820/180820094925_13_久茂logo.png","productType":"0","protectionBusinesses":"0","protectionHeart":"1","rushShopping":"0","shopSwitch":"0","supplierId":"","supplierName":"久茂国际"}
         * productName : 安心保-KL上海重板
         * productNo : 4801
         * readiness : {"overSpace":"1","realPrice":"14.00","space":"100"}
         * salesCount : 0
         * sort : {"integrated":"2","price":"4","sales":"5"}
         * startPort : PVG-AMS
         * suit : {"overSpace":"10000","realPrice":"14.00","space":"0"}
         */

        private String airline;
        private String burstType;
        private String comment;
        private String evaluation;
        private String productDate;
        private ProductIconsBean productIcons;
        private String productName;
        private String productNo;
        private ReadinessBean readiness;
        private String salesCount;
        private SortBean sort;
        private String startPort;
        private SuitBean suit;
        private List<String> area;
        public int viewType;
        public int position = -1;
        public int smallSortId;
        public String smallSortName;
        private SpaceDataBean spaceData;
        private String totalPrice;

        public SpaceDataBean getSpaceData() {
            return spaceData;
        }

        public void setSpaceData(SpaceDataBean spaceData) {
            this.spaceData = spaceData;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getAirline() {
            return airline;
        }

        public void setAirline(String airline) {
            this.airline = airline;
        }

        public String getBurstType() {
            return burstType;
        }

        public void setBurstType(String burstType) {
            this.burstType = burstType;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getEvaluation() {
            return evaluation;
        }

        public void setEvaluation(String evaluation) {
            this.evaluation = evaluation;
        }

        public String getProductDate() {
            return productDate;
        }

        public void setProductDate(String productDate) {
            this.productDate = productDate;
        }

        public ProductIconsBean getProductIcons() {
            return productIcons;
        }

        public void setProductIcons(ProductIconsBean productIcons) {
            this.productIcons = productIcons;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductNo() {
            return productNo;
        }

        public void setProductNo(String productNo) {
            this.productNo = productNo;
        }

        public ReadinessBean getReadiness() {
            return readiness;
        }

        public void setReadiness(ReadinessBean readiness) {
            this.readiness = readiness;
        }

        public String getSalesCount() {
            return salesCount;
        }

        public void setSalesCount(String salesCount) {
            this.salesCount = salesCount;
        }

        public SortBean getSort() {
            return sort;
        }

        public void setSort(SortBean sort) {
            this.sort = sort;
        }

        public String getStartPort() {
            return startPort;
        }

        public void setStartPort(String startPort) {
            this.startPort = startPort;
        }

        public SuitBean getSuit() {
            return suit;
        }

        public void setSuit(SuitBean suit) {
            this.suit = suit;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }

        public static class ProductIconsBean {
            /**
             * crowdfunding : 0
             * goodsExtensionInsurance : 0
             * limitedSale : 0
             * newProduct : 0
             * popularity : 0
             * productLogo : http://admin.3j-mall.com/aWCargoWebJMPic/0000/180820/180820094925_13_久茂logo.png
             * productType : 0
             * protectionBusinesses : 0
             * protectionHeart : 1
             * rushShopping : 0
             * shopSwitch : 0
             * supplierId :
             * supplierName : 久茂国际
             */

            private String crowdfunding;
            private String goodsExtensionInsurance;
            private String limitedSale;
            private String newProduct;
            private String popularity;
            private String productLogo;
            private String productType;
            private String protectionBusinesses;
            private String protectionHeart;
            private String rushShopping;
            private String shopSwitch;
            private String supplierId;
            private String supplierName;

            public String getCrowdfunding() {
                return crowdfunding;
            }

            public void setCrowdfunding(String crowdfunding) {
                this.crowdfunding = crowdfunding;
            }

            public String getGoodsExtensionInsurance() {
                return goodsExtensionInsurance;
            }

            public void setGoodsExtensionInsurance(String goodsExtensionInsurance) {
                this.goodsExtensionInsurance = goodsExtensionInsurance;
            }

            public String getLimitedSale() {
                return limitedSale;
            }

            public void setLimitedSale(String limitedSale) {
                this.limitedSale = limitedSale;
            }

            public String getNewProduct() {
                return newProduct;
            }

            public void setNewProduct(String newProduct) {
                this.newProduct = newProduct;
            }

            public String getPopularity() {
                return popularity;
            }

            public void setPopularity(String popularity) {
                this.popularity = popularity;
            }

            public String getProductLogo() {
                return productLogo;
            }

            public void setProductLogo(String productLogo) {
                this.productLogo = productLogo;
            }

            public String getProductType() {
                return productType;
            }

            public void setProductType(String productType) {
                this.productType = productType;
            }

            public String getProtectionBusinesses() {
                return protectionBusinesses;
            }

            public void setProtectionBusinesses(String protectionBusinesses) {
                this.protectionBusinesses = protectionBusinesses;
            }

            public String getProtectionHeart() {
                return protectionHeart;
            }

            public void setProtectionHeart(String protectionHeart) {
                this.protectionHeart = protectionHeart;
            }

            public String getRushShopping() {
                return rushShopping;
            }

            public void setRushShopping(String rushShopping) {
                this.rushShopping = rushShopping;
            }

            public String getShopSwitch() {
                return shopSwitch;
            }

            public void setShopSwitch(String shopSwitch) {
                this.shopSwitch = shopSwitch;
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
        }

        public static class ReadinessBean {
            /**
             * overSpace : 1
             * realPrice : 14.00
             * space : 100
             */

            private String overSpace;
            private String realPrice;
            private String space;

            public String getOverSpace() {
                return overSpace;
            }

            public void setOverSpace(String overSpace) {
                this.overSpace = overSpace;
            }

            public String getRealPrice() {
                return realPrice;
            }

            public void setRealPrice(String realPrice) {
                this.realPrice = realPrice;
            }

            public String getSpace() {
                return space;
            }

            public void setSpace(String space) {
                this.space = space;
            }
        }

        public static class SortBean {
            /**
             * integrated : 2
             * price : 4
             * sales : 5
             */

            private String integrated;
            private String price;
            private String sales;

            public String getIntegrated() {
                return integrated;
            }

            public void setIntegrated(String integrated) {
                this.integrated = integrated;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSales() {
                return sales;
            }

            public void setSales(String sales) {
                this.sales = sales;
            }
        }

        public static class SuitBean {
            /**
             * overSpace : 10000
             * realPrice : 14.00
             * space : 0
             */

            private String overSpace;
            private String realPrice;
            private String space;

            public String getOverSpace() {
                return overSpace;
            }

            public void setOverSpace(String overSpace) {
                this.overSpace = overSpace;
            }

            public String getRealPrice() {
                return realPrice;
            }

            public void setRealPrice(String realPrice) {
                this.realPrice = realPrice;
            }

            public String getSpace() {
                return space;
            }

            public void setSpace(String space) {
                this.space = space;
            }
        }

        public static class SpaceDataBean {
            /**
             * bookingPosition : 0
             * overSpace : 500
             * price : 16.99
             * reOverSpace : 0
             * reSpace : 100
             * space : 0
             */

            private String bookingPosition;
            private String overSpace;
            private String price;
            private String reOverSpace;
            private String reSpace;
            private String space;

            public String getBookingPosition() {
                return bookingPosition;
            }

            public void setBookingPosition(String bookingPosition) {
                this.bookingPosition = bookingPosition;
            }

            public String getOverSpace() {
                return overSpace;
            }

            public void setOverSpace(String overSpace) {
                this.overSpace = overSpace;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getReOverSpace() {
                return reOverSpace;
            }

            public void setReOverSpace(String reOverSpace) {
                this.reOverSpace = reOverSpace;
            }

            public String getReSpace() {
                return reSpace;
            }

            public void setReSpace(String reSpace) {
                this.reSpace = reSpace;
            }

            public String getSpace() {
                return space;
            }

            public void setSpace(String space) {
                this.space = space;
            }
        }
    }

}
