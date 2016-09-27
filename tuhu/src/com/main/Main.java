package com.main;

import com.domain.Brand;
import com.domain.Car;
import com.domain.CarPJDetail;
import com.domain.PJ;
import com.service.GetBrand;
import com.service.SelectCarName;
import com.service.SelectPaiLiang;
import com.service.SelectYear;
import com.util.GetBaoyangItem;
import com.util.GetImage;
import com.util.PraseProductDetail;
import com.util.WriteExcel;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class Main {
//    public static Logger logger=Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        //TODO 1.根据接口获取车的型号参数
//        long st=System.currentTimeMillis();
//        Car car=new Car();
//        car.setBrand("F - 丰田");
//        car.setBrand1("丰田");
//        car.setBrand2("一汽丰田");
//        car.setSeriesName("卡罗拉");
//        car.setPaiLiang("2.0L");
//        car.setYear("2011");
//        car.setCarID("VE-FJTKLLYSJT");
//        car.setCarModel("");
//        car.setTid("");
//        car.setProperty("");
//        car.setPropertyValue("");
//        PJ pj= GetBaoyangItem.getBaoyangItem(car);
//        PraseProductDetail.praseAllItem(pj.getCarPJList());
//        List<CarPJDetail> list=PraseProductDetail.getCarPJDetailList();
//        pj.setCarPjDetailList(list);
//        String filepath="C:\\tuhu\\data\\"+car.getBrand1()+"\\"+car.getSeriesName()+"\\"+car.getPaiLiang()+"\\"+car.getYear()+"\\"+car.getBrand1()+"_"+car.getSeriesName()+"_"+car.getPaiLiang()+"_"+car.getYear()+".xls";
//        WriteExcel.writeDataToExcel(pj,filepath);
//        System.out.println("数据成功写入excel！");
//        GetImage.getImgData(list);
//        System.out.println("商品图片抓取完毕！！");
//        long ed=System.currentTimeMillis();
//        System.out.println("整个过程耗时："+(ed-st)/1000+"秒");

        //TODO main
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


                        PJ pj= GetBaoyangItem.getBaoyangItem(car);
                        PraseProductDetail.praseAllItem(pj.getCarPJList());
                        List<CarPJDetail> list=PraseProductDetail.getCarPJDetailList();
                        pj.setCarPjDetailList(list);

                        String filepath="C:\\tuhu\\data\\"+brand1+"\\"+brand.getCarName()+"\\"+paiLiang+"\\"+year+"\\"+brand1+"_"+brand.getCarName()+"_"+paiLiang+"_"+year+".xls";
                        WriteExcel.writeDataToExcel(pj,filepath);
                        GetImage.getImgData(list);
                    }
                }
            }
        }
            long ed=System.currentTimeMillis();
            System.out.println("本次抓取数据一共耗时："+(ed-st)/1000+"秒");
//
//                    //TODO test 本田
//                    long st=System.currentTimeMillis();
//                    Car car=new Car();
//                    car.setBrand("B - 本田");
//                    car.setBrand1("本田");
//                    car.setBrand2("广州本田");
//                    car.setSeriesName("雅阁");
//                    car.setPaiLiang("2.0L");
//                    car.setYear("2015");
//                    car.setCarID("VE-HNDG07AY");
//                    car.setCarModel("");
//                    car.setTid("");
//                    car.setProperty("");
//                    car.setPropertyValue("");
//
//                    PJ pj= GetBaoyangItem.getBaoyangItem(car);
//                    PraseProductDetail.praseAllItem(pj.getCarPJList());
//                    List<CarPJDetail> list=PraseProductDetail.getCarPJDetailList();
//                    pj.setCarPjDetailList(list);
//
//                    String filepath="C:\\tuhu\\data\\"+car.getBrand1()+"\\"+car.getSeriesName()+"\\"+car.getPaiLiang()+"\\"+car.getYear()+"\\"+car.getBrand1()+"_"+car.getSeriesName()+"_"+car.getPaiLiang()+"_"+car.getYear()+".xls";
//                    WriteExcel.writeDataToExcel(pj,filepath);
//                    GetImage.getImgData(list);
//
//                    long ed=System.currentTimeMillis();
//                    System.out.println("本次抓取数据一共耗时："+(ed-st)/1000+"秒");//
//                    //TODO test 日产
/*                    long st=System.currentTimeMillis();
                    Car car=new Car();
                    car.setBrand("R - 日产");
                    car.setBrand1("日产");
                    car.setBrand2("东风日产");
                    car.setSeriesName("骐达");
                    car.setPaiLiang("1.6L");
                    car.setYear("2011");
                    car.setCarID("VE-FENG07AZ");
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
                    GetImage.getImgData(list);

                    long ed=System.currentTimeMillis();
                    System.out.println("本次抓取数据一共耗时："+(ed-st)/1000+"秒");*/

    }








}
