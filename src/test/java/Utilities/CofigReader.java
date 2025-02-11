package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class CofigReader {
	
	private static Properties prop = null;

	public static String getProperty(String key) {

		if (prop == null) {
			intializeProperties();
		}
		return prop.getProperty(key);

	}

	public static Properties intializeProperties() {

		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/Config/Config.properties");
			prop.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

}
