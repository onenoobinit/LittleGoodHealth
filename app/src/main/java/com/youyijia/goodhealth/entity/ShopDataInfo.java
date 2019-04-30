package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/12.
 */
public class ShopDataInfo {
    /**
     * commodityId : 12
     * name : 益生菌（300亿）五盒装
     * priceName : 优选价
     * commodityType : {"code":1010,"name":"COMMON","text":"普通货物"}
     * commodityStatus : {"code":1010,"name":"PUTAWAY","text":"上架"}
     * note :
     * originalPrice : 900
     * costPrice : null
     * presentPrice : 900
     * coverImage : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/bc345cef-da54-44fd-8f24-f4ccace12f92.png
     * commodityAttachmentsAtCenterPosition : null
     * commodityAttachmentsAtCommodityParticularsPosition : null
     * extend1 : 全程包邮
     * specifications : {"id":"5bf5267f48ca066b6e74891c","commodityId":12,"hasDefaultOption":true,"defaultItemNumber":1,"specifications":[{"attrname":"规格","attrvalue":[{"infoName":"默认"}]}],"commodityItem":[{"attached":{"规格":"默认"},"number":1,"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/bc345cef-da54-44fd-8f24-f4ccace12f92.png","stock":99,"originalPrice":900,"costPrice":null,"presentPrice":900}]}
     * logisticsFee : null
     * displayOrder : 4
     * sales : 3
     * salesDisplay : 3
     * soldOutDate : null
     * createDate : null
     */

    private String commodityId;
    private String name;
    private String priceName;
    private CommodityTypeBean commodityType;
    private CommodityStatusBean commodityStatus;
    private String note;
    private int originalPrice;
    private Object costPrice;
    private int presentPrice;
    private String coverImage;
    private Object commodityAttachmentsAtCenterPosition;
    private Object commodityAttachmentsAtCommodityParticularsPosition;
    private String extend1;
    private SpecificationsBeanX specifications;
    private Object logisticsFee;
    private int displayOrder;
    private int sales;
    private int salesDisplay;
    private Object soldOutDate;
    private Object createDate;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public CommodityTypeBean getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(CommodityTypeBean commodityType) {
        this.commodityType = commodityType;
    }

    public CommodityStatusBean getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(CommodityStatusBean commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Object getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Object costPrice) {
        this.costPrice = costPrice;
    }

    public int getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(int presentPrice) {
        this.presentPrice = presentPrice;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Object getCommodityAttachmentsAtCenterPosition() {
        return commodityAttachmentsAtCenterPosition;
    }

    public void setCommodityAttachmentsAtCenterPosition(Object commodityAttachmentsAtCenterPosition) {
        this.commodityAttachmentsAtCenterPosition = commodityAttachmentsAtCenterPosition;
    }

    public Object getCommodityAttachmentsAtCommodityParticularsPosition() {
        return commodityAttachmentsAtCommodityParticularsPosition;
    }

    public void setCommodityAttachmentsAtCommodityParticularsPosition(Object commodityAttachmentsAtCommodityParticularsPosition) {
        this.commodityAttachmentsAtCommodityParticularsPosition = commodityAttachmentsAtCommodityParticularsPosition;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public SpecificationsBeanX getSpecifications() {
        return specifications;
    }

    public void setSpecifications(SpecificationsBeanX specifications) {
        this.specifications = specifications;
    }

    public Object getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(Object logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getSalesDisplay() {
        return salesDisplay;
    }

    public void setSalesDisplay(int salesDisplay) {
        this.salesDisplay = salesDisplay;
    }

    public Object getSoldOutDate() {
        return soldOutDate;
    }

    public void setSoldOutDate(Object soldOutDate) {
        this.soldOutDate = soldOutDate;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public static class CommodityTypeBean {
        /**
         * code : 1010
         * name : COMMON
         * text : 普通货物
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

    public static class SpecificationsBeanX {
        /**
         * id : 5bf5267f48ca066b6e74891c
         * commodityId : 12
         * hasDefaultOption : true
         * defaultItemNumber : 1
         * specifications : [{"attrname":"规格","attrvalue":[{"infoName":"默认"}]}]
         * commodityItem : [{"attached":{"规格":"默认"},"number":1,"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/bc345cef-da54-44fd-8f24-f4ccace12f92.png","stock":99,"originalPrice":900,"costPrice":null,"presentPrice":900}]
         */

        private String id;
        private int commodityId;
        private boolean hasDefaultOption;
        private int defaultItemNumber;
        private List<SpecificationsBean> specifications;
        private List<CommodityItemBean> commodityItem;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public boolean isHasDefaultOption() {
            return hasDefaultOption;
        }

        public void setHasDefaultOption(boolean hasDefaultOption) {
            this.hasDefaultOption = hasDefaultOption;
        }

        public int getDefaultItemNumber() {
            return defaultItemNumber;
        }

        public void setDefaultItemNumber(int defaultItemNumber) {
            this.defaultItemNumber = defaultItemNumber;
        }

        public List<SpecificationsBean> getSpecifications() {
            return specifications;
        }

        public void setSpecifications(List<SpecificationsBean> specifications) {
            this.specifications = specifications;
        }

        public List<CommodityItemBean> getCommodityItem() {
            return commodityItem;
        }

        public void setCommodityItem(List<CommodityItemBean> commodityItem) {
            this.commodityItem = commodityItem;
        }

        public static class SpecificationsBean {
            /**
             * attrname : 规格
             * attrvalue : [{"infoName":"默认"}]
             */

            private String attrname;
            private List<AttrvalueBean> attrvalue;

            public String getAttrname() {
                return attrname;
            }

            public void setAttrname(String attrname) {
                this.attrname = attrname;
            }

            public List<AttrvalueBean> getAttrvalue() {
                return attrvalue;
            }

            public void setAttrvalue(List<AttrvalueBean> attrvalue) {
                this.attrvalue = attrvalue;
            }

            public static class AttrvalueBean {
                /**
                 * infoName : 默认
                 */

                private String infoName;

                public String getInfoName() {
                    return infoName;
                }

                public void setInfoName(String infoName) {
                    this.infoName = infoName;
                }
            }
        }

        public static class CommodityItemBean {
            /**
             * attached : {"规格":"默认"}
             * number : 1
             * img : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/bc345cef-da54-44fd-8f24-f4ccace12f92.png
             * stock : 99
             * originalPrice : 900
             * costPrice : null
             * presentPrice : 900
             */

            private AttachedBean attached;
            private int number;
            private String img;
            private int stock;
            private int originalPrice;
            private Object costPrice;
            private int presentPrice;

            public AttachedBean getAttached() {
                return attached;
            }

            public void setAttached(AttachedBean attached) {
                this.attached = attached;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(int originalPrice) {
                this.originalPrice = originalPrice;
            }

            public Object getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(Object costPrice) {
                this.costPrice = costPrice;
            }

            public int getPresentPrice() {
                return presentPrice;
            }

            public void setPresentPrice(int presentPrice) {
                this.presentPrice = presentPrice;
            }

            public static class AttachedBean {
                /**
                 * 规格 : 默认
                 */

                private String 规格;

                public String get规格() {
                    return 规格;
                }

                public void set规格(String 规格) {
                    this.规格 = 规格;
                }
            }
        }
    }
}
