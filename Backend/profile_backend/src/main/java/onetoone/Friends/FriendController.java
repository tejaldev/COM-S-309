package onetoone.Friends;

import java.util.List;

import onetoone.Persons.Person;
import onetoone.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/friends") // Set a base URL for all endpoints
public class FriendController {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    private PersonRepository personRepository;


    private static final String success = "{\"message\":\"success\"}";

    @GetMapping("/all")
    public ResponseEntity<List<Friend>> getAllFriends() {
        List<Friend> friends = friendRepository.findAll();
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Friend> getFriendById(@PathVariable int id) {
        Friend friend = friendRepository.findById(id);
        if (friend == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(friend);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFriendToUser1(@RequestBody Friend friend) {
        // Find the user with ID 1
        Person user1 = personRepository.findById(4);

        if (user1 == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user1 as the owner of this friend
        friend.setPerson(user1);

        // Save the friend
        Friend savedFriend = friendRepository.save(friend);

        // Update the user1's friend reference
        user1.setFriend(savedFriend);
        personRepository.save(user1);

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

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
