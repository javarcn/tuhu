package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class Baoyang {
    private List<BaoyangItem> Items=new ArrayList<BaoyangItem>();

    public List<BaoyangItem> getItems() {
        return Items;
    }

    public void setItems(List<BaoyangItem> items) {
        Items = items;
    }
}
