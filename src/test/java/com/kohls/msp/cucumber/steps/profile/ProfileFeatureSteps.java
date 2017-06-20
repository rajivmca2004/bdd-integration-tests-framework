package com.kohls.msp.cucumber.steps.profile;

import static com.kohls.msp.common.BddEnum.X_APP_API_KEY;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.kohls.msp.common.BaseTestingStep;
import com.kohls.msp.common.MspApiEnum;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Black Box testing BDD - Profile Integration Tests
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/08/2017
 */
public class ProfileFeatureSteps extends BaseTestingStep {

	private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	private Response response;
	private RequestSpecification request;

	private static HttpHeaders headers;

	@Value("${openapi.url.HTTPS}")
	private String openAPI;

	@Value("${openapi.app.key}")
	private String openAPIKey;
	
	@Value("${openapi.app.secret}")
	private String secret;
	
	/*
	 * All Initial config code will go here for #Loyalty Services
	 */

	@Given("^grant type is \"([^\"]*)\"$")
	public void grant_type_is(String grantType) throws Throwable {
		headers = buildHeaders();
		request = given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType(ContentType.URLENC);
		headers.set(X_APP_API_KEY.value(), openAPIKey);
		request.headers(headers);
		request.formParam("grant_type", grantType);
	}

	@Given("^user id is \"([^\"]*)\"$")
	public void user_id_is(String userId) throws Throwable {
		request.formParam("userId", userId);
	}

	@Given("^user password is \"([^\"]*)\"$")
	public void user_password_is(String password) throws Throwable {
		request.formParam("password", password);
	}

	@When("^access token service is called$")
	public void access_token_service_is_called() throws Throwable {
		request.formParam("client_id", openAPIKey);
		request.formParam("secret", secret);
		response = request.when().post(openAPI.concat(MspApiEnum.OAPI_SIGNIN_PROFILE_API.value()));
	}

	@Then("^retrun access token$")
	public void retrun_access_token() throws Throwable {
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
		String access_token = jsonPath.getString("access_token");
		assertThat(access_token).isNotEmpty();
	}

	@Then("^response code is (\\d+)$")
	public void response_code_is(int statusCode) throws Throwable {
		assertThat(response.getStatusCode()).isEqualTo(statusCode);
	}

	/*
	 * Set Common Header
	 */
	@Override
	protected HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED);
		return headers;
	}

	@Override
	protected HttpHeaders buildHeaders(HttpHeaders httpHeaders) {
		return null;
	}
}
