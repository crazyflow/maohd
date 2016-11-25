package com.cbecs.workflow.utility;

import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Utility
{
    
    
    private static final Logger logger = LoggerFactory.getLogger(Utility.class);
    
    public static String HttpPost(String posturl, StringEntity entity)
    {        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String str = "";
        HttpPost method = new HttpPost(posturl);
        method.setEntity(entity);
        try {
            HttpResponse result = httpClient.execute(method);
            posturl = URLDecoder.decode(posturl, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                try {
                    str = EntityUtils.toString(result.getEntity());
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + posturl, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + posturl, e);
        }
        return str;
    }
}
