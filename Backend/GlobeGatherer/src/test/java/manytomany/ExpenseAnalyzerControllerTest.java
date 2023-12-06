package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.ExpenseAnalyzers.ExpenseAnalyzer;
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

public class ExpenseAnalyzerControllerTest {

    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }

    @Test
    public void createExpenseDescriptionTest() {
        // Define expense event
        ExpenseAnalyzer expense = new ExpenseAnalyzer("France", 1000, "Food");

        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(expense)
                .when()
                .post("/expense/Ella");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(201, statusCode);

        // Extract fields from the response
        String destination = response.jsonPath().getString("destination");
        int estimatedCost = response.jsonPath().getInt("estimatedCost");
        String selectedCostItem = response.jsonPath().getString("selectedCostItem");
        int id = response.jsonPath().getInt("id");

        // Assert the response fields
        assertEquals("France", destination);
        assertEquals(1000, estimatedCost);
        assertEquals("Food", selectedCostItem);
        assertTrue(id > 0); // Assuming id is a positive number
    }


    @Test
    public void getAllExpenseEventsBySignUpNameTest() {
        // Assuming you have a user with the SignUpName who has expense events

        // Send request and receive response
        given()
                .when()
                .get("/expense/Ella")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0))); // Assert that the response is an array with at least 0 elements
    }

    @Test
    public void getAllExpenseTest() {
        given()
                .when()
                .get("/expense/all")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }



}
