package manytomany.TravelHistories;

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


@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to TravelHistory Entity!!!!")
@RestController
public class TravelHistoryController {

    @Autowired
    TravelHistoryRepository travelHistoryRepository;

    @Autowired
    private PersonRepository personRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @ApiOperation(value = "Get Travel history", response = TravelHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/TravelHistory")
    List<TravelHistory> getAllTravelToDo(){
        return travelHistoryRepository.findAll();
    }

    @ApiOperation(value = "Get Travel history by sign up name", response = TravelHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/history/{SignUpName}")
    public ResponseEntity<List<TravelHistory>> getAllTravelHBySignUpName(@PathVariable String SignUpName) {
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getHistories() == null || user.getHistories().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<TravelHistory> histories = new ArrayList<>(user.getHistories());

        return ResponseEntity.ok(histories);
    }

    @ApiOperation(value = "Post Travel history by sign up name", response = TravelHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping("/TravelHistory/{SignUpName}")
    public ResponseEntity<String> createTravelHistoryForUser(@PathVariable String SignUpName, @RequestBody TravelHistory travel) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || travel == null) {
            return null; // User not found or travel is null, return null or handle as needed
        }

        // Set the user as the owner of this travel history entry
        travel.setPerson(user);
        travelHistoryRepository.save(travel);
        // Save the travel history entry
        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }


}
