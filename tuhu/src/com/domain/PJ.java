package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class PJ {
    private List<CarPJ> carPJList =new ArrayList<>();
    private List<CarPJDetail> carPjDetailList =new ArrayList<>();
    private List<String> urlList=new ArrayList<>();
    private Property Property;

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public Property getProperty() {
        return Property;
    }

    public void setProperty(Property property) {
        Property = property;
    }

    public List<CarPJ> getCarPJList() {
        return carPJList;
    }

    public void setCarPJList(List<CarPJ> carPJList) {
        this.carPJList = carPJList;
    }

    public List<CarPJDetail> getCarPjDetailList() {
        return carPjDetailList;
    }

    public void setCarPjDetailList(List<CarPJDetail> carPjDetailList) {
        this.carPjDetailList = carPjDetailList;
    }
}
