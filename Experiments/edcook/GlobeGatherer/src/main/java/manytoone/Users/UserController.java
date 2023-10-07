package manytoone.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import manytoone.TravelToDos.TravelToDo;
import manytoone.TravelToDos.TravelToDoRepository;

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
    TravelToDoRepository travelToDoRepository;

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

    @PutMapping("/users/{userId}/travel_todo/{travelToDoId}")
    String assignTravelToDoToUser(@PathVariable int userId,@PathVariable int travelToDoId){
        User user = userRepository.findById(userId);
        TravelToDo destination = travelToDoRepository.findById(travelToDoId);
        if(user == null || destination == null)
            return failure;
        destination.setUser(user);
        user.setDestination(destination);
        userRepository.save(user);
        return success;
    }

    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }
}
