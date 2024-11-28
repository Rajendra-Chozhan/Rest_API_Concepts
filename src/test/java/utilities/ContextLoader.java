package utilities;

public class ContextLoader {
	
	private static final SystemConfig config = new SystemConfig();
	
public static LocalScenarioContext localScenarioContext;


















public static LocalScenarioContext getLocalScenarioContext() {
	return (localScenarioContext == null)? localScenarioContext = new LocalScenarioContext():localScenarioContext;
	


}








}
