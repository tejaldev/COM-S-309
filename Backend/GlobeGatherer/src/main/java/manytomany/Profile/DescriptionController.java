package manytomany.Profile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Vivek Bengre
 *
 */

@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to DESCRIPTION Entity!")
@RestController
public class DescriptionController {

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @ApiOperation(value = "Get All Descriptions that exist", response = Description.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/description/all")
    List<Description> getAllDescription(){
        return descriptionRepository.findAll();
    }


    @ApiOperation(value = "Get Description of the current user", response = Description.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping("/description/{SignUpName}")
    public ResponseEntity<Description> getDescriptionBySignUpName(@PathVariable String SignUpName) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getDescription() == null) {
            return ResponseEntity.notFound().build();
        }

        Description description = user.getDescription();

        return ResponseEntity.ok(description);
    }


    @ApiOperation(value = "Add a description to the current user", response = Description.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping("/description/{SignUpName}")
    public ResponseEntity<String> addDescriptionBySignUpName(
            @PathVariable String SignUpName,
            @RequestBody Description description) {

        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user as the owner of this description
        description.setPerson(user);

        // Save the description
        Description savedDescription = descriptionRepository.save(description);

        // Update the user's description reference
        user.setDescription(savedDescription);
        personRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

}
