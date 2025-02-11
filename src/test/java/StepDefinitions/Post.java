package StepDefinitions;

import java.util.HashMap;

import org.junit.Assert;

import Utilities.CofigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Post {
	private RequestSpecification request;
    private Response response;
//	public static int userId;
	public static HashMap map = new HashMap();
	public static HashMap addressMap = new HashMap();
	private CofigReader reader = new CofigReader();
	
	@Given("User set POST for user API Endpoint {string}")
	public void user_set_post_for_user_api_endpoint(String string) {
		
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");

		
//		map.put("user_id", RestUtils.getUserId());
//		map.put("addressId", RestUtils.getAddressId());
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);

		
	}

	@When("User sends Post Request with valid End Point and valid data")
	public void user_sends_post_request_with_valid_end_point_and_valid_data() {
		
		response = request.body(map).post("/uap/createusers");
		System.out.println(response.asPrettyString());
//		System.out.println(response.path("user_id"));
//		JsonPath jsonPath = response.jsonPath();
//		userId = jsonPath.getInt("user_id");
		System.out.println(response.getBody().asPrettyString());

	}

	@Then("A valid response is received and new User is created with status code {string}")
	public void a_valid_response_is_received_and_new_user_is_created_with_status_code(String string) {
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
	}
	
	@Given("User set POST for user Endpoint {string}")
	public void user_set_post_for_user_endpoint(String string) {
		
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		
		map.put("userAddress", addressMap);
	}
	
	@When("User sends Post Request with valid End Point and valid data with only mandatory fields")
	public void user_sends_post_request_with_valid_end_point_and_valid_data_with_only_mandatory_fields() {
	    
		response = request.body(map).post("/uap/createusers");
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());

	}

	@Then("A valid response is received and status code {string} is creater with new User.")
	public void a_valid_response_is_received_and_status_code_is_creater_with_new_user(String string) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
	}

	@Given("User set POST for user API with invalid Endpoint {string}")
	public void user_set_post_for_user_api_with_invalid_endpoint(String string) {
		
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with INVALID End Point")
	public void user_sends_post_request_with_invalid_end_point() {
	    
		response = request.body(map).post("/uap/createus");
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("No User is created and Status Code should be {int} with error message {string}")
	public void no_user_is_created_and_status_code_should_be_with_error_message(Integer int1, String string) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(404, statusCode);
//		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		
	}
	
	@Given("User set POST with Endpoint {string}")
	public void user_set_post_with_endpoint(String string) {
	   
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", "");
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
		
		
	}

	@When("User sends Post Request with valid End Point and Data without First Name")
	public void user_sends_post_request_with_valid_end_point_and_data_without_first_name() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		
		System.out.println(response.getBody().asPrettyString());
		
	}

	@Then("No User is created with Status Code {int} with error message {string}")
	public void no_user_is_created_with_status_code_with_error_message(Integer int1, String string) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(400, statusCode);
		System.out.println(response.getBody().asString());
		
	}
	
	@Given("User set POST with Endpoint {string} for firstname AlphaNumeric")
	public void user_set_post_with_endpoint_for_firstname_alpha_numeric(String string) {
		
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", "shdd36453");
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
		
	}
	
	@When("User sends Post Request with valid End Point and Data with First Name field AlphaNumeric")
	public void user_sends_post_request_with_valid_end_point_and_data_with_first_name_field_alpha_numeric() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("No User is created with {int} with error message {string}")
	public void no_user_is_created_with_with_error_message(Integer int1, String string) {
	   
		int statusCode = response.getStatusCode();
		Assert.assertEquals(400, statusCode);
		System.out.println(response.getBody().asString());
		
	}
	
	@Given("User set POST with Endpoint {string} for firstname Numeric")
	public void user_set_post_with_endpoint_for_firstname_numeric(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", "36453");
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data with First Name field Numeric")
	public void user_sends_post_request_with_valid_end_point_and_data_with_first_name_field_numeric() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("No User is created and receive Status Code {int} with error message {string}")
	public void no_user_is_created_and_receive_status_code_with_error_message(Integer int1, String string) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(400, statusCode);
		System.out.println(response.getBody().asString());
	}
	
	@Given("User set POST for user API Endpoint {string} for firstname special character")
	public void user_set_post_for_user_api_endpoint_for_firstname_special_character(String string) {
	   
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", "sjfhrn@#");
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data with First Name field contains Special Character")
	public void user_sends_post_request_with_valid_end_point_and_data_with_first_name_field_contains_special_character() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}
	
	@Given("User set POST for user API Endpoint {string} for firstname one letter")
	public void user_set_post_for_user_api_endpoint_for_firstname_one_letter(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", "M");
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
		
	}

	@When("User sends Post Request with valid End Point and Data with First Name field contains one letter")
	public void user_sends_post_request_with_valid_end_point_and_data_with_first_name_field_contains_one_letter() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}
	
	@Given("User set POST for user API Endpoint {string} without lastname")
	public void user_set_post_for_user_api_endpoint_without_lastname(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", " ");
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
		
	}

	@When("User sends Post Request with valid End Point and Data without Last Name")
	public void user_sends_post_request_with_valid_end_point_and_data_without_last_name() {
	   
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}
	
	@Given("User set POST with Endpoint {string} for lastname AlphaNumeric")
	public void user_set_post_with_endpoint_for_lastname_alpha_numeric(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", "dgebd234");
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data with LastName field AlphaNumeric")
	public void user_sends_post_request_with_valid_end_point_and_data_with_last_name_field_alpha_numeric() {
	   
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}
	
	@Given("User set POST with Endpoint {string} for lastname Numeric")
	public void user_set_post_with_endpoint_for_lastname_numeric(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", "234");
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data with LastName field Numeric")
	public void user_sends_post_request_with_valid_end_point_and_data_with_last_name_field_numeric() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}
	
	@Given("User set POST with Endpoint {string} for lastname one letter")
	public void user_set_post_with_endpoint_for_lastname_one_letter(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", "K");
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data with LastName one letter")
	public void user_sends_post_request_with_valid_end_point_and_data_with_last_name_one_letter() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Given("User set POST with Endpoint {string} for lastname with special character")
	public void user_set_post_with_endpoint_for_lastname_with_special_character(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", "sge#$");
		map.put("user_contact_number", RestUtils.getUserContactNumber());
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data with LastName special character")
	public void user_sends_post_request_with_valid_end_point_and_data_with_last_name_special_character() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}
	
	@Given("User set POST for user API Endpoint {string} without phone number")
	public void user_set_post_for_user_api_endpoint_without_phone_number(String string) {
	   
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number", "");
		map.put("user_email_id", RestUtils.getUserEmailid());
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
//		map.remove("user_contact_number");
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data without Phone No")
	public void user_sends_post_request_with_valid_end_point_and_data_without_phone_no() {
	    
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
		
	}
	
	@Given("User set POST for user API Endpoint {string} without email")
	public void user_set_post_for_user_api_endpoint_without_email(String string) {
	    
		String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
		map.clear();
		addressMap.clear();
		map.put("user_first_name", RestUtils.getUserFirstName());
		map.put("user_last_name", RestUtils.getUserLastName());
		map.put("user_contact_number",RestUtils.getUserContactNumber());
		map.put("user_email_id", " ");
		addressMap.put("plotNumber", RestUtils.getPlotNo());
		addressMap.put("street", RestUtils.getStreet());
		addressMap.put("state", RestUtils.getState());
		addressMap.put("country", RestUtils.getCountry());
		addressMap.put("zipCode", RestUtils.getZipcode());
		map.remove("user_contact_number");
		
		map.put("userAddress", addressMap);
	}

	@When("User sends Post Request with valid End Point and Data without email")
	public void user_sends_post_request_with_valid_end_point_and_data_without_email() {
	   
		System.out.println("Request Body Sent: " + request.body(map).log().all());
		response = request.body(map).post("/uap/createusers");
		//System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	



}
