package com.domain;

/**
 * Created by hwc on 2016/9/19.
 */
public class Tags {
    private String Type;
    private String Tag;
    private String TagColor;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getTagColor() {
        return TagColor;
    }

    public void setTagColor(String tagColor) {
        TagColor = tagColor;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "Type='" + Type + '\'' +
                ", Tag='" + Tag + '\'' +
                ", TagColor='" + TagColor + '\'' +
                '}';
    }
}
