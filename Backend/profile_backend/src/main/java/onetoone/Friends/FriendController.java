package onetoone.Friends;

import java.util.List;

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
    public ResponseEntity<Friend> createFriend(@Valid @RequestBody Friend friend) {
        Friend savedFriend = friendRepository.save(friend);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFriend);
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
