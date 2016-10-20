package com.service;

import com.domain.Car;
import com.util.Httpget;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by hwc on 2016/9/18.
 */
public class GetBaoYangList {
    private static String url_before="http://by.tuhu.cn/change/GetBaoYangPackages.html?vehicle=";
    private static String url_after1="{\"Brand\":\"%s\",\"VehicleId\":\"%s\",\"PaiLiang\":\"%s\",\"Nian\":\"%s\",\"Tid\":\"%s\",\"Properties\":null}";
    private static String url_after2="{\"Brand\":\"%s\",\"VehicleId\":\"%s\",\"PaiLiang\":\"%s\",\"Nian\":\"%s\",\"Tid\":\"\",\"Properties\":[{\"Property\":\"%s\",\"PropertyValue\":\"%s\"}]}";
    public static String getBaoyang(Car car)   {
        String url1=null;
        if(car.getPropertyValue()==null || "".equals(car.getPropertyValue())){
            url1=String.format(url_after1,car.getBrand(),car.getCarID(),car.getPaiLiang(),car.getYear(),car.getTid());
        }else {
            url1=String.format(url_after2,car.getBrand(),car.getCarID(),car.getPaiLiang(),car.getYear(),car.getProperty(),car.getPropertyValue());
        }
        //此处需要进行URL编码
        String url2= null;
        try {
            url2 = java.net.URLEncoder.encode(url1,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String html= null;
        try {
            html = Httpget.getHtml(url_before + url2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }
}
