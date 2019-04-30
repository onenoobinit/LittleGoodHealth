package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/28.
 */
public class ShopCartPriceInfo {
    /**
     * actualProductAmountTotal : 1020
     * amountPayable : 1020
     * logisticsFee : 0
     * logisticsMode : 全场包邮
     * originProductAmountTotal : 1020
     * reducedPrice : 0
     * shoppingCartItemList : [{"commodity":{"commodityId":50,"commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"commodityType":{"code":1010,"name":"COMMON","text":"普通货物"},"coverImage":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ae79ec05-31f9-4ff9-9b67-6e7fb6790f31.jpg?height=406&width=650","createDate":"2019-03-06","displayOrder":28,"extend1":"一盒运费5元，两盒包邮","name":"雨衣","originalPrice":100,"presentPrice":100,"priceName":"优选价","sales":0,"salesDisplay":0,"specifications":{"commodityId":50,"commodityItem":[{"attached":{"大小":"s","颜色":"红"},"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/67d5e93a-641b-47c2-9ccc-fcfc3ecf65d8.jpg?height=406&width=650","number":1,"originalPrice":100,"presentPrice":100,"stock":120}],"defaultItemNumber":1,"hasDefaultOption":true,"id":"5c7f84c0746d5b4de58aaab3","specifications":[{"attrname":"颜色","attrvalue":[{"infoName":"红"},{"infoName":"黑"},{"infoName":"白"}]},{"attrname":"大小","attrvalue":[{"infoName":"s"},{"infoName":"m"},{"infoName":"l"}]}]}},"commodityCount":1,"commodityId":50,"commoditySpecificationNo":1,"itemId":388},{"commodity":{"commodityId":40,"commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"commodityType":{"code":1010,"name":"COMMON","text":"普通货物"},"coverImage":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/a7e52ee6-5385-43b1-ae53-63f0a9cc8f2b.jpg?height=1440&width=2560","createDate":"2019-01-07","displayOrder":21,"extend1":"一盒运费5元，两盒包邮","name":"我不会上架的","originalPrice":230,"presentPrice":230,"priceName":"优选价","sales":0,"salesDisplay":0,"specifications":{"commodityId":40,"commodityItem":[{"attached":{"大小":"100","颜色":"黑"},"number":1,"originalPrice":230,"presentPrice":230,"stock":120}],"defaultItemNumber":1,"hasDefaultOption":false,"id":"5c32c88cf623370a6dc7fde6","specifications":[{"attrname":"大小","attrvalue":[{"infoName":"100"},{"infoName":"200"}]},{"attrname":"颜色","attrvalue":[{"infoName":"黑"},{"infoName":"白"}]}]}},"commodityCount":4,"commodityId":40,"commoditySpecificationNo":1,"itemId":389}]
     */

    private int actualProductAmountTotal;
    private int amountPayable;
    private int logisticsFee;
    private String logisticsMode;
    private int originProductAmountTotal;
    private int reducedPrice;
    private List<ShoppingCartItemListBean> shoppingCartItemList;

    public int getActualProductAmountTotal() {
        return actualProductAmountTotal;
    }

    public void setActualProductAmountTotal(int actualProductAmountTotal) {
        this.actualProductAmountTotal = actualProductAmountTotal;
    }

    public int getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(int amountPayable) {
        this.amountPayable = amountPayable;
    }

    public int getLogisticsFee() {
        return logisticsFee;
    }

    public void setLogisticsFee(int logisticsFee) {
        this.logisticsFee = logisticsFee;
    }

    public String getLogisticsMode() {
        return logisticsMode;
    }

    public void setLogisticsMode(String logisticsMode) {
        this.logisticsMode = logisticsMode;
    }

    public int getOriginProductAmountTotal() {
        return originProductAmountTotal;
    }

    public void setOriginProductAmountTotal(int originProductAmountTotal) {
        this.originProductAmountTotal = originProductAmountTotal;
    }

