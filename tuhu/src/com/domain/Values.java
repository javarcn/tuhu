package com.domain;

/**
 * Created by hwc on 2016/9/19.
 */
public class Values {
    private String ImageUrl;
    private String Key;
    private String DisplayValue;
    private String Value;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getDisplayValue() {
        return DisplayValue;
    }

    public void setDisplayValue(String displayValue) {
        DisplayValue = displayValue;
    }

    @Override
    public String toString() {
        return "Values{" +
                "ImageUrl='" + ImageUrl + '\'' +
                ", Key='" + Key + '\'' +
                ", DisplayValue='" + DisplayValue + '\'' +
                '}';
    }
}
