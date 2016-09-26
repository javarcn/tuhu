package com.main;

import com.domain.Brand;
import com.domain.Car;
import com.service.GetBrand;
import com.service.SelectCarName;
import com.service.SelectPaiLiang;
import com.service.SelectYear;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //TODO 1.根据接口获取车的型号参数
//        String brand="F - 丰田";
//        String vehicleId="VE-FJTKLLYSJT";
//        String paiLiang="2.0L";
//        String nian="2011";
//        String tid="";
        long st=System.currentTimeMillis();
/*        Car car=new Car();
        car.setBrand("F - 丰田");
        car.setBrand1("丰田");
        car.setBrand2("一汽丰田");
        car.setSeriesName("卡罗拉");
        car.setPaiLiang("2.0L");
        car.setYear("2011");
        car.setCarID("VE-FJTKLLYSJT");
        car.setCarModel("");
        car.setTid("");
        car.setProperty("");
        car.setPropertyValue("");*/
//        PJ pj= GetBaoyangItem.getBaoyangItem(car);
//        PraseProductDetail.praseAllItem(pj.getCarPJList());
//        List<CarPJDetail> list=PraseProductDetail.getCarPJDetailList();
//        pj.setCarPjDetailList(list);
//        WriteExcel.writeDataToExcel(pj);
        System.out.println("数据成功写入excel！");
//        GetImage.getImgData(list);
        System.out.println("商品图片抓取完毕！！");
        long ed=System.currentTimeMillis();
        System.out.println("整个过程耗时："+(ed-st)/1000+"秒");

        //TODO test
        int i=1;
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
                        System.out.println(i+car.toString());
                        i++;
                    }
                }
            }

        }
    }








}
