package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Fileutil {

	
	public Fileutil() {}
	
	
	
	public static FileReader getFileReader(String filepath) {
		
		FileReader file = null;
		
		try {
			file = new FileReader(filepath);
			
		}catch (FileNotFoundException var3) {
			System.out.println("Unable to find file" + filepath + var3.getMessage());
			
		}
		return file;
		
		
		
	}
}
