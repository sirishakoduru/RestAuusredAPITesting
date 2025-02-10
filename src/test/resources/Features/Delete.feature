@Delete
Feature: User is able to delete the data

Background: Admin sets the valid Basic Auth for Authorization
Given The user is on the API and user sets Basic Authentication with username "Numpy@gmail.com" and password "userapi@2025"

@Delete_User_By_ID_And_Name_Valid_data
Scenario Outline: Check if User is able to delete users with valid userId and Firstname
Given User sets the Delete request with a valid endpoint "<endpoint>"
When User send an HTTP Delete request with valid endpoint
Then User receives 200 and OK status code
Examples: 
      | endpoint                           |
      |uap/deleteuser/18786              |
      | uap/deleteuser/username/keannuu |