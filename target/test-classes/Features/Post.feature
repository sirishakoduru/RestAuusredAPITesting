@Post 
Feature: Creating new user with valid feild values and mandatory fields

Background: Admin sets the valid Basic Auth for Authorization
Given The user is on the API and user sets Basic Authentication with username "Numpy@gmail.com" and password "userapi@2025"

@Post_validFields
Scenario: Create new User with all valid field values
Given User set POST for user API Endpoint "/createusers"
When User sends Post Request with valid End Point and valid data
Then A valid response is received and new User is created with status code "201 Created"

@Post_mandatoryFields
Scenario: Create new User with only mandatory field values
Given User set POST for user Endpoint "/createusers"
When User sends Post Request with valid End Point and valid data with only mandatory fields
Then A valid response is received and status code "201 Created" is creater with new User. 

@Post_invalid_Endpoint
Scenario: Create new User with INVALID ENDPOINT
Given User set POST for user API with invalid Endpoint "/createus"
When User sends Post Request with INVALID End Point
Then No User is created and Status Code should be 404 with error message "Not Found"

@Post_withoutfirstname
Scenario: Create new User WITHOUT FIRSTNAME
Given User set POST with Endpoint "/createusers"
When User sends Post Request with valid End Point and Data without First Name
Then No User is created with Status Code 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_firstname_alphanumeric
Scenario: Create new User with First Name field AlphaNumeric
Given User set POST with Endpoint "/createusers" for firstname AlphaNumeric
When User sends Post Request with valid End Point and Data with First Name field AlphaNumeric
Then No User is created with 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_firstname_numeric
Scenario: Create new User with First Name field only Numeric
Given User set POST with Endpoint "/createusers" for firstname Numeric
When User sends Post Request with valid End Point and Data with First Name field Numeric
Then No User is created and receive Status Code 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_firstname_specialcharacters
Scenario: Create new User with First Name field contains SPECIAL CHARACTER
Given User set POST for user API Endpoint "/createusers" for firstname special character
When User sends Post Request with valid End Point and Data with First Name field contains Special Character
Then No User is created and receive Status Code 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_firstname_oneletter
Scenario: Create new User with First Name field contains SPECIAL CHARACTER
Given User set POST for user API Endpoint "/createusers" for firstname one letter
When User sends Post Request with valid End Point and Data with First Name field contains one letter
Then No User is created and receive Status Code 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_withoutlastname
Scenario: Create new User WITHOUT LASTNAME
Given User set POST for user API Endpoint "/createusers" without lastname
When User sends Post Request with valid End Point and Data without Last Name
Then No User is created and receive Status Code 400 with error message "User Last Name is mandatory and should contains alphabets only"

@Post_Lastname_alphanumeric
Scenario: Create new User with last Name field AlphaNumeric
Given User set POST with Endpoint "/createusers" for lastname AlphaNumeric
When User sends Post Request with valid End Point and Data with LastName field AlphaNumeric
Then No User is created with 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_Lastname_numeric
Scenario: Create new User with last Name field AlphaNumeric
Given User set POST with Endpoint "/createusers" for lastname Numeric
When User sends Post Request with valid End Point and Data with LastName field Numeric
Then No User is created with 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_Lastname_Oneletter
Scenario: Create new User with last Name field AlphaNumeric
Given User set POST with Endpoint "/createusers" for lastname one letter
When User sends Post Request with valid End Point and Data with LastName one letter
Then No User is created with 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_Lastname_Specialcharacter
Scenario: Create new User with last Name field AlphaNumeric
Given User set POST with Endpoint "/createusers" for lastname with special character
When User sends Post Request with valid End Point and Data with LastName special character
Then No User is created with 400 with error message "user FirstName is mandatory and should contains alphabets only"

@Post_without_phonenumber
Scenario: Create new User WITHOUT PHONE NO
Given User set POST for user API Endpoint "/createusers" without phone number
When User sends Post Request with valid End Point and Data without Phone No
Then No User is created with 400 with error message "Phone Number is required and should contains 10 numeric values only"

@Post_without_emial
Scenario: Create new User WITHOUT EMAIL
Given User set POST for user API Endpoint "/createusers" without email
When User sends Post Request with valid End Point and Data without email
Then No User is created with 400 with error message "User email Id is required and should be in proper email format"
