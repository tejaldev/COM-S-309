package manytomany.SearchHistories;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import manytomany.Friends.Friend;
import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import manytomany.Profile.Description;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to SEARCH Entity!")
@RestController
public class SearchHistoryController {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";


    @ApiOperation(value = "Get All Search histories that exist", response = SearchHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping(path = "/search/all")
    List<SearchHistory> getAllRating(){

        return searchHistoryRepository.findAll();
    }


    @ApiOperation(value = "Get All Search Histories for the current user", response = SearchHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping("/search/{SignUpName}")
    public ResponseEntity<List<SearchHistory>> getAllSearchessBySignUpName(@PathVariable String SignUpName) {
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getSearches() == null || user.getSearches().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<SearchHistory> searches = new ArrayList<>(user.getSearches());

        return ResponseEntity.ok(searches);
    }


    @ApiOperation(value = "Add a Search for the current user", response = SearchHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping("/search/add/{SignUpName}")
    String createRatingDescriptionForUser(@PathVariable String SignUpName, @RequestBody SearchHistory rating) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return failure; // User not found
        }

        // Set the user as the owner of this search history
        rating.setPerson(user);

        // Save the search history entry
        searchHistoryRepository.save(rating);

        return success;
    }


    @ApiOperation(value = "Update Search histories by ID", response = SearchHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PutMapping(path = "/search/update/{id}")
    SearchHistory updateRating(@PathVariable int id, @RequestBody SearchHistory request){
        SearchHistory description = searchHistoryRepository.findById(id);
        if(description == null)
            return null;
        searchHistoryRepository.save(request);
        return searchHistoryRepository.findById(id);
    }


    @ApiOperation(value = "Delete Search histories by ID", response = SearchHistory.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @DeleteMapping(path = "/search/delete/{id}")
    String deleteRating(@PathVariable int id){
        searchHistoryRepository.deleteById(id);
        return success;
    }
}
