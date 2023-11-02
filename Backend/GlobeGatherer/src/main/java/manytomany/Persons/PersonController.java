package manytomany.Persons;

import java.util.List;
import java.util.Map;

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

    @GetMapping(path = "/persons/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        try {
            List<Person> persons = personRepository.findAll();

            // Optional: If you want to handle missing/deleted friends
            persons.forEach(person -> {
                if (person.getFriend() == null) {
                    // Handle the case where a person has no friend
                    // You can set person.setFriend(null) or perform any other logic here
                }
            });

            return ResponseEntity.ok(persons);
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

    @PostMapping(path = "/persons/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String username = request.get("SignUpUsername");
        String password = request.get("SignUpPassword");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("{\"message\":\"Username and password are required.\"}");
        }

        // Query the database to find a user with the given username
        Person person = personRepository.findBySignUpUsername(username);

        if (person == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"Invalid credentials.\"}");
        }

        // Check if the provided password matches the stored password
        if (person.getSignUpPassword().equals(password)) {
            return ResponseEntity.ok("{\"message\":\"Login successful.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"Invalid credentials.\"}");
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
