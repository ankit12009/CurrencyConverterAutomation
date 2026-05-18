package utilitiy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties{
	

	public   static String  Read(String properties) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\setProperty");
		prop.load(file);
		return prop.getProperty(properties);
	}
}