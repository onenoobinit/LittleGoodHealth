package com.mobile.android.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangqiang on 2019/1/14.
 */
public class PortInfo implements Serializable {
    private List<AirlineListBean> airlineList;
    private List<AirlineListAllBean> airlineListAll;
    private List<DestinationListBean> destinationList;
    private List<DestinationListAllBean> destinationListAll;

    public List<AirlineListBean> getAirlineList() {
        return airlineList;
    }

    public void setAirlineList(List<AirlineListBean> airlineList) {
        this.airlineList = airlineList;
    }

    public List<AirlineListAllBean> getAirlineListAll() {
        return airlineListAll;
    }

    public void setAirlineListAll(List<AirlineListAllBean> airlineListAll) {
        this.airlineListAll = airlineListAll;
    }

    public List<DestinationListBean> getDestinationList() {
        return destinationList;
    }

    public void setDestinationList(List<DestinationListBean> destinationList) {
        this.destinationList = destinationList;
    }

    public List<DestinationListAllBean> getDestinationListAll() {
        return destinationListAll;
    }

    public void setDestinationListAll(List<DestinationListAllBean> destinationListAll) {
        this.destinationListAll = destinationListAll;
    }

    public static class AirlineListBean implements Serializable {
        /**
         * dataList : [{"airlineAbbr":"KL","airlineNameC":"荷兰皇家航空公司","airlineNameE":"KLM ROYAL DUTCH AIRLINES"},{"airlineAbbr":"LH","airlineNameC":"LUFTHANSA CARGO AG","airlineNameE":"LUFTHANSA CARGO AG Langer"},{"airlineAbbr":"AF","airlineNameC":"AIR FRANCE","airlineNameE":"AIR FRANCE"},{"airlineAbbr":"OS","airlineNameC":"AUSTRIAN AIRLINES","airlineNameE":"AUSTRIAN AIRLINES"},{"airlineAbbr":"CZ","airlineNameC":"中国南方航空股份有限公司","airlineNameE":"CHINA SOUTHERN AIRLINES CO.,LTD"},{"airlineAbbr":"EY","airlineNameC":"ETIHAD AIRWAYS","airlineNameE":"ETIHAD AIRWAYS"},{"airlineAbbr":"DL","airlineNameC":"DELTA AIR LINES INC.","airlineNameE":"DELTA AIR LINES INC."},{"airlineAbbr":"LX","airlineNameC":"SWISS INTERNATIONAL AIR LINES LTD.","airlineNameE":"SWISS INTERNATIONAL AIR LINES LTD."},{"airlineAbbr":"RU","airlineNameC":"AIRBRIDGE CARGO","airlineNameE":"AIRBRIDGE CARGO"},{"airlineAbbr":"CV","airlineNameC":"CARGOLUX AIRLINES INTERNATIONAL S.A.","airlineNameE":"CARGOLUX AIRLINES INTERNATIONAL S.A."},{"airlineAbbr":"SQ","airlineNameC":"SINGAPORE AIRLINES LTD.","airlineNameE":"SINGAPORE AIRLINES LTD."},{"airlineAbbr":"ET","airlineNameC":"ETHIOPIAN AIRLINES ENTERPRISE","airlineNameE":"ETHIOPIAN MENBER OF INTERNATINAL AIR TRANSPORT ASSOCIATION"},{"airlineAbbr":"CK","airlineNameC":"CHINA CARGO AIRLINES LTD.","airlineNameE":"CHINA CARGO AIRLINES LTD."},{"airlineAbbr":"SU","airlineNameC":"AEROFLOT RUSSIAN AIRLINES","airlineNameE":"AEROFLOT RUSSIAN AIRLINES"},{"airlineAbbr":"SL","airlineNameC":"RIO SUL LINHAS AEREAS S.A.","airlineNameE":"RIO SUL LINHAS AEREAS S.A."},{"airlineAbbr":"5J","airlineNameC":"CEBU PACIFIC AIR","airlineNameE":"CEBU PACIFIC AIR"},{"airlineAbbr":"Y8","airlineNameC":"YANGTZE RIVER EXPRESS AIRLINES COMP.","airlineNameE":"YANGTZE RIVER EXPRESS AIRLINES COMP."},{"airlineAbbr":"LO","airlineNameC":"LOT - POLISH AIRLINES","airlineNameE":"LOT - POLISH AIRLINES"},{"airlineAbbr":"W5","airlineNameC":"MAHAN AIRLINES","airlineNameE":"MAHAN AIRLINES"},{"airlineAbbr":"UPS","airlineNameC":"联合包裹物流（上海）有限公司","airlineNameE":"联合包裹物流（上海）有限公司"}]
         * group : 最近
         */

        private String group;
        private List<DataListBean> dataList;

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * airlineAbbr : KL
             * airlineNameC : 荷兰皇家航空公司
             * airlineNameE : KLM ROYAL DUTCH AIRLINES
             */

            private String airlineAbbr;
            private String airlineNameC;
            private String airlineNameE;

            public String getAirlineAbbr() {
                return airlineAbbr;
            }

            public void setAirlineAbbr(String airlineAbbr) {
                this.airlineAbbr = airlineAbbr;
            }

            public String getAirlineNameC() {
                return airlineNameC;
            }

            public void setAirlineNameC(String airlineNameC) {
                this.airlineNameC = airlineNameC;
            }

            public String getAirlineNameE() {
                return airlineNameE;
            }

