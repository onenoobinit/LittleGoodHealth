package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/26.
 */
public class WxPayInfo {
    /**
     * appId : wx89bfbe26609a6b54
     * nonceStr : VVCHLSEBXVE3TD6_YY53S224S2LAUIF2
     * partnerId : 1480775252
     * prepayId : wx26153255246967ea68b5d0be2133347660
     * sign : 03DDE1A22AE16681D59DCA97D5074005
     * timestamp : 1556263975
     * wxPackage : Sign=WXPay
     */

    private String appId;
    private String nonceStr;
    private String partnerId;
    private String prepayId;
    private String sign;
    private int timestamp;
    private String wxPackage;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getWxPackage() {
        return wxPackage;
    }

    public void setWxPackage(String wxPackage) {
        this.wxPackage = wxPackage;
    }
}
