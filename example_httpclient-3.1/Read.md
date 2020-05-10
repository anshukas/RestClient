# Writing RestClient.

HTTP GET request with zipcode = 00602 
GET http://api.census.gov/data/2011/acs5?get=NAME,B19013_001E,B01003_001E&for=zip+code+tabulation+area:00602&key=f53f9800117354950c1c7ffbb5a7eb923665293a

These are the following important things:
 - endpoint = https://api.census.gov/data/2014/acs5
 - get=NAME,B19013_001E,B01003_001E
 - for=zip+code+tabulation+area:00602
 - key=f53f9800117354950c1c7ffbb5a7eb923665293a
 
 Take zipcode from user/properties file. Keep it independent so that we try out multiple zipcode.
 
# Technology:
 - HttpClient i.e commons-httpclient-3.1.jar
 	http://hc.apache.org/httpclient-3.x/
 - Added **commons-logging-1.2.jar** else you'll get exception about logging class not found
 - Added **commons-codec-1.9.jar**
