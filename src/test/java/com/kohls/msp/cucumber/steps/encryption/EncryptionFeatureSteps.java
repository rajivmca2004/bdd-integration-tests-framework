/**
 * 
 */
package com.kohls.msp.cucumber.steps.encryption;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import com.kohls.msp.common.BaseTestingStep;
import com.kohls.msp.common.BddEnum;
import com.kohls.msp.common.BddTestUtil;
import com.kohls.msp.common.MspApiEnum;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Black Box testing BDD- Encryption Integration Services
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/06/2017
 */
public class EncryptionFeatureSteps extends BaseTestingStep {
	
	private static final String ENCRYPTION_CORRELATION_ID = "fdafadf";
	private static final String APPLICATION_JSON="application/json";

	@Value("${msp.accept.header}")
	private String accept;
	
	@Value("${msp.encryption.channel.header}")
	private String channel;
	
	@Value("${msp.encryption.host}")
	private String encryptionHost;
	
	private static HttpHeaders headers;
	
	private static Response encryptResponse, decryptResponse;
	private RequestSpecification request;
	
	/*
	 * All Initial config code will go here for #Loyalty Services
	 */
	@Given("^config setup for Encryption Decryption$")
	public void config_setup_for_Encryption_Decryption() throws Throwable {
		headers= buildHeaders();
	}
	
	/*
	 * 1. Scenario: Encrypt plain text
	 */
	
	@Given("^send the plain string with request body \"([^\"]*)\"$")
	public void send_the_plain_string_with_request_body(String fileBody) throws Throwable {
		// Prepare request
		byte[] file = Files.readAllBytes(Paths.get(fileBody));
		request = given().headers(headers).body(file);
	}
	
	@Given("^channel id for encryption is \"([^\"]*)\"$")
	public void channel_id_for_encryption_is(String arg1) throws Throwable {
		headers.set(BddEnum.X_CHANNEL.value(), channel);
	}

	@When("^encryption service will be called$")
	public void encryption_service_will_be_called() throws Throwable {
		// RESTAssured REST API call
		encryptResponse = request.when().post(encryptionHost.concat(MspApiEnum.ENCRYPT_API.value()));
	}

	@Then("^encrypted string is returned$")
	public void encrypted_string_is_returned() throws Throwable {
		assertThat(encryptResponse.getStatusCode()).isEqualTo(200);
	}
	
	/*
	 * 2. Scenario: Decrypt valid JSON
	 */

	@Given("^send encrypted  valid request JSON with body$")
	public void send_encrypted_valid_request_JSON_with_body() throws Throwable {
		/*
		 * Add response of the Encyption API
		 * Input: Encrypted JSON text
		 */
		request = given().headers(headers).body(encryptResponse.getBody().asString());
	}

	@When("^decryption service will be called$")
	public void decryption_service_will_be_called() throws Throwable {
		decryptResponse = request.when().post(encryptionHost.concat(MspApiEnum.DECRYPT_API.value()));
	}
	
	@Then("^decrypted plain text JSON is returned \"([^\"]*)\"$")
	public void decrypted_plain_text_JSON_is_returned(String fileBody) throws Throwable {
		assertThat(decryptResponse.getStatusCode()).isEqualTo(200);
		/*
		 *  Comparing decrypted JSON response fron the decrypt API with the actual request JSON of the Encrypt API
		 *  Assert for JSON comparison
		 */
		assertEquals(BddTestUtil.readJsonFile("src/test/resources/encryption/encryptionRequest.json"), decryptResponse.getBody().asString(), true);
	}
	
	/*
	 * Set Common Header
	 */
	@Override
	protected HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(BddEnum.ACCEPT.value(), accept);
		headers.set(BddEnum.X_CORRELATION_ID.value(), ENCRYPTION_CORRELATION_ID);
		headers.set(BddEnum.CONTENT_TYPE.value(),APPLICATION_JSON );
		return headers;
	}
}

