package com.main;

import com.domain.*;
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

/**
 * Created by Administrator on 2016/10/12.
 */
public class CarModel {
    public static Logger logger= Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("程序启动！数据爬取开始！");
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
                            try {
                                PJ pj= GetBaoyangItem.getBaoyangItem(car);
                                if(pj.getProperty()==null){
                                    car.setCarModel("全适配");
                                    CarPJ carPJ=new CarPJ();
                                    carPJ.setCar(car);
                                    carModelList.add(carPJ);
                                }else {
                                    List<Values> valuesList=pj.getProperty().getValues();
                                    for(Values values:valuesList){
                                        car.setCarModel(values.getDisplayValue());
                                        CarPJ carPJ=new CarPJ();
                                        carPJ.setCar(car);
                                        carModelList.add(carPJ);
                                    }
                                }
                            }catch (Exception e){
                                logger.error("爬取错误",e);
                                System.exit(1);
                            }
                        }
                    }
                }
            }

        }
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

        long ed=System.currentTimeMillis();
        logger.info("车型汇总完毕！一共耗时：" + (ed - st) / 1000 + "秒");
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
