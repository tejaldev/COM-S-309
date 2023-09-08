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
public class PeopleController {
    HashMap<String, Person> peopleList = new  HashMap<>();

    // CRUDL (create/read/update/delete/list)
    // Use POST, GET, PUT, DELETE, GET methods for CRUDL

    // LIST Operation (GET)
    // Gets all the people in the list and returns it in JSON format
    // This controller takes no input.
    // Spring Boot automatically converts the list to JSON format
    // in this case because of @ResponseBody
    // Note: To LIST, we use the GET method

    // CREATE Operation (POST)
    // Spring Boot automatically converts JSON input into a person object and
    // the method below enters it into the list.
    // It returns a string message in this example.
    // in this case because of @ResponseBody
    // Note: To CREATE we use POST method
    @GetMapping("/users/")
    public @ResponseBody String pp() {
        return "Go to http://localhost:8080/users/all instead";
    }

    @GetMapping("/users/all/")
    public @ResponseBody HashMap<String, Person> getAllPersons() {
        return peopleList;
    }

    @PostMapping("/users/add/")
    public @ResponseBody String createPerson(@RequestBody Person person) {
        System.out.println(person);
        peopleList.put(person.getFullName(), person); // Change to getFullName()
        return "New user " + person.getFullName() + " Added!";
    }

    // READ Operation (GET)
    // Spring Boot gets the PATHVARIABLE from the URL
    // We extract the person from the HashMap.
    // Spring Boot automatically converts Person to JSON format when we return it
    // in this case because of @ResponseBody
    // Note: To READ we use GET method
    @GetMapping("/users/{fullName}")
    public @ResponseBody Person getPerson(@PathVariable String fullName) {
        Person p = peopleList.get(fullName);
        return p;
    }

    // UPDATE Operation (PUT)
    // We extract the person from the HashMap and modify it.
    // Spring Boot automatically converts the Person to JSON format
    // Spring Boot gets the PATHVARIABLE from the URL
    // Here we are returning what we sent to the method
    // in this case because of @ResponseBody
    // Note: To UPDATE we use PUT method
    @PutMapping("/users/update/{fullName}")
    public @ResponseBody Person updatePerson(@PathVariable String fullName, @RequestBody Person p) {
        peopleList.replace(fullName, p);
        return peopleList.get(fullName);
    }

    // DELETE Operation (DELETE)
    // Spring Boot gets the PATHVARIABLE from the URL
    // We return the entire list -- converted to JSON
    // in this case because of @ResponseBody
    // Note: To DELETE we use DELETE method
    @DeleteMapping("/users/delete/{fullName}")
    public @ResponseBody HashMap<String, Person> deletePerson(@PathVariable String fullName) {
        peopleList.remove(fullName);
        return peopleList;
    }
}
