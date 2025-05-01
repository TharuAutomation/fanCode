package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtils {
	static {
		loadBaseURI();
    }
	private static void loadBaseURI() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(inputStream);
            // Set the baseURI using the value from the properties file
            String baseURI = properties.getProperty("baseURI");
            RestAssured.baseURI = baseURI;  // Set the base URI dynamically
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }
	 public static Response getendPoint(String endpoint) {
		 return RestAssured.given()
                 .when()
                 .get(endpoint);
	    }
	 
	 //Following are not used in the project but for API framework it would be useful
	 /* POST request utility method
	    public static Response post(String endpoint, Object body) {
	        return RestAssured.given().contentType("application/json").body(body).when().post(endpoint);
	    }

	    // PUT request utility method
	    public static Response put(String endpoint, Object body) {
	        return RestAssured.given().contentType("application/json").body(body).when().put(endpoint);
	    }

	    // DELETE request utility method
	    public static Response delete(String endpoint) {
	        return RestAssured.given().when().delete(endpoint);
	    }*/

}
