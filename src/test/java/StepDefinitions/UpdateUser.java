package StepDefinitions;

import java.util.HashMap;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateUser {
	
	private RequestSpecification request;
    private Response response;
    public static HashMap map = new HashMap();
	public static HashMap addressMap = new HashMap();
	
	String user_first_name = RestUtils.getUserFirstName();
	String user_last_name = RestUtils.getUserLastName();
	String user_contact_number = RestUtils.getUserContactNumber();
	String user_email_id = RestUtils.getUserEmailid();
	String plotNumber = RestUtils.getPlotNo();
	String Street = RestUtils.getStreet();
	String state = RestUtils.getState();
	String Country = RestUtils.getCountry();
	String zipCode = RestUtils.getZipcode();
	int user_id = 4491;
	
	@Given("User set the PUT request with the firstname valid data and valid Endpoint")
	public void user_set_the_put_request_with_the_firstname_valid_data_and_valid_endpoint() {
	    
		RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
		request = RestAssured.given()
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name",user_first_name);
		map.put("user_last_name",user_last_name);
		map.put("user_contact_number",user_contact_number);
		map.put("user_email_id",user_email_id);
		addressMap.put("plotNumber",plotNumber);
		addressMap.put("street",Street);
		addressMap.put("state",state);
		addressMap.put("country",Country);
		addressMap.put("zipCode",zipCode);
		
		map.put("userAddress", addressMap);
		
		
	}

	@When("User sends HTTPS Request and request Body with endpoint {string}")
	public void user_sends_https_request_and_request_body_with_endpoint(String string) {
		
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).pathParam("userId",user_id).put("/uap/updateuser/{userId}");
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("Admin receives the updated first name for the user ID with complete details successfully with a {int} Status Code.")
	public void admin_receives_the_updated_first_name_for_the_user_id_with_complete_details_successfully_with_a_status_code(Integer int1) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		System.out.println(response.getBody().asString());
	}
	

}
