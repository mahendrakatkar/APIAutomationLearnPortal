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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class DataDrivenTestDay2 {
	// to avoid using baseUri("http://api.zippopotam.us/") in every TC> USE
	// FOLLOWING RequestSpecification

	@BeforeClass
	public void setup() {
		RestAssured.requestSpecification = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us/")
				.setContentType("application/json").build();
		
		RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				// OTHER FIELDS LIKE
				//.expectContentType("application/json")
				// .expectResponseTime(5L, TimeUnit.SECONDS)
				// .expectBody(null)
				.build();
	}

	@Test
	public void verifyState1() {
		/*
		 * given() .spec(requestSpecification) .when()
		 */
	get("/US/00210")
		.then().assertThat().spec(responseSpecification)
		.body("places[0].state", equalTo("New Hampshire"));
	}
	
	@Test
	public void verifyState2() {
		/*
		 * given() .spec(requestSpecification) .when()
		 */
	get("/US/12345")
		.then().assertThat().spec(responseSpecification)
		.body("places[0].state", equalTo("New York"));
	}
	
	@Test
	public void verifyState3() {
		/*
		 * given() .spec(requestSpecification) .when()
		 */
	get("/de/24848")
		.then().assertThat().spec(responseSpecification)
		.body("places[0].state", equalTo("Schleswig-Holstein"));
	}
	
	@Test
	public void verifyState4() {
		/*
		 * given() .spec(requestSpecification) .when()
		 */
	get("/ca/Y1A")
		.then().assertThat().spec(responseSpecification)
		.body("places[0].state", equalTo("Yukon"));
	}
	
	@Test(dataProvider = "zipcodedata")
	public void verifyStates(String countryCode, String zipCode, String state) {
		get("/"+countryCode+"/"+zipCode)
		.then().assertThat().spec(responseSpecification)
		.body("places[0].state", equalTo(state));
	}
	
	@DataProvider(name = "zipcodedata")
	public Object[][] getZippedCode() {
		return new Object[][] {
			{"US","00210","New Hampshire"},
			{"US","12345","New York"},
			{"de","24848","Schleswig-Holstein"},
			{"ca","Y1A","Yukon"}
		};
	}
}
