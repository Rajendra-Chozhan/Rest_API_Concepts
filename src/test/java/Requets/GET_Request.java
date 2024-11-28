package Requets;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GET_Request {

	
	 @Test
	 public  void GET_EmployeesList() {
					
		
			Response res =
				given()
				
				.contentType("application/json")
				//.body(data)
				.when()
				   .get("http://localhost:3000/employees")
				   
				.then()
				   .statusCode(200)	
				  .log().all()
				.extract().response();
				
				String jasonresponse=res.asString();
				System.out.println(jasonresponse);
								
			}
}
