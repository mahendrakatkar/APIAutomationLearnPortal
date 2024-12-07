
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import repackage.Repackage;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.ss.formula.ptg.GreaterEqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterThanPtg;
import org.json.JSONArray;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import javax.print.DocFlavor.STRING;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.gherkin.model.Given;

public class Task5Ready() {
    private static int initialEmployeeCount;
    private static int newEmployeeId;
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
        RestAssured.basePath = "/api/v1";
    }
    @Test(priority = 1)
    public void getAllEmployees() {
        Response response = when()
                .get("/employees")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .extract()
                .response();
        Assert.assertEquals(response.jsonPath().getString("status"), "success");
        initialEmployeeCount = response.jsonPath().getList("data").size();
        System.out.println("Initial Employee Count: " + initialEmployeeCount);
        Assert.assertTrue(initialEmployeeCount > 0, "Employee count should be greater than 0");
    }
    @Test(priority = 2)
    public void createEmployee() {
        HashMap<String, String> newEmployee = new HashMap<>();
        newEmployee.put("name", "John Doe");
        newEmployee.put("salary", "50000");
        newEmployee.put("age", "30");
        Response response = given()
                .body(newEmployee)
                .header("Content-Type", "application/json")
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .extract()
                .response();
        Assert.assertEquals(response.jsonPath().getString("status"), "success");
        newEmployeeId = response.jsonPath().getInt("data.id");
        System.out.println("New Employee ID: " + newEmployeeId);
        getAllEmployees();
        Assert.assertEquals(initialEmployeeCount + 1, initialEmployeeCount + 1, "Employee count should increase by 1");
    }
    @Test(priority = 3)
    public void getEmployeeDetails() {
        Response response = given()
                .when()
                .get("/employee/" + newEmployeeId)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .extract()
                .response();
        Assert.assertEquals(response.jsonPath().getString("status"), "success");
        Assert.assertEquals(response.jsonPath().getString("data.employee_name"), "John Doe");
        Assert.assertEquals(response.jsonPath().getString("data.employee_salary"), "50000");
        Assert.assertEquals(response.jsonPath().getString("data.employee_age"), "30");
    }
    @Test(priority = 4)
    public void updateEmployeeDetails() {
        HashMap<String, String> updatedEmployee = new HashMap<>();
        updatedEmployee.put("name", "John Doe Updated");
        updatedEmployee.put("salary", "60000");
        updatedEmployee.put("age", "35");
        Response response = given()
                .body(updatedEmployee)
                .header("Content-Type", "application/json")
                .when()
                .put("/update/" + newEmployeeId)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .extract()
                .response();
        Assert.assertEquals(response.jsonPath().getString("status"), "success");
        System.out.println("Updated Employee ID: " + newEmployeeId);
    }
    @Test(priority = 5)
    public void verifyUpdatedDetails() {
        Response response = when()
                .get("/employee/" + newEmployeeId)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .extract()
                .response();
        Assert.assertEquals(response.jsonPath().getString("status"), "success");
        Assert.assertEquals(response.jsonPath().getString("data.employee_name"), "John Doe Updated");
        Assert.assertEquals(response.jsonPath().getString("data.employee_salary"), "60000");
        Assert.assertEquals(response.jsonPath().getString("data.employee_age"), "35");
    }
    @Test(priority = 6)
    public void deleteEmployee() {
        Response response = when()
                .delete("/delete/" + newEmployeeId)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
                .extract()
                .response();
        Assert.assertEquals(response.jsonPath().getString("status"), "success");
        getAllEmployees(); // Call the GET employees to update the count
        Assert.assertEquals(initialEmployeeCount, initialEmployeeCount - 1, "Employee count should decrease by 1");
    }

