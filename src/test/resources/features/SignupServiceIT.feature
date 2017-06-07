@signupServices
Feature: Cucumber -  SignUp Services Integration Test 

Scenario Outline: set initial configuration for Encryption-Decryption Microservices 
	Given config setup for Encryption Decryption
	And channel id for encryption is "<channel>"
	Examples: 
		| channel |
		|    ios |