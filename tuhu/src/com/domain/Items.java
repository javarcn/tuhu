package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class Items {
    private String BaoYangType;
    private String ZhName;
    private String DataTip;
    private String ResultType;
    private List<Products> Products=new ArrayList<Products>();
    private  Property Property;
    private String InAdapteReason;

    public List<Products> getProducts() {
        return Products;
    }

    public void setProducts(List<Products> products) {
        Products = products;
    }

    public Property getProperty() {
        return Property;
    }

    public void setProperty(Property property) {
        Property = property;
    }

    public String getBaoYangType() {
        return BaoYangType;
    }

    public void setBaoYangType(String baoYangType) {
        BaoYangType = baoYangType;
    }

    public String getZhName() {
        return ZhName;
    }

    public void setZhName(String zhName) {
        ZhName = zhName;
    }

    public String getDataTip() {
        return DataTip;
    }

    public void setDataTip(String dataTip) {
        DataTip = dataTip;
    }

    public String getResultType() {
        return ResultType;
    }

    public void setResultType(String resultType) {
        ResultType = resultType;
    }


    public String getInAdapteReason() {
        return InAdapteReason;
    }

    public void setInAdapteReason(String inAdapteReason) {
        InAdapteReason = inAdapteReason;
    }

    @Override
    public String toString() {
        return "Items{" +
                "BaoYangType='" + BaoYangType + '\'' +
                ", ZhName='" + ZhName + '\'' +
                ", DataTip='" + DataTip + '\'' +
                ", ResultType='" + ResultType + '\'' +
                ", Products=" + Products +
                ", Property=" + Property +
                ", InAdapteReason='" + InAdapteReason + '\'' +
                '}';
    }
}
