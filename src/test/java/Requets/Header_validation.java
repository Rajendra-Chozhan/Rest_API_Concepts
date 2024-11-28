package Requets;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class Header_validation {

	
	
		   @Test
		   public void ressponseAssertion() {
			   
			   RequestSpecification res= RestAssured.given();
			

		      //base URL
		      RestAssured.baseURI = "https://reqres.in/";

		      //GET operation
		      given() .when().get("/api/users?page=2").
		      then().log().headers()

		      //verify status code as 200
		      .assertThat().header("Content-Type", "application/json; charset=utf-8")

		     // verify body
		   // .body("Location", Matchers.equalTo("Makinac Island"))

		  //verify header
		     .header("Content-Encoding" , "gzip");
		   }
}
