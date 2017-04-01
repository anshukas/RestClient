package com.transport.util;

import java.io.IOException;

public interface HttpClientHandlerV4 {
	public void initialize();
	public String doGet(String url) throws IOException;
}
