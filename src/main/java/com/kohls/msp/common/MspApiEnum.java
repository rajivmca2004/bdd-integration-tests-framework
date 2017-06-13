package com.kohls.msp.common;
/**
 * This class contains API URIs
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */
public enum MspApiEnum {
	LOYALTY_PROFILE_API("/v1/loyalty/profile"), 
	LOYALTY_API("/v1/loyalty"),
	ENCRYPT_API("/v1/encrypt"),
	DECRYPT_API("/v1/decrypt"),
	OAPI_SIGNIN_PROFILE_API("/token");

	private String value;

	MspApiEnum(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}