    public int getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(int reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public List<ShoppingCartItemListBean> getShoppingCartItemList() {
        return shoppingCartItemList;
    }

    public void setShoppingCartItemList(List<ShoppingCartItemListBean> shoppingCartItemList) {
        this.shoppingCartItemList = shoppingCartItemList;
    }

    public static class ShoppingCartItemListBean {
        /**
         * commodity : {"commodityId":50,"commodityStatus":{"code":1010,"name":"PUTAWAY","text":"上架"},"commodityType":{"code":1010,"name":"COMMON","text":"普通货物"},"coverImage":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ae79ec05-31f9-4ff9-9b67-6e7fb6790f31.jpg?height=406&width=650","createDate":"2019-03-06","displayOrder":28,"extend1":"一盒运费5元，两盒包邮","name":"雨衣","originalPrice":100,"presentPrice":100,"priceName":"优选价","sales":0,"salesDisplay":0,"specifications":{"commodityId":50,"commodityItem":[{"attached":{"大小":"s","颜色":"红"},"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/67d5e93a-641b-47c2-9ccc-fcfc3ecf65d8.jpg?height=406&width=650","number":1,"originalPrice":100,"presentPrice":100,"stock":120}],"defaultItemNumber":1,"hasDefaultOption":true,"id":"5c7f84c0746d5b4de58aaab3","specifications":[{"attrname":"颜色","attrvalue":[{"infoName":"红"},{"infoName":"黑"},{"infoName":"白"}]},{"attrname":"大小","attrvalue":[{"infoName":"s"},{"infoName":"m"},{"infoName":"l"}]}]}}
         * commodityCount : 1
         * commodityId : 50
         * commoditySpecificationNo : 1
         * itemId : 388
         */

        private CommodityBean commodity;
        private int commodityCount;
        private int commodityId;
        private int commoditySpecificationNo;
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

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public static class CommodityBean {
            /**
             * commodityId : 50
             * commodityStatus : {"code":1010,"name":"PUTAWAY","text":"上架"}
             * commodityType : {"code":1010,"name":"COMMON","text":"普通货物"}
             * coverImage : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ae79ec05-31f9-4ff9-9b67-6e7fb6790f31.jpg?height=406&width=650
             * createDate : 2019-03-06
             * displayOrder : 28
             * extend1 : 一盒运费5元，两盒包邮
             * name : 雨衣
             * originalPrice : 100
             * presentPrice : 100
             * priceName : 优选价
             * sales : 0
             * salesDisplay : 0
             * specifications : {"commodityId":50,"commodityItem":[{"attached":{"大小":"s","颜色":"红"},"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/67d5e93a-641b-47c2-9ccc-fcfc3ecf65d8.jpg?height=406&width=650","number":1,"originalPrice":100,"presentPrice":100,"stock":120}],"defaultItemNumber":1,"hasDefaultOption":true,"id":"5c7f84c0746d5b4de58aaab3","specifications":[{"attrname":"颜色","attrvalue":[{"infoName":"红"},{"infoName":"黑"},{"infoName":"白"}]},{"attrname":"大小","attrvalue":[{"infoName":"s"},{"infoName":"m"},{"infoName":"l"}]}]}
             */

            private int commodityId;
            private CommodityStatusBean commodityStatus;
            private CommodityTypeBean commodityType;
            private String coverImage;
            private String createDate;
            private int displayOrder;
            private String extend1;
            private String name;
            private int originalPrice;
            private int presentPrice;
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

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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

            public int getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(int originalPrice) {
                this.originalPrice = originalPrice;
            }

            public int getPresentPrice() {
                return presentPrice;
            }

            public void setPresentPrice(int presentPrice) {
                this.presentPrice = presentPrice;
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
                 * commodityId : 50
                 * commodityItem : [{"attached":{"大小":"s","颜色":"红"},"img":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/67d5e93a-641b-47c2-9ccc-fcfc3ecf65d8.jpg?height=406&width=650","number":1,"originalPrice":100,"presentPrice":100,"stock":120}]
                 * defaultItemNumber : 1
                 * hasDefaultOption : true
                 * id : 5c7f84c0746d5b4de58aaab3
                 * specifications : [{"attrname":"颜色","attrvalue":[{"infoName":"红"},{"infoName":"黑"},{"infoName":"白"}]},{"attrname":"大小","attrvalue":[{"infoName":"s"},{"infoName":"m"},{"infoName":"l"}]}]
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
                     * attached : {"大小":"s","颜色":"红"}
                     * img : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/67d5e93a-641b-47c2-9ccc-fcfc3ecf65d8.jpg?height=406&width=650
                     * number : 1
                     * originalPrice : 100
                     * presentPrice : 100
                     * stock : 120
                     */

                    private AttachedBean attached;
                    private String img;
                    private int number;
                    private int originalPrice;
                    private int presentPrice;
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

                    public int getOriginalPrice() {
                        return originalPrice;
                    }

                    public void setOriginalPrice(int originalPrice) {
                        this.originalPrice = originalPrice;
                    }

                    public int getPresentPrice() {
                        return presentPrice;
                    }

                    public void setPresentPrice(int presentPrice) {
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
                         * 大小 : s
                         * 颜色 : 红
                         */

                        private String 大小;
                        private String 颜色;

                        public String get大小() {
                            return 大小;
                        }

                        public void set大小(String 大小) {
                            this.大小 = 大小;
                        }

                        public String get颜色() {
                            return 颜色;
                        }

                        public void set颜色(String 颜色) {
                            this.颜色 = 颜色;
                        }
                    }
                }

                public static class SpecificationsBean {
                    /**
                     * attrname : 颜色
                     * attrvalue : [{"infoName":"红"},{"infoName":"黑"},{"infoName":"白"}]
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
                         * infoName : 红
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
}
