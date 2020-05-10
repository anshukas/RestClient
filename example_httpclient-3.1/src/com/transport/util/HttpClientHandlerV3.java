package com.transport.util;

import java.io.IOException;

public interface HttpClientHandlerV3 {
	public void initialize();
	public String doGet(String url) throws IOException;	
}
