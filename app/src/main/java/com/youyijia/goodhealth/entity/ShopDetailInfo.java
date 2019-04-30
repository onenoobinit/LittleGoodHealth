package com.youyijia.goodhealth.entity;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/17.
 */
public class ShopDetailInfo {


    /**
     * commodityAttachmentsAtCenterPosition : [{"attachmentFullUrl":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/f30e7c98-d4c0-47ea-86a1-8caa68c1ad71.jpg?height=754&width=700","attachmentId":80,"commodityId":40,"displayOrder":0,"fileType":{"code":1010,"name":"IMAGE","text":"图片"},"positionType":{"code":1010,"name":"CENTER","text":"中心轮播位置"}}]
     * commodityAttachmentsAtCommodityParticularsPosition : [{"attachmentFullUrl":"https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ccb4e42a-2e9c-4b47-9ea0-8f95a7688ffa.jpg?height=2480&width=1748","attachmentId":81,"commodityId":40,"displayOrder":0,"fileType":{"code":1010,"name":"IMAGE","text":"图片"},"positionType":{"code":1020,"name":"COMMODITY_PARTICULARS","text":"商品详情位置"}}]
     * commodityId : 40
     * commodityStatus : {"code":1010,"name":"PUTAWAY","text":"上架"}
     * commodityType : {"code":1010,"name":"COMMON","text":"普通货物"}
     * coverImage : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/a7e52ee6-5385-43b1-ae53-63f0a9cc8f2b.jpg?height=1440&width=2560
     * createDate : 2019-01-07
     * displayOrder : 21
     * extend1 : 一盒运费5元，两盒包邮
     * name : 我不会上架的
     * originalPrice : 230
     * presentPrice : 221.0
     * priceName : 员工价
     * sales : 0
     * salesDisplay : 0
     * specifications : {"commodityId":40,"commodityItem":[{"attached":{"大小":"100","颜色":"黑"},"number":1,"originalPrice":230,"presentPrice":221,"stock":120},{"attached":{"大小":"100","颜色":"白"},"number":2,"originalPrice":230,"presentPrice":221,"stock":120},{"attached":{"大小":"200","颜色":"黑"},"number":3,"originalPrice":230,"presentPrice":221,"stock":120},{"attached":{"大小":"200","颜色":"白"},"number":4,"originalPrice":230,"presentPrice":221,"stock":120}],"defaultItemNumber":1,"hasDefaultOption":false,"id":"5c32c88cf623370a6dc7fde6","specifications":[{"attrname":"大小","attrvalue":[{"infoName":"100"},{"infoName":"200"}]},{"attrname":"颜色","attrvalue":[{"infoName":"黑"},{"infoName":"白"}]}]}
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
    private double presentPrice;
    private String priceName;
    private int sales;
    private int salesDisplay;
    private SpecificationsBeanX specifications;
    private List<CommodityAttachmentsAtCenterPositionBean> commodityAttachmentsAtCenterPosition;
    private List<CommodityAttachmentsAtCommodityParticularsPositionBean> commodityAttachmentsAtCommodityParticularsPosition;

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

    public double getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(double presentPrice) {
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

    public List<CommodityAttachmentsAtCenterPositionBean> getCommodityAttachmentsAtCenterPosition() {
        return commodityAttachmentsAtCenterPosition;
    }

    public void setCommodityAttachmentsAtCenterPosition(List<CommodityAttachmentsAtCenterPositionBean> commodityAttachmentsAtCenterPosition) {
        this.commodityAttachmentsAtCenterPosition = commodityAttachmentsAtCenterPosition;
    }

    public List<CommodityAttachmentsAtCommodityParticularsPositionBean> getCommodityAttachmentsAtCommodityParticularsPosition() {
        return commodityAttachmentsAtCommodityParticularsPosition;
    }

    public void setCommodityAttachmentsAtCommodityParticularsPosition(List<CommodityAttachmentsAtCommodityParticularsPositionBean> commodityAttachmentsAtCommodityParticularsPosition) {
        this.commodityAttachmentsAtCommodityParticularsPosition = commodityAttachmentsAtCommodityParticularsPosition;
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
         * commodityId : 40
         * commodityItem : [{"attached":{"大小":"100","颜色":"黑"},"number":1,"originalPrice":230,"presentPrice":221,"stock":120},{"attached":{"大小":"100","颜色":"白"},"number":2,"originalPrice":230,"presentPrice":221,"stock":120},{"attached":{"大小":"200","颜色":"黑"},"number":3,"originalPrice":230,"presentPrice":221,"stock":120},{"attached":{"大小":"200","颜色":"白"},"number":4,"originalPrice":230,"presentPrice":221,"stock":120}]
         * defaultItemNumber : 1
         * hasDefaultOption : false
         * id : 5c32c88cf623370a6dc7fde6
         * specifications : [{"attrname":"大小","attrvalue":[{"infoName":"100"},{"infoName":"200"}]},{"attrname":"颜色","attrvalue":[{"infoName":"黑"},{"infoName":"白"}]}]
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
             * attached : {"大小":"100","颜色":"黑"}
             * number : 1
             * originalPrice : 230
             * presentPrice : 221.0
             * stock : 120
             */

