package com.kohls.msp.cucumber.steps;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.Properties;

import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;

import com.kohls.msp.common.BaseTesting;
import com.kohls.msp.main.MspBddApplication;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Black Box testing BDD POC with Junit+ Cucumber + RESTAssured + AssertJ+SpringBoot Spring Boot
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */

@ContextConfiguration(classes = MspBddApplication.class)
public class LoyaltyFeatureStepDefsIT extends BaseTesting {

	private Response response;
	private RequestSpecification request;

	/*
	 * 1. Scenario: Update Loyalty with email
	 */
	@Given("^when send the updated loyalty values with email$")
	public void when_send_the_updated_loyalty_values_with_email() throws IOException {
		byte[] file = Files.readAllBytes(Paths.get("src/test/resources/mockJson/loyaltyUpdateWithEmail.json"));
		request = given().headers(buildHeaders()).body(file);
	}

	@When("^loyalty update service will be called for email$")
	public void loyalty_service_will_be_called() throws Throwable {
		// RESTAssured REST API call
		response = request.when().post("http://localhost:8003/v1/loyalty/profile");
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("^customer profile is updated with email$")
	public void the_status_code_is() throws Throwable {
		// JSON Assert for JSON comparison
		assertEquals("{\"message\": \"Profile Updated Successfully\"}", response.getBody().asString(), true);
		// AssertJ Matchers
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	/*
	 * 2. Scenario: Update Loyalty
	 */
	@Given("^when send the updated loyalty values$")
	public void when_send_the_updated_loyalty_values() throws Throwable {
		byte[] file = Files.readAllBytes(Paths.get("src/test/resources/mockJson/loyaltyUpdate.json"));
		request = given().headers(buildHeaders()).body(file);
	}

	@When("^loyalty update service will be called$")
	public void loyalty_update_service_will_be_called() throws Throwable {
		// RESTAssured REST API call
		response = request.when().post("http://localhost:8003/v1/loyalty/profile");
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("^customer profile is updated$")
	public void customer_profile_is_updated() throws Throwable {
		// JSON Assert for JSON comparison
		assertEquals("{\"message\": \"Profile Updated Successfully\"}", response.getBody().asString(), false);
		// AssertJ Matchers
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	/*
	 * 3. Scenario: Create Loyalty Profile
	 */
	@Given("^input customer data$")
	public void input_customer_data() throws Throwable {
		byte[] file = Files.readAllBytes(Paths.get("src/test/resources/mockJson/loyaltyCreate.json"));
		request = given().headers(buildHeaders()).body(file);
	}

	@When("^create loyalty profile service will be called$")
	public void create_loyalty_profile_service_will_be_called() throws Throwable {
		// RESTAssured REST API call
		response = request.when().post("http://localhost:8003/v1/loyalty");
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("^customer profile is sucessfully created$")
	public void customer_profile_is_sucessfully_created() throws Throwable {
		// JSON Assert for JSON comparison
		// AssertJ Matchers
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	@Override
	protected HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", "application/json");
		// Note:Need to change access token after expiry
		headers.set("access_token", "O7F1NwW7FiZ4ddenmbAPtAiFAQJk");
		headers.set("x-correlation-id", "fdafadf");
		headers.set("x-channel", "MCOM");
		return headers;
	}
}
