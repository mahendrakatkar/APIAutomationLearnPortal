import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import repackage.Repackage;
import org.json.JSONArray;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import javax.print.DocFlavor.STRING;
import static io.restassured.RestAssured.*;

public class ZippopotamBasicTestDay1 {
	@Test
	public void verifyCountryDetails() {
		given().baseUri("http://api.zippopotam.us/")
		.when().get("/US/00210")
		.then().log().all().assertThat().statusCode(200)
		.body("country", equalTo("United States"));
	}
	@Test
	public void placeVerify() {
		given().baseUri("http://api.zippopotam.us/")
		.when().get("/US/00210")
		.then().log().all().assertThat().statusCode(200)
		.body("places[0].state",equalTo("New Hampshire"))
		.body("places[0].latitude",equalTo("43.0059"));
		//.body("places[0].placeName", equalTo("Portsmouth"));
	}
	
	@Test
	public void negativeScen() {
		given().baseUri("http://api.zippopotam.us/")
		.when().get("/US/002d10") 
		.then().log().all().assertThat().statusCode(404);
		//.body("country", equalTo("United States"));
	}
	
	@Test
	public void verifyAPresopnse() {
		given().baseUri("http://api.zippopotam.us/")               
		.when().get("/US/00210")
		.then().log().all().assertThat().statusCode(200)
		.contentType(equalTo("application/json"));
	}
}
