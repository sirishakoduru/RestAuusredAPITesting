@Update
Feature: User is able to update the data

Background: Admin sets the valid Basic Auth for Authorization
Given The user is on the API and user sets Basic Authentication with username "Numpy@gmail.com" and password "userapi@2025"

@Update_userFirstName
Scenario: Check if user is able to update a user with new first name 
Given User set the PUT request with the firstname valid data and valid Endpoint
When User sends HTTPS Request and request Body with endpoint "updateuser/{userId}"
Then Admin receives the updated first name for the user ID with complete details successfully with a 200 Status Code.




