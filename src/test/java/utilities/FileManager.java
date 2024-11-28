package utilities;

import java.io.FileReader;



public class FileManager {
	
	
	public static FileReader getUserJsonFileReader() {
		
		return Fileutil.getFileReader(EnvPropertyManager.getUserJsonPath());
		
	}

}
