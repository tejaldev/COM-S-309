package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.Persons.Person;
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

public class PersonControllerTest {


    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port;
    }


    @Test
    public void createPersonTest() {
        // Define user and travel history details
        Person person = new Person("John1", "John1", "123456", "john@gmail.com", "771676726");

        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(person)
                .when()
                .post("/persons/add");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Extract "message" from the response and compare with expected value
        String message = response.jsonPath().getString("message");
        assertEquals("success", message);
    }

//    @Test
//    public void loginTest() {
//        // Define user and travel history details
//        LoginRequest loginRequest = new LoginRequest("John", "12345");
//
//        // Send request and receive response
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .body(loginRequest)
//                .when()
//                .post("/login");
//
//        // Print the response body for debugging
//        System.out.println("Response body: " + response.getBody().asString());
//
//        // Check status code
//        int statusCode = response.getStatusCode();
//        assertEquals(200, statusCode);
//
//        // Extract "message" from the response and compare with expected value
//        String message = response.jsonPath().getString("message");
//        assertEquals("Welcome", message);
//    }




    @Test
    public void getAllPersonSignTest() {
        given()
                .when()
                .get("/persons/cred")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    public void getAllPersonSignUpNamesTest() {
        given()
                .when()
                .get("/persons/all")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

}

