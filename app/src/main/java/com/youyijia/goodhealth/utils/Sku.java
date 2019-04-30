package com.youyijia.goodhealth.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by wangqiang on 2019/4/30.
 */
public class Sku implements Parcelable {
    private String number;
    private String originalPrice;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(String presentPrice) {
        this.presentPrice = presentPrice;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    private String presentPrice;
    private String stock;
    private List<SkuAttribute> attributes;



    public List<SkuAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SkuAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "number='" + number + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", presentPrice=" + presentPrice +
                ", stock=" + stock +
                ", attributes=" + attributes +
                '}';
    }

    public Sku() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.number);
        dest.writeString(this.originalPrice);
        dest.writeString(this.presentPrice);
        dest.writeString(this.stock);
        dest.writeTypedList(this.attributes);
    }

    protected Sku(Parcel in) {
        this.number = in.readString();
        this.originalPrice = in.readString();
        this.presentPrice = in.readString();
        this.stock = in.readString();
        this.attributes = in.createTypedArrayList(SkuAttribute.CREATOR);
    }

    public static final Creator<Sku> CREATOR = new Creator<Sku>() {
        @Override
        public Sku createFromParcel(Parcel source) {
            return new Sku(source);
        }

        @Override
        public Sku[] newArray(int size) {
            return new Sku[size];
        }
    };
}
