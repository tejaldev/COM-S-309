package manytomany.Admins;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 *
 * @author Raghuram Guddati
 *
 */
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to ADMIN Entity!!!!")
@RestController
public class AdminController {

    HashMap<String, Person> personList = new  HashMap<>();

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PersonRepository personRepository;  // Inject the PersonRepository

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";


    @ApiOperation(value = "Get Person's Credentials by SignUpName", response = Credential.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping("/admins/{SignUpName}")
    public ResponseEntity<Credential> getPersonBySignUpName(@PathVariable String SignUpName) {
        Person person = personRepository.findBySignUpName(SignUpName);

        if (person != null) {
            // Create a Credential instance and set the desired fields
            Credential credential = new Credential();
            credential.setSignUpUsername(person.getSignUpUsername());
            credential.setSignUpPassword(person.getSignUpPassword());

            return ResponseEntity.ok(credential);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiOperation(value = "Create a new Admin", response = Admin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 400, message = "Bad Request")})

    @PostMapping(path = "/admins/add")
    String createAdmin(@RequestBody Admin admin){
        if (admin == null)
            return failure;
        adminRepository.save(admin);
        return success;
    }



    @ApiOperation(value = "Ban a Person by SignUpName", response = Admin.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @DeleteMapping(path = "/admins/delete/{SignUpName}")
    String banPerson(@PathVariable String SignUpName) {
        personRepository.deleteBySignUpName(SignUpName);
        return success;
    }
}
