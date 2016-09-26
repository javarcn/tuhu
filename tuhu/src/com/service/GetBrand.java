package com.service;

import com.domain.Brand;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.util.Httpget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/18.
 */
public class GetBrand {

    public static List<String> BrandList() throws IOException {
        List<String> list=new ArrayList<String>();
        String json= Httpget.getHtml("http://item.tuhu.cn/Car/GetCarBrands2");
        Gson gson=new Gson();
        JsonObject jsonObject=gson.fromJson(json,JsonObject.class);
        String letter[]={"A","B","C","D","F","G","H","J","K","L","M","N","O","P","Q","R","S","T","W","X","Y","Z"};
        for(int i=0;i<letter.length;i++){
           JsonArray jsonArray1= (JsonArray) jsonObject.get(letter[i]);
            for(int j=0;j<jsonArray1.size();j++){
                Brand brand=gson.fromJson(jsonArray1.get(j),Brand.class);
                list.add(brand.getBrand());
            }
        }
        return list;
    }
}
