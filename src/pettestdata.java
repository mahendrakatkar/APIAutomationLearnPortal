import org.testng.annotations.Test;

import io.opentelemetry.sdk.logs.data.Body;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.hamcrest.Matcher;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class pettestdata {

	@Test
	public void testPetdata() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		Response response = (Response) RestAssured.given().basePath("/v2")
				// .contentType("application/json")
				.when().get("/pet/12345") 					// TASK 3.1 GET CALL
				.then().assertThat().statusCode(200) 		// TASK 3.2 STATUS CODE is 200
				.body("status", equalTo("pending")) 		// task 3.6 status is ‘pending’
				// .body("category[0].name", equalTo("dog"))
				.body("name", equalTo("snoopie")) 			// task 3.5 name is ‘snoopie’  
				.extract();

		// String contentType= response.getContentType(); // System.out.println("content type is "+contentType);

		boolean contentType1 = 
				response.getContentType().equals("application/json");
		System.out.println("contentType is application/json -" + contentType1); // TASK 3.3 content type is ‘application/json’
																				
		JsonPath jsonPath = response.jsonPath();
		String DogName = jsonPath.getString("category.name");
		System.out.println("Dog Name is -" + DogName); // task 3.4 pet is a ‘dog’

		String nameSno = jsonPath.getString("name");
		System.out.println("name is " + nameSno);

		
		
		
		/*
		 * Response response =given() .baseUri("https://petstore.swagger.io")
		 * .basePath("/v2") .contentType("application/json") .when() .get("/pet/12345")
		 * .then().extract().response();
		 * 
		 * response.getStatusCode();
		 */

	}

}
