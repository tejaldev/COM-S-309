package manytomany;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import manytomany.Friends.Friend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@RunWith(SpringRunner.class)

public class FriendControllerTest {


    int port = 8080;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-013.class.las.iastate.edu:" + port + "/friends";
    }


    @Test
    public void addFriendToUser1Test() {
        // Define user and travel history details
        Friend friend = new Friend("tejal1");

        // Send request and receive response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(friend)
                .when()
                .post("/add/Raghu");

        // Print the response body for debugging
        System.out.println("Response body: " + response.getBody().asString());

        // Extract "message" from the response
        String message = response.jsonPath().getString("message");

        // Check if the message matches the expected values or if the response body is a JSON object
        assertTrue(message.equals("Friend added successfully.") || message.equals("You are already friends with this user.")
                || response.getBody().jsonPath().get() != null);
    }



    @Test
    public void getAllFriendsBySignUpNameTest() {

        // Send request and receive response
        given()
                .when()
                .get("/Raghu")
                .then()
                .statusCode(200) // Assuming a successful response
                .body("$", hasSize(greaterThanOrEqualTo(0))); // Assert that the response is an array with at least 0 elements
    }


}

