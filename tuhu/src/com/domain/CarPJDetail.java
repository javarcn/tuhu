package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/19.
 */
public class CarPJDetail {
    private String cid;
    private String name;//产品名称
    private String first;
    private String second;
    private String third;
    private String brand;//所属品牌
    private String keyword;//产品关键词
    private String val_unit;//计价单位
    private String isSale;//是否可销售
    private String detail;//产品详情
    //SPU属性
    private String spu_brand;//品牌
    private String spu_weight;//重量
    private String spu_color;//颜色
    private String spu_place;//产地
    private String spu_pakageSize;//包装尺寸
    private String spu_pakage;//包装
    private String spu_size;//尺寸
    private String spu_code;//商品编号
    private String spu_model;//型号
    private String spu_vol;//商品容量
    private String spu_jyGrade;//机油等级
    private String spu_jyNian;//粘稠度
    private String spu_jyDong;//适配发动机
    private String spu_jyLevel;//基础油级别
    private String spu_fukuan;//幅宽
    private String spu_zheshu;//折数
    private String spu_gujia;//骨架
    private String spu_series;//系列
    private String spu_daoliu;//导流片
    private String spu_brakeLocation;//刹车位置
    private String spu_brakeType;//刹车类型
    private String spu_xintai;//形态
    private String spu_function;//功能
    //SKU属性
    private String sku_pid;//sku编码
    private String sku_name;//sku名称
    private String sku_salePoint;//商品卖点
    private String sku_saleName;//销售名称
    private String sku_isSale;//是否可销售
    private String sku_price;//参考价格
    private String price;//销售价格
    private String img;
    private String skuColor;//产品颜色
    private String skuSize;//产品尺寸
    private List<String> bigImg=new ArrayList<>();
    private List<String> detailImg=new ArrayList<>();
    private List<String> urlList=new ArrayList<>();

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> getBigImg() {
        return bigImg;
    }

    public void setBigImg(List<String> bigImg) {
        this.bigImg = bigImg;
    }

    public List<String> getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(List<String> detailImg) {
        this.detailImg = detailImg;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSku_pid() {
        return sku_pid;
    }

    public void setSku_pid(String sku_pid) {
        this.sku_pid = sku_pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getVal_unit() {
        return val_unit;
    }

    public void setVal_unit(String val_unit) {
        this.val_unit = val_unit;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpu_brand() {
        return spu_brand;
    }

    public void setSpu_brand(String spu_brand) {
        this.spu_brand = spu_brand;
    }

    public String getSpu_weight() {
        return spu_weight;
    }

    public void setSpu_weight(String spu_weight) {
        this.spu_weight = spu_weight;
    }

    public String getSpu_color() {
        return spu_color;
    }

    public void setSpu_color(String spu_color) {
        this.spu_color = spu_color;
    }

    public String getSpu_place() {
        return spu_place;
    }

    public void setSpu_place(String spu_place) {
        this.spu_place = spu_place;
    }

    public String getSpu_pakageSize() {
        return spu_pakageSize;
    }

    public void setSpu_pakageSize(String spu_pakageSize) {
        this.spu_pakageSize = spu_pakageSize;
    }

    public String getSpu_pakage() {
        return spu_pakage;
    }

    public void setSpu_pakage(String spu_pakage) {
        this.spu_pakage = spu_pakage;
    }

    public String getSpu_size() {
        return spu_size;
    }

    public void setSpu_size(String spu_size) {
        this.spu_size = spu_size;
    }

    public String getSpu_code() {
        return spu_code;
    }

    public void setSpu_code(String spu_code) {
        this.spu_code = spu_code;
    }

    public String getSpu_model() {
        return spu_model;
    }

    public void setSpu_model(String spu_model) {
        this.spu_model = spu_model;
    }

    public String getSpu_vol() {
        return spu_vol;
    }

    public void setSpu_vol(String spu_vol) {
        this.spu_vol = spu_vol;
    }

    public String getSpu_jyGrade() {
        return spu_jyGrade;
    }

    public void setSpu_jyGrade(String spu_jyGrade) {
        this.spu_jyGrade = spu_jyGrade;
    }

    public String getSpu_jyNian() {
        return spu_jyNian;
    }

    public void setSpu_jyNian(String spu_jyNian) {
        this.spu_jyNian = spu_jyNian;
    }

    public String getSpu_jyDong() {
        return spu_jyDong;
    }

    public void setSpu_jyDong(String spu_jyDong) {
        this.spu_jyDong = spu_jyDong;
    }

    public String getSpu_jyLevel() {
        return spu_jyLevel;
    }

    public void setSpu_jyLevel(String spu_jyLevel) {
        this.spu_jyLevel = spu_jyLevel;
    }

    public String getSpu_fukuan() {
        return spu_fukuan;
    }

    public void setSpu_fukuan(String spu_fukuan) {
        this.spu_fukuan = spu_fukuan;
    }

    public String getSpu_zheshu() {
        return spu_zheshu;
    }

    public void setSpu_zheshu(String spu_zheshu) {
        this.spu_zheshu = spu_zheshu;
    }

    public String getSpu_gujia() {
        return spu_gujia;
    }

    public void setSpu_gujia(String spu_gujia) {
        this.spu_gujia = spu_gujia;
    }

    public String getSpu_series() {
        return spu_series;
    }

    public void setSpu_series(String spu_series) {
        this.spu_series = spu_series;
    }

    public String getSpu_daoliu() {
        return spu_daoliu;
    }

    public void setSpu_daoliu(String spu_daoliu) {
        this.spu_daoliu = spu_daoliu;
    }

    public String getSpu_brakeLocation() {
        return spu_brakeLocation;
    }

    public void setSpu_brakeLocation(String spu_brakeLocation) {
        this.spu_brakeLocation = spu_brakeLocation;
    }

    public String getSpu_brakeType() {
        return spu_brakeType;
    }

    public void setSpu_brakeType(String spu_brakeType) {
        this.spu_brakeType = spu_brakeType;
    }

    public String getSpu_xintai() {
        return spu_xintai;
    }

    public void setSpu_xintai(String spu_xintai) {
        this.spu_xintai = spu_xintai;
    }

    public String getSpu_function() {
        return spu_function;
    }

    public void setSpu_function(String spu_function) {
        this.spu_function = spu_function;
    }


    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getSku_salePoint() {
        return sku_salePoint;
    }

    public void setSku_salePoint(String sku_salePoint) {
        this.sku_salePoint = sku_salePoint;
    }

    public String getSku_saleName() {
        return sku_saleName;
    }

    public void setSku_saleName(String sku_saleName) {
        this.sku_saleName = sku_saleName;
    }

    public String getSku_isSale() {
        return sku_isSale;
    }

    public void setSku_isSale(String sku_isSale) {
        this.sku_isSale = sku_isSale;
    }

    public String getSku_price() {
        return sku_price;
    }

    public void setSku_price(String sku_price) {
        this.sku_price = sku_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSkuColor() {
        return skuColor;
    }

    public void setSkuColor(String skuColor) {
        this.skuColor = skuColor;
    }

    public String getSkuSize() {
        return skuSize;
    }

    public void setSkuSize(String skuSize) {
        this.skuSize = skuSize;
    }
}
