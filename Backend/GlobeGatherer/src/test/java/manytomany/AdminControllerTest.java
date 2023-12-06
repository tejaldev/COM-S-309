package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.Admins.Admin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)

public class AdminControllerTest {


    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }


    @Test
    public void createAdminTest() {
        // Define user and travel history details
        Admin admin = new Admin("John", "12345", "john@gmail.com", "771676726");

        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(admin)
                .when()
                .post("/admins/add");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Extract "message" from the response and compare with expected value
        String message = response.jsonPath().getString("message");
        assertEquals("success", message);
    }

    @Test
    public void getPersonBySignUpNameTest() {
        // Send request and receive response
        given()
                .when()
                .get("/admins/Raghu")
                .then()
                .statusCode(200) // Assuming a successful rsponse
                .body("signUpUsername", notNullValue()) // Adjust this based on the actual structure of your response
                .body("signUpPassword", notNullValue()); // Adjust this based on the actual structure of your response
    }


    @Test
    public void getAllRatingTest() {
        given()
                .when()
                .get("/rating/all")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

}

