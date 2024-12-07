package authTEST;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import petModel.Category;
import petModel.Pet;
import repackage.Repackage;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.util.Assert;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class BasicAuthTesting {

	@Test
	public void basicAuth() {
		given()
		.auth().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth")
		.then().statusCode(200);
	}
}
