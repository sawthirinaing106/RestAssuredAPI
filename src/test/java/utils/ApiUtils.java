package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    //retrieve all list out
    public static Response get(String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then().extract().response();
    }

    // POST request
    public static Response createUser(String body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();
    }

    // PUT request
    public static Response updateUser(int id, String body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/users/" + id)
                .then()
                .extract()
                .response();
    }

    // DELETE request
    public static Response deleteUser(int id) {
        return given()
                .when()
                .delete("/users/" + id)
                .then()
                .extract()
                .response();
    }
}
