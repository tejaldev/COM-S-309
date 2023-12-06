package manytomany.Persons;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import manytomany.Friends.FriendRepository;
import manytomany.Profile.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Vivek Bengre
 *
 */

@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to PERSON Entity!")
@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    private String login = "{\"message\":\"Welcome\"}";

    @ApiOperation(value = "Get All Persons that exist", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/persons/all")
    public ResponseEntity<List<String>> getAllPersonSignUpNames() {
        try {
            List<Person> persons = personRepository.findAll();

            // Extract the SignUpName from each person
            List<String> signUpNames = persons.stream()
                    .map(Person::getSignUpName)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(signUpNames);
        } catch (Exception e) {
            e.printStackTrace(); // Print the error for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Get All Person's Credentials", response = PersonInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/persons/cred")
    public ResponseEntity<List<Map<String, String>>> getAllPersonSign() {
        try {
            List<Person> persons = personRepository.findAll();

            // Extract the SignUpName and SignUpPassword from each person
            List<Map<String, String>> signUpData = persons.stream()
                    .map(person -> {
                        Map<String, String> data = new HashMap<>();
                        data.put("SignUpName", person.getSignUpName());
                        data.put("SignUpPassword", person.getSignUpPassword());
                        return data;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(signUpData);
        } catch (Exception e) {
            e.printStackTrace(); // Print the error for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @ApiOperation(value = "Search a Person by ID", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/persons/{id}")
    Person getPersonById( @PathVariable int id){

        return personRepository.findById(id);
    }


    @ApiOperation(value = "Add Person to the app", response = Person.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping(path = "/persons/add")
    String createPerson(@RequestBody Person person){
        if (person == null)
            return failure;
        personRepository.save(person);
        return success;
    }


    @ApiOperation(value = "Checks if the credentials are correct", response = LoginRequest.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String signUpUsername = loginRequest.getSignUpUsername();
        String password = loginRequest.getSignUpPassword();

        // Check if user exists and if password matches
        Person user = personRepository.findBySignUpUsername(signUpUsername);


            return ResponseEntity.ok(login);

    }


    @GetMapping(path = "/username")
    public ResponseEntity<String> getSignUpUsernameForMostRecentPassword() {
        String signUpUsername = personRepository.findSignUpNameByMostRecentPassword();
        if (signUpUsername != null) {
            return ResponseEntity.ok(signUpUsername);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
