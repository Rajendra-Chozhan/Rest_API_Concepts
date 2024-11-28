package serial;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Serialization {

	
	@Test
	public void tosetProgramDetails(){
	ProgramInfo pinfo = new ProgramInfo();
	pinfo.setOnline("true");
	pinfo.setProgramDescription("Intro to Sql by shu220");
	pinfo.setProgramId("0");
	pinfo.setProgramName("SQL220");
	RestAssured.baseURI="https://lms-program-rest-service.herokuapp.com/programs";
	Response res = given().log().all().auth().basic("admin","password")
	.header("Content-Type", "application/json")
	.body(pinfo)
	.when().post()
	.then().statusCode(200).extract().response();
	String response = res.asString();
	System.out.println(response);
	}
}
