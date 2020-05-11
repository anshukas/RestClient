package com.transport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * <h1>Call WebAPI using HttpURLConnection!</h1>
 * 
 * 	The HttpURLConnectionClient can be use to call restful service.
 * 
 * @author anshukansakar
 *
 */

public class HttpURLConnectionClient {
	
	/**
	 * This method is used to call WebAPI with default value of ConnectTimeout and ReadTimeout is zero.
	 * 
	 *  <p> i.e TIMEOUT_MILLIS = 0; </p> 
	 *  
	 * @param url - API URL.
	 * @param headers - List set in HTTP header.
	 * @return String
	 * @throws IOException
	 */
	public String doGet(String url, Map<String, String> headers) throws IOException {
		final int TIMEOUT_MILLIS = 0; // infinity.
		return doGet(url, headers, TIMEOUT_MILLIS);
	}
	
	/**
	 * This method is used to call WebAPI with default value of ConnectTimeout and ReadTimeout is zero. It also has default UTF-8 encoding.
	 * 
	 * <p> i.e TIMEOUT_MILLIS = 0; </p>
	 * <p> i.e encoding = UTF-8; </p>
	 * 
	 * @param url - API URL.
	 * @param headers - List set in HTTP header.
	 * @param jsonString - HTTP POST data in String.
	 * @return String
	 * @throws IOException
	 */
	public String doPost(String url, Map<String, String> headers, String jsonString)
			throws IOException {
		final int TIMEOUT_MILLIS = 0; // infinity.
		String encoding = "UTF-8";
		return doPost(url, encoding, headers, jsonString, TIMEOUT_MILLIS);
	}
	
	/**
	 * This method is used to call WebAPI.
	 * 
	 * @param url
	 * @param headers
	 * @param TIMEOUT_MILLIS
	 * @return
	 * @throws IOException
	 */
	public String doGet(String url, Map<String, String> headers, final int TIMEOUT_MILLIS) throws IOException {

		final StringBuffer sb = new StringBuffer("");

		HttpURLConnection httpConn = null;
		BufferedReader br = null;
		InputStream is = null;
		InputStreamReader isr = null;

		try {
			URL urlObj = new URL(url);
			httpConn = (HttpURLConnection) urlObj.openConnection();
			httpConn.setConnectTimeout(TIMEOUT_MILLIS); // take time to connect.
			httpConn.setReadTimeout(TIMEOUT_MILLIS); // take time to read the data.
			httpConn.setRequestMethod("GET"); // HTTPS method.
			httpConn.setUseCaches(false); // cache utilization.
			// allowed to send the body of the request (in the case of GET is false, when
			// the POST is to true).
			httpConn.setDoOutput(false);
			httpConn.setDoInput(true); // allowed to receive the body of the response.

			if (headers != null) {
				for (String key : headers.keySet()) {
					httpConn.setRequestProperty(key, headers.get(key)); // Set the HTTP headers
				}
			}

			final int responseCode = httpConn.getResponseCode();
			String encoding = httpConn.getContentEncoding();
			if (encoding == null) {
				encoding = "UTF-8";
			}

			if (responseCode == HttpURLConnection.HTTP_OK) {
				is = httpConn.getInputStream();
				isr = new InputStreamReader(is, encoding);
				br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			} else {
				// If the status is other than HTTP_OK (200)
				throw new IOException("responseCode is " + responseCode);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}
		return sb.toString();
	}
	
	/**
	 * This method is used to call WebAPI.
	 * @param url 			 - API URL.
	 * @param encoding 		 - Use to set encoding.
	 * @param headers 		 - List set in HTTP header.
	 * @param jsonString	 - HTTP POST data in String.
	 * @param TIMEOUT_MILLIS - Use to set ConnectTimeout and ReadTimeout.
	 * @return
	 * @throws IOException
	 */
	public String doPost(String url, String encoding, Map<String, String> headers, String jsonString, final int TIMEOUT_MILLIS)
			throws IOException {

		final StringBuffer sb = new StringBuffer("");

		HttpURLConnection httpConn = null;
		BufferedReader br = null;
		InputStream is = null;
		InputStreamReader isr = null;

		try {
			URL urlObj = new URL(url);
			httpConn = (HttpURLConnection) urlObj.openConnection();
			httpConn.setConnectTimeout(TIMEOUT_MILLIS); // take time to connect.
			httpConn.setReadTimeout(TIMEOUT_MILLIS); // take time to read data.
			httpConn.setRequestMethod("POST"); // HTTP method.
			httpConn.setUseCaches(false); // cache utilization.
			// allowed to send the body of the request
			// (in the case of GET is false, when the POST is to true).
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true); // allowed to receive the body of the response.

			if (headers != null) {
				for (String key : headers.keySet()) {
					// set the HTTP headers.
					httpConn.setRequestProperty(key, headers.get(key));
				}
			}

			final OutputStream os = httpConn.getOutputStream();
			final boolean autoFlash = true;
			final PrintStream ps = new PrintStream(os, autoFlash, encoding);
			ps.print(jsonString);
			ps.close();

			final int responseCode = httpConn.getResponseCode();

			String _responseEncoding = httpConn.getContentEncoding();
			if (_responseEncoding == null) {
				_responseEncoding = "UTF-8";
			}

			if (responseCode == HttpURLConnection.HTTP_OK) {
				is = httpConn.getInputStream();
				isr = new InputStreamReader(is, _responseEncoding);
				br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			} else {
				// If the status is other than HTTP_OK (200)
				throw new IOException("responseCode is " + responseCode);
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			if (httpConn != null) {
				httpConn.disconnect();
			}
		}
		return sb.toString();
	}
}
