package manytomany.TravelToDos;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import manytomany.Profile.Description;
import manytomany.TravelHistories.TravelHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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



    @ApiOperation(value = "Put all Travel to do by id", response = TravelToDo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PutMapping(path = "/TravelToDo/{id}")
    TravelToDo updateTravelToDo(@PathVariable int id, @RequestBody TravelToDo request){
        TravelToDo travel = travelToDoRepository.findById(id);
        if(travel == null)
            return null;
        travelToDoRepository.save(request);
        return travelToDoRepository.findById(id);
    }


    @ApiOperation(value = "delete all Travel to do by id", response = TravelToDo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @DeleteMapping(path = "/TravelToDo/{id}")
    String deleteTravelToDo(@PathVariable int id){

        // Check if there is an object depending on user and then remove the dependenc

        // delete the laptop if the changes have not been reflected by the above statement
        travelToDoRepository.deleteById(id);
        return success;
    }
}
