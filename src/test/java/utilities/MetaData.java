package utilities;

import java.io.FileReader;
import java.io.IOException;

import utilities.ContextLoader;
import utilities.Context;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.Primitives;
import utilities.LocalScenarioContext;

import freemarker.template.utility.DateUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class MetaData {
	
	
	LocalScenarioContext localScenarioContext = ContextLoader.getLocalScenarioContext();
	
	
	public String getUserPayloadFromJsonFile(String userParams) {
			
		String userRequestBody = null;
		String id = "";
		
		 JSONObject userJsonvalues = getJsonObject(FileManager.getUserJsonFileReader());
		String defaultJsonvalue = userJsonvalues.get("Default").toString();
		userModel usersRequest = getClassObject(defaultJsonvalue,userModel.class);
		id = localScenarioContext.isContains(Context.ID)? localScenarioContext.getStringContext(Context.ID) : RandomDataGenerator.getRandomNumber(1);

		if (userParams.equalsIgnoreCase("Default")) {

			usersRequest.setId("UserCreated at "+ DateUtils.getCurrentYear());

			usersRequest.getId();

		} 
		
localScenarioContext.setStringContext(Context.REQUEST_BODY, defaultJsonvalue);
		return userRequestBody;}
	
	

	
public static <T> T getClassObject (String jsonString, Class<T> jsonModelClass) {
	
	Gson gson = new Gson(); return
	 Primitives.wrap(jsonModelClass).cast(gson.fromJson(jsonString, jsonModelClass));
	}






	private JSONObject getJsonObject(FileReader userJsonFileReader) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonobject = null;

		
				try {
					jsonobject = (JSONObject)jsonParser.parse(userJsonFileReader);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}catch(ParseException var4) {}
		
		
		return jsonobject;
		}

	
	
	
	}

	
		
	
	
	
