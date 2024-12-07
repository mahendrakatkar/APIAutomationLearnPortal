import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ExtractDataTest {
	
	@BeforeClass
	public void setup() {
		RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us/")
				.setContentType("application/json").build();
		
		RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				// OTHER FIELDS LIKE
				// .expectContentType("application/json")
				// .expectResponseTime(5L, TimeUnit.SECONDS)
				// .expectBody(null)
				.build();
	}

	// expectation is extract
	@Test
	public void verifyData() {
		 Response response = given()
				 .spec(requestSpecification)
				 			.when().get("/US/00210")
				 			.then().extract().response();
		
	int statuscode=	response.statusCode();
	String country=response.path("country");
	String state=response.path("places[0].state");
	
	System.out.println("status code is "+statuscode);
	System.out.println(country+" - "+state);
		
		
	}
	
	
	
	
}
