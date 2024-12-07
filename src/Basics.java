import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import repackage.Repackage;
import org.json.JSONArray;
import com.aventstack.extentreports.util.Assert;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import javax.print.DocFlavor.STRING;
import static io.restassured.RestAssured.*;

public class Basics {
//	Resources available --> 
	//posts    
	//  Get a specific resource data 
				public static void main(String[] args) {
				RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
				String bodyIS= given()
				.when().get("/posts/3")
				.then().statusCode(200).assertThat()
				.body("body", equalTo("et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"))
				.body("title", equalTo("ea molestias quasi exercitationem repellat qui ipsa sit aut"))
				.extract().asString();
				System.out.println(bodyIS);
			
	//Modify a specific resource data
				   given().header("Content-Type", "application/json")
					.body("{\r\n"
							+ "    \"userId\": 1,\r\n"
							+ "    \"id\": 3,\r\n"
							+ "    \"title\": \"test modify\",\r\n"
							+ "    \"body\": \"test modify 2\"\r\n"
							+ "}")
						.when().put("/posts/3")
			.then().assertThat().log().all().statusCode(200);
		
	// getting above details again
					String bodyreturened= given()
							.when().get("/posts/3")
							.then().statusCode(200).assertThat()
							.body("body", equalTo("et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"))
							.body("title", equalTo("ea molestias quasi exercitationem repellat qui ipsa sit aut"))
							.extract().asString();
							System.out.println(bodyreturened);
				   
	//	 Delete a specific resource
				given()
				.when().delete("/posts/3")
				.then().log().all().statusCode(200);
				
	// Create your own resource 
				   given().header("Content-Type", "application/json")
							.body("{\r\n"
									+ "    \"userId\": 134,\r\n"
									+ "    \"id\": 1013,\r\n"
									+ "    \"title\": \"new body title \",\r\n"
									+ "    \"body\": \"new body \"\r\n"
									+ "}")
								.when().post("/posts")
					.then().assertThat().log().all().statusCode(201);
		}
}
