package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.GoogleMaps.GoogleMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)

public class GoogleMapControllerTest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }

    @Test
    public void createGoogleMapDescriptionTest() {
        // Define google map event
        GoogleMap map = new GoogleMap("France");


        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post("/GoogleMaps/Ella");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(201, statusCode);

        String destination = response.jsonPath().getString("destinationName");
        int id = response.jsonPath().getInt("id");

        // Assert the response fields
        assertEquals("France", destination);
        assertTrue(id > 0);
    }

    @Test
    public void getAllGoogleMapEventsBySignUpNameTest() {
        // Assuming you have a user with the SignUpName who has expense events

        // Send request and receive response
        given()
                .when()
                .get("/GoogleMaps/Ella")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0))); // Assert that the response is an array with at least 0 elements
    }

    @Test
    public void getAllGoogleMapTest() {
        given()
                .when()
                .get("/GoogleMaps")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

}
