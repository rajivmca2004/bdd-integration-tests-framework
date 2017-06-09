#@ignore
@signupServices
Feature: Cucumber -  SignUp Services Integration Test 

Scenario Outline: set initial configuration for SignUp Services 
	Given app API Key header "<x-app-api_key>"
	And grant type is "<grant_type>"
	And user id is "<userId>"
	And user password is "<password>"
	And client id is "<client_id>"
	And secret is "<secret>"
	When access token service is called
	Then retrun access token
	And response code is 200
	Examples: 
		|x-app-api_key  | grant_type | userId | password | client_id | secret |
		| JBmYK1DyITEQAmUa27kWIpOjSZyyHAJR | password | rajivtest1@kohls.com | Kohls123 | JBmYK1DyITEQAmUa27kWIpOjSZyyHAJR | KnyK31GAMz585uAU |