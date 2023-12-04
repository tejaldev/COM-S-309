package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.GoogleImages.GoogleImage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)

public class GoogleImageControllerTest {


    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }


    @Test
    public void createGoogleImageForSignUpNameTest() {
        // Define user and travel history details
        String base64Image = "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAIAAAD8GO2jAAAAWklEQVR42mJ8z4AAsNBXwbgBT1FA1TH5TqgAS8EA0J/ZxAjAz1AIoMAITMzPAdAPr5g1+gFv8wAAAABJRU5ErkJggg==";  // Your Base64-encoded image string

        // Decode Base64 string to byte array
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        GoogleImage googleImage = new GoogleImage(imageBytes);

        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(googleImage)
                .when()
                .post("/GoogleImages/Raghu");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(201, statusCode);

        // Validate JSON keys
        response.then()
                .contentType("application/json")
                .body("params", notNullValue()) // Adjust this based on the actual structure of your response
                .body("id", notNullValue());


    }


    @Test
    public void getImageBySignUpNameTest() {

        given()
                .when()
                .get("/GoogleImages/Raghu")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0))); // Assert that the response is an array with at least 0 elements
    }

    @Test
    public void getAllGoogleImageTest() {
        given()
                .when()
                .get("/GoogleImages")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

}

