package manytomany.Friends;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import manytomany.Admins.Credential;
import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to FRIEND Entity!")
@RestController
@RequestMapping("/friends") // Set a base URL for all endpoints
public class FriendController {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    private PersonRepository personRepository;


    private static final String success = "{\"message\":\"success\"}";


    @ApiOperation(value = "Get Person's Friends by SignUpName", response = Friend.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @GetMapping("/{SignUpName}")
    public ResponseEntity<List<Friend>> getAllFriendsBySignUpName(@PathVariable String SignUpName) {
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getFriends() == null || user.getFriends().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Friend> friends = new ArrayList<>(user.getFriends());

        return ResponseEntity.ok(friends);
    }


    @ApiOperation(value = "Add a Friend to the current user", response = Friend.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PostMapping("/add/{SignUpName}")
    public ResponseEntity<String> addFriendToUser1(@PathVariable String SignUpName, @RequestBody Friend friend) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user as the owner of this friend
        friend.setPerson(user);

        // Save the friend
        Friend savedFriend = friendRepository.save(friend);

        // Update the user's friend reference
        user.setFriend(savedFriend); // Add the friend to the collection
        personRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }


    @ApiOperation(value = "Update a Person's Friend by ID", response = Friend.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @PutMapping("/update/{id}")
    public ResponseEntity<Friend> updateFriend(@PathVariable int id, @Valid @RequestBody Friend request) {
        Friend friend = friendRepository.findById(id);
        if (friend == null) {
            return ResponseEntity.notFound().build();
        }
        // Update the friend object here
        friendRepository.save(request);
        return ResponseEntity.ok(request);
    }


    @ApiOperation(value = "Delete a Person's Friend by ID", response = Friend.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "Not Found")})

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFriend(@PathVariable int id) {
        Friend friend = friendRepository.findById(id);
        if (friend == null) {
            return ResponseEntity.notFound().build();
        }
        friendRepository.deleteById(id);
        return ResponseEntity.ok(success);
    }
}
