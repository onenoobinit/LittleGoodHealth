package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/21.
 */
public class ShopCartDataInfo {
    /**
     * commodity : {"commodityId":10,"commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"commodityType":{"code":1010,"name":"COMMON","text":"普通货物"},"coverImage":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/e8d966e6-aa90-4f77-981d-730e124221c0.png","displayOrder":1,"extend1":"全程包邮","name":"益生菌（500亿）减脂型","priceName":"优选价","sales":10,"salesDisplay":10,"specifications":{"commodityId":10,"commodityItem":[{"attached":{"规格":"默认"},"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/e8d966e6-aa90-4f77-981d-730e124221c0.png","number":1,"originalPrice":240,"presentPrice":240,"stock":99}],"defaultItemNumber":1,"hasDefaultOption":true,"id":"5bf5262a48ca066b6e74891a","specifications":[{"attrname":"规格","attrvalue":[{"infoName":"默认"}]}]}}
     * commodityCount : 1
     * commodityId : 10
     * commoditySpecificationNo : 10
     * customerId : 2165
     * itemId : 1243
     */

    private CommodityBean commodity;
    private int commodityCount;
    private int commodityId;
    private int commoditySpecificationNo;
    private int customerId;
    private int itemId;

    public CommodityBean getCommodity() {
        return commodity;
    }

    public void setCommodity(CommodityBean commodity) {
        this.commodity = commodity;
    }

    public int getCommodityCount() {
        return commodityCount;
    }

    public void setCommodityCount(int commodityCount) {
        this.commodityCount = commodityCount;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommoditySpecificationNo() {
        return commoditySpecificationNo;
    }

    public void setCommoditySpecificationNo(int commoditySpecificationNo) {
        this.commoditySpecificationNo = commoditySpecificationNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public static class CommodityBean {
        /**
         * commodityId : 10
         * commodityStatus : {"code":1010,"name":"PUTAWAY","text":"上架"}
         * commodityType : {"code":1010,"name":"COMMON","text":"普通货物"}
         * coverImage : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/e8d966e6-aa90-4f77-981d-730e124221c0.png
         * displayOrder : 1
         * extend1 : 全程包邮
         * name : 益生菌（500亿）减脂型
         * priceName : 优选价
         * sales : 10
         * salesDisplay : 10
         * specifications : {"commodityId":10,"commodityItem":[{"attached":{"规格":"默认"},"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/e8d966e6-aa90-4f77-981d-730e124221c0.png","number":1,"originalPrice":240,"presentPrice":240,"stock":99}],"defaultItemNumber":1,"hasDefaultOption":true,"id":"5bf5262a48ca066b6e74891a","specifications":[{"attrname":"规格","attrvalue":[{"infoName":"默认"}]}]}
         */

        private int commodityId;
        private CommodityStatusBean commodityStatus;
        private CommodityTypeBean commodityType;
        private String coverImage;
        private int displayOrder;
        private String extend1;
        private String name;
        private String priceName;
        private int sales;
        private int salesDisplay;
        private SpecificationsBeanX specifications;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public CommodityStatusBean getCommodityStatus() {
            return commodityStatus;
        }

        public void setCommodityStatus(CommodityStatusBean commodityStatus) {
            this.commodityStatus = commodityStatus;
        }

        public CommodityTypeBean getCommodityType() {
            return commodityType;
        }

        public void setCommodityType(CommodityTypeBean commodityType) {
            this.commodityType = commodityType;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }

        public String getExtend1() {
            return extend1;
        }

        public void setExtend1(String extend1) {
            this.extend1 = extend1;
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

        public SpecificationsBeanX getSpecifications() {
            return specifications;
        }

        public void setSpecifications(SpecificationsBeanX specifications) {
            this.specifications = specifications;
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

        public static class SpecificationsBeanX {
            /**
             * commodityId : 10
             * commodityItem : [{"attached":{"规格":"默认"},"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/e8d966e6-aa90-4f77-981d-730e124221c0.png","number":1,"originalPrice":240,"presentPrice":240,"stock":99}]
             * defaultItemNumber : 1
             * hasDefaultOption : true
             * id : 5bf5262a48ca066b6e74891a
             * specifications : [{"attrname":"规格","attrvalue":[{"infoName":"默认"}]}]
             */

            private int commodityId;
            private int defaultItemNumber;
            private boolean hasDefaultOption;
            private String id;
            private List<CommodityItemBean> commodityItem;
            private List<SpecificationsBean> specifications;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public int getDefaultItemNumber() {
                return defaultItemNumber;
            }

            public void setDefaultItemNumber(int defaultItemNumber) {
                this.defaultItemNumber = defaultItemNumber;
            }

            public boolean isHasDefaultOption() {
                return hasDefaultOption;
            }

            public void setHasDefaultOption(boolean hasDefaultOption) {
                this.hasDefaultOption = hasDefaultOption;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<CommodityItemBean> getCommodityItem() {
                return commodityItem;
            }

            public void setCommodityItem(List<CommodityItemBean> commodityItem) {
                this.commodityItem = commodityItem;
            }

            public List<SpecificationsBean> getSpecifications() {
                return specifications;
            }

            public void setSpecifications(List<SpecificationsBean> specifications) {
                this.specifications = specifications;
            }

            public static class CommodityItemBean {
                /**
                 * attached : {"规格":"默认"}
                 * img : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/e8d966e6-aa90-4f77-981d-730e124221c0.png
                 * number : 1
                 * originalPrice : 240.0
                 * presentPrice : 240.0
                 * stock : 99
                 */

                private AttachedBean attached;
                private String img;
                private int number;
                private double originalPrice;
                private double presentPrice;
                private int stock;

                public AttachedBean getAttached() {
                    return attached;
                }

                public void setAttached(AttachedBean attached) {
                    this.attached = attached;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public int getNumber() {
                    return number;
                }

                public void setNumber(int number) {
                    this.number = number;
                }

                public double getOriginalPrice() {
                    return originalPrice;
                }

                public void setOriginalPrice(double originalPrice) {
                    this.originalPrice = originalPrice;
                }

                public double getPresentPrice() {
                    return presentPrice;
                }

                public void setPresentPrice(double presentPrice) {
                    this.presentPrice = presentPrice;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
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
        }
    }
}
