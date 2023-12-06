package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.TravelToDos.TravelToDo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)

public class TravelToDoControllerTest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }


    @Test
    public void createTravelToDoDescriptionTest() {
        // Define travel event
        TravelToDo todo = new TravelToDo("Paris", "France");


        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(todo)
                .when()
                .post("/TravelToDo/Ella");

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
    public void getAllTravelToDoEventsBySignUpNameTest() {
        // Assuming you have a user with the SignUpName who has travel events

        // Send request and receive response
        given()
                .when()
                .get("/TravelToDo/Ella")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0))); // Assert that the response is an array with at least 0 elements
    }

    @Test
    public void getAllTravelToDoTest() {
        given()
                .when()
                .get("/TravelToDo")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }


}
