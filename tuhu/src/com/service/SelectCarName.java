package com.service;

import com.domain.Brand;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.util.GetHtmlByUrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/18.
 */
public class SelectCarName {

    public static List<Brand> CarNameList(String brand) throws IOException {
        String url= java.net.URLEncoder.encode(brand,"utf-8");
        List<Brand> list=new ArrayList<Brand>();
        String json = GetHtmlByUrl.getHtml("http://item.tuhu.cn/Car/SelOneBrand?Brand=" + url);
        Gson gson=new Gson();
        JsonObject jsonObject=gson.fromJson(json, JsonObject.class);
        JsonArray jsonArray= (JsonArray) jsonObject.get("OneBrand");
        for(int i=0;i<jsonArray.size();i++){
            Brand brand1=gson.fromJson(jsonArray.get(i),Brand.class);
            list.add(brand1);
        }
        return list;
    }
}
