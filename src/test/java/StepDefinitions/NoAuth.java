package StepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NoAuth {
	
	private RequestSpecification request;
    private Response response;
	
    @Given("The user sets Basic Authentication with username {string} and password {string}.")
    public void the_user_sets_basic_authentication_with_username_and_password(String username, String password) {
        
    	RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
		request = RestAssured.given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json");
		
		
    }
    
    @When("The user send get request with invalid auth")
    public void the_user_send_get_request_with_invalid_auth() {
       
    	response = request.get("/uap/users");
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
    	
    }

    @Then("The user receives status code {string} unauthorized.")
    public void the_user_receives_status_code_unauthorized(String string) {

    	int statusCode = response.getStatusCode();
		Assert.assertEquals(401, statusCode);

    }
    
    @Given("The user sets No Auth Authentication")
    public void the_user_sets_no_auth_authentication() {
        
    	RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
    	request = RestAssured.given(); 
    }

    @When("The user send get request without auth")
    public void the_user_send_get_request_without_auth() {
        
    	response = request.get("/uap/users");
    	System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());		
    			
    }

    @Then("The user receives status code {string} with message unauthorized.")
    public void the_user_receives_status_code_with_message_unauthorized(String string) {
        
    	int statusCode = response.getStatusCode();
		Assert.assertEquals(401, statusCode);
    }


}
