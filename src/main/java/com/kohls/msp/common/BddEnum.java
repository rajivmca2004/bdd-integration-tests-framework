package com.kohls.msp.common;

/**
 * This class contains common Constants
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */
public enum BddEnum {
	X_CORRELATION_ID("x-correlation-id"), 
	X_CHANNEL("x-channel"), 
	ACCESS_TOKEN("access_token"), 
	X_APP_API_KEY("x-app-api_key"), 
	DEV_ENV("application-dev.properties"), 
	LOCAL_ENV("application-default.properties");

	private String value;

	BddEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
