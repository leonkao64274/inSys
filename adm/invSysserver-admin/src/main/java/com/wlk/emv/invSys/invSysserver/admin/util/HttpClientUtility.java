/*
 * @(#)HttpClientUtility.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 HttpClient 工具類別
 *
 * Modify History:
 * v1.00,   /01/11, LeonKao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
*
* @author LeonKao
*/
public class HttpClientUtility {
   
   public static ClientHttpRequestFactory getClientHttpRequestFactory() {
       //int timeout = 30 * 1000;
       int timeout = 5 * 1000;
       RequestConfig config = RequestConfig.custom()
               .setConnectTimeout(timeout)
               .setConnectionRequestTimeout(timeout)
               .setSocketTimeout(timeout)
               .build();
       
       CloseableHttpClient client = HttpClientBuilder
               .create()
               .setDefaultRequestConfig(config)
               .build();
       
       HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
       requestFactory.setConnectTimeout(timeout);
       //requestFactory.setConnectionRequestTimeout(timeout);
       requestFactory.setReadTimeout(timeout);
       //return new HttpComponentsClientHttpRequestFactory(client);
       return requestFactory;
   }
   
}
