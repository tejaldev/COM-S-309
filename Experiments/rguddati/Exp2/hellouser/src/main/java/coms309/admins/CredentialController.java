package coms309.admins;

import coms309.users.User;
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
public class CredentialController {
    HashMap<String, Credentials> credList = new  HashMap<>();

    @GetMapping("/cred")
    public @ResponseBody String pp() {

        return "To view all Credentials : <button><a href=\"http://localhost:8080/cred/all\"> click here</a></button>";
    }

    @GetMapping("/cred/all")
    public @ResponseBody HashMap<String, Credentials> getAllUsers() {

        return credList;
    }

    @PostMapping("/cred/add")
    public @ResponseBody String createUser(@RequestBody Credentials cred) {
        System.out.println(cred);
        credList.put(cred.getFullName(), cred);
        return "New credentials for " + cred.getFullName() + " Added!";
    }

    @GetMapping("/cred/search/{fullName}")
    public @ResponseBody Credentials getUserFull(@PathVariable String fullName) {
        Credentials p = credList.get(fullName);
        return p;
    }

    @PutMapping("/cred/update/{fullName}")
    public @ResponseBody Credentials updateUser(@PathVariable String fullName, @RequestBody Credentials p) {
        credList.replace(fullName, p);
        return credList.get(fullName);
    }


    @DeleteMapping("/cred/delete/{fullName}")
    public @ResponseBody HashMap<String, Credentials> deleteUser(@PathVariable String fullName) {
        credList.remove(fullName);
        return credList;
    }
}
