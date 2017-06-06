Feature: Cucumber - Combined Integration Test to test multiple Services 

Scenario Outline: Create an encrypted email 
	Given send the plain string with request body "<body>" 
	When encryption service will be called 
	Then encrypted string is returned 
	Examples: 
		| body |
		|    src/test/resources/mockJson/encyptionRequest.json |

Scenario: Decrypt the email 
	Given send encrypted  valid request JSON with body
	When decryption service will be called 
	Then decrypted string is returned 
	
Scenario Outline: Use it to signup for loyalty 
	Given when send the updated loyalty values with request body "<body>" 
	When loyalty update service will be called 
	Then customer profile is updated 
	Examples: 
		| body |
		|    src/test/resources/mockJson/loyaltyUpdate.json |
		|    src/test/resources/mockJson/loyaltyUpdateWithEmail.json |