package com.util;

import com.domain.CarPJ;
import com.domain.CarPJDetail;
import com.sun.org.apache.xml.internal.serialize.ElementState;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hwc on 2016/9/20.
 */
public class PraseProductDetail {
    private static List<String> PidList=new ArrayList<>();//存放唯一的pid
    private static List<CarPJDetail> carPJDetailList=new ArrayList<>();
    private static int num=0;//控制递归结束条件

    public static List<CarPJDetail> getCarPJDetailList(){
        return carPJDetailList;
    }

    public static void praseAllItem(List<CarPJ> carPJList) throws IOException {
        for(CarPJ carPJ:carPJList){
            if(!PidList.contains(carPJ.getUrl())){
             CarPJDetail carPJDetail=praseOneItemDetail(carPJ.getUrl());
                num=carPJDetail.getUrlList().size();
                while (num>0){
                    CarPJDetail carPJDetail1=praseOneItemDetail(carPJDetail.getUrlList().get(0));
                    if(carPJDetail1!=null){
                        num=carPJDetail1.getUrlList().size();
                    }else {
                        num=0;
                    }

                }
            }
        }
    }

    /**
     *  获取产品详情页面
     */
    public static CarPJDetail praseOneItemDetail(String url) throws IOException {
        if(!PidList.contains(url)){

            String urlString="http://item.tuhu.cn/Products/"+url+".html";
            //TODO 获取产品详情页面
            Document document= Jsoup.connect(urlString).timeout(60000).get();
            CarPJDetail cd=new CarPJDetail();
            //TODO 产品ID
            String type[]=url.split("-");
            String cid=type[0]+"-"+type[1];
            cd.setCid(cid);
            //TODO 抓取产品名称
            Element DisplayName=document.getElementsByClass("DisplayName").get(0);
            cd.setName(DisplayName.text());
            cd.setSku_name(DisplayName.text());
            //TODO 未抓取列：SKU名称、产品详情、产品颜色、是否可销售、以及产品颜色
            //TODO 抓取标题
            Elements titles=document.getElementsByClass("bread_navi").get(0).getElementsByTag("a");
            cd.setFirst(titles.get(1).text());
            cd.setSecond(titles.get(2).text());
            cd.setThird(titles.get(3).text());
            //TODO 价格
            Element price=document.getElementsByClass("price").get(0);
            cd.setPrice(price.child(0).text());
            cd.setSku_price(price.child(0).text());
            //TODO 商品卖点
            Element small_tit=document.getElementsByClass("small_tit").get(0);
            cd.setSku_salePoint(small_tit.text());

            //TODO pid 商品ID
            cd.setSku_pid(url);
            //TODO SPU 属性
            Elements spu=document.getElementsByClass("properties").get(0).getElementsByTag("li");
            String brand=spu.get(0).ownText();
            String b[]=brand.split("/");
            cd.setBrand(b[0]);
            cd.setKeyword(cd.getThird()+" "+b[0]);
            cd.setSpu_brand(brand);
            String jijia="";//计价单位
            for(int i=1;i<spu.size();i++){
                //TODO 获取属性名
                String at=spu.get(i).child(0).text().split("：")[0];
                //TODO 获取属性值
                String ats=spu.get(i).ownText();
                if(at.equals("重量")){
                    cd.setSpu_weight(ats);
                }else if(at.equals("颜色")){
                    cd.setSpu_color(ats);
                }else if(at.equals("产地")){
                    cd.setSpu_place(ats);
                }else if(at.equals("包装尺寸")){
                    cd.setSpu_pakageSize(ats);
                    jijia=ats;
                }else if(at.equals("包装")){
                    cd.setSpu_pakage(ats);
                    jijia=ats;
                }else if(at.equals("尺寸")){
                    cd.setSpu_size(ats);
                    jijia=ats;
                }else if(at.equals("商品编号")){
                    cd.setSpu_code(ats);
                }else if(at.equals("型号")){
                    cd.setSpu_model(ats);
                }else if(at.equals("商品容量")){
                    cd.setSpu_vol(ats);
                }else if(at.equals("机油等级")){
                    cd.setSpu_jyGrade(ats);
                }else if(at.equals("粘稠度")){
                    cd.setSpu_jyNian(ats);
                }else if(at.equals("适配发动机")){
                    cd.setSpu_jyDong(ats);
                }else if(at.equals("基础油级别")){
                    cd.setSpu_jyLevel(ats);
                }else if(at.equals("幅宽")){
                    cd.setSpu_fukuan(ats);
                }else if(at.equals("折数")){
                    cd.setSpu_zheshu(ats);
                }else if(at.equals("骨架")){
                    cd.setSpu_gujia(ats);
                }else if(at.equals("系列")){
                    cd.setSpu_series(ats);
                }else if(at.equals("导流片")){
                    cd.setSpu_daoliu(ats);
                }else if(at.equals("刹车位置")){
                    cd.setSpu_brakeLocation(ats);
                }else if(at.equals("刹车类型")){
                    cd.setSpu_brakeType(ats);
                }else if(at.equals("形态")){
                    cd.setSpu_xintai(ats);
                }else if(at.equals("功能")){
                    cd.setSpu_function(ats);
                }
            }
            cd.setVal_unit(jijia);

            //TODO 抓取big img
            Elements images=document.getElementsByClass("images").get(0).children();
            List<String> bigImg=new ArrayList<>();
            for(int i=0;i<images.size();i++){
                bigImg.add(images.get(i).attr("data-src"));
            }
            cd.setBigImg(bigImg);

            //TODO 抓取detail img
            Elements description=document.getElementsByClass("description").get(0).getElementsByTag("img");
            List<String> detailImg=new ArrayList<>();
            for(int j=1;j<description.size();j++){
                detailImg.add(description.get(j).attr("src"));
            }
            cd.setDetailImg(detailImg);
            PidList.add(url);

            List<String> urlList=new ArrayList<>();
            //TODO 获取产品颜色列表URL
            Elements category=document.getElementsByClass("category");
            if(category.size()>0){
                Elements c=category.get(0).children();
                Element color=category.get(0).getElementsByClass("current").first().getElementsByTag("img").first();
                cd.setSkuColor(color.attr("alt"));
                for(int l=0;l<c.size();l++){
                    String cc=c.get(l).attr("href");
                    if(!PidList.contains(cc.substring(29,cc.length()-5))){
                        urlList.add(cc.substring(29,cc.length()-5));
                    }
                }
            }
            //TODO 获取产品尺寸列表URL
            Elements unit=document.getElementsByClass("unit");
            if(unit.size()>0){
                Elements u=unit.get(0).children();
                //TODO 获取产品尺寸
                String size=unit.first().getElementsByClass("current").get(0).ownText();
                cd.setSkuSize(size);
                for(int k=0;k<u.size();k++){
                    String uu=u.get(k).attr("href");
                    if(!PidList.contains(uu.substring(29,uu.length()-5))){
                        urlList.add(uu.substring(29,uu.length()-5));
                    }
                }
            }
            cd.setUrlList(urlList);
            carPJDetailList.add(cd);
            return cd;
        }
        return null;
    }

}
