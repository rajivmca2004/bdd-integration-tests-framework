package com.kohls.msp.common;

import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;

import com.kohls.msp.Application;

/**
 * This class contains common test methods
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */
@ContextConfiguration(classes = { Application.class })
public abstract class BaseTestingStep {

	protected abstract HttpHeaders buildHeaders();

	protected abstract HttpHeaders buildHeaders(HttpHeaders httpHeaders);
}