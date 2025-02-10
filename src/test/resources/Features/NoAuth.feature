@No_Auth
Feature: Get User with invalid Auth

Scenario: Admin sets the invalid Basic Auth for Authorization
Given The user sets Basic Authentication with username "test@gmail.com" and password "userapi@2025".
When The user send get request with invalid auth
Then The user receives status code "401" unauthorized.

Scenario: Admin sets the No Auth for Authorization
Given The user sets No Auth Authentication 
When The user send get request without auth
Then The user receives status code "401" with message unauthorized.