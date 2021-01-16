package com.online.store.demo.steps.catalogue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import com.online.store.demo.common.ApiEnum;
import com.online.store.demo.common.BaseTestingStep;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

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
	 * 1. Scenario: Update Loyalty with/without email
	 */
	@Given("^I Set GET order service api endpoint$")
	public void i_Set_GET_order_service_api_endpoint() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^fetch order service will be called$")
	public void fetch_order_service_will_be_called() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		response=request.when().get(ordersHost.concat(ApiEnum.ORDERS_API.value()));
	    throw new PendingException();
	}

	@Then("^receive valid HTTP response code (\\d+)$")
	public void receive_valid_HTTP_response_code(int ok , int error) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertThat(response.getStatusCode()).isIn(ok, error);
	    throw new PendingException();
	}

	@Given("^create order data with request body \"([^\"]*)\"$")
	public void create_order_data_with_request_body(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^create  order postOrderscservice will be called$")
	public void create_order_postOrderscservice_will_be_called() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^customer profile is successfully created (\\d+) or existed (\\d+)$")
	public void customer_profile_is_successfully_created_or_existed(int arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Override
	protected HttpHeaders buildHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected HttpHeaders buildHeaders(HttpHeaders httpHeaders) {
		// TODO Auto-generated method stub
		return null;
	}
}
