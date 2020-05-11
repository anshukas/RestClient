package com.transport.driver;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.transport.util.HttpURLConnectionClient;

public class Demo {
	public static void main(String[] args) throws IOException {
		Demo obj = new Demo();
		System.out.println(obj.callWebAPI());
	}

	private static final String WEB_API_ENDPOINT = "https://api.thecatapi.com/v1/votes";
	public String callWebAPI() throws IOException {

		final String postJson = "{\r\n" + 
				"  \"image_id\": \"MTg3ODYyOQ\",\r\n" + 
				"  \"sub_id\": \"my-user-1234\",\r\n" + 
				"  \"value\": 1\r\n" + 
				"}";
		
		final Map<String, String> httpHeaders = new LinkedHashMap<String, String>();
		httpHeaders.put("Content-Type", "application/xml");
		httpHeaders.put("x-api-key", "DEMO-API-KEY");
		
		HttpURLConnectionClient client = new HttpURLConnectionClient();
		final String resultStr = client.doPost(WEB_API_ENDPOINT, "UTF-8", httpHeaders, postJson,0);
		
		return resultStr;
	}
}