            public void setAirlineNameE(String airlineNameE) {
                this.airlineNameE = airlineNameE;
            }
        }
    }

    public static class AirlineListAllBean implements Serializable {
        /**
         * airlineAbbr : 3U
         * airlineNameC : SICHUAN AIRLINES CO. LTD.
         * airlineNameE : SICHUAN AIRLINES CO. LTD.
         * areaNameC : 欧洲线
         * areaNameE : europe
         */

        private String airlineAbbr;
        private String airlineNameC;
        private String airlineNameE;
        private String areaNameC;
        private String areaNameE;

        public String getAirlineAbbr() {
            return airlineAbbr;
        }

        public void setAirlineAbbr(String airlineAbbr) {
            this.airlineAbbr = airlineAbbr;
        }

        public String getAirlineNameC() {
            return airlineNameC;
        }

        public void setAirlineNameC(String airlineNameC) {
            this.airlineNameC = airlineNameC;
        }

        public String getAirlineNameE() {
            return airlineNameE;
        }

        public void setAirlineNameE(String airlineNameE) {
            this.airlineNameE = airlineNameE;
        }

        public String getAreaNameC() {
            return areaNameC;
        }

        public void setAreaNameC(String areaNameC) {
            this.areaNameC = areaNameC;
        }

        public String getAreaNameE() {
            return areaNameE;
        }

        public void setAreaNameE(String areaNameE) {
            this.areaNameE = areaNameE;
        }
    }

    public static class DestinationListBean implements Serializable {
        /**
         * dataList : [{"cityNameC":"阿姆斯特丹","cityNameE":"AMSTERDAM"},{"cityNameC":"法兰克福","cityNameE":"FRANKFURT"},{"cityNameC":"巴塞罗那，西班牙 (Spain)，欧洲","cityNameE":"BARCELONA"},{"cityNameC":"马德里 ,西班牙 (Spain)首都 , 欧洲","cityNameE":"MADRID"},{"cityNameC":"曼彻斯特，英国 (United Kingdom), 欧洲","cityNameE":"MANCHESTER"},{"cityNameC":"波尔图，葡萄牙（ Portugal），欧洲","cityNameE":"PORTO"},{"cityNameC":"布拉格，捷克 (Czech Republic)首都 , 欧洲","cityNameE":"PRAGUE"},{"cityNameC":"孟买，印度 (India),亚洲","cityNameE":"MUMBAI"},{"cityNameC":"汉堡","cityNameE":"HAMBURG"},{"cityNameC":"布达佩斯，匈牙利 (Hungary) 首都，欧洲","cityNameE":"BUDAPEST"},{"cityNameC":"维也纳","cityNameE":"VIENNA"},{"cityNameC":"布鲁塞尔","cityNameE":"BRUSSELS"},{"cityNameC":"杜塞尔多夫","cityNameE":"DUSSELDORF"},{"cityNameC":"里斯本，葡萄牙 (Portugal)首都、欧洲","cityNameE":"LISBON"},{"cityNameC":"斯图加特","cityNameE":"STUTTGART"},{"cityNameC":"苏黎世，瑞士 (Switzerland),欧洲","cityNameE":"ZURICH"},{"cityNameC":"BUCHAREST","cityNameE":"BUCHAREST"},{"cityNameC":"哥德堡，瑞典 (Sweden)，欧洲","cityNameE":"GOTHENBURG"},{"cityNameC":"鹿特丹","cityNameE":"ROTTERDAM"},{"cityNameC":"里昂","cityNameE":"LYON"},{"cityNameC":"慕尼黑","cityNameE":"MUNICH"},{"cityNameC":"奥斯陆，挪威 (Norway)首都，北欧","cityNameE":"OSLO"},{"cityNameC":"都柏林","cityNameE":"DUBLIN"},{"cityNameC":"赫尔辛基","cityNameE":"HELSINKI"},{"cityNameC":"巴伦西亚，西班牙（ Spain），欧洲","cityNameE":"VALENCIA"}]
         * group : 热门
         */

        private String group;
        private List<DataListBeanX> dataList;

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public List<DataListBeanX> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBeanX> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBeanX implements Serializable {
            /**
             * cityNameC : 阿姆斯特丹
             * cityNameE : AMSTERDAM
             */

            private String cityNameC;
            private String cityNameE;

            public String getCityNameC() {
                return cityNameC;
            }

            public void setCityNameC(String cityNameC) {
                this.cityNameC = cityNameC;
            }

            public String getCityNameE() {
                return cityNameE;
            }

            public void setCityNameE(String cityNameE) {
                this.cityNameE = cityNameE;
            }
        }
    }

    public static class DestinationListAllBean implements Serializable {
        /**
         * airport : AMS
         * cityNameC : 阿姆斯特丹
         * cityNameE : AMSTERDAM
         * countryNameC : 荷兰
         * countryNameE : NETHERLANDS
         * range : 0
         * valid : 1
         */

        private String airport;
        private String cityNameC;
        private String cityNameE;
        private String countryNameC;
        private String countryNameE;
        private String range;
        private String valid;

        public String getAirport() {
            return airport;
        }

        public void setAirport(String airport) {
            this.airport = airport;
        }

        public String getCityNameC() {
            return cityNameC;
        }

        public void setCityNameC(String cityNameC) {
            this.cityNameC = cityNameC;
        }

        public String getCityNameE() {
            return cityNameE;
        }

        public void setCityNameE(String cityNameE) {
            this.cityNameE = cityNameE;
        }

        public String getCountryNameC() {
            return countryNameC;
        }

        public void setCountryNameC(String countryNameC) {
            this.countryNameC = countryNameC;
        }

        public String getCountryNameE() {
            return countryNameE;
        }

        public void setCountryNameE(String countryNameE) {
            this.countryNameE = countryNameE;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }
    }
}
