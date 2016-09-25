package com.util;

/**
 * Created by hwc on 2016/9/20.
 */
public class HttpPost {

    public static String post(){

        /*
        //TODO post请求数据方法1：jsoup
        Document document= Jsoup.connect(url).timeout(6000).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36").ignoreContentType(true).data(data).post();
        System.out.println(document);

        */
        /*  //TODO post请求数据方法2： HttpPost
        CloseableHttpClient httpClient= HttpClients.createDefault();
        try {
            HttpPost httpPost=new HttpPost(url);
            // 创建参数队列
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            formParams.add(new BasicNameValuePair("vehicle", vehicle));
            formParams.add(new BasicNameValuePair("baoyangType", baoyangType));
            formParams.add(new BasicNameValuePair("pid", pid));
            UrlEncodedFormEntity formEntity;
            formEntity=new UrlEncodedFormEntity(formParams,"utf-8");
            httpPost.setEntity(formEntity);
            CloseableHttpResponse response=httpClient.execute(httpPost);
            try {
                int statusCode=response.getStatusLine().getStatusCode();
                if(statusCode== HttpStatus.SC_OK){
                   HttpEntity httpEntity1=response.getEntity();
                    String json= EntityUtils.toString(httpEntity1,Charset.forName("utf-8"));
                    System.out.println(json);
                }
            }finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }*/
        return null;
    }


}
