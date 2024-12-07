import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.support.FindAll;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;
import groovy.json.JsonSlurper;
import groovyjarjarantlr4.v4.codegen.model.decl.Decl;
import java.util.List;
import java.util.Map;
import javax.security.auth.callback.LanguageCallback;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import groovy.json.JsonSlurper;
   
public class Task7 {
	
	@Test
	public void weatherTask7A() {
		RestAssured.baseURI="https://api.openweathermap.org/data/2.5/weather";
		RestAssured.requestSpecification=(RequestSpecification) new RequestSpecBuilder()
				.addQueryParam("q", "hyderabad")
				.addQueryParam("appid", "7b0c487716db8559cb745b07f49078fa").build();
				
		 Response response = given().when().get().then().statusCode(200).body("name",equalTo("Hyderabad")).extract().response();
	}
	@Test
	public void weatherTask7B() {
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		RestAssured.requestSpecification=(RequestSpecification) new RequestSpecBuilder()
				.addQueryParam("lat", "lat")
				.addQueryParam("lon", "78.4744")
				.addQueryParam("appid", "7b0c487716db8559cb745b07f49078fa")
				.build();
		Response response = given().when().get().then().statusCode(200).body("name",equalTo("Hyderabad")).extract().response();
		String cityName= response.path("name");
		System.out.println("City Name is "+cityName); // city name
		String CountryName= response.path("sys.country");
		System.out.println("CountryName Name is "+CountryName); //country name
		float MinTemp= response.path("main.temp_min");
		System.out.println("Minimum temprature is "+MinTemp);   //min temp
		assertTrue(MinTemp>0);
	     if (MinTemp > 0) {
	    	 System.out.print("Minimum temprature is greater than 0 :");
	            System.out.println(true);
	        } else {
	            System.out.println(false); }
	      float mainTemp= response.path("main.temp");         //main temp
			System.out.println("main temprature is "+mainTemp);
			  if (mainTemp > 0) {
			    	 System.out.print("main temprature is greater than 0 :");
			            System.out.println(true);
			        } else {System.out.print("main temprature is not greater than 0 :");
			            System.out.println(false);
			        }}}