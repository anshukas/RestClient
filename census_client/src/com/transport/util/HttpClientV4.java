package com.transport.util;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientV4 {
	
    protected HttpClient createHttpClient() { 
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(); 
        cm.setMaxTotal(128); 
        cm.setDefaultMaxPerRoute(24); 
        RequestConfig.Builder requestBuilder = RequestConfig.custom(); 
        requestBuilder = requestBuilder.setConnectTimeout(5000); 
        requestBuilder = requestBuilder.setSocketTimeout(5000); 
 
        HttpClientBuilder builder = HttpClientBuilder.create(); 
        builder.setDefaultRequestConfig(requestBuilder.build()); 
        builder.setConnectionManager(cm); 
 
        return builder.build(); 
    }
	
	public String doGet(String url){
		
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("Accept", "application/json");
		
		try {
			HttpResponse response = createHttpClient().execute(httpget);
			int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
