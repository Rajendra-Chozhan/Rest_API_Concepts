package utilities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Arrays;
import io.restassured.*;
import io.restassured.http.Method;
import io.restassured.response.Response;

import org.testng.asserts.SoftAssert;

import utilities.Context;
import utilities.ContextLoader;

import utilities.EnvPropertyManager;
import utilities.LocalScenarioContext;

public class CommonApi {
	
	//protected Logger log;
	
	public CommonApi() {
		
		
		super();
		//log = LogManager.getLogger(this.getClass());
	}
	
	LocalScenarioContext localScenarioContext = ContextLoader.getLocalScenarioContext();

RestClientUtil restClientutil = new RestClientUtil();

EnvPropertyManager envPropertyManager= new EnvPropertyManager();

SoftAssert softAssert = new SoftAssert();
	
	public  void setAPIRequestParameters (Map<String, String> requestParams) {

		validateParameterNamesAndValues (requestParams);

		Map<String, String> headers = new LinkedHashMap<>();

		headers.put("Content-Type", "application/json");

	//	headers.put("Accept-Encoding", "gzip, deflate, br");

	//	headers.put("Authorization", localScenarioContext.getShiftStringContext(Context.ACCESS_TOKEN));

		Map<String, String> queryParameters = new LinkedHashMap<>();

		String path = null;

		Map<String, String> pathParameters = new LinkedHashMap<>();

		String idValue = localScenarioContext.isContains(Context.ID) ? localScenarioContext.getStringContext(Context.ID): "";
		
		
		

		for (Map.Entry<String, String> entry: requestParams.entrySet()) {

		switch (entry.getKey().toUpperCase()) {

		case "HEADERS":
			
		List<String> headerParams = Arrays.asList(entry.getValue().split(","));

		headerParams = modifyHeaderParameters (headerParams);

		for (String param: headerParams) {

		String[] paramNameValue = param.split("=");

		if (paramNameValue.length > 1)

		headers.put(paramNameValue[0], paramNameValue[1]);
		
		else
			
			headers.remove(paramNameValue[0]);
		
		}
		
		
		break;
		
		case "QUERYPARAMETERS":

			List<String> queryParams = Arrays.asList(entry.getValue().split(","));

			for (String param : queryParams) {

			param=param.contains(";") ? param.replace(";", ","): param;

			String[] paramNameValue = param.split("=");

			if (paramNameValue.length>1){

			queryParameters.put(paramNameValue [0], paramNameValue[1]);

			}

			}

			break;

			case "PATH":

			path = entry.getValue();

			path = path.toLowerCase(Locale.ROOT).contains("<id>")? path.replace("<Id>",idValue): path;

			break;
			
			case "PATHPARAMETERS":

				List<String> pathParams = Arrays.asList(entry.getValue().split(","));

				for (String param : pathParams) {

				String[] paramNameValue = param.split("=");

				String paramName = paramNameValue[0];

				String paramValue = paramNameValue[1];

				paramValue = paramName.equalsIgnoreCase("Id") ? idValue: paramValue;

				pathParameters.put(paramName,

				paramValue.equalsIgnoreCase("[blank]")?"": paramValue);

				}

				break;
				
				
		}}


		localScenarioContext.setMapStringContext(Context.HEADERS, headers);

		localScenarioContext.setMapStringContext(Context.QUERY_PARAMS, queryParameters);

		localScenarioContext.setStringContext(Context.PATH, path);

		localScenarioContext.setMapStringContext(Context.PATH_PARAMS, pathParameters);

//		log.info("Headers ->"+ headers);

//		log.info("Query Parameters >"+ queryParameters);

//		log.info("Path : -> " + path);

//		log.info("Path Parameters:->" + pathParameters);

		}


public void validateParameterNamesAndValues(Map<String, String> requestParams) {

for (Map. Entry<String, String> entry:  requestParams.entrySet()) {

//log.info("Validate ParameterNamesAndValues:"+ entry.getKey() + ":" + entry.getValue());

String parameter = entry.getKey();

String value = entry.getValue();

// Fail if null or emoty

CustomAssert.assertIfNullorEmpty(parameter, "Parameter should be specified for API request");

CustomAssert.assertIfNullorEmpty(value,"Value should be specified for the parameter: " + parameter + " for API request");

switch (parameter.toUpperCase()) {

case "HEADERS":

case "QUERYPARAMETERS":

case "PATH":

case "PATHPARAMETERS":

break;

default:

CustomAssert.assertIfNullorEmpty( null, "Incorrect parameter name:" + parameter);

break;}}}


public List<String> modifyHeaderParameters (List<String> headerParams) {

List<String> result = new ArrayList<>();

for (String param: headerParams) {

String[] paramNameValue = param.split( "");

String paramName = paramNameValue[0];

String paramValue = paramNameValue [1];


if (paramName.equalsIgnoreCase( "Authorization")) {

String modifiedParamValue = paramValue;

switch (paramValue.toUpperCase()) {

case "INVALID":

modifiedParamValue = EnvPropertyManager.getInvalidToken();

break;

case "EXPIRED":

modifiedParamValue = EnvPropertyManager.getExpiredToken();

break;

case "NO TOKEN":

modifiedParamValue = "";

break;
}
paramValue = modifiedParamValue;
}
result.add(paramName + "="+ paramValue);
}
return result;
}



public void triggerAPI (String method, String api, int expectedStatusCode, String statusDescription) {

String baseUri = null;

Response response;

String responseBody;

Object requestBody = null;

Map<String, String> headers = localScenarioContext.getMapStringContext(Context.HEADERS);

Map<String, String> queryParams = localScenarioContext.getMapStringContext(Context.QUERY_PARAMS);

String path = localScenarioContext.getStringContext(Context.PATH);

Map<String, String> pathParams = localScenarioContext.getMapStringContext(Context.PATH_PARAMS);

baseUri = EnvPropertyManager.apiBaseURI();

EnvPropertyManager.apiBaseURI();

requestBody = localScenarioContext.getStringContext(Context.REQUEST_BODY);

localScenarioContext.setStringContext(Context.METHOD,method);

response = restClientutil.doHttpRequestWithBodyasresponse (baseUri, getMethod(method), headers, requestBody, path, expectedStatusCode );

responseBody = response.body().asString();

boolean statusDesCheck = response.statusLine().toUpperCase().contains(statusDescription.toUpperCase());

softAssert.assertEquals(response.statusCode(), expectedStatusCode, "Status Code is Mismatched");

//log.info("Actual Status Code is: " + response.getStatusCode()+" Expected Status Code is "+expectedStatusCode);



softAssert.assertTrue(statusDesCheck, "Status Description is Mismatched");

//log.info("Actual Status Description is: " + response.statusLine()+ "Expected Status Description is" + statusDescription);

//log.info("Response is:->" + responseBody.trim());

localScenarioContext.setStringContext(Context.RESPONSE_BODY, responseBody);

softAssert.assertAll();
}



public Method getMethod(String methodType) {
	
	Method method = null;
	switch (methodType.toUpperCase()) {
	
	
	
	
	case "GET":
		method = Method.GET;
		break;
	case "POST":
		method = Method.POST;
		break;
	case "PUT":
		method = Method.PUT;
		break;
	case "DELETE":
		method = Method.DELETE;
		break;
	case "HEAD":
		method = Method.HEAD;
		break;
	case "OPTIONS":
		method = Method.OPTIONS;
		break;
	case "PATCH":
		method = Method.PATCH;
		break;
		default:
		//	log.error(methodType + "method not available");
	}
	return method;
	
	
	
	
}


}