package coms309.users;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


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

        return "To view all Users: go to http://localhost:8080/users/all instead";
    }

    @GetMapping("/users/all")
    public @ResponseBody HashMap<String, User> getAllUsers() {

        return userList;
    }

    @PostMapping("/users/add")
    public @ResponseBody String createUser(@RequestBody User user) {
        System.out.println(user);
        userList.put(user.getFullName(), user);
        return "New user " + user.getFullName() + " Added!";
    }


    @GetMapping("/users/search/{fullName}")
    public @ResponseBody User getUserFull(@PathVariable String fullName) {
        User p = userList.get(fullName);
        return p;
    }
    @GetMapping("/users/search/country/{country}")
    public @ResponseBody List<User> getUsersByCountry(@PathVariable String country) {
        List<User> usersWithCountry = new ArrayList<>();

        for (Map.Entry<String, User> entry : userList.entrySet()) {
            User user = entry.getValue();
            if (user.getCountry().equals(country)) {
                usersWithCountry.add(user);
            }
        }

        return usersWithCountry;
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
