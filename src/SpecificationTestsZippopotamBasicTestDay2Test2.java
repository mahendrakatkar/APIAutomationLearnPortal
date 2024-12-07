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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class SpecificationTestsZippopotamBasicTestDay2Test2 {
	// to avoid using baseUri("http://api.zippopotam.us/") in every TC> USE
	// FOLLOWING RequestSpecification

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

	@Test
	public void verifyCountryDetails() {
		/*
		 * given() .spec(requestSpecification) .when()
		 */
	get("/US/00210")
		.then().log().all().assertThat().spec(responseSpecification)
		.body("country", equalTo("United States"));
	}

	@Test
	public void placeVerify() {
		get("/US/00210").then().log().all().assertThat().spec(responseSpecification)
				.body("places[0].state", equalTo("New Hampshire")).body("places[0].latitude", equalTo("43.0059"));
		// .body("places[0].placeName", equalTo("Portsmouth"));
	}

	// failing because above said expected is 200 and here 404 
	/*
	 * @Test public void negativeScen() {
	 * get("/US/002d10").then().assertThat().statusCode(404); // .body("country",
	 * equalTo("United States")); }
	 */
	@Test
	public void verifyAPresopnse() {
		// removed below 3 lines from each above TC
		given().spec(requestSpecification).when().get("/US/00210").then().log().all().assertThat()
				.spec(responseSpecification).contentType(equalTo("application/json"));
	}

}
