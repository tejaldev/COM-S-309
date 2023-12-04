package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.Profile.Description;
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

public class DescriptionControllerTest {


    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }


    @Test
    public void addDescriptionBySignUpNameTest() {
        // Define user and travel history details
        Description description = new Description("I am Raghuram. I am a Junior.");

        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(description)
                .when()
                .post("/description/Raghu");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(201, statusCode);

        // Extract "message" from the response and compare with expected value
        String message = response.jsonPath().getString("message");
        assertEquals("success", message);
    }

    @Test
    public void getDescriptionBySignUpNameTest() {
        // Send request and receive response
        given()
                .when()
                .get("/description/Raghu")
                .then()
                .statusCode(200) // Assuming a successful response
                .contentType("application/json") // Assert that the response has content type JSON
                .body("description", notNullValue()) // Adjust this based on the actual structure of your response
                .body("id", notNullValue()); // Similarly, adjust this based on your response structure
    }

    @Test
    public void getAllDescriptionTest() {
        given()
                .when()
                .get("/description/all")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

}