            private AttachedBean attached;
            private int number;
            private int originalPrice;
            private double presentPrice;
            private int stock;

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

            public int getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(int originalPrice) {
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
                 * 大小 : 100
                 * 颜色 : 黑
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
             * attrname : 大小
             * attrvalue : [{"infoName":"100"},{"infoName":"200"}]
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
                 * infoName : 100
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

    public static class CommodityAttachmentsAtCenterPositionBean {
        /**
         * attachmentFullUrl : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/f30e7c98-d4c0-47ea-86a1-8caa68c1ad71.jpg?height=754&width=700
         * attachmentId : 80
         * commodityId : 40
         * displayOrder : 0
         * fileType : {"code":1010,"name":"IMAGE","text":"图片"}
         * positionType : {"code":1010,"name":"CENTER","text":"中心轮播位置"}
         */

        private String attachmentFullUrl;
        private int attachmentId;
        private int commodityId;
        private int displayOrder;
        private FileTypeBean fileType;
        private PositionTypeBean positionType;

        public String getAttachmentFullUrl() {
            return attachmentFullUrl;
        }

        public void setAttachmentFullUrl(String attachmentFullUrl) {
            this.attachmentFullUrl = attachmentFullUrl;
        }

        public int getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(int attachmentId) {
            this.attachmentId = attachmentId;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }

        public FileTypeBean getFileType() {
            return fileType;
        }

        public void setFileType(FileTypeBean fileType) {
            this.fileType = fileType;
        }

        public PositionTypeBean getPositionType() {
            return positionType;
        }

        public void setPositionType(PositionTypeBean positionType) {
            this.positionType = positionType;
        }

        public static class FileTypeBean {
            /**
             * code : 1010
             * name : IMAGE
             * text : 图片
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

        public static class PositionTypeBean {
            /**
             * code : 1010
             * name : CENTER
             * text : 中心轮播位置
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
    }

    public static class CommodityAttachmentsAtCommodityParticularsPositionBean {
        /**
         * attachmentFullUrl : https://uhealth-app-img.oss-cn-beijing.aliyuncs.com/ccb4e42a-2e9c-4b47-9ea0-8f95a7688ffa.jpg?height=2480&width=1748
         * attachmentId : 81
         * commodityId : 40
         * displayOrder : 0
         * fileType : {"code":1010,"name":"IMAGE","text":"图片"}
         * positionType : {"code":1020,"name":"COMMODITY_PARTICULARS","text":"商品详情位置"}
         */

        private String attachmentFullUrl;
        private int attachmentId;
        private int commodityId;
        private int displayOrder;
        private FileTypeBeanX fileType;
        private PositionTypeBeanX positionType;

        public String getAttachmentFullUrl() {
            return attachmentFullUrl;
        }

        public void setAttachmentFullUrl(String attachmentFullUrl) {
            this.attachmentFullUrl = attachmentFullUrl;
        }

        public int getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(int attachmentId) {
            this.attachmentId = attachmentId;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getDisplayOrder() {
            return displayOrder;
        }

        public void setDisplayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
        }

        public FileTypeBeanX getFileType() {
            return fileType;
        }

        public void setFileType(FileTypeBeanX fileType) {
            this.fileType = fileType;
        }

        public PositionTypeBeanX getPositionType() {
            return positionType;
        }

        public void setPositionType(PositionTypeBeanX positionType) {
            this.positionType = positionType;
        }

        public static class FileTypeBeanX {
            /**
             * code : 1010
             * name : IMAGE
             * text : 图片
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

        public static class PositionTypeBeanX {
            /**
             * code : 1020
             * name : COMMODITY_PARTICULARS
             * text : 商品详情位置
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
    }
}
