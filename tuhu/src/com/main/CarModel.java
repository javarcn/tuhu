package com.main;

import com.domain.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.service.*;
import com.util.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */
public class CarModel {
    public static Logger logger= Logger.getLogger(Main.class);

    public static void main(String[] args){
        logger.info("程序启动！车型汇总数据爬取开始！");
        long st=System.currentTimeMillis();
        List<CarPJ> carModelList=new ArrayList<CarPJ>();
        List<String> ss = GetBrand.BrandList();
        for(int i = 0;i <ss.size();i++){
            String oneBrand = ss.get(i);
            List<Brand> brandList= SelectCarName.CarNameList(oneBrand);
            for(Brand brand:brandList){
                String brand1=oneBrand.split("-")[1].trim();
                List<String> paiLiangList= SelectPaiLiang.PaiLiangList(brand.getProductID());
                if (paiLiangList.size()>0){
                    for(String paiLiang:paiLiangList){
                        List<String> yearList= SelectYear.YearList(brand.getProductID(),paiLiang);
                        for(String year:yearList){

                            Car car=new Car();
                            car.setBrand(oneBrand);
                            car.setBrand2(brand.getBrandType());
                            car.setBrand1(brand1);
                            car.setSeriesName(brand.getCarName());
                            car.setCarID(brand.getProductID());
                            car.setPaiLiang(paiLiang);
                            car.setYear(year);
                            long s=System.currentTimeMillis();
                            logger.info("开始抓取:" + brand1 + "-" + brand.getCarName() + "-" + paiLiang + "-" + year + "车型汇总数据！");
                            try {
                                PJ pj= getAllCarModel(car);
                                if(pj.getProperty()==null){
                                    car.setCarModel("全适配");
                                    CarPJ carPJ=new CarPJ();
                                    carPJ.setCar(car);
                                    carModelList.add(carPJ);
                                }else {
                                    List<Values> valuesList=pj.getProperty().getValues();
                                    for(Values values:valuesList){
                                        Car car1=new Car();
                                        car1.setBrand(oneBrand);
                                        car1.setBrand2(brand.getBrandType());
                                        car1.setBrand1(brand1);
                                        car1.setSeriesName(brand.getCarName());
                                        car1.setCarID(brand.getProductID());
                                        car1.setPaiLiang(paiLiang);
                                        car1.setYear(year);
                                        car1.setCarModel(values.getDisplayValue());
                                        CarPJ carPJ=new CarPJ();
                                        carPJ.setCar(car1);
                                        carModelList.add(carPJ);
                                    }
                                }
                            }catch (Exception e){
                                logger.error("爬取错误",e);
                                System.exit(1);
                            }
                            long e=System.currentTimeMillis();
                            logger.info(brand1+"-"+brand.getCarName()+"-"+paiLiang+"-"+year+"车型汇总完毕！共耗时"+(e-s)/1000+"秒");
                        }
                    }
                }
            }

        }

        long ed=System.currentTimeMillis();
        logger.info("车型汇总数据抓取完毕！一共耗时：" + (ed - st) / 1000 + "秒");


        long start=System.currentTimeMillis();
        logger.info("车型数据汇总开始写入excel");


        PJ pj=new PJ();
        pj.setCarPJList(carModelList);
        String f1="tuhu" + File.separator + "data" + File.separator + "车型汇总" + ".xlsx";
        try {
            WriteExcel.writeDataToExcel_F1(pj,f1);
        }catch (Exception ex){
            logger.error("爬取错误",ex);
            deleteDir(new File(f1));
            System.exit(1);
        }

        long end=System.currentTimeMillis();
        logger.info("车型汇总写入完毕！一共耗时：" + (end - start) / 1000 + "秒");
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static PJ getAllCarModel(Car car){
        PJ pj=new PJ();
        //TODO 获取该车型所有保养品
        String html = GetBaoYangList.getBaoyang(car);
        Gson gson=new Gson();
        JsonArray jsonArray=gson.fromJson(html,JsonArray.class);

        //TODO 配件关系表
        List<CarPJ> carPjList=new ArrayList<CarPJ>();
        Property property = null;
        for(int i=0;i<jsonArray.size();i++) {
            Baoyang baoyang = gson.fromJson(jsonArray.get(i), Baoyang.class);
            List<BaoyangItem> list = baoyang.getItems();
            for (BaoyangItem ls : list) {
                List<Items> itemsList = ls.getItems();
                for (Items items : itemsList) {
                    //出现不兼容，记录Property
                    if (items.getProperty() != null) {
                        property = items.getProperty();
                    }
                }
            }
        }
        pj.setProperty(property);
        pj.setCarPJList(carPjList);
        return pj;
    }


}
