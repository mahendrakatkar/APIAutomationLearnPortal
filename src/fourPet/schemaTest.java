package fourPet;
import io.opentelemetry.sdk.logs.data.Body;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import repackage.Repackage;
import org.json.JSONArray;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import com.aventstack.extentreports.util.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import javax.print.DocFlavor.STRING;


import static io.restassured.RestAssured.*;

public class schemaTest {
	@Test
	public void testPetdata() {
		
		given().baseUri("https://petstore.swagger.io/v2")
				.contentType("application/json")
				.when().get("/pet/9223372036854775807") 					
				.then().assertThat().statusCode(200)
			.body(matchesJsonSchemaInClasspath("resources\\schemaPet.json"));
				
}
}
