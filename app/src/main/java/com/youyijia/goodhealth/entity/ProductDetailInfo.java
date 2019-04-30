package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/1/17.
 */
public class ProductDetailInfo {

    /**
     * portData : {"destination":[{"area":"AMS","portList":[{"name":"阿姆斯特丹，荷兰 (Netherlands)首都，欧洲","port":"AMS","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"11.00"},"w1000":{"offType":"0","price":"14.00"},"w300":{"offType":"0","price":"12.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"13.00"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲1区","portList":[{"name":"布鲁塞尔，比利时 (Belgium)首都，欧洲","port":"BRU","valid":"1"},{"name":"夏尔.戴高乐机场","port":"CDG","valid":"1"},{"name":"法兰克福，德国 (Germany)，欧洲","port":"FRA","valid":"1"},{"name":"卢森堡，卢森堡 (Luxembourg)首都 ,欧洲","port":"LUX","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"11.50"},"w1000":{"offType":"0","price":"14.50"},"w300":{"offType":"0","price":"12.50"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"13.50"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲2区","portList":[{"name":"不来梅，德国 (Germany)，欧洲","port":"BRE","valid":"1"},{"name":"巴塞尔，瑞士 (Switzerland)，欧洲","port":"BSL","valid":"1"},{"name":"科隆，德国 (Germany)，欧洲","port":"CGN","valid":"1"},{"name":"德累斯顿 ,德国 (Germany)，欧洲","port":"DRS","valid":"1"},{"name":"都柏林，爱尔兰 (Ireland)首都，欧洲","port":"DUB","valid":"1"},{"name":"杜塞尔多夫 ,德国 (Germany)，欧洲","port":"DUS","valid":"1"},{"name":"艾恩德霍文 ,荷兰 (Netherlands ),欧洲","port":"EIN","valid":"1"},{"name":"明斯特 , 德国 (Germany), 欧洲","port":"FMO","valid":"1"},{"name":"格罗宁根，荷兰（ Netherlands），欧洲","port":"GRQ","valid":"1"},{"name":"汉诺威，德国 (Germany)，欧洲","port":"HAJ","valid":"1"},{"name":"汉堡，德国 (Germany)，欧洲","port":"HAM","valid":"1"},{"name":"莱比锡 ,德国 (Germany), 欧洲","port":"LEJ","valid":"1"},{"name":"里尔 ,法国 (France), 欧洲","port":"LIL","valid":"1"},{"name":"里昂 ,法国 (France),欧洲","port":"LYS","valid":"1"},{"name":"米卢斯 , 法国 (France), 欧洲","port":"MLH","valid":"1"},{"name":"马斯特里赫特，荷兰（ Netherlands），欧洲","port":"MST","valid":"1"},{"name":"慕尼黑 , 德国 (Germany),欧洲","port":"MUC","valid":"1"},{"name":"纽伦堡 ,德国 (Germany), 欧洲","port":"NUE","valid":"1"},{"name":"鹿特丹机场","port":"RTM","valid":"1"},{"name":"斯图加特，德国 (Germany)，欧洲","port":"STR","valid":"1"},{"name":"泰格尔机场","port":"TXL","valid":"1"},{"name":"苏黎世，瑞士 (Switzerland),欧洲","port":"ZRH","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"12.00"},"w1000":{"offType":"0","price":"15.00"},"w300":{"offType":"0","price":"13.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"14.00"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲3区","portList":[{"name":"巴塞罗那，西班牙 (Spain)，欧洲","port":"BCN","valid":"1"},{"name":"比隆,丹麦（Demmark），北欧","port":"BLL","valid":"1"},{"name":"布达佩斯，匈牙利 (Hungary) 首都，欧洲","port":"BUD","valid":"1"},{"name":"哥本哈根，丹麦 (Denmark)首都，欧洲","port":"CPH","valid":"1"},{"name":"哥德堡，瑞典 (Sweden)，欧洲","port":"GOT","valid":"1"},{"name":"赫尔辛基，芬兰 (Finland)首都，北欧","port":"HEL","valid":"1"},{"name":"伦敦，英国","port":"LHR","valid":"1"},{"name":"里斯本，葡萄牙 (Portugal)首都、欧洲","port":"LIS","valid":"1"},{"name":"林兹 ,奥地利 (Austria), 欧洲","port":"LNZ","valid":"1"},{"name":"马德里 ,西班牙 (Spain)首都 , 欧洲","port":"MAD","valid":"1"},{"name":"曼彻斯特，英国 (United Kingdom), 欧洲","port":"MAN","valid":"1"},{"name":"纽长斯尔，英国 (United Kingdom),欧洲","port":"NCL","valid":"1"},{"name":"波尔图，葡萄牙（ Portugal），欧洲","port":"OPO","valid":"1"},{"name":"布拉格，捷克 (Czech Republic)首都 , 欧洲","port":"PRG","valid":"1"},{"name":"维也纳 ,奥地利 (Austria),首都 ,欧洲","port":"VIE","valid":"1"},{"name":"奥肯切机场","port":"WAW","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"12.50"},"w1000":{"offType":"0","price":"15.50"},"w300":{"offType":"0","price":"13.50"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"14.50"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲4区","portList":[{"name":"斯特哥尔摩，瑞典","port":"ARN","valid":"1"},{"name":"布里斯托尔，英国（ United Kingdom），欧洲","port":"BRS","valid":"1"},{"name":"霍恩埃姆斯，奥地利（ Austria），欧洲中部","port":"HOH","valid":"1"},{"name":"马尔默，瑞典","port":"MMX","valid":"1"},{"name":"奥斯陆，挪威 (Norway)首都，北欧","port":"OSL","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"13.00"},"w1000":{"offType":"0","price":"16.00"},"w300":{"offType":"0","price":"14.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"15.00"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲5区","portList":[{"name":"贝尔法斯特，英国（ United Kingdom），欧洲","port":"BFS","valid":"1"},{"name":"格拉斯哥，英国 (United Kingdom)欧洲","port":"GLA","valid":"1"},{"name":"利兹，英国 (United Kingdom ), 欧洲","port":"LBA","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"13.50"},"w1000":{"offType":"0","price":"16.50"},"w300":{"offType":"0","price":"14.50"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"15.50"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲6区","portList":[{"name":"香农,爱尔兰（Ireland），欧洲","port":"SNN","valid":"1"},{"name":"斯塔万格，挪威 (Norway)，欧洲","port":"SVG","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"14.00"},"w1000":{"offType":"0","price":"17.00"},"w300":{"offType":"0","price":"15.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"16.00"},"w5000":{"offType":"0","price":"0.00"}}}],"transit":"AMS"}
     * productAttachInfo : {"deliverySituation":[{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"10:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"10:30:00"}],"flightInfo":{"theFirstFlight":{"detailData":[{"aircraftType":"B787-9","arrivalDate":"18:00 &nbsp;&nbsp;","flightNumber":"KL896","flightShift":"1,2,3,4,5,6,7","startDate":"12:15","transportation":"飞机"},{"aircraftType":"B787-9","arrivalDate":"04:30 &nbsp;&nbsp; +1","flightNumber":"KL894","flightShift":"3,5,7","startDate":"23:59","transportation":"飞机"}],"route":"PVG-AMS"},"theSecondaryFlight":{"detailData":[{"aircraftType":"","arrivalDate":"","flightNumber":"KL8625","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8621","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8623","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"}],"route":"AMS-CDG"}},"generalCargoPolicy":{"chemicalsPackageClaim":"液体 托盘 散装","directTransfer":"中转","lithiumBatteryModel":"PI966 PI967 PI970 纽扣电池 蓄电池","minWeight":"100.00KG","packageLimit":"","sizeLimit":"板型尺寸： ","tips":"","weightLimit":"0.00KG"}}
     * productDate : {"activity":{"code":"","sign":"","type":"0"},"date":"2019-01-20","errData":"","price":"11.50","space":{"overSpace":"5000","readiness":"0","suit":"0"}}
     * productDetail : {"airline":"KL","destination":"CDG","directPoint":"AMS","heavySign":"0","mergeNo":["4801"],"productName":"安心保-KL上海重板","productNo":"4801","readinessCount":"0","readinessOffMoney":"0","shopSwitch":"0","startPort":"PVG","supplierId":"","supplierName":"久茂国际","trayType":"1"}
     * proportionList : {"active":"0","proportion":[{"data":"0","offType":"0"}],"realActive":""}
     * totalPrice : {"priceCheckId":"","quotedPriceId":"1168993","throwingGoodsLimit":"0","total":"0.00"}
     * transferPrice : {"chargeableWeight":"","differencePrice":"","minPrice":"","reallyPrice":"","transferType":"0","unitPrice":""}
     * weightPrice : {"active":"100","weight":{"w100":"11.50","w1000":"14.50","w300":"12.50","w3000":"0.00","w500":"13.50","w5000":"0.00"}}
     */

