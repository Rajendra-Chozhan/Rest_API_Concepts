package utilities;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;



public class EnvPropertyManager {
	
	

	//private static final Logger log = LogManager.getLogger(EnvPropertyManager.class);

	private static Properties envProperties  = new Properties();

	private static StringWriter writer = new StringWriter();

	private static final String PROPERTIES_FILE = "configuration"+ File.separator+"Config.properties";

	static {

	try (InputStream resourceAsStream = EnvPropertyManager.class.getClassLoader()

	.getResourceAsStream(PROPERTIES_FILE)) {

	envProperties.load(resourceAsStream);

	} catch (IOException e) {

	//log.error( "Properties file not found in path", e.getMessage());
}
}

public static String getUrl() {
	
	String url = envProperties.getProperty("url");
	return url;
	
	
}

public static String getInvalidToken() {
	// TODO Auto-generated method stub
	return null;
}

public static String getExpiredToken() {
	// TODO Auto-generated method stub
	return null;
}

public static String apiBaseURI() {
	
	return null;
}


public static String getUserJsonPath() {
	String userjsonpath = envProperties.getProperty("jsonpath");
	return userjsonpath;
}


}