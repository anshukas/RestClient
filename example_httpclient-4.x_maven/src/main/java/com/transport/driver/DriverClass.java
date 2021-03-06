package com.transport.driver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.transport.util.HttpClientV4;


/**
 * This is the driver class. Everything start from here.
 * 
 * @author anshu.kumar
 *
 */
public class DriverClass {
	public static void main(String[] args){
		DriverClass dc = new DriverClass();
		
		Properties pro = dc.readDataFromPropertiesFile("config.properties");
		
		// Transport layer or GET call
		HttpClientV4 httpClientHandler = new HttpClientV4();
		
		String response = httpClientHandler.doGet(pro.getProperty("endpoint"));		// TODO: Make zipcode as input from properties
		System.out.println(response);		
	}
	
	/**
	 * Reading the config.properties file
	 * @param fileName - config.properties 
	 * @return - prop
	 */
	public Properties readDataFromPropertiesFile(String fileName){
    	Properties prop = new Properties();
    	InputStream input = null;

    	try {
    		input = getClass().getResourceAsStream(fileName);
    		
    		// load a properties file
    		prop.load(input);

    		// get the property value and print it out
    		System.out.println(prop.getProperty("say"));
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
		return prop;
    }
}
