package com.kohls.msp.cucumber.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * This class is configured to run all cucumber feature describe by <i>feature</i> option in {@link CucumberOptions}.
 *
 * @author rajiv.srivastava@kohls.com
 * @since 06/03/2017
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = false,
		// features = { "src/test/resources/features" },
		features = { "classpath:features/" },
		plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json" }, 
		tags = {"~@ignore" }, 
		glue = { "cucumber.api.spring", "com.kohls.msp.cucumber.steps" })
public class MspTestCucumberRunnerTest {

}
