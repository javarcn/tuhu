package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class Gifts {
    private List<Tag> Tag=new ArrayList<Tag>();
    private String Description;
    private String DisplayName;
    private String Pid;


    public List<Tag> getTag() {
        return Tag;
    }

    public void setTag(List<Tag> tag) {
        Tag = tag;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    @Override
    public String toString() {
        return "Gifts{" +
                "Tag=" + Tag +
                ", Description='" + Description + '\'' +
                ", DisplayName='" + DisplayName + '\'' +
                ", Pid='" + Pid + '\'' +
                '}';
    }
}
