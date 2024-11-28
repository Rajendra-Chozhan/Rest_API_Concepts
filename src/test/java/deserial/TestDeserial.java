package deserial;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import deserial.ProgramInfo;
import io.restassured.RestAssured;

public class TestDeserial {

	@Test
	public void getPojoFromProgramInfoObject() throws JsonMappingException,JsonProcessingException
	{
	//Deserialization
	ProgramInfo[] allprogramdetails = RestAssured.given().auth().basic("admin", "password").log().all()
	.when().get("https://lms-program-rest-service.herokuapp.com/programs").as(ProgramInfo[].class);
	
	System.out.println("no of programs: "+ allprogramdetails.length);
	System.out.println("Program Name: "+allprogramdetails[allprogramdetails.length-1].getProgramId());
	System.out.println("Program Name: "+allprogramdetails[allprogramdetails.length-1].getOnline());
	System.out.println("Program Name: "+allprogramdetails[allprogramdetails.length-1].getProgramName());
	System.out.println("Program Description: "+allprogramdetails[allprogramdetails.length-1].getProgramDescription());
	}}
