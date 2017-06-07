package com.kohls.msp.cucumber.steps.loyalty;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import com.kohls.msp.common.BaseTestingStep;
import com.kohls.msp.common.BddEnum;
import com.kohls.msp.common.MspApiEnum;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Black Box testing BDD - Loyalty Integration Tests
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */
public class LoyaltyFeatureSteps extends BaseTestingStep {

	private static final String LOYALTY_CORRELATION_ID = "fdafadf";
	private static final String APPLICATION_JSON="application/json";

	@Value("${msp.loyalty.channel.header}")
	private String channel;
	
	@Value("${msp.loyalty.host}")
	private String loyaltyHost;
	
	private Response response;
	private RequestSpecification request;
	
	private static HttpHeaders headers;
	
	/*
	 * 1. Scenario: Update Loyalty with/without email
	 */
	
	/*
	 * All Initial config code will go here for #Loyalty Services
	 */
	@Given("^config setup for Loyalty$")
	public void config_setup() throws Throwable {
		headers= buildHeaders();
	}

	@Given("^send the updated loyalty values with request body \"([^\"]*)\"$")
	public void send_the_updated_loyalty_values_with_request_body(String fileBody) throws Throwable {
		// Prepare request
		byte[] file = Files.readAllBytes(Paths.get(fileBody));
		request = given().headers(headers).body(file);
	}
	
	@Given("^channel id for loyaty is \"([^\"]*)\"$")
	public void channel_id_for_loyaty_is(String arg1) throws Throwable {
		headers.set(BddEnum.X_CHANNEL.value(), channel);
	}

	@When("^loyalty update service will be called$")
	public void loyalty_update_service_will_be_called() throws Throwable {
		// RESTAssured REST API call
		response=request.when().post(loyaltyHost.concat(MspApiEnum.LOYALTY_PROFILE_API.value()));
	}

	@Then("^customer profile is updated$")
	public void customer_profile_is_updated() throws Throwable {
		// Assert for JSON comparison
		assertEquals("{\"message\": \"Profile Updated Successfully\"}", response.getBody().asString(), true);
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	/*
	 * 3. Scenario: Create Loyalty Profile
	 */
	
	/*
	 * Failure Test Case
	 */
	
	@Given("^input create customer data with request body \"([^\"]*)\"$")
	public void input_customer_data_with_request_body(String fileBody) throws Throwable {
		byte[] file = Files.readAllBytes(Paths.get(fileBody));
		request = given().headers(buildHeaders()).body(file);
	}

	@When("^create loyalty profile service will be called$")
	public void create_loyalty_profile_service_will_be_called() throws Throwable {
		response=request.when().post(loyaltyHost.concat(MspApiEnum.LOYALTY_API.value()));
	}

	@Then("^customer profile is sucessfully created$")
	public void customer_profile_is_sucessfully_created() throws Throwable {
		assertThat(response.getStatusCode()).isEqualTo(200);
	}
	
	@Override
	protected HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(BddEnum.CONTENT_TYPE.value(), APPLICATION_JSON);
		// Note:Need to change access token after expiry
		headers.set(BddEnum.ACCESS_TOKEN.value(), "unxbeHnM3oWnSvVHSYeZMTmAt0hA");
		headers.set(BddEnum.X_CORRELATION_ID.value(), LOYALTY_CORRELATION_ID);
		headers.set(BddEnum.X_CHANNEL.value(), channel);
		return headers;
	}
}
