package com.util;

import com.domain.CarPJDetail;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class GetImage {
    public static Logger logger = Logger.getLogger(GetImage.class);

    /**
     * 测试
     */

    public static void getImgData(List<CarPJDetail> carPJDetailList) throws IOException {

        for (CarPJDetail car : carPJDetailList) {
            String pid = car.getSku_pid().replace("/", "-");
            for (int i = 0; i < car.getBigImg().size(); i++) {
                String fileName = "C:\\tuhu\\picture\\" + car.getCid() + "\\" + pid + "\\big\\" + pid + "_" + (i + 1) + ".jpg";
                File file=IsPathExist(fileName);
                if(file!=null){
                    byte[] btImg = getImageFromNetByUrl(car.getBigImg().get(i));
                    if (null != btImg && btImg.length > 0) {
                        writeImageToDisk(btImg, file);
                    } else {
                        logger.error("图片获取失败：" + car.getBigImg().get(i) + "——" + pid);
                    }
                }else {
                    logger.debug("该图片已存在");

                }

            }
            for (int j = 0; j < car.getDetailImg().size(); j++) {
                String fileName = "C:\\tuhu\\picture\\" + car.getCid() + "\\" + pid + "\\detail\\" + pid + "_" + (j + 1) + ".jpg";
                File file=IsPathExist(fileName);
                if(file !=null){
                    byte[] btImg = getImageFromNetByUrl(car.getDetailImg().get(j));
                    if (null != btImg && btImg.length > 0) {
                        writeImageToDisk(btImg, file);
                    } else {
                        logger.error("图片获取失败：" + car.getDetailImg().get(j) + "——" + pid);
                    }
                }else {
                    logger.debug("该图片已存在");

                }

            }
        }
    }

    public static File IsPathExist(String destFileName){
        try {
            File file = new File(destFileName);
            if (file.exists()) {
                return null;
            }
            if (destFileName.endsWith(File.separator)) {
                return null;
            }
            //判断目标文件所在的目录是否存在
            if (!file.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                if (!file.getParentFile().mkdirs()) {
                    return null;
                }
            }
            //创建目标文件
            try {
                if (file.createNewFile()) {

                } else {
                    logger.error("创建单个文件" + destFileName + "失败！");
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("创建单个文件" + destFileName + "失败！" + e.getMessage());
                return null;
            }
            return file;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断该图片是否存在
     *
     * @param img 图片数据流
     */
    public static void writeImageToDisk(byte[] img, File file) {
        try {
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
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(toUtf8String(strUrl));
            CloseableHttpResponse response = client.execute(httpGet);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    InputStream inputStream = response.getEntity().getContent();
                    byte[] btImg = readInputStream(inputStream);//得到图片的二进制数据
                    inputStream.close();
                    return btImg;
                }
//                else {
//                    System.out.println(response.getStatusLine().getStatusCode());
//                }
            }finally {
                response.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.close();
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
                } else {
                    sb.append(c);
                }
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    logger.error(ex);
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

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
