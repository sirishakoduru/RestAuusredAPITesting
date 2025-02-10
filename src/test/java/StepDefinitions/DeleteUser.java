package StepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteUser {
	private RequestSpecification request;
    private Response response;
    
    @Given("User sets the Delete request with a valid endpoint {string}")
    public void user_sets_the_delete_request_with_a_valid_endpoint(String endpoint) {
        
    	RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
		request = RestAssured.given()
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .header("Content-Type", "application/json");
		
		response = request.delete(endpoint);
		
	}

    @When("User send an HTTP Delete request with valid endpoint")
    public void user_send_an_http_delete_request_with_valid_endpoint() {
        
		System.out.println(response.getBody().asPrettyString());
		//response = request.get(endpoint);
	}


    @Then("User receives {int} and OK status code")
    public void user_receives_and_ok_status_code(Integer int1) {
        
	int statusCode=response.getStatusCode();
	System.out.println(response.statusCode());
	Assert.assertEquals(200,statusCode);

	}

}
