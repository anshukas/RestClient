# RestClient
Life is about consume many resources. :)

<hr/>

# Writing RestClient.
HTTP GET request with zipcode = 00602 <br/>
GET http://api.census.gov/data/2011/acs5?get=NAME,B19013_001E,B01003_001E&for=zip+code+tabulation+area:00602&key=f53f9800117354950c1c7ffbb5a7eb923665293a

These are the following important things:
- endpoint = https://api.census.gov/data/2014/acs5
- get=NAME,B19013_001E,B01003_001E
- for=zip+code+tabulation+area:00602
- key=f53f9800117354950c1c7ffbb5a7eb923665293a

<hr/>

# Technology:
Project Name: **census_client** <br/>
HttpClient i.e httpcomponents-client-4.5.3 <br/>
- http://hc.apache.org/downloads.cgi  <br/>

Project Name: **census_client_httpclient-3.1** <br/>
HttpClient i.e commons-httpclient-3.1.jar <br/>
 	http://hc.apache.org/httpclient-3.x/  <br/>
 - Added **commons-logging-1.2.jar** else you'll get exception about logging class not found. <br/>
 - Added **commons-codec-1.9.jar**   else you'll get exception about logging class not found. <br/>
