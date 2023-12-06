package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.Calendars.Calendar;
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

public class CalendarControllerTest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }

    @Test
    public void createCalendarDescriptionTest() {
        // Define cal event
        Calendar cal = new Calendar("France", "Jan 1", "Jan 20");


        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(cal)
                .when()
                .post("/cal/Ella");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(201, statusCode);

        String destination = response.jsonPath().getString("destination");
        String estimatedCost = response.jsonPath().getString("startDate");
        String selectedCostItem = response.jsonPath().getString("endDate");
        int id = response.jsonPath().getInt("id");

        // Assert the response fields
        assertEquals("France", destination);
        assertEquals("Jan 1", estimatedCost);
        assertEquals("Jan 20", selectedCostItem);
        assertTrue(id > 0);
    }

    @Test
    public void getAllCalEventsBySignUpNameTest() {
        // Assuming you have a user with the SignUpName who has cal events

        // Send request and receive response
        given()
                .when()
                .get("/cal/Ella")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0))); // Assert that the response is an array with at least 0 elements
    }

    @Test
    public void getAllCalendarTest() {
        given()
                .when()
                .get("/cal/all")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }
}
