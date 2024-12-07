import static io.restassured.RestAssured.when;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import repackage.Repackage;
import org.testng.Assert;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.ss.formula.ptg.GreaterEqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterThanPtg;
import org.json.JSONArray;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import javax.print.DocFlavor.STRING;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.gherkin.model.Given;
public class Task5 {	
	
	  @BeforeTest
	  public void setup() { // Setting up the default request and
	  RequestSpecification requestSpec = new
			  				RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com")
			  			//	.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n"
			  			//			+ "")
			  				.build();
	  	  ResponseSpecification responseSpec = new ResponseSpecBuilder()
			  		//.expectStatusCode(200)
			  		.build();
	  }
	  @Test 
	  public void getEmployeeDetails() { 
		  Response response = given()
		  	.cookie("Cookie","humans_21909=1; _gid=GA1.2.901162151.1732720187; __gads=ID=275b3264f2270497:T=1732720189:RT=1732769679:S=ALNI_Mascdm_OWX8uIHZl0Q996gS2Z081Q; __gpi=UID=00000f793355ca1c:T=1732720189:RT=1732769679:S=ALNI_MYJse7dSQyQqTCHeM7-jkS62Bdwew; __eoi=ID=66a3c484a9844165:T=1732720189:RT=1732769679:S=AA-AfjZSpqzMmYHZIk33SwxK9Vxi; XSRF-TOKEN=eyJpdiI6InRxWVpVZWJFak02VTVMa3RVSmNvL1E9PSIsInZhbHVlIjoiWTNiUWwyUEpXMERIRGRhKzlVRlJKSy9vQ0lrTXdRekk0L0E0Tmg5WE1zWmY3aVhnUklaRUpiWC9ObjluRnl2VyIsIm1hYyI6ImM0M2ViY2FjZTA2YzFiOTQ5NDUzYTMxZWFlMzUyYzQ5YTYyMTIyMDRkMTdiYmFiYjU0MGY2NTRhOTQzMTUzOWQifQ%3D%3D; laravel_session=eyJpdiI6IkhMYVNmZmladHAwQWRMNnNhMGVEb2c9PSIsInZhbHVlIjoiUWx1bXZhRktCK2tRWlVkSjZac0RmaWNEN3BYQ0RUVXFuMDR0OXF5RGJiZXpPb0VXcmxRY2NWWWd4MXlxcjhncSIsIm1hYyI6ImU0MDU5NTU3MzlmN2ZjOGU5Y2UyZTIyZjViZGEyZDk0NTk4Njg0MDA2NDY5ZTZmYjlkNTQ3YTQzYjI0YzczNDMifQ%3D%3D; _ga=GA1.2.1896410988.1732720187; _ga_GC9YM40HPS=GS1.1.1732767607.2.1.1732769683.55.0.0; FCNEC=%5B%5B%22AKsRol9kPlW4vc8yF8MY3YyMCtWaAyVQoNFLdJoRRklvdyqYPbZKU6tndOocn-g6lw6GWJjXp2p7MfsCSesINrPDRveMavwiEyJnwOyaQKCaOk4L-J5UlpAfzRYL5Ov5H_9QXN3WMZcNxYYS3omzrU_6HpYZplj7wQ%3D%3D%22%5D%5D")
		  	.when()
		  .get("/api/v1/employees")
		  .then().assertThat().statusCode(200).extract().response(); 
		  }
	 
}