
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.openqa.selenium.support.FindAll;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import groovy.json.JsonSlurper;
import groovyjarjarantlr4.v4.codegen.model.decl.Decl;

import java.util.List;
import java.util.Map;

import javax.security.auth.callback.LanguageCallback;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import groovy.json.JsonSlurper;
public class Task6 {
   
	@Test
	public void langugaeEng() {
		RestAssured.baseURI = "https://wearecommunity.io";
		RestAssured.basePath="/api/v2";
		
		Response response =given()
			    .header("Accept", "application/json") 
			    .when().get("/events").then().extract().response();
		//1st way>
		/*
		 * List<Map<String, ?>> events = response.jsonPath().getList("events");
		 * events.stream() .filter(event -> "En".equals(event.get("language")))
		 * .map(event -> event.get("title")) .forEach(System.out::println);
		 */	 
		// 2nd way> 
		  List<Map<String, ?>> events1 = response.jsonPath().getList("events");
		   for (Map<String, ?> event : events1) {
	            String language = (String) event.get("language");
	            if ("En".equals(language)) {  
	                String title = (String) event.get("title");  
	                System.out.println(title);  
	            }
	        }
	}
}