@Post 
Feature: Creating new user with valid feild values and mandatory fields

#Background: Admin sets the valid Basic Auth for Authorization
#Given The user is on the API and user sets Basic Authentication with username "Numpy@gmail.com" and password "userapi@2025"

@Post_validFields
Scenario Outline: Create new User with all valid field values from excel
Given User set POST for user API with post Endpoint "/createusers" and data "<Sheetname>" and <row>
When User sends Post Request with valid End Point and valid data from excel.
Then A valid response with status code "201 Created" and new User is created with data from excel.
Examples:
| Sheetname   | row | 
| Sheet1 |         1|
| Sheet1 |         2|

@Get_Valid_endpoint
Scenario: Check if Admin is able to retrieve the user with valid user_id
Given User sets the GET request with valid endpoint "user/{userId}" with user_id from post
When User sends GET request with endpoint and userid created from post
Then User receives a "200" OK status code and the response body should contain with user_id

@Update_userFirstName
Scenario: Check if user is able to update a user with new first name created from post
Given User set the PUT request with the firstname and valid Endpoint.
When User sends HTTPS Request with endpoint "updateuser/{userId}"
Then Admin receives the updated first name for the user ID with successfully with a 200 Status Code.

@Delete_User_By_ID_And_Name_Valid_data
Scenario Outline: Check if User is able to delete users with valid userId and Firstname
Given User sets the Delete request with a valid endpoint "/deleteuser/{userId}" from post
When User send an HTTP Delete request with user_id from post
Then User receives 200 and OK status code when deleted