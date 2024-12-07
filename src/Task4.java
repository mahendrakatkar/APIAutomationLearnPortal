import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Task4 {
//  Get a specific resource data 

	@Test
	public void Task4Test() {

		// int minUserCount=3;

		/*
		 * given().baseUri("https://jsonplaceholder.typicode.com") .when().get("users")
		 * .then().assertThat().statusCode(200) //status code validation .body("size()",
		 * greaterThan(3)) // checking that there more than ‘3’ users in the list
		 * //.body("id",greaterThan(minUserCount)); //it is checking individuals id and
		 * failed with list of ids present .body("name", hasItem("Ervin Howell"));
		 * //checking that one of the users has a name of “Ervin Howell” //
		 * System.out.println(bodyIS);
		 * 
		 */
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		Response response = (Response) given().when().get("users").then().assertThat().statusCode(200) // status code
																										// validation
				.body("size()", greaterThan(3)) // checking that there more than ‘3’ users in the list
												// .body("id",greaterThan(minUserCount)); //it is checking individuals
												// id and failed with list of ids present
				.body("name", hasItem("Ervin Howell")) // checking that one of the users has a name of “Ervin Howell”
				.extract();
		// System.out.println(bodyIS);
		JsonPath jsonPath = response.jsonPath();

		boolean nameCheck = jsonPath.getString("name").contains("Ervin Howell"); // name validation
		if (nameCheck) {
			System.out.println("User is with 'Ervin Howell'");
		} else {
			System.out.println("No user is with 'Ervin Howell' found");
		}
		// System.out.println("name is matching with Ervin Howell "+nameCheck);

	}
}