package onetoone.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import onetoone.Laptops.Laptop;
import onetoone.Laptops.LaptopRepository;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LaptopRepository laptopRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }

    @PostMapping(path = "/users")
    String createUser(User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }

    @PostMapping(path = "/signup")
    public String registerUser(@RequestBody User user){
        // validate
        if(user == null || user.getEmailId() == null || user.getPassword() == null
                || user.getEmailId().isEmpty() || user.getPassword().isEmpty()) {
            return "Invalid data.";
        }

        // Check if user exists
        User existingUser = userRepository.findByEmailId(user.getEmailId());
        if (existingUser != null) {
            return "Email already in use.";
        }

        userRepository.save(user);

        return "success added user with email " + user.getEmailId();
    }

    @PostMapping(path = "/logout")
    public String logoutUser(HttpServletRequest request) {
        // Invalidate the session
        return String.valueOf(new ResponseEntity<>("Logged out successfully.", HttpStatus.OK));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        // Validate request, send reset email, etc.
        return new ResponseEntity<>("Password reset email sent.", HttpStatus.OK);
    }



    /* not safe to update */
//    @PutMapping("/users/{id}")
//    User updateUser(@PathVariable int id, @RequestBody User request){
//        User user = userRepository.findById(id);
//        if(user == null)
//            return null;
//        userRepository.save(request);
//        return userRepository.findById(id);
//    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);

        if(user == null) {
            throw new RuntimeException("user id does not exist");
        }
        else if (user.getId() != id){
            throw new RuntimeException("path variable id does not match User request id");
        }

        userRepository.save(request);
        return userRepository.findById(id);
    }

    @PutMapping("/users/{userId}/laptops/{laptopId}")
    String assignLaptopToUser(@PathVariable int userId,@PathVariable int laptopId){
        User user = userRepository.findById(userId);
        Laptop laptop = laptopRepository.findById(laptopId);
        if(user == null || laptop == null)
            return failure;
        laptop.setUser(user);
        user.setLaptop(laptop);
        userRepository.save(user);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }
}
