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

public class PetMethods {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://petstore.swagger.io";
		RestAssured.basePath = "/v2";

		RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
	}

	/*
	 * @Test public void testGet() {
	 * get("/pet/9223372016900016926").then().statusCode(200); }
	 * 
	 * @Test public void testDelete() { delete("/pet/9223372016900016926")
	 * .then().statusCode(200); }
	 */

	@Test
	public void testPostfirstway() {

		String RequestbodyPost = "{\"id\":0,\"category\":{\"id\":0,\"name\":\"dog1\"},\"name\":\"doggie1\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}";
		Response response = given().body(RequestbodyPost).when().post("/pet");
		/*
		 * .then() .statusCode(200);
		 */
		Long idLong = response.path("id");
		String id = idLong.toString();
		System.out.println("new pet created with id " + id);
	}

	// deserialization
	@Test
	public void testPost2ndway() {

		Category category = new Category(1, "dog");
		Pet pet = new Pet(12345, "snooopiee", category, "pending");
		Response response = given().body(pet).when().post("/pet");
		/*
		 * .then() .statusCode(200);
		 */
		// Long idLong = response.path("id");
		// String id = idLong.toString();
		String id = response.path("id").toString(); // if not works then use above two lines
		System.out.println("new pet created with id " + id);

	}

	// Serialization

	@Test
	public void testGet() {
		Pet pet = get("/pet/12345").as(Pet.class);
		System.out.println(pet.getName());

	}

	@Test
	public void testPut() {

		Category category = new Category(1, "dog");
		Pet pet = new Pet(12345, "snooopiee", category, "available");
		Response response = given().body(pet).when().put("/pet");
		String status = response.path("status").toString();
		System.out.println("Status of pet is updated id 12345 : " + status);

	}
	
	@Test
	public void allTestTogether() {
		
		// Map<String, Object> petMat=  getPetData() ;  // can be passed all following data from separate method
		Map<String, Object> categoryMap= new HashMap<String, Object>();
		categoryMap.put("id", 1);
		categoryMap.put("name", "dog");
		
		Map<String, Object> petMat= new HashMap<String, Object>();
		petMat.put("name", "doggie-e");
		petMat.put("status", "pending");
		petMat.put("category", categoryMap);
		//return petMat;
		
		
		// create new pet
		String newPetId = given().body(petMat).when().post("/pet").path("id").toString();

		// get details of above pet
		get("/pet/" + newPetId).then().statusCode(200).and().body("status", equalTo("pending"));

		// updating
		petMat.put("status", "available");
		petMat.put("id", newPetId);  // server expecting id also so added this line
		given().body(petMat).when().put("/pet").then().statusCode(200);

		// getting updated
		get("/pet/" + newPetId).then().statusCode(200).and().body("status", equalTo("available"));

		// deleting
		delete("/pet/" + newPetId).then().statusCode(200);

		// checking deleted by get query
		get("/pet/" + newPetId).then().statusCode(200);
	}
	}
/*  
 * private Map<String, Object> getPetData() { Map<String, Object> categoryMap=
 * new HashMap<String, Object>(); categoryMap.put("id", 1);
 * categoryMap.put("name", "dog");
 * 
 * Map<String, Object> petMat= new HashMap<String, Object>(); petMat.put("name",
 * "doggie-e"); petMat.put("status", "pending"); petMat.put("category",
 * categoryMap); return petMat;
 * 
 * } }
 */
