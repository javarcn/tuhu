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

import java.io.IOException;
import java.util.List;

public class Main {
    public static Logger logger=Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
/*        logger.debug("程序启动！数据爬取开始！");
        long st=System.currentTimeMillis();
        for(String oneBrand:GetBrand.BrandList()){
           List<Brand> brandList=SelectCarName.CarNameList(oneBrand);
            for(Brand brand:brandList){
                List<String> paiLiangList=SelectPaiLiang.PaiLiangList(brand.getProductID());
                if (paiLiangList.size()==0 ||paiLiangList ==null)
                    break;
                for(String paiLiang:paiLiangList){
                    List<String> yearList=SelectYear.YearList(brand.getProductID(),paiLiang);
                    for(String year:yearList){
                        Car car=new Car();
                        car.setBrand(oneBrand);
                        car.setBrand2(brand.getBrandType());
                       String brand1=oneBrand.split("-")[1].trim();
                        car.setBrand1(brand1);
                        car.setSeriesName(brand.getCarName());
                        car.setCarID(brand.getProductID());
                        car.setPaiLiang(paiLiang);
                        car.setYear(year);
                        String filepath="tuhu\\data\\"+brand1+"\\"+brand.getCarName()+"\\"+paiLiang+"\\"+year+"\\"+brand1+"_"+brand.getCarName()+"_"+paiLiang+"_"+year+".xls";
                        if(IsFileExist.path(filepath)==null){
                            long s=System.currentTimeMillis();
                            logger.debug("开始抓取:"+brand1+"-"+brand.getCarName()+"-"+paiLiang+"-"+year+"车型数据！");
                            PJ pj= GetBaoyangItem.getBaoyangItem(car);
                            PraseProductDetail.praseAllItem(pj.getCarPJList());
                            List<CarPJDetail> list=PraseProductDetail.getCarPJDetailList();
                            pj.setCarPjDetailList(list);
                            WriteExcel.writeDataToExcel(pj,filepath);
                            GetImage.getImgData(list);
                            long e=System.currentTimeMillis();
                            logger.debug(brand1+"-"+brand.getCarName()+"-"+paiLiang+"-"+year+"车型数据抓取完毕！共耗时"+(e-s)/1000+"秒");
                        }else {
                            GetImage.getImgData(PraseProductDetail.getCarPJDetailList());
                        }

                    }
                }
            }
        }
            long ed=System.currentTimeMillis();
            System.out.println("本次抓取数据一共耗时："+(ed-st)/1000+"秒");*/

                    //TODO test 本田
                    long st=System.currentTimeMillis();
                    Car car=new Car();
                    car.setBrand("A - 奥迪");
                    car.setBrand1("奥迪");
                    car.setBrand2("一汽大众奥迪");
                    car.setSeriesName("A3");
                    car.setPaiLiang("1.4T(35TFSI)");
                    car.setYear("2016");
                    car.setCarID("VE-AADA3AD");
                    car.setCarModel("");
                    car.setTid("");
                    car.setProperty("");
                    car.setPropertyValue("");

                    PJ pj= GetBaoyangItem.getBaoyangItem(car);
                    PraseProductDetail.praseAllItem(pj.getCarPJList());
                    List<CarPJDetail> list=PraseProductDetail.getCarPJDetailList();
                    pj.setCarPjDetailList(list);

                    String filepath="C:\\tuhu\\data\\"+car.getBrand1()+"\\"+car.getSeriesName()+"\\"+car.getPaiLiang()+"\\"+car.getYear()+"\\"+car.getBrand1()+"_"+car.getSeriesName()+"_"+car.getPaiLiang()+"_"+car.getYear()+".xls";
                    WriteExcel.writeDataToExcel(pj,filepath);
//                    GetImage.getImgData(list);

                    long ed=System.currentTimeMillis();
                    System.out.println("本次抓取数据一共耗时："+(ed-st)/1000+"秒");

    }








}
