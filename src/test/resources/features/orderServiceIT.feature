@orderService 
Feature: Test Order and Catalogue Microservices Integration Test 

#@ignore
Scenario: Fetch order 
	Given I Set GET order service api endpoint
	When fetch order service will be called 
	Then receive valid HTTP response code 200

#@ignore		
Scenario Outline: Create catalogue in catalogue microservice 
	Given create catalogue data with request body "<body>" 
	When create  catalogues service will be called 
	Then catalogue created with valid HTTP response code 200 
	Examples: 
		| body |
		|    src/test/resources/catalogues/catalogues.json |