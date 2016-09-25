package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class BaoyangItem {
    private String PackageType;
    private String ZhName;
    private String SuggestTip;
    private List<Items> Items=new ArrayList<Items>();
    private String IsActivity;

    public List<Items> getItems() {
        return Items;
    }

    public void setItems(List<Items> items) {
        Items = items;
    }

    public String getPackageType() {
        return PackageType;
    }

    public void setPackageType(String packageType) {
        PackageType = packageType;
    }

    public String getZhName() {
        return ZhName;
    }

    public void setZhName(String zhName) {
        ZhName = zhName;
    }

    public String getSuggestTip() {
        return SuggestTip;
    }

    public void setSuggestTip(String suggestTip) {
        SuggestTip = suggestTip;
    }

    public String getIsActivity() {
        return IsActivity;
    }

    public void setIsActivity(String isActivity) {
        IsActivity = isActivity;
    }

    @Override
    public String toString() {
        return "BaoyangItem{" +
                "PackageType='" + PackageType + '\'' +
                ", ZhName='" + ZhName + '\'' +
                ", SuggestTip='" + SuggestTip + '\'' +
                ", Items=" + Items +
                ", IsActivity='" + IsActivity + '\'' +
                '}';
    }
}
