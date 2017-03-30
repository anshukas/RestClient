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
 - HttpClient i.e httpcomponents-client-4.5.3
 	http://hc.apache.org/downloads.cgi
