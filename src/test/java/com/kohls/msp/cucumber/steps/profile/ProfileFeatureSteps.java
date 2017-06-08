package com.kohls.msp.cucumber.steps.profile;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import com.kohls.msp.common.BaseTestingStep;
import com.kohls.msp.common.MspApiEnum;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Black Box testing BDD - Profile Integration Tests
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/08/2017
 */
public class ProfileFeatureSteps extends BaseTestingStep {

	private static final String APPLICATION_JSON = "application/json";
	private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	private Response response;
	private RequestSpecification request;

	private static HttpHeaders headers;

	@Value("${msp.profile.host}")
	private String profileHost;

	@Before
	public void setUp() {
		// Initialization code will go here
		headers = buildHeaders();
	}

	/*
	 * All Initial config code will go here for #Loyalty Services
	 */

	@Given("^has authorization id \"([^\"]*)\"$")
	public void has_authorization_id(String authorizationToken) throws Throwable {
		headers.set(HttpHeaders.AUTHORIZATION, authorizationToken);
	}

	@Given("^login with user credetials with login text body \"([^\"]*)\"$")
	public void login_with_user_credetials_with_login_text_body(String txtBody) throws Throwable {
		// Prepare request
		request = given().headers(headers).body(txtBody);
	}

	@When("^signup service is called$")
	public void signup_service_is_called() throws Throwable {
		// RESTAssured REST API call
		response = request.when().post(profileHost.concat(MspApiEnum.OAPI_SIGNIN_PROFILE_API.value()));
	}

	@Then("^customer is able to login$")
	public void customer_is_able_to_login() throws Throwable {
	}

	@Then("^response is \"([^\"]*)\"$")
	public void response_is(String statusCode) throws Throwable {
		assertThat(response.getStatusCode()).isEqualTo(Integer.parseInt(statusCode));
	}

	@Then("^response is not empty$")
	public void response_is_not_empty() throws Throwable {
	}

	/*
	 * Set Common Header
	 */
	@Override
	protected HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, APPLICATION_JSON);
		headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED);
		return headers;
	}
}
