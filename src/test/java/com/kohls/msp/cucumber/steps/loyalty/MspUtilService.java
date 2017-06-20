package com.kohls.msp.cucumber.steps.loyalty;

import static io.restassured.RestAssured.given;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.kohls.msp.common.BddEnum;
import com.kohls.msp.common.MspApiEnum;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Component
public class MspUtilService {
	private static final String ACCESS_TOKEN = "access_token";
	private static final String SECRET = "secret";
	private static final String CLIENT_ID = "client_id";
	private static final String PASSWORD_VALUE = "Kohls123";
	private static final String EMAIL = "rajivtest5@kohls.com";
	private static final String USER_ID = "userId";
	private static final String PASSWORD = "password";
	private static final String GRANT_TYPE = "grant_type";
	private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	@Value("${openapi.app.key}")
	private String openAPIKey;

	@Value("${openapi.app.secret}")
	private String secret;

	public String getAccessToken(String env) {

		HttpHeaders headers = buildHeaders();
		RequestSpecification request = given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType(ContentType.URLENC).headers(headers).formParam(GRANT_TYPE, PASSWORD)
				.formParam(USER_ID, EMAIL).formParam(PASSWORD, PASSWORD_VALUE).formParam(CLIENT_ID, openAPIKey)
				.formParam(SECRET, secret);

		Response response = request.when().post(env.concat(MspApiEnum.OAPI_SIGNIN_PROFILE_API.value()));
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
		String access_token = jsonPath.getString(ACCESS_TOKEN);
		return access_token;
	}

	protected HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED);
		headers.set(BddEnum.X_APP_API_KEY.value(), openAPIKey);
		return headers;
	}

}
