#@ignore
@signupServices
Feature: Cucumber -  SignUp Services Integration Test 

Scenario Outline: set initial configuration for SignUp Services 
	Given grant type is "<grant_type>"
	And user id is "<userId>"
	And user password is "<password>"
	When access token service is called
	Then retrun access token
	And response code is 200
	Examples: 
		| grant_type | userId | password |
		| password | rajivtest5@kohls.com | Kohls123 |