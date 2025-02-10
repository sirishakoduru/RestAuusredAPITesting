package StepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String getUserId() {
		String generatedString = RandomStringUtils.randomNumeric(4);
		return (generatedString);
	}
	public static String getAddressId() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return (generatedString);
	}
	public static String getUserFirstName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("sravs" + generatedString);
	}
	public static String getUserLastName() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return ("sat" + generatedString);
	}
	public static String getUserContactNumber() {
		return ("678" + RandomStringUtils.randomNumeric(7));
		
	}
	public static String getUserEmailid() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return (generatedString+"@gmail.com");
	}
	public static String getPlotNo() {
		String generatedString = RandomStringUtils.randomNumeric(3);
		return ("kls-" + generatedString);
	}
	public static String getStreet() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	public static String getState() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	
	public static String getCountry() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	
	public static String getZipcode() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}
	

}
