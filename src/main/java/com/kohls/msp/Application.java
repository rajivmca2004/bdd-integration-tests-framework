package com.kohls.msp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.kohls.msp.common.BddEnum;

/**
 * This class contains main Spring Boot class
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */
@SpringBootApplication
public class Application {

	private static final Resource[] DEV_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(BddEnum.DEV_ENV.value()) };
	private static final Resource[] TEST_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(BddEnum.TEST_ENV.value()) };
	private static final Resource[] LOCAL_DEFAULT_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(BddEnum.LOCAL_ENV.value()) };

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/*
	 * Environment profiling
	 */

	@Profile("dev")
	public static class DevConfig {
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(DEV_PROPERTIES);
			pspc.setIgnoreUnresolvablePlaceholders(true);
			return pspc;
		}
	}

	@Profile("test")
	public static class TestConfig {
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(TEST_PROPERTIES);
			return pspc;
		}
	}

	@Profile("default")
	public static class ProdConfig {
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
			PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			pspc.setLocations(LOCAL_DEFAULT_PROPERTIES);
			return pspc;
		}
	}
}