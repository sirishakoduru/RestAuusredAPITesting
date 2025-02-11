package StepDefinitions;

import java.util.List;

import org.junit.Assert;

import Utilities.CofigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.path.json.JsonPath.from;

public class getUsers {
	private RequestSpecification request;
    private Response response;
    private CofigReader reader = new CofigReader();

	
    @Given("The user is on the API and user sets Basic Authentication with username {string} and password {string}")
	public void the_user_is_on_the_api_and_user_sets_basic_authentication_with_username_and_password(String username, String password) {
	   
    	String baseurl = reader.getProperty("BaseUrl");
		RestAssured.baseURI = baseurl;
		request = RestAssured.given()
                .auth().preemptive().basic(reader.getProperty("username"),reader.getProperty("password"))
                .header("Content-Type", "application/json");
	}

	@Given("Admin sets the GET request with valid endpoint {string}")
	public void admin_sets_the_get_request_with_valid_endpoint(String string)
	{
		request = request.when().log().all();
	}

	@When("Admin sends GET request with endpoint")
	public void admin_sends_get_request_with_endpoint() {
	    
		response = request.get("/uap/users");
//		System.out.println(response.asPrettyString());
//		System.out.println(response.getBody().asPrettyString());
		
	}

	@Then("Admin receives a {string} OK status code and the response body should contain a list of users")
	public void admin_receives_a_ok_status_code_and_the_response_body_should_contain_a_list_of_users(String string) {
	  
//		System.out.println("Response Code: " + response.getStatusCode());
//        System.out.println("Response Body: " + response.getBody().asString());
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);

 }
	
	@Given("Admin sets the GET request with invalid endpoint {string}")
	public void admin_sets_the_get_request_with_invalid_endpoint(String string) {
	    
		request = request.when().log().all();
	}
	@When("Admin sends GET request with endpoints")
	public void admin_sends_get_request_with_endpoints() {
	    
		response = request.get("/uap/user");
		
	}

	@Then("Admin receives a {string} not found status code")
	public void admin_receives_a_not_found_status_code(String string) {
	    
		int statusCode = response.getStatusCode();
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		Assert.assertEquals(404, statusCode);
	}
	
	@Given("User set GET request for user API Endpoint.")
	public void user_set_get_request_for_user_api_endpoint() {
	    
		request = request.when().log().all();
	}

	@When("User sends get Request with valid endpoint {string}")
	public void user_sends_get_request_with_valid_endpoint(String endpoint) {
	    
		response = request.get(endpoint);
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}


	@Then("User receives status code {int} OK.")
	public void user_receives_status_code_ok(Integer int1) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		String actualStatusLine = response.getStatusLine();
		System.out.println("ActualStatusLine :" +actualStatusLine);
		Assert.assertEquals("HTTP/1.1 200 ", actualStatusLine);
		String contentType = response.getContentType();
		System.out.println("ContentType :" +contentType);
		Assert.assertEquals("application/json", contentType);
	}
	
	@When("User sends get Request with invalid user_id and userfirstname endpoint {string}")
	public void user_sends_get_request_with_invalid_user_id_and_userfirstname_endpoint(String endpoint) {
	    
		response = request.get(endpoint);
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("User receives status code {string} not found.")
	public void user_receives_status_code_not_found(String string) {
	   
		int statusCode = response.getStatusCode();
		Assert.assertEquals(404, statusCode);
		String actualStatusLine = response.getStatusLine();
		System.out.println("ActualStatusLine :" +actualStatusLine);
		
	}
	
	@Given("User set POST request for user API Endpoint {string}")
	public void user_set_post_request_for_user_api_endpoint(String string) {
	    
		request = request.when().log().all();
	}

	@When("User sends post Request with Invalid Method")
	public void user_sends_post_request_with_invalid_method() {
	    
		response = request.post("/uap/users");
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("User receives status code {string} Method not Allowed")
	public void user_receives_status_code_method_not_allowed(String string) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(405, statusCode);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		String actualStatusLine = response.getStatusLine();
		System.out.println("ActualStatusLine :" +actualStatusLine);
		Assert.assertEquals("HTTP/1.1 405 ", actualStatusLine);
	}

	    
	


}
