package Requets;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class DELETE {

	    @Test
	    public void deleteRequest() {
	    	 RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	        Response response = given()
	                .header("Content-type", "application/json")
	                .when()
	                .delete("/posts/1")
	                .then()
	                .extract().response();

	        Assert.assertEquals(200, response.statusCode());
	    }
}
