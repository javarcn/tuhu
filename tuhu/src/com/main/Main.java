package com.main;

import com.domain.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.service.GetBaoYangList;
import com.util.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
        Car car=new Car();
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
        car.setPropertyValue("");
        PJ pj= GetBaoyangItem.getBaoyangItem(car);
        PraseProductDetail.praseAllItem(pj.getCarPJList());
        List<CarPJDetail> list=PraseProductDetail.getCarPJDetailList();
        pj.setCarPjDetailList(list);
        WriteExcel.writeDataToExcel(pj);
        System.out.println("数据成功写入excel！");
        GetImage.getImgData(list);
        System.out.println("商品图片抓取完毕！！");
        long ed=System.currentTimeMillis();
        System.out.println("整个过程耗时："+(ed-st)/1000+"秒");

    }








}
