Feature: Cucumber POC for Loyalty Microservices Integration Test 

Scenario: Update Loyalty with email
	Given when send the updated loyalty values with email
	When loyalty update service will be called for email
	Then customer profile is updated with email
	
Scenario: Update Loyalty
	Given when send the updated loyalty values
	When loyalty update service will be called
	Then customer profile is updated

##
@ignore	
Scenario: Create Loyalty Profile
	Given input customer data
	When create loyalty profile service will be called
	Then customer profile is sucessfully created