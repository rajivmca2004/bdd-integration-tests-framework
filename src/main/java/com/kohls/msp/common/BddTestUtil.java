/**
 * 
 */
package com.kohls.msp.common;

import java.io.FileReader;

import org.json.simple.parser.JSONParser;

/**
 * @author rajiv.srivastava1
 *
 */
public class BddTestUtil {

	public static String readJsonFile(String file) {
		JSONParser parser = new JSONParser();
		try {
			return parser.parse(new FileReader(file)).toString();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
