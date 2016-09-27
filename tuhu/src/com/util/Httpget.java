package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

public class Httpget {
//    public static Logger logger=Logger.getLogger(Httpget.class);
	
	public static String getHtml(String url) throws IOException{
        String html=null;
        int retryTimes=1;
        do {
            CloseableHttpClient client=HttpClients.createDefault();
            try {
                HttpGet httpGet=new HttpGet(url.trim());
                CloseableHttpResponse response=client.execute(httpGet);
                try {
                    int statuscode=response.getStatusLine().getStatusCode();
                    if(statuscode==HttpStatus.SC_OK){
                        HttpEntity httpEntity=response.getEntity();
                        if(httpEntity!=null){
                            html=EntityUtils.toString(httpEntity,Charset.forName("utf-8"));
                            return html;
                        }
                    }
                }finally {
                    response.close();
                }

            } catch (Exception e) {
//                logger.debug("访问出现"+url+"异常,1S后进行第"+retryTimes+"次重试！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                client.close();
            }
        }while (++retryTimes<6);
        throw new RuntimeException("服务端异常，超出重试次数!");
    }
}
