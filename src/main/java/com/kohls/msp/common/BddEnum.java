package com.kohls.msp.common;

public enum BddEnum {
	CONTENT_TYPE("content-type"), ACCESS_TOKEN("access_token"), X_CORRELATION_ID("x-correlation-id"), X_CHANNEL(
			"x-channel");

	private String value;

	BddEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
