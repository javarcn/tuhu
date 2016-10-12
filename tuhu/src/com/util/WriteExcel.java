package com.util;

import com.domain.CarPJ;
import com.domain.CarPJDetail;
import com.domain.PJ;
import com.main.DataSingleton;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * Created by hwc on 2016/9/20.
 */
public class WriteExcel {
    public static Logger logger = Logger.getLogger(WriteExcel.class);

    /**
     * 本地生成excel
     */
    public static void writeDataToExcel(PJ pj, String filepath) throws IOException {
        try {
            File file = new File(filepath);
            if (file.exists()) {
                logger.info(filepath + "已存在");
                return;
            }
            if (filepath.endsWith(File.separator)) {
                return;
            }
            //判断目标文件所在的目录是否存在
            if (!file.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                if (!file.getParentFile().mkdirs()) {
                    return;
                }
            }



            //创建目标文件
            try {
                if (file.createNewFile()) {

                } else {
                    logger.error("创建单个文件" + filepath + "失败！");
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("创建单个文件" + filepath + "失败！" + e.getMessage(), e);
                return;
            }

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("配件关系");
            sheet.setDefaultRowHeightInPoints(20);
            XSSFRow row = sheet.createRow(0);
            for (int i = 0; i < 11; i++) {
                XSSFCell cell = row.createCell(i);
                if (i < 3) {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 10);
                } else if (i < 5) {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 6);
                } else if (i < 6) {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 30);
                } else if (i < 7) {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 18);

                } else if (i < 8) {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 80);

                } else if (i < 9) {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 20);

                } else if (i < 10) {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 15);

                } else {
                    sheet.setColumnWidth(cell.getColumnIndex(), 256 * 30);
                }

            }
            row.createCell(0).setCellValue("一级品牌名称");
            row.createCell(1).setCellValue("二级品牌名称");
            row.createCell(2).setCellValue("车系名称");
            row.createCell(3).setCellValue("车排量");
            row.createCell(4).setCellValue("车年份");
            row.createCell(5).setCellValue("适配车型");
            row.createCell(6).setCellValue("保养项目");
            row.createCell(7).setCellValue("保养产品");
            row.createCell(8).setCellValue("建议使用频率");
            row.createCell(9).setCellValue("车型ID");
            row.createCell(10).setCellValue("保养产品ID");


            int rowNum = 1;
            List<CarPJ> list = pj.getCarPJList();
            for (CarPJ ls : list) {
                XSSFRow row1 = sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(ls.getCar().getBrand1());
                row1.createCell(1).setCellValue(ls.getCar().getBrand2());
                row1.createCell(2).setCellValue(ls.getCar().getSeriesName());
                row1.createCell(3).setCellValue(ls.getCar().getPaiLiang());
                row1.createCell(4).setCellValue(ls.getCar().getYear());
                if (ls.getCar().getCarModel() != null && !ls.getCar().getCarModel().equals("")) {
                    row1.createCell(5).setCellValue(ls.getCar().getCarModel());
                } else {
                    row1.createCell(5).setCellValue("全适配");
                }
                row1.createCell(6).setCellValue(ls.getBaoyangName());
                row1.createCell(7).setCellValue(ls.getPJ_Name());
                row1.createCell(8).setCellValue(ls.getSuggestTip());
                row1.createCell(9).setCellValue(ls.getCar().getCarID());
                row1.createCell(10).setCellValue(ls.getPJ_ID());
                rowNum++;
            }
            //TODO 详情页面数据
            XSSFSheet sheet1 = wb.createSheet("商品详情页");
            XSSFRow row1 = sheet1.createRow(0);
            row1.createCell(0).setCellValue("产品编号");
            row1.createCell(1).setCellValue("产品名称");
            row1.createCell(2).setCellValue("一级分类");
            row1.createCell(3).setCellValue("二级分类");
            row1.createCell(4).setCellValue("三级分类");
            row1.createCell(5).setCellValue("所属品牌");
            row1.createCell(6).setCellValue("产品关键词");
            row1.createCell(7).setCellValue("计价单位");
            row1.createCell(8).setCellValue("是否可销售");
            row1.createCell(9).setCellValue("品牌");
            row1.createCell(10).setCellValue("重量");
            row1.createCell(11).setCellValue("颜色");
            row1.createCell(12).setCellValue("产地");
            row1.createCell(13).setCellValue("包装尺寸");
            row1.createCell(14).setCellValue("包装");
            row1.createCell(15).setCellValue("尺寸");
            row1.createCell(16).setCellValue("商品编号");
            row1.createCell(17).setCellValue("型号");
            row1.createCell(18).setCellValue("商品容量");
            row1.createCell(19).setCellValue("机油等级");
            row1.createCell(20).setCellValue("粘稠度");
            row1.createCell(21).setCellValue("适配发动机");
            row1.createCell(22).setCellValue("基础油级别");
            row1.createCell(23).setCellValue("幅宽");
            row1.createCell(24).setCellValue("折数");
            row1.createCell(25).setCellValue("骨架");
            row1.createCell(26).setCellValue("系列");
            row1.createCell(27).setCellValue("导流片");
            row1.createCell(28).setCellValue("刹车位置");
            row1.createCell(29).setCellValue("刹车类型");
            row1.createCell(30).setCellValue("形态");
            row1.createCell(30).setCellValue("功能");
            row1.createCell(31).setCellValue("sku编码");
            row1.createCell(32).setCellValue("sku名称");
            row1.createCell(33).setCellValue("商品卖点");
            row1.createCell(34).setCellValue("销售名称");
            row1.createCell(35).setCellValue("是否可销售");
            row1.createCell(36).setCellValue("参考价格");
            row1.createCell(37).setCellValue("销售价格");
            row1.createCell(38).setCellValue("产品尺寸");
            row1.createCell(39).setCellValue("产品颜色");

            int num = 1;
            List<CarPJDetail> carPJDetailList = pj.getCarPjDetailList();
            for (CarPJDetail ls : carPJDetailList) {
                XSSFRow row2 = sheet1.createRow(num);
                row2.createCell(0).setCellValue(ls.getCid());
                row2.createCell(1).setCellValue(ls.getName());
                row2.createCell(2).setCellValue(ls.getFirst());
                row2.createCell(3).setCellValue(ls.getSecond());
                row2.createCell(4).setCellValue(ls.getThird());
                row2.createCell(5).setCellValue(ls.getBrand());
                row2.createCell(6).setCellValue(ls.getKeyword());
                row2.createCell(7).setCellValue(ls.getVal_unit());
                row2.createCell(8).setCellValue(ls.getIsSale());
                row2.createCell(9).setCellValue(ls.getSpu_brand());
                row2.createCell(10).setCellValue(ls.getSpu_weight());
                row2.createCell(11).setCellValue(ls.getSpu_color());
                row2.createCell(12).setCellValue(ls.getSpu_place());
                row2.createCell(13).setCellValue(ls.getSpu_pakageSize());
                row2.createCell(14).setCellValue(ls.getSpu_pakage());
                row2.createCell(15).setCellValue(ls.getSpu_size());
                row2.createCell(16).setCellValue(ls.getSpu_code());
                row2.createCell(17).setCellValue(ls.getSpu_model());
                row2.createCell(18).setCellValue(ls.getSpu_vol());
                row2.createCell(19).setCellValue(ls.getSpu_jyGrade());
                row2.createCell(20).setCellValue(ls.getSpu_jyNian());
                row2.createCell(21).setCellValue(ls.getSpu_jyDong());
                row2.createCell(22).setCellValue(ls.getSpu_jyLevel());
                row2.createCell(23).setCellValue(ls.getSpu_fukuan());
                row2.createCell(24).setCellValue(ls.getSpu_zheshu());
                row2.createCell(25).setCellValue(ls.getSpu_gujia());
                row2.createCell(26).setCellValue(ls.getSpu_series());
                row2.createCell(27).setCellValue(ls.getSpu_daoliu());
                row2.createCell(28).setCellValue(ls.getSpu_brakeLocation());
                row2.createCell(29).setCellValue(ls.getSpu_brakeType());
                row2.createCell(30).setCellValue(ls.getSpu_xintai());
                row2.createCell(30).setCellValue(ls.getSpu_function());
                row2.createCell(31).setCellValue(ls.getSku_pid());
                row2.createCell(32).setCellValue(ls.getSku_name());
                row2.createCell(33).setCellValue(ls.getSku_salePoint());
                row2.createCell(34).setCellValue("热销");
                row2.createCell(35).setCellValue(ls.getSku_isSale());
                row2.createCell(36).setCellValue(ls.getSku_price());
                row2.createCell(37).setCellValue(ls.getPrice());
                row2.createCell(38).setCellValue(ls.getSkuSize());
                row2.createCell(39).setCellValue(ls.getSkuColor());
                num++;
            }
            FileOutputStream outputStream = new FileOutputStream(filepath);
            try {
                wb.write(outputStream);
                outputStream.flush();
                outputStream.close();
                logger.info("写入文件成功！" + file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
                file.delete();
                logger.error("创建文件" + filepath + "失败！" + e.getMessage(), e);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("创建文件" + filepath + "失败！" + e1.getMessage(), e1);
        }
    }

    public static void writeDataToExcel_BD(PJ pj, String filepath) throws IOException {
        try {
            File file = new File(filepath);
            InputStream fs = null;
            if (file.exists()) {
                logger.info(filepath + "已存在,开始合并");
                fs = new FileInputStream(filepath);

//                POIFSFileSystem ps = new POIFSFileSystem(fs);
                XSSFWorkbook wb = new XSSFWorkbook(fs);
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFSheet sheet1 = wb.getSheetAt(1);
                int rowNum = sheet.getLastRowNum() + 1;
                int num = sheet1.getLastRowNum() + 1;
                List<CarPJ> list = pj.getCarPJList();
                for (CarPJ ls : list) {

                    XSSFRow row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(ls.getCar().getBrand1());
                    row1.createCell(1).setCellValue(ls.getCar().getBrand2());
                    row1.createCell(2).setCellValue(ls.getCar().getSeriesName());
                    row1.createCell(3).setCellValue(ls.getCar().getPaiLiang());
                    row1.createCell(4).setCellValue(ls.getCar().getYear());
                    if (ls.getCar().getCarModel() != null && !ls.getCar().getCarModel().equals("")) {
                        row1.createCell(5).setCellValue(ls.getCar().getCarModel());
                    } else {
                        row1.createCell(5).setCellValue("全适配");
                    }
                    row1.createCell(6).setCellValue(ls.getBaoyangName());
                    row1.createCell(7).setCellValue(ls.getPJ_Name());
                    row1.createCell(8).setCellValue(ls.getSuggestTip());
                    row1.createCell(9).setCellValue(ls.getCar().getCarID());
                    row1.createCell(10).setCellValue(ls.getPJ_ID());
                    rowNum++;
                }
                //TODO 详情页面数据


                List<CarPJDetail> carPJDetailList = pj.getCarPjDetailList();
                for (CarPJDetail ls : carPJDetailList) {
                    XSSFRow row2 = sheet1.createRow(num);
                    row2.createCell(0).setCellValue(ls.getCid());
                    row2.createCell(1).setCellValue(ls.getName());
                    row2.createCell(2).setCellValue(ls.getFirst());
                    row2.createCell(3).setCellValue(ls.getSecond());
                    row2.createCell(4).setCellValue(ls.getThird());
                    row2.createCell(5).setCellValue(ls.getBrand());
                    row2.createCell(6).setCellValue(ls.getKeyword());
                    row2.createCell(7).setCellValue(ls.getVal_unit());
                    row2.createCell(8).setCellValue(ls.getIsSale());
                    row2.createCell(9).setCellValue(ls.getSpu_brand());
                    row2.createCell(10).setCellValue(ls.getSpu_weight());
                    row2.createCell(11).setCellValue(ls.getSpu_color());
                    row2.createCell(12).setCellValue(ls.getSpu_place());
                    row2.createCell(13).setCellValue(ls.getSpu_pakageSize());
                    row2.createCell(14).setCellValue(ls.getSpu_pakage());
                    row2.createCell(15).setCellValue(ls.getSpu_size());
                    row2.createCell(16).setCellValue(ls.getSpu_code());
                    row2.createCell(17).setCellValue(ls.getSpu_model());
                    row2.createCell(18).setCellValue(ls.getSpu_vol());
                    row2.createCell(19).setCellValue(ls.getSpu_jyGrade());
                    row2.createCell(20).setCellValue(ls.getSpu_jyNian());
                    row2.createCell(21).setCellValue(ls.getSpu_jyDong());
                    row2.createCell(22).setCellValue(ls.getSpu_jyLevel());
                    row2.createCell(23).setCellValue(ls.getSpu_fukuan());
                    row2.createCell(24).setCellValue(ls.getSpu_zheshu());
                    row2.createCell(25).setCellValue(ls.getSpu_gujia());
                    row2.createCell(26).setCellValue(ls.getSpu_series());
                    row2.createCell(27).setCellValue(ls.getSpu_daoliu());
                    row2.createCell(28).setCellValue(ls.getSpu_brakeLocation());
                    row2.createCell(29).setCellValue(ls.getSpu_brakeType());
                    row2.createCell(30).setCellValue(ls.getSpu_xintai());
                    row2.createCell(30).setCellValue(ls.getSpu_function());
                    row2.createCell(31).setCellValue(ls.getSku_pid());
                    row2.createCell(32).setCellValue(ls.getSku_name());
                    row2.createCell(33).setCellValue(ls.getSku_salePoint());
                    row2.createCell(34).setCellValue("热销");
                    row2.createCell(35).setCellValue(ls.getSku_isSale());
                    row2.createCell(36).setCellValue(ls.getSku_price());
                    row2.createCell(37).setCellValue(ls.getPrice());
                    row2.createCell(38).setCellValue(ls.getSkuSize());
                    row2.createCell(39).setCellValue(ls.getSkuColor());
                    num++;
                }
                FileOutputStream outputStream = new FileOutputStream(filepath);
                try {
                    wb.write(outputStream);
                    outputStream.flush();
                    outputStream.close();
                    fs.close();
                    logger.info("写入文件成功！" + file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    file.delete();
                    logger.error("创建文件" + filepath + "失败！" + e.getMessage(), e);
                }

            } else {
                //创建目标文件
                try {
                    if (file.createNewFile()) {

                    } else {
                        logger.error("创建单个文件" + filepath + "失败！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("创建单个文件" + filepath + "失败！" + e.getMessage(), e);
                    return;
                }
                if (!file.getParentFile().exists()) {
                    //如果目标文件所在的目录不存在，则创建父目录
                    if (!file.getParentFile().mkdirs()) {
                        return;
                    }
                }


                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("配件关系");
                sheet.setDefaultRowHeightInPoints(20);
                XSSFRow row = sheet.createRow(0);
                for (int i = 0; i < 11; i++) {
                    XSSFCell cell = row.createCell(i);
                    if (i < 3) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 10);
                    } else if (i < 5) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 6);
                    } else if (i < 6) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 30);
                    } else if (i < 7) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 18);

                    } else if (i < 8) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 80);

                    } else if (i < 9) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 20);

                    } else if (i < 10) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 15);

                    } else {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 30);
                    }

                }
                row.createCell(0).setCellValue("一级品牌名称");
                row.createCell(1).setCellValue("二级品牌名称");
                row.createCell(2).setCellValue("车系名称");
                row.createCell(3).setCellValue("车排量");
                row.createCell(4).setCellValue("车年份");
                row.createCell(5).setCellValue("适配车型");
                row.createCell(6).setCellValue("保养项目");
                row.createCell(7).setCellValue("保养产品");
                row.createCell(8).setCellValue("建议使用频率");
                row.createCell(9).setCellValue("车型ID");
                row.createCell(10).setCellValue("保养产品ID");


                XSSFSheet sheet1 = wb.createSheet("商品详情页");
                XSSFRow rowt = sheet1.createRow(0);
                rowt.createCell(0).setCellValue("产品编号");
                rowt.createCell(1).setCellValue("产品名称");
                rowt.createCell(2).setCellValue("一级分类");
                rowt.createCell(3).setCellValue("二级分类");
                rowt.createCell(4).setCellValue("三级分类");
                rowt.createCell(5).setCellValue("所属品牌");
                rowt.createCell(6).setCellValue("产品关键词");
                rowt.createCell(7).setCellValue("计价单位");
                rowt.createCell(8).setCellValue("是否可销售");
                rowt.createCell(9).setCellValue("品牌");
                rowt.createCell(10).setCellValue("重量");
                rowt.createCell(11).setCellValue("颜色");
                rowt.createCell(12).setCellValue("产地");
                rowt.createCell(13).setCellValue("包装尺寸");
                rowt.createCell(14).setCellValue("包装");
                rowt.createCell(15).setCellValue("尺寸");
                rowt.createCell(16).setCellValue("商品编号");
                rowt.createCell(17).setCellValue("型号");
                rowt.createCell(18).setCellValue("商品容量");
                rowt.createCell(19).setCellValue("机油等级");
                rowt.createCell(20).setCellValue("粘稠度");
                rowt.createCell(21).setCellValue("适配发动机");
                rowt.createCell(22).setCellValue("基础油级别");
                rowt.createCell(23).setCellValue("幅宽");
                rowt.createCell(24).setCellValue("折数");
                rowt.createCell(25).setCellValue("骨架");
                rowt.createCell(26).setCellValue("系列");
                rowt.createCell(27).setCellValue("导流片");
                rowt.createCell(28).setCellValue("刹车位置");
                rowt.createCell(29).setCellValue("刹车类型");
                rowt.createCell(30).setCellValue("形态");
                rowt.createCell(30).setCellValue("功能");
                rowt.createCell(31).setCellValue("sku编码");
                rowt.createCell(32).setCellValue("sku名称");
                rowt.createCell(33).setCellValue("商品卖点");
                rowt.createCell(34).setCellValue("销售名称");
                rowt.createCell(35).setCellValue("是否可销售");
                rowt.createCell(36).setCellValue("参考价格");
                rowt.createCell(37).setCellValue("销售价格");
                rowt.createCell(38).setCellValue("产品尺寸");
                rowt.createCell(39).setCellValue("产品颜色");

                int rowNum = 1;
                int num = 1;

                List<CarPJ> list = pj.getCarPJList();
                for (CarPJ ls : list) {

                    XSSFRow row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(ls.getCar().getBrand1());
                    row1.createCell(1).setCellValue(ls.getCar().getBrand2());
                    row1.createCell(2).setCellValue(ls.getCar().getSeriesName());
                    row1.createCell(3).setCellValue(ls.getCar().getPaiLiang());
                    row1.createCell(4).setCellValue(ls.getCar().getYear());
                    if (ls.getCar().getCarModel() != null && !ls.getCar().getCarModel().equals("")) {
                        row1.createCell(5).setCellValue(ls.getCar().getCarModel());
                    } else {
                        row1.createCell(5).setCellValue("全适配");
                    }
                    row1.createCell(6).setCellValue(ls.getBaoyangName());
                    row1.createCell(7).setCellValue(ls.getPJ_Name());
                    row1.createCell(8).setCellValue(ls.getSuggestTip());
                    row1.createCell(9).setCellValue(ls.getCar().getCarID());
                    row1.createCell(10).setCellValue(ls.getPJ_ID());
                    rowNum++;
                }
                //TODO 详情页面数据


                List<CarPJDetail> carPJDetailList = pj.getCarPjDetailList();
                for (CarPJDetail ls : carPJDetailList) {
                    XSSFRow row2 = sheet1.createRow(num);
                    row2.createCell(0).setCellValue(ls.getCid());
                    row2.createCell(1).setCellValue(ls.getName());
                    row2.createCell(2).setCellValue(ls.getFirst());
                    row2.createCell(3).setCellValue(ls.getSecond());
                    row2.createCell(4).setCellValue(ls.getThird());
                    row2.createCell(5).setCellValue(ls.getBrand());
                    row2.createCell(6).setCellValue(ls.getKeyword());
                    row2.createCell(7).setCellValue(ls.getVal_unit());
                    row2.createCell(8).setCellValue(ls.getIsSale());
                    row2.createCell(9).setCellValue(ls.getSpu_brand());
                    row2.createCell(10).setCellValue(ls.getSpu_weight());
                    row2.createCell(11).setCellValue(ls.getSpu_color());
                    row2.createCell(12).setCellValue(ls.getSpu_place());
                    row2.createCell(13).setCellValue(ls.getSpu_pakageSize());
                    row2.createCell(14).setCellValue(ls.getSpu_pakage());
                    row2.createCell(15).setCellValue(ls.getSpu_size());
                    row2.createCell(16).setCellValue(ls.getSpu_code());
                    row2.createCell(17).setCellValue(ls.getSpu_model());
                    row2.createCell(18).setCellValue(ls.getSpu_vol());
                    row2.createCell(19).setCellValue(ls.getSpu_jyGrade());
                    row2.createCell(20).setCellValue(ls.getSpu_jyNian());
                    row2.createCell(21).setCellValue(ls.getSpu_jyDong());
                    row2.createCell(22).setCellValue(ls.getSpu_jyLevel());
                    row2.createCell(23).setCellValue(ls.getSpu_fukuan());
                    row2.createCell(24).setCellValue(ls.getSpu_zheshu());
                    row2.createCell(25).setCellValue(ls.getSpu_gujia());
                    row2.createCell(26).setCellValue(ls.getSpu_series());
                    row2.createCell(27).setCellValue(ls.getSpu_daoliu());
                    row2.createCell(28).setCellValue(ls.getSpu_brakeLocation());
                    row2.createCell(29).setCellValue(ls.getSpu_brakeType());
                    row2.createCell(30).setCellValue(ls.getSpu_xintai());
                    row2.createCell(30).setCellValue(ls.getSpu_function());
                    row2.createCell(31).setCellValue(ls.getSku_pid());
                    row2.createCell(32).setCellValue(ls.getSku_name());
                    row2.createCell(33).setCellValue(ls.getSku_salePoint());
                    row2.createCell(34).setCellValue("热销");
                    row2.createCell(35).setCellValue(ls.getSku_isSale());
                    row2.createCell(36).setCellValue(ls.getSku_price());
                    row2.createCell(37).setCellValue(ls.getPrice());
                    row2.createCell(38).setCellValue(ls.getSkuSize());
                    row2.createCell(39).setCellValue(ls.getSkuColor());
                    num++;
                }
                FileOutputStream outputStream = new FileOutputStream(filepath);
                try {
                    wb.write(outputStream);
                    outputStream.flush();
                    outputStream.close();
                    logger.info("写入文件成功！" + file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    file.delete();
                    logger.error("创建文件" + filepath + "失败！" + e.getMessage(), e);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("创建文件" + filepath + "失败！" + e1.getMessage(), e1);
        }
    }
    public static void writeDataToExcel_F1(PJ pj, String filepath) throws IOException {
        try {
            File file = new File(filepath);
            InputStream fs = null;
            if (file.exists()) {
                logger.info(filepath + "已存在,开始合并");
                fs = new FileInputStream(filepath);

                XSSFWorkbook wb = new XSSFWorkbook(fs);
                XSSFSheet sheet = wb.getSheetAt(0);
//                XSSFSheet sheet1 = wb.getSheetAt(1);
                int rowNum = sheet.getLastRowNum() + 1;
//                int num = sheet.getLastRowNum() + 1;
                List<CarPJ> list = pj.getCarPJList();
                for (CarPJ ls : list) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(ls.getCar().getBrand1());
                    sb.append(ls.getCar().getBrand2());
                    sb.append(ls.getCar().getSeriesName());
                    sb.append(ls.getCar().getPaiLiang());
                    sb.append(ls.getCar().getYear());
                    if (ls.getCar().getCarModel() != null && !ls.getCar().getCarModel().equals("")) {

                        sb.append(ls.getCar().getCarModel());
//                        System.out.println("xxx=:"+ls.getCar().getCarModel());
                    } else {
                        sb.append("全适配");
                    }

                    if(DataSingleton.INSTANCE.get(sb.toString())!=null){
//                        logger.info(sb.toString()+"重复");
                        continue;
                    }
                    DataSingleton.INSTANCE.put(sb.toString(),"1");
                    XSSFRow row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(ls.getCar().getBrand1());
                    row1.createCell(1).setCellValue(ls.getCar().getBrand2());
                    row1.createCell(2).setCellValue(ls.getCar().getSeriesName());
                    row1.createCell(3).setCellValue(ls.getCar().getPaiLiang());
                    row1.createCell(4).setCellValue(ls.getCar().getYear());
                    if (ls.getCar().getCarModel() != null && !ls.getCar().getCarModel().equals("")) {
                        row1.createCell(5).setCellValue(ls.getCar().getCarModel());
                    } else {
                        row1.createCell(5).setCellValue("全适配");
                    }
                    rowNum++;
                }

                FileOutputStream outputStream = new FileOutputStream(filepath);
                try {
                    wb.write(outputStream);
                    outputStream.flush();
                    outputStream.close();
                    fs.close();
                    logger.info("写入文件成功！" + file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    file.delete();
                    logger.error("创建文件" + filepath + "失败！" + e.getMessage(), e);
                }

            } else {
                //创建目标文件
                try {
                    if (file.createNewFile()) {

                    } else {
                        logger.error("创建单个文件" + filepath + "失败！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("创建单个文件" + filepath + "失败！" + e.getMessage(), e);
                    return;
                }
                if (!file.getParentFile().exists()) {
                    //如果目标文件所在的目录不存在，则创建父目录
                    if (!file.getParentFile().mkdirs()) {
                        return;
                    }
                }


                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("车型汇总");
                sheet.setDefaultRowHeightInPoints(20);
                XSSFRow row = sheet.createRow(0);
                for (int i = 0; i < 11; i++) {
                    XSSFCell cell = row.createCell(i);
                    if (i < 3) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 10);
                    } else if (i < 5) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 6);
                    } else if (i < 6) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 30);
                    } else if (i < 7) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 18);

                    } else if (i < 8) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 80);

                    } else if (i < 9) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 20);

                    } else if (i < 10) {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 15);

                    } else {
                        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 30);
                    }

                }
                row.createCell(0).setCellValue("一级品牌名称");
                row.createCell(1).setCellValue("二级品牌名称");
                row.createCell(2).setCellValue("车系名称");
                row.createCell(3).setCellValue("车排量");
                row.createCell(4).setCellValue("车年份");
                row.createCell(5).setCellValue("适配车型");


