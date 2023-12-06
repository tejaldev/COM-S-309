package manytomany.Friends;

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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

//    @PostMapping("/add/{SignUpName}")
//    public ResponseEntity<String> addFriendToUser1(@PathVariable String SignUpName, @RequestBody Friend friend) {
//        // Find the user by SignUpName
//        Person user = personRepository.findBySignUpName(SignUpName);
//
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        friend.setPerson(user);
//
//        Friend savedFriend = friendRepository.save(friend);
//
//        user.setFriend(savedFriend); // Add the friend to the collection
//        personRepository.save(user);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(success);
//    }

    @PostMapping("/add/{SignUpName}")
    public ResponseEntity<String> addFriendToUser1(@PathVariable String SignUpName, @RequestBody Friend friend) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        friend.setPerson(user);

        Friend savedFriend = friendRepository.save(friend);

        user.setFriend(savedFriend);
        personRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }


    @Transactional
    @PostMapping("/add/check/{SignUpName}")
    public ResponseEntity<String> addFriendToUser2(@PathVariable String SignUpName, @RequestBody Friend friend) {
        Friend us = new Friend(SignUpName);
        String SignUpName2 = us.getName();
        try {
            Person user = personRepository.findBySignUpName(SignUpName);
            Person user2 = personRepository.findBySignUpName(friend.getName());

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            // Check if the friend is already a friend of the current user
            if (user.getFriends().stream().anyMatch(existingFriend -> existingFriend.getName().equals(friend.getName()))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are already friends with this user.");
            }

            friend.setPerson(user);
            Friend savedFriend = friendRepository.save(friend);

            // Update person_in_app_id for the saved friend
            friendRepository.updatePersonInAppIdByFriendName(friend.getName());


            user.getFriends().add(savedFriend); // Add the friend to the collection
            personRepository.save(user);

            addFriendToUser(friend.getName(), new Friend(SignUpName));

            return ResponseEntity.status(HttpStatus.CREATED).body("Friend added successfully.");
        } catch (Exception e) {
            // Handle exceptions and return a custom error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
    @Transactional
    public void addFriendToUser(String userName, Friend friend) {
        try {
            Person user = personRepository.findBySignUpName(userName);

            if (user == null) {
                // Handle appropriately (throw an exception or log an error)
                return;
            }

            // Check if the friend is already a friend of the current user
            if (user.getFriends().stream().anyMatch(existingFriend -> existingFriend.getName().equals(friend.getName()))) {
                // Handle appropriately (throw an exception or log an error)
                return;
            }

            friend.setPerson(user);
            Friend savedFriend = friendRepository.save(friend);

            // Update person_in_app_id for the saved friend
            friendRepository.updatePersonInAppIdByFriendName(friend.getName());

            user.getFriends().add(savedFriend);
            personRepository.save(user);
        } catch (Exception e) {
            // Handle exceptions (throw an exception or log an error)
        }
    }

}
