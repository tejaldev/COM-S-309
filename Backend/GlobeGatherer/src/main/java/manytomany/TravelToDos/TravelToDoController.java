package manytomany.TravelToDos;

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

@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to TravelToDo Entity!!!!")

@RestController
public class TravelToDoController {

    @Autowired
    TravelToDoRepository travelToDoRepository;

    @Autowired
    private PersonRepository personRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @ApiOperation(value = "Get all Travel to do", response = TravelToDo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/TravelToDo")
    List<TravelToDo> getAllTravelToDo(){
        return travelToDoRepository.findAll();
    }


    @ApiOperation(value = "Get all Travel to do by sign up name", response = TravelToDo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping("/TravelToDo/{SignUpName}")
    public ResponseEntity<List<TravelToDo>> getTravelToDosBySignUpName(@PathVariable String SignUpName) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getTravelToDo() == null) {
            return ResponseEntity.notFound().build();
        }

        List<TravelToDo> travelToDos = new ArrayList<>(user.getTravelToDos());

        return ResponseEntity.ok(travelToDos);
    }


    @ApiOperation(value = "Post all Travel to do by sign up name", response = TravelToDo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping("/TravelToDo/{SignUpName}")
    public ResponseEntity<String> createTravelToDoForPerson(@PathVariable String SignUpName, @RequestBody TravelToDo travel) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user as the owner of this TravelToDo
        travel.setPerson(user);

        // Save the TravelToDo
        TravelToDo savedTravel = travelToDoRepository.save(travel);

        // Update the user's TravelToDo reference
        user.setTravelToDo(savedTravel);
        personRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

}
