package com.transport.util;
/**
 * # Writing RestClient. 
 * Technology:
 * - HttpClient i.e commons-httpclient-3.1.jar
 *	http://hc.apache.org/httpclient-3.x/
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.util.HttpURLConnection;

public class HttpClientHandlerImpl implements HttpClientHandlerV3 {
	
	private String className = this.getClass().getName();

	/** setter and getter */
	private HttpClient client;
    private int timeout = 15000;
    private int socketTimeOut = 15000;
   
    public void initialize() {
        MultiThreadedHttpConnectionManager connectionManager=new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(getTimeout());
        params.setSoTimeout(getSocketTimeOut());
        connectionManager.setParams(params);
        setClient(new HttpClient(connectionManager));
    }
   
    /**
     * 
     */
    @Override
    public String doGet(String url) throws IOException {
        HttpMethod getMethod = new GetMethod(url);
        getMethod.addRequestHeader("Accept", "application/json");
        BufferedReader bufferedReader = null;
        try {
            int returnCode = getClient().executeMethod(getMethod);
            if (HttpURLConnection.HTTP_OK != returnCode) {
                System.out.println(className
                        + ": connection error code={0}"+
                        returnCode);
                throw new Exception(
                        "An error connecting to the service, code: "
                                + returnCode);
            }
            System.out.println("Response success with response code: {0}"+returnCode);
            bufferedReader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(),"ISO-8859-1"));
            StringBuilder buffer = new StringBuilder();
            String aLine = bufferedReader.readLine();
            while(aLine!=null){
                buffer.append(aLine);
                aLine = bufferedReader.readLine();
            }
            return buffer.toString();
        } catch (Exception exc) {
            System.out.println(className + ": error={0}"+ exc);
        }finally{
            if(getMethod!=null)
                getMethod.releaseConnection();
            if(bufferedReader!=null)
                bufferedReader.close();
        }
        return null;
    }
    
    /** setter and getter */
    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    protected HttpClient getClient() {
        return client;
    }

    protected void setClient(HttpClient client) {
        this.client = client;
    }
    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
