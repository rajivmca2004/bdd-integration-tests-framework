#@ignore
@loyaltyService 
Feature: Cucumber - Loyalty Microservices Integration Test 

Scenario Outline: set initial configuration for Loyalty Microservices 
	Given config setup for Loyalty
	And channel id for loyaty is "<channel>"
	Examples: 
		| channel |
		|    MCOM |

Scenario Outline: update Loyalty 
	Given send the updated loyalty values with request body "<body>" 
	When loyalty update service will be called 
	Then customer profile is updated 
	Examples: 
		| body |
		|    src/test/resources/loyalty/loyaltyUpdate.json |
		|    src/test/resources/loyalty/loyaltyUpdateWithEmail.json |
		
		#@ignore	
Scenario Outline: Create Loyalty Profile 
	Given input create customer data with request body "<body>" 
	When create loyalty profile service will be called 
	Then customer profile is sucessfully created 200 or existed 400
	Examples: 
		| body |
		|    src/test/resources/loyalty/loyaltyCreate.json |