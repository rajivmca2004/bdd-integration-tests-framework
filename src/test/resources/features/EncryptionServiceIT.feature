Feature: Cucumber - Encryption Decryption Microservices Integration Test 

Scenario: set initial configuration for Encryption-Decryption Microservices 
	Given config setup for Encryption Decryption
	
Scenario Outline: encrypt plain string 
	Given send the plain string with request body "<body>" 
	When encryption service will be called 
	Then encrypted string is returned 
	Examples: 
		| body |
		|    src/test/resources/mockJson/encyptionRequest.json |
		
Scenario Outline: decrypt valid JSON body 
	Given send encrypted  valid request JSON with body
	When decryption service will be called 
	Then decrypted string is returned "<body>"
	Examples: 
		| body |
		|    src/test/resources/mockJson/encyptionRequest.json |