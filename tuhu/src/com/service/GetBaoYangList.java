package com.service;

import com.domain.Car;
import com.util.Httpget;


import java.io.IOException;

/**
 * Created by hwc on 2016/9/18.
 */
public class GetBaoYangList {
    private static String url_before="http://by.tuhu.cn/change/GetBaoYangPackages.html?vehicle=";
//    private static String url_after1="{\"Brand\":\"%s\",\"VehicleId\":\"%s\",\"PaiLiang\":\"%s\",\"Nian\":\"%s\",\"Tid\":\"%s\",\"Properties\":null}";
    private static String url_after="{\"Brand\":\"%s\",\"VehicleId\":\"%s\",\"PaiLiang\":\"%s\",\"Nian\":\"%s\",\"Tid\":\"%s\",\"Properties\":[{\"Property\":\"%s\",\"PropertyValue\":\"%s\"}]}";
    public static String getBaoyang(Car car)  {
        int retryTimes=0;
        do {
            try {
                String url1=String.format(url_after,car.getBrand(),car.getCarID(),car.getPaiLiang(),car.getYear(),car.getTid(),car.getProperty(),car.getPropertyValue());
                //此处需要进行URL编码
                String url2=java.net.URLEncoder.encode(url1,"utf-8");
                String html= Httpget.getHtml(url_before + url2);
                return html;
            }catch (IOException e){
                e.printStackTrace();
            }
        }while (++retryTimes<6);
        throw new RuntimeException("服务端异常异常，超出重试次数");
    }
}
