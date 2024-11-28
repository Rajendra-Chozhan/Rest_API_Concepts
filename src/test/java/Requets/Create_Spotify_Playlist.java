package Requets;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Create_Spotify_Playlist {

	
	RequestSpecification requestspecification;
	ResponseSpecification responsespecification;
	String access_token = "BQAZ5-ZMNu1m6ocsL3Kimjf6QP4K7Wbnby_OnFimDmngumMtcBOLnvUhO4B2poKTEeq1tAl5c3fVPsS6Ggxz1hGCPaXvtcnpoInES8VwKT8B7sRkWzgfNtPkxNGnYrAjXfdb6ec6aXL9qMy3hEa19fBeX69AEbSoA4Zlaa4-IjhsYv2rYclGGiXNEkqIsE4L0BZVUbczu3V79DxtB8ZmBj9INqepRL4hzgivyfiMZGhHpAxjEr2ucjs2-kTI2vu0zxGlHKIASg";
	
	
	 @Test
	    public void createplaylistRequest() {
		 
		 RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder().
				 
				 setBaseUri("https://api.spotify.com").setBasePath("/v1").
				 addHeader("Authorization", "Bearer" + access_token).setContentType(ContentType.JSON).log(LogDetail.ALL);
		 

		 requestspecification = requestspecbuilder.build();
		 
		 ResponseSpecBuilder responsespecbuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL);
		 responsespecification = responsespecbuilder.build();
		 
		 String payload = "{\r\n"
		 		+ "    \"name\": \"ARR Playlist\",\r\n"
		 		+ "    \"description\": \"New playlist  - ARR\",\r\n"
		 		+ "    \"public\": false\r\n"
		 		+ "}";
		 
		 given(requestspecification).body(payload).when().post("/users/az1h5l0oifhhu1esb0svyy4x9/playlists").then().spec(responsespecification).assertThat().statusCode(201);	    }
}
