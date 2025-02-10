@Get_User
Feature: GET ALL USERS

Background: Admin sets the valid Basic Auth for Authorization
Given The user is on the API and user sets Basic Authentication with username "Numpy@gmail.com" and password "userapi@2025"

@Get_Valid_endpoint
Scenario: Check if Admin is able to retrieve the list of all users with valid credentials
Given Admin sets the GET request with valid endpoint "/users"
When Admin sends GET request with endpoint
Then Admin receives a "200" OK status code and the response body should contain a list of users

@Get_Invalid_endpoint
Scenario: Check if Admin is able to retrieve the list of all users with valid credentials and invalid endpoint
Given Admin sets the GET request with invalid endpoint "/user"
When Admin sends GET request with endpoints
Then Admin receives a "404" not found status code

@Get_Valid_UserID_UserName
Scenario Outline: Check if user is able to get user details by valid user id and Userfirstname.
Given User set GET request for user API Endpoint.
When User sends get Request with valid endpoint "<endpoint>"
Then User receives status code 200 OK.
Examples:
|endpoint|  
|uap/user/20402 |    
|uap/users/username/dheek |  

@Get_Invalid_UserID_UserName
Scenario Outline: Check if user is able to get user details by invalid user id and UserFirstname.
Given User set GET request for user API Endpoint.
When User sends get Request with invalid user_id and userfirstname endpoint "<endpoint>"
Then User receives status code "404" not found.
Examples:
|endpoint|  
|uap/user/45 |
|uap/user/ |   
|uap/users/username/dhe |  
|uap/users/username/ |  
|uap/users/username/546 |  

@Get_InvalidMethod
Scenario: Check if user is able to get users with Invalid Method
Given User set POST request for user API Endpoint "/users" 
When User sends post Request with Invalid Method 
Then User receives status code "405" Method not Allowed