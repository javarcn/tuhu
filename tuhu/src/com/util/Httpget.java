package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class Httpget {
	
	public static String getHtml(String url) throws IOException{
		String html=null;
		CloseableHttpClient client=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
		CloseableHttpResponse response=client.execute(httpGet);
		try {
			int statuscode=response.getStatusLine().getStatusCode();
			if(statuscode==HttpStatus.SC_OK){
				HttpEntity httpEntity=response.getEntity();
				if(httpEntity!=null){
					html=EntityUtils.toString(httpEntity,Charset.forName("utf-8"));
				}
			}
		} catch (Exception e) {
			System.out.println("访问出现"+url+"异常");
			e.printStackTrace();
		}finally {
			response.close();
		}
		return html;
	}

}
