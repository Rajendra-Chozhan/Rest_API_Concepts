package utilities;

import java.util.Map;



import io.restassured.http.Method;
import io.restassured.response.Response;

public class RestClientUtil {
	
	private static Response response = null;
//	private static final Logger log = LogManager.getLogger(RestClientUtil.class);
	public Response doHttpRequestWithBodyasresponse(String baseUri, Method method, Map<String, String> headers,
			Object requestBody, String path, int expectedStatusCode) {
		
		return null;
	}

}
