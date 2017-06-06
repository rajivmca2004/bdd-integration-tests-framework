package com.kohls.msp.common;

public enum MspApiEnum {
	LOYALTY_PROFILE_API("/v1/loyalty/profile"), 
	LOYALTY_API("/v1/loyalty"),
	ENCRYPT_API("/v1/encrypt"),
	DECRYPT_API("/v1/decrypt");

	private String value;

	MspApiEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
