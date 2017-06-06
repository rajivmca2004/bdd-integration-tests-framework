Feature: Cucumber POC for Loyalty Microservices Integration Test 

Scenario: set initial configuration
	Given config setup

Scenario Outline: update Loyalty
	Given when send the updated loyalty values with request body "<body>"
	When loyalty update service will be called
	Then customer profile is updated
	Examples:
    | body |
    |    src/test/resources/mockJson/loyaltyUpdate.json |
    |    src/test/resources/mockJson/loyaltyUpdateWithEmail.json |

#@ignore	
Scenario Outline: Create Loyalty Profile
	Given input create customer data with request body "<body>"
	When create loyalty profile service will be called
	Then customer profile is sucessfully created
	Examples:
    | body |
    |    src/test/resources/mockJson/loyaltyCreate.json |