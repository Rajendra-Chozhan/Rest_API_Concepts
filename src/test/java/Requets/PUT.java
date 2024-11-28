package Requets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class PUT {
	
	
	
	

	 //   @Test
	    public void updateEmployee() {
	    	 
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	 
	        String requestBody = "{\r\n" +
	          
	            " \"title\":\"Title No:1\"\r\n" +
	            "}";
	 
	 
	        Response response = null;
	 
	        try {
	            response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(requestBody)
	                .put("/photos/1");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        System.out.println("Response :" + response.asString());
	        System.out.println("Status Code :" + response.getStatusCode());
	       
	 
	 
	    }
	    
	    
	    
	    @Test(priority=1)
		 public  void POST_An_Employee_using_JsonFile() throws FileNotFoundException {
						
			
			File file = new File(".\\Body1.json");
			
			FileReader fr = new FileReader(file);
			
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject data2=new JSONObject(jt);
				
				
				RequestSpecification res= RestAssured.given();
				
					
					res.contentType(ContentType.JSON);
				
					res.body(data2.toString())
					.when()
					   .put("https://reqres.in/api/users/2")
					   
					   
					.then()
					   .statusCode(200)	
					  .body("name", equalTo("morpheus"))
					  .body("job", equalTo("zion resident"))
					   
					  
					.log().all()
					.extract().response();
					
				
									
				}

}
