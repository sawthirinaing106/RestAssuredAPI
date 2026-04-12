package tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserApiTest {

    @Test
    public void getUsers() {

        // Set the base URI to JSONPlaceholder
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request to /users endpoint
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200) // Verify response code is 200
                .log().all();    // Print full response
    }
}
