package onetoone.Persons;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import onetoone.Friends.Friend;
import onetoone.Friends.FriendRepository;

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

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/persons/all")
    List<Person> getAllPersons(){
        return personRepository.findAll();
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

    @DeleteMapping(path = "/persons/delete{id}")
    String deletePerson(@PathVariable int id){
        personRepository.deleteById(id);
        return success;
    }
}
