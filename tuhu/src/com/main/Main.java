package com.main;

import com.domain.Brand;
import com.domain.Car;
import com.domain.CarPJDetail;
import com.domain.PJ;
import com.service.GetBrand;
import com.service.SelectCarName;
import com.service.SelectPaiLiang;
import com.service.SelectYear;
import com.util.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Logger logger=Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("程序启动！数据爬取开始！");
        long st=System.currentTimeMillis();

        List<String> ss = GetBrand.BrandList();
//        int j = 0;
        for(int i = 0;i <ss.size();i++){
            String oneBrand = ss.get(i);
//            List<PJ> pj_list = new ArrayList<PJ>();
            List<Brand> brandList=SelectCarName.CarNameList(oneBrand);
            for(Brand brand:brandList){
                String brand1=oneBrand.split("-")[1].trim();
                List<String> paiLiangList=SelectPaiLiang.PaiLiangList(brand.getProductID());
                if (paiLiangList.size()==0 ||paiLiangList ==null)
                    break;
                for(String paiLiang:paiLiangList){
                    List<String> yearList=SelectYear.YearList(brand.getProductID(),paiLiang);
                    for(String year:yearList){

                        Car car=new Car();
                        car.setBrand(oneBrand);
                        car.setBrand2(brand.getBrandType());
                        car.setBrand1(brand1);
                        car.setSeriesName(brand.getCarName());
                        car.setCarID(brand.getProductID());
                        car.setPaiLiang(paiLiang);
                        car.setYear(year);
                        String filepath="tuhu"+ File.separator+"data"+File.separator+brand1+File.separator
                                +brand.getCarName()+File.separator+paiLiang+File.separator+year+File.separator
                                +brand1+"_"+brand.getCarName()+"_"+paiLiang+"_"+year+".xlsx";
                        String f1="tuhu" + File.separator + "data" + File.separator + "车型汇总" + ".xlsx";
                        String f2="tuhu" + File.separator + "data" + File.separator + "商品汇总" + ".xlsx";

                        if(IsFileExist.path(filepath)==null){
//                            if(i>j){
//                                String ob = ss.get(j);
//                                String ob_brand1=ob.split("-")[1].trim();
//                                WriteExcel.writeDataToExcel(pj_list, "tuhu"+ File.separator+"data"+File.separator+ob_brand1+File.separator+ob_brand1+".xls");
//                            }

                            long s=System.currentTimeMillis();
                            logger.info("开始抓取:" + brand1 + "-" + brand.getCarName() + "-" + paiLiang + "-" + year + "车型数据！");
                            List<CarPJDetail> list = new ArrayList<CarPJDetail>();
                            try {
                                PJ pj= GetBaoyangItem.getBaoyangItem(car);
                                 PraseProductDetail.praseAllItem(pj.getCarPJList());
                                list =PraseProductDetail.getCarPJDetailList();
                                pj.setCarPjDetailList(list);
//                                WriteExcel.writeDataToExcel(pj, filepath);
//
//                                WriteExcel.writeDataToExcel_BD(pj, "tuhu" + File.separator + "data" + File.separator + brand1 + File.separator + brand1 + ".xlsx");
//                                WriteExcel.writeDataToExcel_F1(pj,f1);
//                                WriteExcel.writeDataToExcel_F2(pj,f2);
//                                pj_list.add(pj);
                            }catch (Exception ex){
                                logger.error("爬取错误",ex);
                                deleteDir(new File(filepath));
                                System.exit(1);
                            }


                            logger.info(brand1+"-"+brand.getCarName()+"-"+paiLiang+"-"+year +"数据保存成功,开始抓图片");
                            GetImage.getImgData(list);
                            long e=System.currentTimeMillis();
                            logger.info(brand1+"-"+brand.getCarName()+"-"+paiLiang+"-"+year+"车型数据抓取完毕！共耗时"+(e-s)/1000+"秒");
                        }else {
                            logger.info(brand1+"-"+brand.getCarName()+"-"+paiLiang+"-"+year+"已经存在，跳过");
                            logger.info(brand1+"-"+brand.getCarName()+"-"+paiLiang+"-"+year+"开始合并");

                            List<CarPJDetail> list = new ArrayList<CarPJDetail>();
                            try {
                                PJ pj= GetBaoyangItem.getBaoyangItem(car);
                                PraseProductDetail.praseAllItem(pj.getCarPJList());
                                list =PraseProductDetail.getCarPJDetailList();
                                pj.setCarPjDetailList(list);
//                                WriteExcel.writeDataToExcel_F1(pj,f1);
//                                WriteExcel.writeDataToExcel_F2(pj,f2);
                            }catch (Exception ex){
                                logger.error("爬取错误",ex);
                                deleteDir(new File(f1));
                                deleteDir(new File(f2));
                                System.exit(1);
                            }
//                            GetImage.getImgData(PraseProductDetail.getCarPJDetailList());
                        }

                    }
                }
            }

//            j= i;
        }
        long ed=System.currentTimeMillis();
        logger.info("全部数据抓取完毕！一共耗时：" + (ed - st) / 1000 + "秒");
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



}