    private PortDataBean portData;
    private ProductAttachInfoBean productAttachInfo;
    private ProductDateBean productDate;
    private ProductDetailBean productDetail;
    private ProportionListBean proportionList;
    private TotalPriceBean totalPrice;
    private TransferPriceBean transferPrice;
    private WeightPriceBean weightPrice;

    public PortDataBean getPortData() {
        return portData;
    }

    public void setPortData(PortDataBean portData) {
        this.portData = portData;
    }

    public ProductAttachInfoBean getProductAttachInfo() {
        return productAttachInfo;
    }

    public void setProductAttachInfo(ProductAttachInfoBean productAttachInfo) {
        this.productAttachInfo = productAttachInfo;
    }

    public ProductDateBean getProductDate() {
        return productDate;
    }

    public void setProductDate(ProductDateBean productDate) {
        this.productDate = productDate;
    }

    public ProductDetailBean getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailBean productDetail) {
        this.productDetail = productDetail;
    }

    public ProportionListBean getProportionList() {
        return proportionList;
    }

    public void setProportionList(ProportionListBean proportionList) {
        this.proportionList = proportionList;
    }

    public TotalPriceBean getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TotalPriceBean totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TransferPriceBean getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(TransferPriceBean transferPrice) {
        this.transferPrice = transferPrice;
    }

    public WeightPriceBean getWeightPrice() {
        return weightPrice;
    }

    public void setWeightPrice(WeightPriceBean weightPrice) {
        this.weightPrice = weightPrice;
    }

    public static class PortDataBean {
        /**
         * destination : [{"area":"AMS","portList":[{"name":"阿姆斯特丹，荷兰 (Netherlands)首都，欧洲","port":"AMS","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"11.00"},"w1000":{"offType":"0","price":"14.00"},"w300":{"offType":"0","price":"12.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"13.00"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲1区","portList":[{"name":"布鲁塞尔，比利时 (Belgium)首都，欧洲","port":"BRU","valid":"1"},{"name":"夏尔.戴高乐机场","port":"CDG","valid":"1"},{"name":"法兰克福，德国 (Germany)，欧洲","port":"FRA","valid":"1"},{"name":"卢森堡，卢森堡 (Luxembourg)首都 ,欧洲","port":"LUX","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"11.50"},"w1000":{"offType":"0","price":"14.50"},"w300":{"offType":"0","price":"12.50"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"13.50"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲2区","portList":[{"name":"不来梅，德国 (Germany)，欧洲","port":"BRE","valid":"1"},{"name":"巴塞尔，瑞士 (Switzerland)，欧洲","port":"BSL","valid":"1"},{"name":"科隆，德国 (Germany)，欧洲","port":"CGN","valid":"1"},{"name":"德累斯顿 ,德国 (Germany)，欧洲","port":"DRS","valid":"1"},{"name":"都柏林，爱尔兰 (Ireland)首都，欧洲","port":"DUB","valid":"1"},{"name":"杜塞尔多夫 ,德国 (Germany)，欧洲","port":"DUS","valid":"1"},{"name":"艾恩德霍文 ,荷兰 (Netherlands ),欧洲","port":"EIN","valid":"1"},{"name":"明斯特 , 德国 (Germany), 欧洲","port":"FMO","valid":"1"},{"name":"格罗宁根，荷兰（ Netherlands），欧洲","port":"GRQ","valid":"1"},{"name":"汉诺威，德国 (Germany)，欧洲","port":"HAJ","valid":"1"},{"name":"汉堡，德国 (Germany)，欧洲","port":"HAM","valid":"1"},{"name":"莱比锡 ,德国 (Germany), 欧洲","port":"LEJ","valid":"1"},{"name":"里尔 ,法国 (France), 欧洲","port":"LIL","valid":"1"},{"name":"里昂 ,法国 (France),欧洲","port":"LYS","valid":"1"},{"name":"米卢斯 , 法国 (France), 欧洲","port":"MLH","valid":"1"},{"name":"马斯特里赫特，荷兰（ Netherlands），欧洲","port":"MST","valid":"1"},{"name":"慕尼黑 , 德国 (Germany),欧洲","port":"MUC","valid":"1"},{"name":"纽伦堡 ,德国 (Germany), 欧洲","port":"NUE","valid":"1"},{"name":"鹿特丹机场","port":"RTM","valid":"1"},{"name":"斯图加特，德国 (Germany)，欧洲","port":"STR","valid":"1"},{"name":"泰格尔机场","port":"TXL","valid":"1"},{"name":"苏黎世，瑞士 (Switzerland),欧洲","port":"ZRH","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"12.00"},"w1000":{"offType":"0","price":"15.00"},"w300":{"offType":"0","price":"13.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"14.00"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲3区","portList":[{"name":"巴塞罗那，西班牙 (Spain)，欧洲","port":"BCN","valid":"1"},{"name":"比隆,丹麦（Demmark），北欧","port":"BLL","valid":"1"},{"name":"布达佩斯，匈牙利 (Hungary) 首都，欧洲","port":"BUD","valid":"1"},{"name":"哥本哈根，丹麦 (Denmark)首都，欧洲","port":"CPH","valid":"1"},{"name":"哥德堡，瑞典 (Sweden)，欧洲","port":"GOT","valid":"1"},{"name":"赫尔辛基，芬兰 (Finland)首都，北欧","port":"HEL","valid":"1"},{"name":"伦敦，英国","port":"LHR","valid":"1"},{"name":"里斯本，葡萄牙 (Portugal)首都、欧洲","port":"LIS","valid":"1"},{"name":"林兹 ,奥地利 (Austria), 欧洲","port":"LNZ","valid":"1"},{"name":"马德里 ,西班牙 (Spain)首都 , 欧洲","port":"MAD","valid":"1"},{"name":"曼彻斯特，英国 (United Kingdom), 欧洲","port":"MAN","valid":"1"},{"name":"纽长斯尔，英国 (United Kingdom),欧洲","port":"NCL","valid":"1"},{"name":"波尔图，葡萄牙（ Portugal），欧洲","port":"OPO","valid":"1"},{"name":"布拉格，捷克 (Czech Republic)首都 , 欧洲","port":"PRG","valid":"1"},{"name":"维也纳 ,奥地利 (Austria),首都 ,欧洲","port":"VIE","valid":"1"},{"name":"奥肯切机场","port":"WAW","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"12.50"},"w1000":{"offType":"0","price":"15.50"},"w300":{"offType":"0","price":"13.50"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"14.50"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲4区","portList":[{"name":"斯特哥尔摩，瑞典","port":"ARN","valid":"1"},{"name":"布里斯托尔，英国（ United Kingdom），欧洲","port":"BRS","valid":"1"},{"name":"霍恩埃姆斯，奥地利（ Austria），欧洲中部","port":"HOH","valid":"1"},{"name":"马尔默，瑞典","port":"MMX","valid":"1"},{"name":"奥斯陆，挪威 (Norway)首都，北欧","port":"OSL","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"13.00"},"w1000":{"offType":"0","price":"16.00"},"w300":{"offType":"0","price":"14.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"15.00"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲5区","portList":[{"name":"贝尔法斯特，英国（ United Kingdom），欧洲","port":"BFS","valid":"1"},{"name":"格拉斯哥，英国 (United Kingdom)欧洲","port":"GLA","valid":"1"},{"name":"利兹，英国 (United Kingdom ), 欧洲","port":"LBA","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"13.50"},"w1000":{"offType":"0","price":"16.50"},"w300":{"offType":"0","price":"14.50"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"15.50"},"w5000":{"offType":"0","price":"0.00"}}},{"area":"欧洲6区","portList":[{"name":"香农,爱尔兰（Ireland），欧洲","port":"SNN","valid":"1"},{"name":"斯塔万格，挪威 (Norway)，欧洲","port":"SVG","valid":"1"}],"priceList":{"w100":{"offType":"0","price":"14.00"},"w1000":{"offType":"0","price":"17.00"},"w300":{"offType":"0","price":"15.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"16.00"},"w5000":{"offType":"0","price":"0.00"}}}]
         * transit : AMS
         */

        private String transit;
        private List<DestinationBean> destination;

        public String getTransit() {
            return transit;
        }

        public void setTransit(String transit) {
            this.transit = transit;
        }

        public List<DestinationBean> getDestination() {
            return destination;
        }

        public void setDestination(List<DestinationBean> destination) {
            this.destination = destination;
        }

        public static class DestinationBean {
            /**
             * area : AMS
             * portList : [{"name":"阿姆斯特丹，荷兰 (Netherlands)首都，欧洲","port":"AMS","valid":"1"}]
             * priceList : {"w100":{"offType":"0","price":"11.00"},"w1000":{"offType":"0","price":"14.00"},"w300":{"offType":"0","price":"12.00"},"w3000":{"offType":"0","price":"0.00"},"w500":{"offType":"0","price":"13.00"},"w5000":{"offType":"0","price":"0.00"}}
             */

            private String area;
            private PriceListBean priceList;
            private List<PortListBean> portList;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public PriceListBean getPriceList() {
                return priceList;
            }

            public void setPriceList(PriceListBean priceList) {
                this.priceList = priceList;
            }

            public List<PortListBean> getPortList() {
                return portList;
            }

            public void setPortList(List<PortListBean> portList) {
                this.portList = portList;
            }

            public static class PriceListBean {
                /**
                 * w100 : {"offType":"0","price":"11.00"}
                 * w1000 : {"offType":"0","price":"14.00"}
                 * w300 : {"offType":"0","price":"12.00"}
                 * w3000 : {"offType":"0","price":"0.00"}
                 * w500 : {"offType":"0","price":"13.00"}
                 * w5000 : {"offType":"0","price":"0.00"}
                 */

                private W100Bean w100;
                private W1000Bean w1000;
                private W300Bean w300;
                private W3000Bean w3000;
                private W500Bean w500;
                private W5000Bean w5000;

                public W100Bean getW100() {
                    return w100;
                }

                public void setW100(W100Bean w100) {
                    this.w100 = w100;
                }

                public W1000Bean getW1000() {
                    return w1000;
                }

                public void setW1000(W1000Bean w1000) {
                    this.w1000 = w1000;
                }

                public W300Bean getW300() {
                    return w300;
                }

                public void setW300(W300Bean w300) {
                    this.w300 = w300;
                }

                public W3000Bean getW3000() {
                    return w3000;
                }

                public void setW3000(W3000Bean w3000) {
                    this.w3000 = w3000;
                }

                public W500Bean getW500() {
                    return w500;
                }

                public void setW500(W500Bean w500) {
                    this.w500 = w500;
                }

                public W5000Bean getW5000() {
                    return w5000;
                }

                public void setW5000(W5000Bean w5000) {
                    this.w5000 = w5000;
                }

                public static class W100Bean {
                    /**
                     * offType : 0
                     * price : 11.00
                     */

                    private String offType;
                    private String price;

                    public String getOffType() {
                        return offType;
                    }

                    public void setOffType(String offType) {
                        this.offType = offType;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }
                }

                public static class W1000Bean {
                    /**
                     * offType : 0
                     * price : 14.00
                     */

                    private String offType;
                    private String price;

                    public String getOffType() {
                        return offType;
                    }

                    public void setOffType(String offType) {
                        this.offType = offType;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }
                }

                public static class W300Bean {
                    /**
                     * offType : 0
                     * price : 12.00
                     */

                    private String offType;
                    private String price;

                    public String getOffType() {
                        return offType;
                    }

                    public void setOffType(String offType) {
                        this.offType = offType;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }
                }

                public static class W3000Bean {
                    /**
                     * offType : 0
                     * price : 0.00
                     */

                    private String offType;
                    private String price;

                    public String getOffType() {
                        return offType;
                    }

                    public void setOffType(String offType) {
                        this.offType = offType;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }
                }

                public static class W500Bean {
                    /**
                     * offType : 0
                     * price : 13.00
                     */

                    private String offType;
                    private String price;

                    public String getOffType() {
                        return offType;
                    }

                    public void setOffType(String offType) {
                        this.offType = offType;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }
                }

                public static class W5000Bean {
                    /**
                     * offType : 0
                     * price : 0.00
                     */

                    private String offType;
                    private String price;

                    public String getOffType() {
                        return offType;
                    }

                    public void setOffType(String offType) {
                        this.offType = offType;
                    }

                    public String getPrice() {
                        return price;
                    }

                    public void setPrice(String price) {
                        this.price = price;
                    }
                }
            }

            public static class PortListBean {
                /**
                 * name : 阿姆斯特丹，荷兰 (Netherlands)首都，欧洲
                 * port : AMS
                 * valid : 1
                 */

                private String name;
                private String port;
                private String valid;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPort() {
                    return port;
                }

                public void setPort(String port) {
                    this.port = port;
                }

                public String getValid() {
                    return valid;
                }

                public void setValid(String valid) {
                    this.valid = valid;
                }
            }
        }
    }

    public static class ProductAttachInfoBean {
        /**
         * deliverySituation : [{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"02:00:00"},{"deliveryCity":"","deliveryTime":"19:30","flightNumber":"KL894","intoCabinTime":"10:00:00"},{"deliveryCity":"","deliveryTime":"06:00","flightNumber":"KL896","intoCabinTime":"10:30:00"}]
         * flightInfo : {"theFirstFlight":{"detailData":[{"aircraftType":"B787-9","arrivalDate":"18:00 &nbsp;&nbsp;","flightNumber":"KL896","flightShift":"1,2,3,4,5,6,7","startDate":"12:15","transportation":"飞机"},{"aircraftType":"B787-9","arrivalDate":"04:30 &nbsp;&nbsp; +1","flightNumber":"KL894","flightShift":"3,5,7","startDate":"23:59","transportation":"飞机"}],"route":"PVG-AMS"},"theSecondaryFlight":{"detailData":[{"aircraftType":"","arrivalDate":"","flightNumber":"KL8625","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8621","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8623","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"}],"route":"AMS-CDG"}}
         * generalCargoPolicy : {"chemicalsPackageClaim":"液体 托盘 散装","directTransfer":"中转","lithiumBatteryModel":"PI966 PI967 PI970 纽扣电池 蓄电池","minWeight":"100.00KG","packageLimit":"","sizeLimit":"板型尺寸： ","tips":"","weightLimit":"0.00KG"}
         */

        private FlightInfoBean flightInfo;
        private GeneralCargoPolicyBean generalCargoPolicy;
        private List<DeliverySituationBean> deliverySituation;

        public FlightInfoBean getFlightInfo() {
            return flightInfo;
        }

        public void setFlightInfo(FlightInfoBean flightInfo) {
            this.flightInfo = flightInfo;
        }

        public GeneralCargoPolicyBean getGeneralCargoPolicy() {
            return generalCargoPolicy;
        }

        public void setGeneralCargoPolicy(GeneralCargoPolicyBean generalCargoPolicy) {
            this.generalCargoPolicy = generalCargoPolicy;
        }

        public List<DeliverySituationBean> getDeliverySituation() {
            return deliverySituation;
        }

        public void setDeliverySituation(List<DeliverySituationBean> deliverySituation) {
            this.deliverySituation = deliverySituation;
        }

        public static class FlightInfoBean {
            /**
             * theFirstFlight : {"detailData":[{"aircraftType":"B787-9","arrivalDate":"18:00 &nbsp;&nbsp;","flightNumber":"KL896","flightShift":"1,2,3,4,5,6,7","startDate":"12:15","transportation":"飞机"},{"aircraftType":"B787-9","arrivalDate":"04:30 &nbsp;&nbsp; +1","flightNumber":"KL894","flightShift":"3,5,7","startDate":"23:59","transportation":"飞机"}],"route":"PVG-AMS"}
             * theSecondaryFlight : {"detailData":[{"aircraftType":"","arrivalDate":"","flightNumber":"KL8625","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8621","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8623","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"}],"route":"AMS-CDG"}
             */

            private TheFirstFlightBean theFirstFlight;
            private TheSecondaryFlightBean theSecondaryFlight;

            public TheFirstFlightBean getTheFirstFlight() {
                return theFirstFlight;
            }

            public void setTheFirstFlight(TheFirstFlightBean theFirstFlight) {
                this.theFirstFlight = theFirstFlight;
            }

            public TheSecondaryFlightBean getTheSecondaryFlight() {
                return theSecondaryFlight;
            }

            public void setTheSecondaryFlight(TheSecondaryFlightBean theSecondaryFlight) {
                this.theSecondaryFlight = theSecondaryFlight;
            }

            public static class TheFirstFlightBean {
                /**
                 * detailData : [{"aircraftType":"B787-9","arrivalDate":"18:00 &nbsp;&nbsp;","flightNumber":"KL896","flightShift":"1,2,3,4,5,6,7","startDate":"12:15","transportation":"飞机"},{"aircraftType":"B787-9","arrivalDate":"04:30 &nbsp;&nbsp; +1","flightNumber":"KL894","flightShift":"3,5,7","startDate":"23:59","transportation":"飞机"}]
                 * route : PVG-AMS
                 */

                private String route;
                private List<DetailDataBean> detailData;

                public String getRoute() {
                    return route;
                }

                public void setRoute(String route) {
                    this.route = route;
                }

                public List<DetailDataBean> getDetailData() {
                    return detailData;
                }

                public void setDetailData(List<DetailDataBean> detailData) {
                    this.detailData = detailData;
                }

                public static class DetailDataBean {
                    /**
                     * aircraftType : B787-9
                     * arrivalDate : 18:00 &nbsp;&nbsp;
                     * flightNumber : KL896
                     * flightShift : 1,2,3,4,5,6,7
                     * startDate : 12:15
                     * transportation : 飞机
                     */

                    private String aircraftType;
                    private String arrivalDate;
                    private String flightNumber;
                    private String flightShift;
                    private String startDate;
                    private String transportation;

                    public String getAircraftType() {
                        return aircraftType;
                    }

                    public void setAircraftType(String aircraftType) {
                        this.aircraftType = aircraftType;
                    }

                    public String getArrivalDate() {
                        return arrivalDate;
                    }

                    public void setArrivalDate(String arrivalDate) {
                        this.arrivalDate = arrivalDate;
                    }

                    public String getFlightNumber() {
                        return flightNumber;
                    }

                    public void setFlightNumber(String flightNumber) {
                        this.flightNumber = flightNumber;
                    }

                    public String getFlightShift() {
                        return flightShift;
                    }

                    public void setFlightShift(String flightShift) {
                        this.flightShift = flightShift;
                    }

                    public String getStartDate() {
                        return startDate;
                    }

                    public void setStartDate(String startDate) {
                        this.startDate = startDate;
                    }

                    public String getTransportation() {
                        return transportation;
                    }

                    public void setTransportation(String transportation) {
                        this.transportation = transportation;
                    }
                }
            }

            public static class TheSecondaryFlightBean {
                /**
                 * detailData : [{"aircraftType":"","arrivalDate":"","flightNumber":"KL8625","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8621","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"},{"aircraftType":"","arrivalDate":"","flightNumber":"KL8623","flightShift":"1,2,3,4,5,6,7","startDate":"","transportation":"卡车"}]
                 * route : AMS-CDG
                 */

                private String route;
                private List<DetailDataBeanX> detailData;

                public String getRoute() {
                    return route;
                }

                public void setRoute(String route) {
                    this.route = route;
                }

                public List<DetailDataBeanX> getDetailData() {
                    return detailData;
                }

                public void setDetailData(List<DetailDataBeanX> detailData) {
                    this.detailData = detailData;
                }

                public static class DetailDataBeanX {
                    /**
                     * aircraftType :
                     * arrivalDate :
                     * flightNumber : KL8625
                     * flightShift : 1,2,3,4,5,6,7
                     * startDate :
                     * transportation : 卡车
                     */

                    private String aircraftType;
                    private String arrivalDate;
                    private String flightNumber;
                    private String flightShift;
                    private String startDate;
                    private String transportation;

                    public String getAircraftType() {
                        return aircraftType;
                    }

                    public void setAircraftType(String aircraftType) {
                        this.aircraftType = aircraftType;
                    }

                    public String getArrivalDate() {
                        return arrivalDate;
                    }

                    public void setArrivalDate(String arrivalDate) {
                        this.arrivalDate = arrivalDate;
                    }

                    public String getFlightNumber() {
                        return flightNumber;
                    }

                    public void setFlightNumber(String flightNumber) {
                        this.flightNumber = flightNumber;
                    }

                    public String getFlightShift() {
                        return flightShift;
                    }

                    public void setFlightShift(String flightShift) {
                        this.flightShift = flightShift;
                    }

                    public String getStartDate() {
                        return startDate;
                    }

                    public void setStartDate(String startDate) {
                        this.startDate = startDate;
                    }

                    public String getTransportation() {
                        return transportation;
                    }

                    public void setTransportation(String transportation) {
                        this.transportation = transportation;
                    }
                }
            }
        }

        public static class GeneralCargoPolicyBean {
            /**
             * chemicalsPackageClaim : 液体 托盘 散装
             * directTransfer : 中转
             * lithiumBatteryModel : PI966 PI967 PI970 纽扣电池 蓄电池
             * minWeight : 100.00KG
             * packageLimit :
             * sizeLimit : 板型尺寸：
             * tips :
             * weightLimit : 0.00KG
             */

            private String chemicalsPackageClaim;
            private String directTransfer;
            private String lithiumBatteryModel;
            private String minWeight;
            private String packageLimit;
            private String sizeLimit;
            private String tips;
            private String weightLimit;

            public String getChemicalsPackageClaim() {
                return chemicalsPackageClaim;
            }

            public void setChemicalsPackageClaim(String chemicalsPackageClaim) {
                this.chemicalsPackageClaim = chemicalsPackageClaim;
            }

            public String getDirectTransfer() {
                return directTransfer;
            }

            public void setDirectTransfer(String directTransfer) {
                this.directTransfer = directTransfer;
            }

            public String getLithiumBatteryModel() {
                return lithiumBatteryModel;
            }

            public void setLithiumBatteryModel(String lithiumBatteryModel) {
                this.lithiumBatteryModel = lithiumBatteryModel;
            }

            public String getMinWeight() {
                return minWeight;
            }

            public void setMinWeight(String minWeight) {
                this.minWeight = minWeight;
            }

            public String getPackageLimit() {
                return packageLimit;
            }

            public void setPackageLimit(String packageLimit) {
                this.packageLimit = packageLimit;
            }

            public String getSizeLimit() {
                return sizeLimit;
            }

            public void setSizeLimit(String sizeLimit) {
                this.sizeLimit = sizeLimit;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public String getWeightLimit() {
                return weightLimit;
            }

            public void setWeightLimit(String weightLimit) {
                this.weightLimit = weightLimit;
            }
        }

        public static class DeliverySituationBean {
            /**
             * deliveryCity :
             * deliveryTime : 19:30
             * flightNumber : KL894
             * intoCabinTime : 02:00:00
             */

            private String deliveryCity;
            private String deliveryTime;
            private String flightNumber;
            private String intoCabinTime;

            public String getDeliveryCity() {
                return deliveryCity;
            }

            public void setDeliveryCity(String deliveryCity) {
                this.deliveryCity = deliveryCity;
            }

            public String getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(String deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public String getFlightNumber() {
                return flightNumber;
            }

            public void setFlightNumber(String flightNumber) {
                this.flightNumber = flightNumber;
            }

            public String getIntoCabinTime() {
                return intoCabinTime;
            }

            public void setIntoCabinTime(String intoCabinTime) {
                this.intoCabinTime = intoCabinTime;
            }
        }
    }

    public static class ProductDateBean {
        /**
         * activity : {"code":"","sign":"","type":"0"}
         * date : 2019-01-20
         * errData :
         * price : 11.50
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

    public static class ProductDetailBean {
        /**
         * airline : KL
         * destination : CDG
         * directPoint : AMS
         * heavySign : 0
         * mergeNo : ["4801"]
         * productName : 安心保-KL上海重板
         * productNo : 4801
         * readinessCount : 0
         * readinessOffMoney : 0
         * shopSwitch : 0
         * startPort : PVG
         * supplierId :
         * supplierName : 久茂国际
         * trayType : 1
         */

        private String airline;
        private String destination;
        private String directPoint;
        private String heavySign;
        private String productName;
        private String productNo;
        private String readinessCount;
        private String readinessOffMoney;
        private String shopSwitch;
        private String startPort;
        private String supplierId;
        private String supplierName;
        private String trayType;
        private List<String> mergeNo;

        public String getAirline() {
            return airline;
        }

        public void setAirline(String airline) {
            this.airline = airline;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDirectPoint() {
            return directPoint;
        }

        public void setDirectPoint(String directPoint) {
            this.directPoint = directPoint;
        }

        public String getHeavySign() {
            return heavySign;
        }

        public void setHeavySign(String heavySign) {
            this.heavySign = heavySign;
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

        public String getReadinessCount() {
            return readinessCount;
        }

        public void setReadinessCount(String readinessCount) {
            this.readinessCount = readinessCount;
        }

        public String getReadinessOffMoney() {
            return readinessOffMoney;
        }

        public void setReadinessOffMoney(String readinessOffMoney) {
            this.readinessOffMoney = readinessOffMoney;
        }

        public String getShopSwitch() {
            return shopSwitch;
        }

        public void setShopSwitch(String shopSwitch) {
            this.shopSwitch = shopSwitch;
        }

        public String getStartPort() {
            return startPort;
        }

        public void setStartPort(String startPort) {
            this.startPort = startPort;
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

        public String getTrayType() {
            return trayType;
        }

        public void setTrayType(String trayType) {
            this.trayType = trayType;
        }

        public List<String> getMergeNo() {
            return mergeNo;
        }

        public void setMergeNo(List<String> mergeNo) {
            this.mergeNo = mergeNo;
        }
    }

    public static class ProportionListBean {
        /**
         * active : 0
         * proportion : [{"data":"0","offType":"0"}]
         * realActive :
         */

        private String active;
        private String realActive;
        private List<ProportionBean> proportion;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getRealActive() {
            return realActive;
        }

        public void setRealActive(String realActive) {
            this.realActive = realActive;
        }

        public List<ProportionBean> getProportion() {
            return proportion;
        }

        public void setProportion(List<ProportionBean> proportion) {
            this.proportion = proportion;
        }

        public static class ProportionBean {
            /**
             * data : 0
             * offType : 0
             */

            private String data;
            private String offType;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getOffType() {
                return offType;
            }

            public void setOffType(String offType) {
                this.offType = offType;
            }
        }
    }

    public static class TotalPriceBean {
        /**
         * priceCheckId :
         * quotedPriceId : 1168993
         * throwingGoodsLimit : 0
         * total : 0.00
         */

        private String priceCheckId;
        private String quotedPriceId;
        private String throwingGoodsLimit;
        private String total;

        public String getPriceCheckId() {
            return priceCheckId;
        }

        public void setPriceCheckId(String priceCheckId) {
            this.priceCheckId = priceCheckId;
        }

        public String getQuotedPriceId() {
            return quotedPriceId;
        }

        public void setQuotedPriceId(String quotedPriceId) {
            this.quotedPriceId = quotedPriceId;
        }

        public String getThrowingGoodsLimit() {
            return throwingGoodsLimit;
        }

        public void setThrowingGoodsLimit(String throwingGoodsLimit) {
            this.throwingGoodsLimit = throwingGoodsLimit;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }

    public static class TransferPriceBean {
        /**
         * chargeableWeight :
         * differencePrice :
         * minPrice :
         * reallyPrice :
         * transferType : 0
         * unitPrice :
         */

        private String chargeableWeight;
        private String differencePrice;
        private String minPrice;
        private String reallyPrice;
        private String transferType;
        private String unitPrice;

        public String getChargeableWeight() {
            return chargeableWeight;
        }

        public void setChargeableWeight(String chargeableWeight) {
            this.chargeableWeight = chargeableWeight;
        }

        public String getDifferencePrice() {
            return differencePrice;
        }

        public void setDifferencePrice(String differencePrice) {
            this.differencePrice = differencePrice;
        }

        public String getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(String minPrice) {
            this.minPrice = minPrice;
        }

        public String getReallyPrice() {
            return reallyPrice;
        }

        public void setReallyPrice(String reallyPrice) {
            this.reallyPrice = reallyPrice;
        }

        public String getTransferType() {
            return transferType;
        }

        public void setTransferType(String transferType) {
            this.transferType = transferType;
        }

        public String getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
        }
    }

    public static class WeightPriceBean {
        /**
         * active : 100
         * weight : {"w100":"11.50","w1000":"14.50","w300":"12.50","w3000":"0.00","w500":"13.50","w5000":"0.00"}
         */

        private String active;
        private WeightBean weight;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public WeightBean getWeight() {
            return weight;
        }

        public void setWeight(WeightBean weight) {
            this.weight = weight;
        }

        public static class WeightBean {
            /**
             * w100 : 11.50
             * w1000 : 14.50
             * w300 : 12.50
             * w3000 : 0.00
             * w500 : 13.50
             * w5000 : 0.00
             */

            private String w100;
            private String w1000;
            private String w300;
            private String w3000;
            private String w500;
            private String w5000;

            public String getW100() {
                return w100;
            }

            public void setW100(String w100) {
                this.w100 = w100;
            }

            public String getW1000() {
                return w1000;
            }

            public void setW1000(String w1000) {
                this.w1000 = w1000;
            }

            public String getW300() {
                return w300;
            }

            public void setW300(String w300) {
                this.w300 = w300;
            }

            public String getW3000() {
                return w3000;
            }

            public void setW3000(String w3000) {
                this.w3000 = w3000;
            }

            public String getW500() {
                return w500;
            }

            public void setW500(String w500) {
                this.w500 = w500;
            }

            public String getW5000() {
                return w5000;
            }

            public void setW5000(String w5000) {
                this.w5000 = w5000;
            }
        }
    }
}
