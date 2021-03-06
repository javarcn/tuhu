package com.util;

import com.domain.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.service.GetBaoYangList;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hwc on 2016/9/20.
 */
public class GetBaoyangItem {
    public static Logger logger=Logger.getLogger(GetBaoyangItem.class);


    /**
     * 点击单个保养产品获取更多列表
     */
    public static List<CarPJ> postAllProducts(CarPJ carPJ) throws Exception {
/*        String Brand="F - 丰田";
        String Nian="2011";
        String PaiLiang="2.0L";
        String Tid="";
        String VehicleId="VE-FJTKLLYSJT";
        String pid="OL-Valvoline-All-Climate|5";
        String baoyangType="jiyou";

        String Brand=carPJ.getCar().getBrand();
        String Nian=carPJ.getCar().getYear();
        String PaiLiang=carPJ.getCar().getPaiLiang();
        String Tid=carPJ.getCar().getTid();
        String VehicleId=carPJ.getCar().getCarID();
        String pid=carPJ.getPJ_ID();
        String baoyangType=carPJ.getBaoYangType();
*/

        //TODO post请求数据方法1：jsoup
        String vehicle=null;
        if(carPJ.getCar().getProperty()==null || "".equals(carPJ.getCar().getProperty())){
            vehicle="{\"Brand\":\"%s\",\"FuelType\":\"\",\"Nian\":\"%s\",\"OnRoadTime\":\"\",\"PaiLiang\":\"%s\",\"Properties\":[],\"SalesName\":\"\",\"Tid\":\"%s\",\"VehicleId\":\"%s\",\"Vehicle\":\"\"}";
            vehicle=String.format(vehicle,carPJ.getCar().getBrand(),carPJ.getCar().getYear(),carPJ.getCar().getPaiLiang(),carPJ.getCar().getTid(),carPJ.getCar().getCarID());

        }else {
            vehicle="{\"Brand\":\"%s\",\"FuelType\":\"\",\"Nian\":\"%s\",\"OnRoadTime\":\"\",\"PaiLiang\":\"%s\",\"Properties\":[{\"Property\":\"%s\",\"PropertyValue\":\"%s\"}],\"SalesName\":\"\",\"Tid\":\"%s\",\"VehicleId\":\"%s\",\"Vehicle\":\"\"}";
            vehicle=String.format(vehicle,carPJ.getCar().getBrand(),carPJ.getCar().getYear(),carPJ.getCar().getPaiLiang(),carPJ.getCar().getProperty(),carPJ.getCar().getPropertyValue(),carPJ.getCar().getTid(),carPJ.getCar().getCarID());
        }
        String url="http://by.tuhu.cn/change/ChangeProduct.html";



        Map<String,String> data=new HashMap<String,String>();
        data.put("vehicle",vehicle);
        data.put("baoyangType",carPJ.getBaoYangType());
        data.put("pid",carPJ.getPJ_ID());
        int retryTimes=1;
        String json=null;
        do {
            try {
                Document document=Jsoup.connect(url).timeout(6000).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36").ignoreContentType(true).data(data).post();
                Elements elements=document.getElementsByTag("body");
                json=elements.get(0).text();
                break;
            }catch (Exception e){
                logger.error("访问出现"+url+"异常,1S后进行第"+retryTimes+"次重试！",e);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }while (++retryTimes<6);
        //TODO 获取数据
        Gson gson=new Gson();
        List<CarPJ> carPjList=new ArrayList<CarPJ>();

//        JsonParser jsonParser=new JsonParser();
//        JsonObject jsonObject= (JsonObject) jsonParser.parse(json);
        JsonObject jsonObject= gson.fromJson(json,JsonObject.class);

        JsonObject jsonObject1= (JsonObject) jsonObject.get("Data");
        for(int i=1;i<=jsonObject1.size();i++){
            JsonArray jsonArray= (JsonArray) jsonObject1.get(""+i);
            for(int j=0;j<jsonArray.size();j++){
                Product product=gson.fromJson(jsonArray.get(j),Product.class);
                CarPJ carPJ1=new CarPJ();
                carPJ1.setCar(carPJ.getCar());
                carPJ1.setPJ_ID(product.getPid());
                carPJ1.setPJ_Name(product.getDisplayName());
                carPJ1.setBaoyangName(carPJ.getBaoyangName());
                carPJ1.setBaoYangType(carPJ.getBaoYangType());
                carPJ1.setSuggestTip(carPJ.getSuggestTip());
                String id=product.getPid();
                if(id.indexOf("|")<id.length()-1){
                    carPJ1.setUrl(id.replace("|","/"));
                }else {
                    carPJ1.setUrl(id.replace("|",""));
                }
                carPjList.add(carPJ1);
            }

        }
        return carPjList;
    }

    /**
     *  获取保养项目列表
     */
    public static PJ getBaoyangItem(Car car) throws Exception {
        //TODO 2.获取该车型的保养数据列表

        PJ pj=new PJ();

        //TODO 获取该车型所有保养品
        String html= GetBaoYangList.getBaoyang(car);
        Gson gson=new Gson();
        JsonArray jsonArray=gson.fromJson(html,JsonArray.class);

        //TODO 配件关系表
        List<CarPJ> carPjList=new ArrayList<CarPJ>();
        boolean is_ok = true;
        Property property = null;
        for(int i=0;i<jsonArray.size();i++) {
            Baoyang baoyang = gson.fromJson(jsonArray.get(i), Baoyang.class);
            List<BaoyangItem> list = baoyang.getItems();
            for (BaoyangItem ls : list) {
                List<Items> itemsList = ls.getItems();
                for (Items items : itemsList) {
                    //只要有一个不兼容，就设置成false
                    if (items.getProperty() != null) {
                        is_ok = false;
                        property = items.getProperty();
                    }
                }
            }
        }

        //如果全系兼容，抓取当前
        if (is_ok) {
            System.out.println(car.getBrand1()+" "+ car.getBrand2()+""+car.getSeriesName()+" "+car.getPaiLiang()+" "+car.getYear()+"  全匹配");

            for(int i=0;i<jsonArray.size();i++) {
                Baoyang baoyang = gson.fromJson(jsonArray.get(i), Baoyang.class);
                //获取保养的列表
                List<BaoyangItem> list = baoyang.getItems();
                for (BaoyangItem ls : list) {
                    String SuggestTip = ls.getSuggestTip();
                    String PackageType = ls.getPackageType();
                    List<Items> itemsList = ls.getItems();

                    for (Items items : itemsList) {
                        String baoyangName = items.getZhName();
                        String BaoYangType = items.getBaoYangType();
                        if (PackageType.equals("xby")) {
                            baoyangName = "小保养—" + baoyangName;
                        } else if (PackageType.equals("dby")) {
                            baoyangName = "大保养—" + baoyangName;
                        }
                        List<Products> productsList = items.getProducts();
                        if(productsList!=null){
                            for (Products pu : productsList) {
                                CarPJ carPJ = new CarPJ();
                                //TODO 将|转化成/
                                String pid = pu.getProduct().getPid();
                                carPJ.setCar(car);
                                carPJ.setBaoyangName(baoyangName);
                                carPJ.setBaoYangType(BaoYangType);
                                carPJ.setPJ_ID(pid);
                                carPJ.setSuggestTip(SuggestTip);
                                //TODO 点击单品，获取右侧出现的所有同类品
                                List<CarPJ> carPJList1 = postAllProducts(carPJ);
                                if (carPJList1 != null) {
                                    for (CarPJ carPJ1 : carPJList1) {
                                        carPjList.add(carPJ1);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }else {
            //否则重新来
            List<Values> valuesList = property.getValues();
            for (Values value : valuesList) {
                System.out.println(car.getBrand1()+" "+ car.getBrand2()+""+car.getSeriesName()+" "+car.getPaiLiang()+" "+car.getYear()+" "+value.getDisplayValue()+"  不匹配");
                Car car1 = new Car();
                car1.setBrand(car.getBrand());
                car1.setBrand1(car.getBrand1());
                car1.setBrand2(car.getBrand2());
                car1.setSeriesName(car.getSeriesName());
                car1.setPaiLiang(car.getPaiLiang());
                car1.setYear(car.getYear());
                car1.setCarID(car.getCarID());
                if (property.getType().equals("Tid")) {
                    car1.setTid(value.getKey());
                } else {
                    car1.setProperty(property.getName());
                    car1.setPropertyValue(value.getKey());
                }
                car1.setCarModel(value.getDisplayValue());

                //TODO 获取该车型所有保养品
                String html1 = GetBaoYangList.getBaoyang(car1);
                JsonArray jsonArray1 = gson.fromJson(html1, JsonArray.class);
                for(int i=0;i<jsonArray1.size();i++) {
                    Baoyang baoyang1 = gson.fromJson(jsonArray1.get(i), Baoyang.class);
                    List<BaoyangItem> itemList = baoyang1.getItems();
                    for (BaoyangItem item : itemList) {
                        String SuggestTip = item.getSuggestTip();
                        String PackageType = item.getPackageType();
                        List<Items> itemsList = item.getItems();

                        for (Items items : itemsList) {
                            String baoyangName = items.getZhName();
                            String BaoYangType = items.getBaoYangType();
                            if (PackageType.equals("xby")) {
                                baoyangName = "小保养—" + baoyangName;
                            } else if (PackageType.equals("dby")) {
                                baoyangName = "大保养—" + baoyangName;
                            }
                            List<Products> productsList = items.getProducts();
                            if(productsList!=null){
                                for (Products pu : productsList) {
                                    CarPJ carPJ = new CarPJ();
                                    //TODO 将|转化成/
                                    String pid = pu.getProduct().getPid();
                                    carPJ.setCar(car1);
                                    carPJ.setBaoyangName(baoyangName);
                                    carPJ.setBaoYangType(BaoYangType);
                                    carPJ.setPJ_ID(pid);
                                    carPJ.setSuggestTip(SuggestTip);

                                    //TODO 点击单品，获取右侧出现的所有同类品
                                    List<CarPJ> carPJList1 = postAllProducts(carPJ);
                                    if (carPJList1 != null) {
                                        for (CarPJ carPJ1 : carPJList1) {
                                            carPjList.add(carPJ1);
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        pj.setProperty(property);
        pj.setCarPJList(carPjList);
        return pj;
    }
}
