package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsMapWithSize.anEmptyMap;
import static org.testng.Assert.assertEquals;


public class UserCRUDTest extends BaseTest {

    @Test
    public void testGetAllUsers() {
        Response response = ApiUtils.get("/users?page=2");
        assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, "User list is empty");
    }

    @Test
    public void testGetSingleUser() {
        Response response = ApiUtils.get("/users/1");
        assertEquals(response.getStatusCode(), 200);

        Integer userId = 2;
        Assert.assertNotNull(userId, "User ID is null");
        assertEquals(userId.intValue(), 2);

    }

    @Test
    public void testGetInvalidUser() {
        io.restassured.RestAssured.given().when().get("/users/99999")
                .then()
                .statusCode(404)
                .body("$", anEmptyMap());

    }

    @Test
    public void createUser_shouldReturnValidCreationContract() {

        String requestBody = """
                {
                  "name": "Test User",
                  "username": "testuser123",
                  "email": "testuser@example.com"
                }
                """;

        given()
                .contentType("application/json")
                .accept("application/json")
                .body(requestBody)

                .when()
                .post("/users")

                .then()
                .statusCode(201)
                .contentType(containsString("application/json"))
                .body("id", notNullValue())
                .body("name", not(isEmptyOrNullString()))
                .body("username", not(isEmptyOrNullString()))
                .body("email", containsString("@"));
    }

    @Test
    public void testCreateUser() {
        String requestBody = """
                {
                    "name": "John Doe",
                    "username": "johnd",
                    "email": "john@example.com"
                }
                """;

        Response response = ApiUtils.createUser(requestBody);
        assertEquals(response.statusCode(), 201);
        assertEquals(response.jsonPath().getString("name"), "John Doe");
    }

    @Test
    public void testUpdateUser() {
        String updateBody = """
                {
                    "name": "John Updated",
                    "username": "johnd",
                    "email": "johnupdated@example.com"
                }
                """;

        Response response = ApiUtils.updateUser(1, updateBody);
        assertEquals(response.statusCode(), 200);
        assertEquals(response.jsonPath().getString("name"), "John Updated");
    }

    @Test
    public void testDeleteUser() {
        Response response = ApiUtils.deleteUser(1);
        assertEquals(response.statusCode(), 200);
    }
}
