package com.domain;

/**
 * Created by hwc on 2016/9/19.
 */
public class CarPJ {
    private Car car;
    private String PJ_Name;
    private String PJ_ID;
    private String BaoyangName;
    private String BaoYangType;
    private String SuggestTip;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuggestTip() {
        return SuggestTip;
    }

    public void setSuggestTip(String suggestTip) {
        SuggestTip = suggestTip;
    }

    public String getBaoYangType() {
        return BaoYangType;
    }

    public void setBaoYangType(String baoYangType) {
        BaoYangType = baoYangType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getPJ_Name() {
        return PJ_Name;
    }

    public void setPJ_Name(String PJ_Name) {
        this.PJ_Name = PJ_Name;
    }

    public String getPJ_ID() {
        return PJ_ID;
    }

    public void setPJ_ID(String PJ_ID) {
        this.PJ_ID = PJ_ID;
    }

    public String getBaoyangName() {
        return BaoyangName;
    }

    public void setBaoyangName(String baoyangName) {
        BaoyangName = baoyangName;
    }
}
