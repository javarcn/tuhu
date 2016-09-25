package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class Property {
    private String Type;
    private String Title;
    private String Content;
    private String Name;
    private List<Values> Values=new ArrayList<Values>();

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Values> getValues() {
        return Values;
    }

    public void setValues(List<Values> values) {
        Values = values;
    }

}
