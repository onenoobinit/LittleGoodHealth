package com.youyijia.goodhealth.entity;

/**
 * Created by wangqiang on 2019/4/15.
 */
public class GreenPriceInfo {
    /**
     * totalPay : 3700.0
     * discountMoney : 0
     * finalPay : 3700.0
     */

    private double totalPay;
    private double discountMoney;
    private double finalPay;

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }

    public double getFinalPay() {
        return finalPay;
    }

    public void setFinalPay(double finalPay) {
        this.finalPay = finalPay;
    }
}
