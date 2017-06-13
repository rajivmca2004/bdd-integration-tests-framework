package com.kohls.msp.cucumber.runner;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;

import com.kohls.msp.main.Application;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * This class is configured to run all cucumber feature describe by <i>feature</i> option in {@link CucumberOptions}.
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/03/2017
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features/" }, 
				plugin = { "pretty", "html:target/site/cucumber-pretty","json:target/cucumber.json" }, 
				tags = {"~@ignore" }, 
				glue = { "cucumber.api.spring", "com.kohls.msp.cucumber.steps"},
				strict = false
				//,dryRun=true
				)
@ContextConfiguration(classes = Application.class)
public class MspTestCucumberRunner {
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

}