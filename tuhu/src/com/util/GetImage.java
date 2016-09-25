package com.util;

import com.domain.CarPJDetail;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class GetImage {
    /**
     * 测试
     */

    public static void getImgData(List<CarPJDetail> carPJDetailList) {

        for(CarPJDetail car:carPJDetailList){
            String pid=car.getSku_pid().replace("/","-");
            for(int i=0;i<car.getBigImg().size();i++){
                byte[] btImg = getImageFromNetByUrl(car.getBigImg().get(i));
                if(null != btImg && btImg.length > 0){
                    String fileName ="D:\\tuhu\\"+car.getCid()+"\\"+pid+"\\big\\"+pid+"_"+(i+1)+".jpg";
                    writeImageToDisk(btImg,fileName);
                }else{
                    System.out.println("图片获取失败："+car.getBigImg().get(i)+"——"+pid);
                }
            }
            for(int j=0;j<car.getDetailImg().size();j++){
                byte[] btImg = getImageFromNetByUrl(car.getDetailImg().get(j));
                if(null != btImg && btImg.length > 0){
                    String fileName ="D:\\tuhu\\"+car.getCid()+"\\"+pid+"\\detail\\"+pid+"_"+(j+1)+".jpg";
                    writeImageToDisk(btImg,fileName);
                }else{
                    System.out.println("图片获取失败："+car.getDetailImg().get(j)+"——"+pid);
                }
            }
        }
    }
    /**
     * 将图片写入到磁盘
     * @param img 图片数据流
     */
    public static void writeImageToDisk(byte[] img,String destFileName){
        try {
                File file = new File(destFileName);
                if(file.exists()) {
                    return ;
                }
                if (destFileName.endsWith(File.separator)) {
                    return ;
                }
                //判断目标文件所在的目录是否存在
                if(!file.getParentFile().exists()) {
                    //如果目标文件所在的目录不存在，则创建父目录
                    if(!file.getParentFile().mkdirs()) {
                        return ;
                    }
                }
                //创建目标文件
                try {
                    if (file.createNewFile()) {

                    } else {
                        System.out.println("创建单个文件" + destFileName + "失败！");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
                    return ;
                }
                FileOutputStream fops = new FileOutputStream(file);
                fops.write(img);
                fops.flush();
                fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl){
        try {
//            String u = new String(strUrl.getBytes("utf-8"),"ISO-8859-1");
//            String s = Uri.encode(strUrl, "-![.:/,%?&=]");
/*            String s="";
            if(strUrl.startsWith("https")){
                s= strUrl.substring(8);

            }else {
                s = strUrl.substring(7);
            }
            System.out.println(s);
            String stringUrl= java.net.URLEncoder.encode(s,"utf-8");*/

            CloseableHttpClient client= HttpClients.createDefault();
            HttpGet httpGet=new HttpGet(toUtf8String(strUrl));
            CloseableHttpResponse response=client.execute(httpGet);

            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                InputStream inputStream= response.getEntity().getContent();
                byte[] btImg = readInputStream(inputStream);//得到图片的二进制数据
                inputStream.close();
                return btImg;
            }else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {

                if (Character.isWhitespace(c) == true) {
                    sb.append("+");
                }else {
                    sb.append(c);
                }
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
//    public static void main(String[] args){
//        String s = "http://image.tuhu.cn/Images/Products/1510/64150 介绍图.B38059A6.jpg";
//         byte img[] =getImageFromNetByUrl(s);
//        if(null !=img && img.length>0){
//            writeImageToDisk(img,"D:\\test.jpg");
//        }
//    }
    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}