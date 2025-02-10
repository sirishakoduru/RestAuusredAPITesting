package StepDefinitions;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Assert;

import Utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApi{
	
	public String excelFilePath = "src/test/resources/ExcelData/UserApi.xlsx";
	private static RequestSpecification request;
    private Response response;
	public static int userId;
	public static HashMap map = new HashMap();
	public static HashMap addressMap = new HashMap();
	ExcelReader excelReader = new ExcelReader(excelFilePath);

	
	@Given("User set POST for user API with post Endpoint {string} and data {string} and {int}")
	public void user_set_post_for_user_api_with_post_endpoint_and_data_and(String string, String sheetname, Integer row) throws IOException {
	    
		RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
		request = RestAssured.given()
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .header("Content-Type", "application/json");

		String firstname = excelReader.getCellData(sheetname,row,0);
		String lastname = excelReader.getCellData(sheetname,row,1);
		String contactNo = excelReader.getCellData(sheetname,row,2);
		String emailId = excelReader.getCellData(sheetname,row,3);
		String plotno = excelReader.getCellData(sheetname,row,4);
		String street = excelReader.getCellData(sheetname,row,5);
		String state = excelReader.getCellData(sheetname,row,6);
		String country = excelReader.getCellData(sheetname,row,7);
		String zipcode = excelReader.getCellData(sheetname,row,8);
		
		map.put("user_first_name",firstname);
		map.put("user_last_name",lastname);
		map.put("user_contact_number", contactNo);
		map.put("user_email_id", emailId);
		addressMap.put("plotNumber", plotno);
		addressMap.put("street", street);
		addressMap.put("state", state);
		addressMap.put("country", country);
		addressMap.put("zipCode", zipcode);
		
		map.put("userAddress", addressMap);
		System.out.println(map);
	}

	@When("User sends Post Request with valid End Point and valid data from excel.")
	public void user_sends_post_request_with_valid_end_point_and_valid_data_from_excel() {
	    
		response = request.body(map).post("/uap/createusers");
		System.out.println(response.body().asPrettyString());
		JsonPath jsonPath = response.jsonPath();
		userId = jsonPath.getInt("user_id");
		System.out.println("UserID from response: " +userId);
		System.out.println(response.asPrettyString());
	}

	@Then("A valid response with status code {string} and new User is created with data from excel.")
	public void a_valid_response_with_status_code_and_new_user_is_created_with_data_from_excel(String string) {
	
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
	}
	
	@Given("User sets the GET request with valid endpoint {string} with user_id from post")
	public void user_sets_the_get_request_with_valid_endpoint_with_user_id_from_post(String string) {
	    
		RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
		request = RestAssured.given()
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .header("Content-Type", "application/json");
//		request = request.when().log().all();
	}

	@When("User sends GET request with endpoint and userid created from post")
	public void user_sends_get_request_with_endpoint_and_userid_created_from_post() {
	   
		response = request.get("uap/user/" +userId);
//		JsonPath jsonPath = response.jsonPath();
//		userId = jsonPath.getInt("user_id");
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("User receives a {string} OK status code and the response body should contain with user_id")
	public void user_receives_a_ok_status_code_and_the_response_body_should_contain_with_user_id(String string) {
	    
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
	
	@Given("User set the PUT request with the firstname and valid Endpoint.")
	public void user_set_the_put_request_with_the_firstname_and_valid_endpoint() {
	
		RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
		request = RestAssured.given()
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .header("Content-Type", "application/json");
	}

	@When("User sends HTTPS Request with endpoint {string}")
	public void user_sends_https_request_with_endpoint(String string) {
	    
		map.put("user_first_name","sirisat");
		response = request.body(map).put("/uap/updateuser/" +userId);
		System.out.println(response.asPrettyString());
		System.out.println(response.getBody().asPrettyString());
	}

	@Then("Admin receives the updated first name for the user ID with successfully with a {int} Status Code.")
	public void admin_receives_the_updated_first_name_for_the_user_id_with_successfully_with_a_status_code(Integer int1) {
	   
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
		System.out.println(response.getBody().asString());
	}
	
	@Given("User sets the Delete request with a valid endpoint {string} from post")
	public void user_sets_the_delete_request_with_a_valid_endpoint_from_post(String string) {
	    
		RestAssured.baseURI = "https://userserviceapp-f5a54828541b.herokuapp.com";
		request = RestAssured.given()
                .auth().preemptive().basic("Numpy@gmail.com", "userapi@2025")
                .header("Content-Type", "application/json");
	}

	@When("User send an HTTP Delete request with user_id from post")
	public void user_send_an_http_delete_request_with_user_id_from_post() {
	    
		response = request.delete("uap/deleteuser/" +userId);
		System.out.println("delected userId :" +userId);
		
	}

	@Then("User receives {int} and OK status code when deleted")
	public void user_receives_and_ok_status_code_when_deleted(Integer int1) {
	    
		int statusCode=response.getStatusCode();
		System.out.println(response.statusCode());
		Assert.assertEquals(200,statusCode);
	}


}
