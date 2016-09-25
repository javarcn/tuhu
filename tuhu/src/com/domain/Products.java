package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class Products {
    private Product Product;
    private String Count;
//    private List<Tags> Tags=new ArrayList<Tags>();
//    private List<Gifts> Gifts=new ArrayList<Gifts>();

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

/*    public List<Tags> getTags() {
        return Tags;
    }

    public void setTags(List<Tags> tags) {
        Tags = tags;
    }

    public List<Gifts> getGifts() {
        return Gifts;
    }

    public void setGifts(List<Gifts> gifts) {
        Gifts = gifts;
    }
*/


}
