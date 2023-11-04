package manytomany.Profile;

import java.util.List;
import java.util.Optional;

import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Vivek Bengre
 *
 */

@RestController
public class DescriptionController {

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/description/all")
    List<Description> getAllDescription(){
        return descriptionRepository.findAll();
    }

    @GetMapping(path = "/description/{id}")
    Description getDescriptionById(@PathVariable int id){
        return descriptionRepository.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDescription(@RequestBody Description description) {
        // Find the user with ID 1
        Person user1 = personRepository.findById(1);

        if (user1 == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user1 as the owner of this description
        description.setPerson(user1);

        // Save the description
        Description savedDescription = descriptionRepository.save(description);

        // Update the user1's description reference
        user1.setDescription(savedDescription);
        personRepository.save(user1);

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }



    @PutMapping(path = "/description/update/{id}")
    Description updateDescription(@PathVariable int id, @RequestBody Description request){
        Description description = descriptionRepository.findById(id);
        if(description == null)
            return null;
        descriptionRepository.save(request);
        return descriptionRepository.findById(id);
    }

    @DeleteMapping(path = "/description/delete/{id}")
    String deleteDescription(@PathVariable int id){
        descriptionRepository.deleteById(id);
        return success;
    }
}
