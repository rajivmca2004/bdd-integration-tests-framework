package com.kohls.msp.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.kohls.msp.common.BddEnum;

/**
 * This class contains custom property configurer for the externalization
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/20/2017
 */
@Configuration
public class PropertySourcesConfig {

	private static final Resource[] DEV_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(BddEnum.DEV_ENV.value()) };
	private static final Resource[] TEST_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(BddEnum.TEST_ENV.value()) };
	private static final Resource[] LOCAL_DEFAULT_PROPERTIES = new ClassPathResource[] {
			new ClassPathResource(BddEnum.LOCAL_ENV.value()) };

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
