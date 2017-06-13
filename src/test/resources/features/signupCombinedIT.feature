#@ignore
Feature: Cucumber - Combined Integration Test for multiple Services 

Scenario Outline: Create an encrypted Json
	Given send the plain string with request body "<body>" 
	When encryption service will be called 
	Then status code 200 ok is returend
	Examples: 
		| body |
		|    src/test/resources/encryption/encryptionRequest.json |

Scenario Outline:: Decrypt valid JSON body 
	Given send encrypted  valid request JSON with body
	When decryption service will be called 
	Then status code 200 ok is returned
	And return expected response message "<body>"
	Examples:
		| body |
		| src/test/resources/encryption/encryptionRequest.json | 
	
Scenario Outline: Signup for loyalty 
	Given send the updated loyalty values with request body "<body>" 
	When loyalty update service will be called 
	Then customer profile is updated 
	Examples: 
		| body |
		|    src/test/resources/loyalty/loyaltyUpdate.json |
		|    src/test/resources/loyalty/loyaltyUpdateWithEmail.json |