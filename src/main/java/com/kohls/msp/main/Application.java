package com.kohls.msp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * This class contains main class
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/01/2017
 */
@Configuration
@ComponentScan(basePackages = { "com.*" })
@PropertySource("classpath:application.properties")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}