//
                int rowNum = 1;

                List<CarPJ> list = pj.getCarPJList();
                for (CarPJ ls : list) {

                    StringBuffer sb = new StringBuffer();
                    sb.append(ls.getCar().getBrand1());
                    sb.append(ls.getCar().getBrand2());
                    sb.append(ls.getCar().getSeriesName());
                    sb.append(ls.getCar().getPaiLiang());
                    sb.append(ls.getCar().getYear());
                    if (ls.getCar().getCarModel() != null && !ls.getCar().getCarModel().equals("")) {
                        sb.append(ls.getCar().getCarModel());
                    } else {
                        sb.append("全适配");
                    }

                    if(DataSingleton.INSTANCE.get(sb.toString())!=null){
//                        logger.info(sb.toString()+"重复");
                        continue;
                    }
                    DataSingleton.INSTANCE.put(sb.toString(),"1");

                    XSSFRow row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(ls.getCar().getBrand1());
                    row1.createCell(1).setCellValue(ls.getCar().getBrand2());
                    row1.createCell(2).setCellValue(ls.getCar().getSeriesName());
                    row1.createCell(3).setCellValue(ls.getCar().getPaiLiang());
                    row1.createCell(4).setCellValue(ls.getCar().getYear());
                    if (ls.getCar().getCarModel() != null && !ls.getCar().getCarModel().equals("")) {
                        row1.createCell(5).setCellValue(ls.getCar().getCarModel());
                    } else {
                        row1.createCell(5).setCellValue("全适配");
                    }

                    rowNum++;
                }
                //TODO 详情页面数据
                FileOutputStream outputStream = new FileOutputStream(filepath);
                try {
                    wb.write(outputStream);
                    outputStream.flush();
                    outputStream.close();
                    logger.info("写入文件成功！" + file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    file.delete();
                    logger.error("创建文件" + filepath + "失败！" + e.getMessage(), e);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("创建文件" + filepath + "失败！" + e1.getMessage(), e1);
        }
    }
    public static void writeDataToExcel_F2(PJ pj, String filepath) throws IOException {
        try {
            File file = new File(filepath);
            InputStream fs = null;
            if (file.exists()) {
                logger.info(filepath + "已存在,开始合并");
                fs = new FileInputStream(filepath);

//                POIFSFileSystem ps = new POIFSFileSystem(fs);
                XSSFWorkbook wb = new XSSFWorkbook(fs);
//                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFSheet sheet1 = wb.getSheetAt(0);
//                int rowNum = sheet.getLastRowNum() + 1;
                int num = sheet1.getLastRowNum() + 1;


                List<CarPJDetail> carPJDetailList = pj.getCarPjDetailList();
                for (CarPJDetail ls : carPJDetailList) {
                    if(DataSingleton.INSTANCE.get(ls.getSku_pid())!=null){
                        continue;
                    }
                    DataSingleton.INSTANCE.put(ls.getSku_pid(),"2");
                    XSSFRow row2 = sheet1.createRow(num);
                    row2.createCell(0).setCellValue(ls.getCid());
                    row2.createCell(1).setCellValue(ls.getName());
                    row2.createCell(2).setCellValue(ls.getFirst());
                    row2.createCell(3).setCellValue(ls.getSecond());
                    row2.createCell(4).setCellValue(ls.getThird());
                    row2.createCell(5).setCellValue(ls.getBrand());
                    row2.createCell(6).setCellValue(ls.getKeyword());
                    row2.createCell(7).setCellValue(ls.getVal_unit());
                    row2.createCell(8).setCellValue(ls.getIsSale());
                    row2.createCell(9).setCellValue(ls.getSpu_brand());
                    row2.createCell(10).setCellValue(ls.getSpu_weight());
                    row2.createCell(11).setCellValue(ls.getSpu_color());
                    row2.createCell(12).setCellValue(ls.getSpu_place());
                    row2.createCell(13).setCellValue(ls.getSpu_pakageSize());
                    row2.createCell(14).setCellValue(ls.getSpu_pakage());
                    row2.createCell(15).setCellValue(ls.getSpu_size());
                    row2.createCell(16).setCellValue(ls.getSpu_code());
                    row2.createCell(17).setCellValue(ls.getSpu_model());
                    row2.createCell(18).setCellValue(ls.getSpu_vol());
                    row2.createCell(19).setCellValue(ls.getSpu_jyGrade());
                    row2.createCell(20).setCellValue(ls.getSpu_jyNian());
                    row2.createCell(21).setCellValue(ls.getSpu_jyDong());
                    row2.createCell(22).setCellValue(ls.getSpu_jyLevel());
                    row2.createCell(23).setCellValue(ls.getSpu_fukuan());
                    row2.createCell(24).setCellValue(ls.getSpu_zheshu());
                    row2.createCell(25).setCellValue(ls.getSpu_gujia());
                    row2.createCell(26).setCellValue(ls.getSpu_series());
                    row2.createCell(27).setCellValue(ls.getSpu_daoliu());
                    row2.createCell(28).setCellValue(ls.getSpu_brakeLocation());
                    row2.createCell(29).setCellValue(ls.getSpu_brakeType());
                    row2.createCell(30).setCellValue(ls.getSpu_xintai());
                    row2.createCell(30).setCellValue(ls.getSpu_function());
                    row2.createCell(31).setCellValue(ls.getSku_pid());
                    row2.createCell(32).setCellValue(ls.getSku_name());
                    row2.createCell(33).setCellValue(ls.getSku_salePoint());
                    row2.createCell(34).setCellValue("热销");
                    row2.createCell(35).setCellValue(ls.getSku_isSale());
                    row2.createCell(36).setCellValue(ls.getSku_price());
                    row2.createCell(37).setCellValue(ls.getPrice());
                    row2.createCell(38).setCellValue(ls.getSkuSize());
                    row2.createCell(39).setCellValue(ls.getSkuColor());
                    num++;
                }
                FileOutputStream outputStream = new FileOutputStream(filepath);
                try {
                    wb.write(outputStream);
                    outputStream.flush();
                    outputStream.close();
                    fs.close();
                    logger.info("写入文件成功！" + file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    file.delete();
                    logger.error("创建文件" + filepath + "失败！" + e.getMessage(), e);
                }

            } else {
                //创建目标文件
                try {
                    if (file.createNewFile()) {

                    } else {
                        logger.error("创建单个文件" + filepath + "失败！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("创建单个文件" + filepath + "失败！" + e.getMessage(), e);
                    return;
                }
                if (!file.getParentFile().exists()) {
                    //如果目标文件所在的目录不存在，则创建父目录
                    if (!file.getParentFile().mkdirs()) {
                        return;
                    }
                }


                XSSFWorkbook wb = new XSSFWorkbook();

                XSSFSheet sheet1 = wb.createSheet("商品汇总页");
                XSSFRow rowt = sheet1.createRow(0);
                rowt.createCell(0).setCellValue("产品编号");
                rowt.createCell(1).setCellValue("产品名称");
                rowt.createCell(2).setCellValue("一级分类");
                rowt.createCell(3).setCellValue("二级分类");
                rowt.createCell(4).setCellValue("三级分类");
                rowt.createCell(5).setCellValue("所属品牌");
                rowt.createCell(6).setCellValue("产品关键词");
                rowt.createCell(7).setCellValue("计价单位");
                rowt.createCell(8).setCellValue("是否可销售");
                rowt.createCell(9).setCellValue("品牌");
                rowt.createCell(10).setCellValue("重量");
                rowt.createCell(11).setCellValue("颜色");
                rowt.createCell(12).setCellValue("产地");
                rowt.createCell(13).setCellValue("包装尺寸");
                rowt.createCell(14).setCellValue("包装");
                rowt.createCell(15).setCellValue("尺寸");
                rowt.createCell(16).setCellValue("商品编号");
                rowt.createCell(17).setCellValue("型号");
                rowt.createCell(18).setCellValue("商品容量");
                rowt.createCell(19).setCellValue("机油等级");
                rowt.createCell(20).setCellValue("粘稠度");
                rowt.createCell(21).setCellValue("适配发动机");
                rowt.createCell(22).setCellValue("基础油级别");
                rowt.createCell(23).setCellValue("幅宽");
                rowt.createCell(24).setCellValue("折数");
                rowt.createCell(25).setCellValue("骨架");
                rowt.createCell(26).setCellValue("系列");
                rowt.createCell(27).setCellValue("导流片");
                rowt.createCell(28).setCellValue("刹车位置");
                rowt.createCell(29).setCellValue("刹车类型");
                rowt.createCell(30).setCellValue("形态");
                rowt.createCell(30).setCellValue("功能");
                rowt.createCell(31).setCellValue("sku编码");
                rowt.createCell(32).setCellValue("sku名称");
                rowt.createCell(33).setCellValue("商品卖点");
                rowt.createCell(34).setCellValue("销售名称");
                rowt.createCell(35).setCellValue("是否可销售");
                rowt.createCell(36).setCellValue("参考价格");
                rowt.createCell(37).setCellValue("销售价格");
                rowt.createCell(38).setCellValue("产品尺寸");
                rowt.createCell(39).setCellValue("产品颜色");

//                int rowNum = 1;
                int num = 1;




                List<CarPJDetail> carPJDetailList = pj.getCarPjDetailList();
                for (CarPJDetail ls : carPJDetailList) {
                    if(DataSingleton.INSTANCE.get(ls.getSku_pid())!=null){
                        continue;
                    }
                    DataSingleton.INSTANCE.put(ls.getSku_pid(),"2");
                    XSSFRow row2 = sheet1.createRow(num);
                    row2.createCell(0).setCellValue(ls.getCid());
                    row2.createCell(1).setCellValue(ls.getName());
                    row2.createCell(2).setCellValue(ls.getFirst());
                    row2.createCell(3).setCellValue(ls.getSecond());
                    row2.createCell(4).setCellValue(ls.getThird());
                    row2.createCell(5).setCellValue(ls.getBrand());
                    row2.createCell(6).setCellValue(ls.getKeyword());
                    row2.createCell(7).setCellValue(ls.getVal_unit());
                    row2.createCell(8).setCellValue(ls.getIsSale());
                    row2.createCell(9).setCellValue(ls.getSpu_brand());
                    row2.createCell(10).setCellValue(ls.getSpu_weight());
                    row2.createCell(11).setCellValue(ls.getSpu_color());
                    row2.createCell(12).setCellValue(ls.getSpu_place());
                    row2.createCell(13).setCellValue(ls.getSpu_pakageSize());
                    row2.createCell(14).setCellValue(ls.getSpu_pakage());
                    row2.createCell(15).setCellValue(ls.getSpu_size());
                    row2.createCell(16).setCellValue(ls.getSpu_code());
                    row2.createCell(17).setCellValue(ls.getSpu_model());
                    row2.createCell(18).setCellValue(ls.getSpu_vol());
                    row2.createCell(19).setCellValue(ls.getSpu_jyGrade());
                    row2.createCell(20).setCellValue(ls.getSpu_jyNian());
                    row2.createCell(21).setCellValue(ls.getSpu_jyDong());
                    row2.createCell(22).setCellValue(ls.getSpu_jyLevel());
                    row2.createCell(23).setCellValue(ls.getSpu_fukuan());
                    row2.createCell(24).setCellValue(ls.getSpu_zheshu());
                    row2.createCell(25).setCellValue(ls.getSpu_gujia());
                    row2.createCell(26).setCellValue(ls.getSpu_series());
                    row2.createCell(27).setCellValue(ls.getSpu_daoliu());
                    row2.createCell(28).setCellValue(ls.getSpu_brakeLocation());
                    row2.createCell(29).setCellValue(ls.getSpu_brakeType());
                    row2.createCell(30).setCellValue(ls.getSpu_xintai());
                    row2.createCell(30).setCellValue(ls.getSpu_function());
                    row2.createCell(31).setCellValue(ls.getSku_pid());
                    row2.createCell(32).setCellValue(ls.getSku_name());
                    row2.createCell(33).setCellValue(ls.getSku_salePoint());
                    row2.createCell(34).setCellValue("热销");
                    row2.createCell(35).setCellValue(ls.getSku_isSale());
                    row2.createCell(36).setCellValue(ls.getSku_price());
                    row2.createCell(37).setCellValue(ls.getPrice());
                    row2.createCell(38).setCellValue(ls.getSkuSize());
                    row2.createCell(39).setCellValue(ls.getSkuColor());
                    num++;
                }
                FileOutputStream outputStream = new FileOutputStream(filepath);
                try {
                    wb.write(outputStream);
                    outputStream.flush();
                    outputStream.close();
                    logger.info("写入文件成功！" + file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    file.delete();
                    logger.error("创建文件" + filepath + "失败！" + e.getMessage(), e);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("创建文件" + filepath + "失败！" + e1.getMessage(), e1);
        }
    }



}
