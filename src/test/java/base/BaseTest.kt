package base;

import io.restassured.RestAssured
import org.testng.annotations.BeforeClass

open class BaseTest {
    @BeforeClass
    fun setup() {
        // Set base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"
    }
}