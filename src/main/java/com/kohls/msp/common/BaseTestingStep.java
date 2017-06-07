package com.kohls.msp.common;

import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.kohls.msp.main.Application;

/**
 * This class contains common test methods
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */
@ContextConfiguration(classes = { Application.class })
@TestPropertySource(locations = {"classpath:properties/env.properties"})
public abstract class BaseTestingStep {

	protected abstract HttpHeaders buildHeaders();

}