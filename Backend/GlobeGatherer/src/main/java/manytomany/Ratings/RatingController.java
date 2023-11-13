package manytomany.Ratings;

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

@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to RATING Entity!")
@RestController
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @ApiOperation(value = "Show All Ratings that exist", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/rating/all")
    List<Rating> getAllRating(){
        return ratingRepository.findAll();
    }


    @ApiOperation(value = "Show All Ratings of current user", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/rating/{SignUpName}")
    public ResponseEntity<List<Rating>> getAllRatingBySignUpName(@PathVariable String SignUpName) {
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getRatings() == null || user.getRatings().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Rating> ratings = new ArrayList<>(user.getRatings());

        return ResponseEntity.ok(ratings);
    }


    @ApiOperation(value = "Add ratings to the current user", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping("/rating/add/{SignUpName}")
    public ResponseEntity<String> createRatingDescription(@PathVariable String SignUpName, @RequestBody Rating rating) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user as the owner of this rating
        rating.setPerson(user);

        // Save the rating
        ratingRepository.save(rating);

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }



    @ApiOperation(value = "Update ratings by ID", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PutMapping(path = "/rating/update/{id}")
    Rating updateRating(@PathVariable int id, @RequestBody Rating request){
        Rating description = ratingRepository.findById(id);
        if(description == null)
            return null;
        ratingRepository.save(request);
        return ratingRepository.findById(id);
    }


    @ApiOperation(value = "Delete ratings by ID", response = Rating.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @DeleteMapping(path = "/rating/delete/{id}")
    String deleteRating(@PathVariable int id){
        ratingRepository.deleteById(id);
        return success;
    }
    
}
