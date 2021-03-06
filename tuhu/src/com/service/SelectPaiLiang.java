package com.service;

import com.domain.Values;
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
public class SelectPaiLiang {
    public static List<String> PaiLiangList(String VehicleID){
        List<String> list=new ArrayList<String>();
        String json= null;
        try {
            json = Httpget.getHtml("http://item.tuhu.cn/Car/SelectVehicle?VehicleID=" + VehicleID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson=new Gson();
        JsonObject jsonObject=gson.fromJson(json,JsonObject.class);
        JsonArray jsonArray= (JsonArray) jsonObject.get("PaiLiang");
        for(int i=0;i<jsonArray.size();i++){
            Values values=gson.fromJson(jsonArray.get(i),Values.class);
            list.add(values.getValue());
        }
        return list;
    }

}
