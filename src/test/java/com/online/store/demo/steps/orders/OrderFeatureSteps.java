package com.online.store.demo.steps.orders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.junit.Assert;

import com.online.store.demo.common.ApiEnum;
import com.online.store.demo.common.BaseTestingStep;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

/**
 * Black Box testing BDD - Loyalty Integration Tests
 *
 * @author rajiv.srivastava
 */
public class OrderFeatureSteps extends BaseTestingStep {

	@Value("${orders.host}")
	private String ordersHost;

	private Response response;
	private RequestSpecification request;

	/*
	 * 1. Scenario: This Order microservice will call both catalogue and customer-management microservice and return aggregated result
	 */
	@Given("^I Set GET order service api endpoint$")
	public void i_Set_GET_order_service_api_endpoint() throws Throwable {
		RestAssured.baseURI = ordersHost.concat(ApiEnum.ORDERS_API.value());
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	@When("^fetch order service will be called$")
	public void fetch_order_service_will_be_called() throws Throwable {
		response = request.given().get();
	}

	@Then("^receive valid HTTP response code (\\d+)$")
	public void receive_valid_HTTP_response_code(int ok) throws Throwable {
		//Added Assert if orders is more than 0
		String jsonString = response.asString();
		List<Map<String, String>> orders = JsonPath.from(jsonString).get();
		Assert.assertTrue(orders.size() > 0);
		
		assertThat(response.getStatusCode()).isEqualTo(200);
	}

	@Given("^create order data with request body \"([^\"]*)\"$")
	public void create_order_data_with_request_body(String arg1) throws Throwable {
	}

	@When("^create  order postOrderscservice will be called$")
	public void create_order_postOrderscservice_will_be_called() throws Throwable {
	}

	@Then("^customer profile is successfully created (\\d+) or existed (\\d+)$")
	public void customer_profile_is_successfully_created_or_existed(int arg1, int arg2) throws Throwable {
	}

	@Override
	protected HttpHeaders buildHeaders() {
		return null;
	}

	@Override
	protected HttpHeaders buildHeaders(HttpHeaders httpHeaders) {
		return null;
	}
}
