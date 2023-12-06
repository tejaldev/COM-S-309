package manytomany.Calendars;

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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vivek Bengre
 *
 */
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to Cal Entity!!!!")
@RestController
public class CalendarController {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/cal/all")
    List<Calendar> getAllGoogleMap(){
        return calendarRepository.findAll();
    }

    @ApiOperation(value = "Get Person's Cal", response = Calendar.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})


    @GetMapping(path = "/cal/{SignUpName}")
    public ResponseEntity<List<Calendar>> getMapBySignUpName(@PathVariable String SignUpName) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getCals() == null) {
            return ResponseEntity.notFound().build();
        }

        List<Calendar> cals = new ArrayList<>(user.getCals());

        return ResponseEntity.ok(cals);
    }

    @ApiOperation(value = "Post Person's Cal", response = Calendar.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping("/cal/{SignUpName}")
    public ResponseEntity<Calendar> createGoogleMapForSignUpName(
            @PathVariable String SignUpName,
            @RequestBody Calendar googleMap
    ) {
        // Find the user with the provided SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Associate the GoogleMap entry with the user
        googleMap.setPerson(user);

        // Save the GoogleMap entry
        Calendar savedGoogleMap = calendarRepository.save(googleMap);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGoogleMap);
    }
}
