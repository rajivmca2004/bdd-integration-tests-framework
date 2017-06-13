#@ignore
@encryptionServices
Feature: Cucumber - Encryption Decryption Microservices Integration Test 

Scenario Outline: set initial configuration for Encryption-Decryption Microservices
	Given config setup for Encryption Decryption
	And channel id for encryption is "<channel>"
	Examples:
		| channel |
		|    IOS |
		 
Scenario Outline: encrypt plain string
	Given send the plain string with request body "<body>"
	When encryption service will be called
	Then status code 200 ok is returend
	Examples: 
		| body |
		|    src/test/resources/encryption/encryptionRequest.json |
		
Scenario Outline: decrypt valid JSON body
	Given send encrypted  valid request JSON with body
	When decryption service will be called
	Then status code 200 ok is returned
	And return expected response message "<body>"
	Examples:
		| body |
		| src/test/resources/encryption/encryptionRequest.json |