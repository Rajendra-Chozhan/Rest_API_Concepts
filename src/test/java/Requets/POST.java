package Requets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utilities.Context;
import utilities.MetaData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
public class POST {

	MetaData metadata;
	
	
	//@Test
	 public  void POST_An_Employee_using_hashMap() {
					
			HashMap data=new HashMap();
			
			data.put("id", "7");
			data.put("email", "Raj@gmail.com");
			data.put("first_name", "Raj");
			data.put("last_name", "Chozhan");
			data.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
			
			
			RequestSpecification res= RestAssured.given();
			
				
				res.contentType(ContentType.JSON);
			
				res.body(data)
				.when()
				   .post("https://reqres.in/api/users")
				   
				   
				.then()
				   .statusCode(201)	
				   .body("id", equalTo("7"))
				   .body("email", equalTo("Raj@gmail.com"))
				   .body("first_name", equalTo("Raj"))
				   .body("last_name", equalTo("Chozhan"))
				   .body("avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
				  
				.log().all()
				.extract().response();
				
			
								
			}
	 
	
	 
	//	@Test(priority=1)
		 public  void POST_An_Employee_using_JsonLibrary() {
						
				JSONObject data1=new JSONObject();
				
				data1.put("id", "8");
				data1.put("email", "Raj@gmail1.com");
				data1.put("first_name", "Ram");
				data1.put("last_name", "Kumar");
				data1.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
				
				
				RequestSpecification res= RestAssured.given();
				
					
					res.contentType(ContentType.JSON);
				
					res.body(data1)
					.when()
					   .post("https://reqres.in/api/users")
					   
					   
					.then()
					   .statusCode(201)	
					   .body("id", equalTo("8"))
					   .body("email", equalTo("Raj@gmail1.com"))
					   .body("first_name", equalTo("Ram"))
					   .body("last_name", equalTo("Kumar"))
					   .body("avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
					  
					.log().all()
					.extract().response();
					
				
									
				}
		
		
	//	@Test(priority=1)
		 public  void POST_An_Employee_using_JsonFile() throws FileNotFoundException {
						
			
			File file = new File(".\\Body.json");
			
			FileReader fr = new FileReader(file);
			
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject data2=new JSONObject(jt);
				
				
				RequestSpecification res= RestAssured.given();
				
					
					res.contentType(ContentType.JSON);
				
					res.body(data2.toString())
					.when()
					   .post("https://reqres.in/api/users")
					   
					   
					.then()
					   .statusCode(201)	
					   .body("id", equalTo("10"))
					   .body("email", equalTo("Raj@gmail1.com"))
					   .body("first_name", equalTo("Ignicious"))
					   .body("last_name", equalTo("Edrin"))
					   .body("avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
					  
					.log().all()
					.extract().response();
					
				
									
				}
		
		
		 public void createEmployee() {
		        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		        String requestBody = "{\n" +
		            "  \"name\": \"tammy\",\n" +
		            "  \"salary\": \"5000\",\n" +
		            "  \"age\": \"20\"\n" +
		            "}";

		        Response response = null;
		        try {
		            response = RestAssured.given()
		                .contentType(ContentType.JSON)
		                .body(requestBody)
		                .post("/create");
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        System.out.println("Response :" + response.asString());
		        System.out.println("Status Code :" + response.getStatusCode());
		        System.out.println("Does Reponse contains 'tammy'? :" + response.asString().contains("tammy"));

		        assertEquals(200, response.getStatusCode());
		        
		        
		    
		        		
		        
		        
		        
		    }
		 
		 
		 
		 @Test(priority=1)
		 public  void POST_An_User_using_JsonFile() throws FileNotFoundException {
						
			
			
				
				RequestSpecification res= RestAssured.given();
				
					
					res.contentType(ContentType.JSON);
				
					res.body(metadata.getUserPayloadFromJsonFile("default"))
					.when()
					   .post("https://reqres.in/api/users/1")
					   
					   
					.then()
					   .statusCode(201)	
					   .body("id", equalTo("10"))
					   .body("email", equalTo("Raj@gmail1.com"))
					   .body("first_name", equalTo("Ignicious"))
					   .body("last_name", equalTo("Edrin"))
					   .body("avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
					  
					.log().all()
					.extract().response();
					
				
									
				}
		 

}
