import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import repackage.Repackage;

import org.checkerframework.checker.index.qual.LessThan;
import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import javax.print.DocFlavor.STRING;
import static io.restassured.RestAssured.*;

public class SpecificationTestsZippopotamBasicTestDay2 {
	//to avoid using  baseUri("http://api.zippopotam.us/") in every TC> USE FOLLOWING RequestSpecification
	private	RequestSpecification requestSpecification = new RequestSpecBuilder()
		.setBaseUri("http://api.zippopotam.us/")
		.setContentType("application/json")
		.build();
	
	private ResponseSpecification responseSpecification= new ResponseSpecBuilder()
			.expectStatusCode(200)
			// OTHER FIELDS LIKE
			//.expectContentType("application/json")
			// .expectResponseTime(5L, TimeUnit.SECONDS)
			// .expectBody(null)
			.build();
	
	private ResponseSpecification responseSpecificationError= new ResponseSpecBuilder()
			.expectStatusCode(404)
			// OTHER FIELDS LIKE
			//.expectContentType("application/json")
			// .expectResponseTime(5L, TimeUnit.SECONDS)
			// .expectBody(null)
			.build();
	@Test
	public void verifyCountryDetails() {
		given()
			.spec(requestSpecification)     
		.when().get("/US/00210")
		.then().log().all().assertThat().spec(responseSpecification)
		.body("country", equalTo("United States"));
	}
	
	@Test
	public void placeVerify() {
		given()
			.baseUri("http://api.zippopotam.us/")
		.when().get("/US/00210")
		.then().log().all().assertThat().spec(responseSpecification)
		.body("places[0].state",equalTo("New Hampshire"))
		.body("places[0].latitude",equalTo("43.0059"));
		//.body("places[0].placeName", equalTo("Portsmouth"));
	}
	@Test
	public void negativeScen() {
		given()
			.spec(requestSpecification)
		.when().get("/US/002d10") 
		.then().log().all().assertThat().spec(responseSpecificationError);
		//.body("country", equalTo("United States"));
	}
	
	@Test
	public void verifyAPresopnse() {
		given()
			.spec(requestSpecification)   
		.when().get("/US/00210")
		.then().log().all().assertThat().spec(responseSpecification)
		.contentType(equalTo("application/json"));
	}


}
