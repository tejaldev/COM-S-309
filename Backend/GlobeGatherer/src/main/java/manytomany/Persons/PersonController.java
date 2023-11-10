package manytomany.Persons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import manytomany.Friends.Friend;
import manytomany.Friends.FriendRepository;
import manytomany.Profile.DescriptionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    private String login = "{\"message\":\"Welcome\"}";

    @GetMapping(path = "/persons/all")
    public ResponseEntity<List<String>> getAllPersonSignUpNames() {
        try {
            List<Person> persons = personRepository.findAll();

            // Extract the SignUpName from each person
            List<String> signUpNames = persons.stream()
                    .map(Person::getSignUpName)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(signUpNames);
        } catch (Exception e) {
            e.printStackTrace(); // Print the error for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/persons/cred")
    public ResponseEntity<List<Map<String, String>>> getAllPersonSign() {
        try {
            List<Person> persons = personRepository.findAll();

            // Extract the SignUpName and SignUpPassword from each person
            List<Map<String, String>> signUpData = persons.stream()
                    .map(person -> {
                        Map<String, String> data = new HashMap<>();
                        data.put("SignUpName", person.getSignUpName());
                        data.put("SignUpPassword", person.getSignUpPassword());
                        return data;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(signUpData);
        } catch (Exception e) {
            e.printStackTrace(); // Print the error for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @GetMapping(path = "/persons/{id}")
    Person getPersonById( @PathVariable int id){

        return personRepository.findById(id);
    }

    @PostMapping(path = "/persons/add")
    String createPerson(@RequestBody Person person){
        if (person == null)
            return failure;
        personRepository.save(person);
        return success;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String signUpUsername = loginRequest.getSignUpUsername();
        String password = loginRequest.getSignUpPassword();

        // Check if user exists and if password matches
        Person user = personRepository.findBySignUpUsername(signUpUsername);

        if (user != null && user.getSignUpPassword() != null && user.getSignUpPassword().equals(password)) {
            String welcomeMessage = "Welcome " + user.getSignUpName();
            return ResponseEntity.ok(login);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    }

    @GetMapping(path = "/username")
    public ResponseEntity<String> getSignUpUsernameForMostRecentPassword() {
        String signUpUsername = personRepository.findSignUpNameByMostRecentPassword();
        if (signUpUsername != null) {
            return ResponseEntity.ok(signUpUsername);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/persons/update/{id}")
    Person updatePerson(@PathVariable int id, @RequestBody Person request){
        Person person = personRepository.findById(id);
        if(person == null)
            return null;
        personRepository.save(request);
        return personRepository.findById(id);
    }   
    
    @PutMapping("/persons/{personId}/friends/{friendId}")
    String assignFriendToPerson(@PathVariable int personId,@PathVariable int friendId){
        Person person = personRepository.findById(personId);
        Friend friend = friendRepository.findById(friendId);
        if(person == null || friend == null)
            return failure;
        friend.setPerson(person);
        person.setFriend(friend);
        personRepository.save(person);
        return success;
    }

    @DeleteMapping(path = "/persons/delete/{id}")
    String deletePerson(@PathVariable int id){
        personRepository.deleteById(id);
        return success;
    }
}
