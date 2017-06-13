package com.kohls.msp.cucumber.steps.loyalty;

import static io.restassured.RestAssured.given;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.kohls.msp.common.BddEnum;
import com.kohls.msp.common.MspApiEnum;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MspUtilService{
	private static final String ACCESS_TOKEN = "access_token";
	private static final String SECRET_VALUE = "xjqqE7ajBlP7Ns3e";
	private static final String SECRET = "secret";
	private static final String CLIENT_ID_VALUE = "w0OaAT7UeDLisjZvxqysWE9BLL6EdbCw";
	private static final String CLIENT_ID = "client_id";
	private static final String PASSWORD_VALUE = "Kohls123";
	private static final String EMAIL = "rajivtest5@kohls.com";
	private static final String USER_ID = "userId";
	private static final String PASSWORD = "password";
	private static final String GRANT_TYPE = "grant_type";
	private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	public String getAccessToken(String env) {

		HttpHeaders headers = buildHeaders();
		RequestSpecification request = given()
				.config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.appendDefaultContentCharsetToContentTypeIfUndefined(false)))
				.contentType(ContentType.URLENC)
				.headers(headers)
				.formParam(GRANT_TYPE, PASSWORD)
				.formParam(USER_ID, EMAIL)
				.formParam(PASSWORD, PASSWORD_VALUE)
				.formParam(CLIENT_ID, CLIENT_ID_VALUE)
				.formParam(SECRET, SECRET_VALUE);

		Response response = request.when().post(env.concat(MspApiEnum.OAPI_SIGNIN_PROFILE_API.value()));
		JsonPath jsonPath = new JsonPath(response.getBody().asString());
		String access_token = jsonPath.getString(ACCESS_TOKEN);
		return access_token;
	}

	protected HttpHeaders buildHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set(HttpHeaders.CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED);
		headers.set(BddEnum.X_APP_API_KEY.value(), CLIENT_ID_VALUE);
		return headers;
	}

}
