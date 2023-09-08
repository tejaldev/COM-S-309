package coms309.people;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

/**
 * Controller used to showcase Create and Read from a LIST
 *
 * @author Raghuram Guddati
 */

@RestController
public class UserController {
    HashMap<String, User> userList = new  HashMap<>();

    @GetMapping("/users")
    public @ResponseBody String pp() {

        return "Go to http://localhost:8080/users/all instead";
    }

    @GetMapping("/users/all")
    public @ResponseBody HashMap<String, User> getAllUsers() {

        return userList;
    }

    @PostMapping("/users/add")
    public @ResponseBody String createUser(@RequestBody User person) {
        System.out.println(person);
        userList.put(person.getFullName(), person);
        return "New user " + person.getFullName() + " Added!";
    }


    @GetMapping("/users/{fullName}")
    public @ResponseBody User getUser(@PathVariable String fullName) {
        User p = userList.get(fullName);
        return p;
    }


    @PutMapping("/users/update/{fullName}")
    public @ResponseBody User updateUser(@PathVariable String fullName, @RequestBody User p) {
        userList.replace(fullName, p);
        return userList.get(fullName);
    }


    @DeleteMapping("/users/delete/{fullName}")
    public @ResponseBody HashMap<String, User> deleteUser(@PathVariable String fullName) {
        userList.remove(fullName);
        return userList;
    }
}
