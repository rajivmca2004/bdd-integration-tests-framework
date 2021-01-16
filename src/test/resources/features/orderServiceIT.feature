@orderService 
Feature: Test Order and Catalogue Microservices Integration Test 

#@ignore
#This Order microservice will call both catalogue and customer-management microservice and return aggregated result
Scenario: Fetch order 
	Given I Set GET order service api endpoint
	When fetch order service will be called 
	Then receive valid HTTP response code 200

@ignore		
Scenario Outline: Create Order 
	Given create order data with request body "<body>" 
	When create  order postOrderscservice will be called 
	Then customer profile is successfully created 200
	Examples: 
		| body |
		|    src/test/resources/orders/orderCreate.json |