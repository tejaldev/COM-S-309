package manytomany.TravelToDos;

import java.util.List;

import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import manytomany.Profile.Description;
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
public class TravelToDoController {

    @Autowired
    TravelToDoRepository travelToDoRepository;

    @Autowired
    private PersonRepository personRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/TravelToDo")
    List<TravelToDo> getAllTravelToDo(){
        return travelToDoRepository.findAll();
    }

    @GetMapping(path = "/TravelToDo/{id}")
    TravelToDo getTravelToDoById(@PathVariable int id){
        return travelToDoRepository.findById(id);
    }

    @PostMapping("/TravelToDo")
   public ResponseEntity<String> createTravelToDoForPerson1(@RequestBody TravelToDo travel) {
        Person user1 = personRepository.findById(1);

        if (user1 == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user1 as the owner of this description
        travel.setPerson(user1);

        // Save the description
        TravelToDo savedTravel = travelToDoRepository.save(travel);

        // Update the user1's description reference
        user1.setTravelToDo(savedTravel);
        personRepository.save(user1);

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }



    @PutMapping(path = "/TravelToDo/{id}")
    TravelToDo updateTravelToDo(@PathVariable int id, @RequestBody TravelToDo request){
        TravelToDo travel = travelToDoRepository.findById(id);
        if(travel == null)
            return null;
        travelToDoRepository.save(request);
        return travelToDoRepository.findById(id);
    }

    @DeleteMapping(path = "/TravelToDo/{id}")
    String deleteTravelToDo(@PathVariable int id){

        // Check if there is an object depending on user and then remove the dependenc

        // delete the laptop if the changes have not been reflected by the above statement
        travelToDoRepository.deleteById(id);
        return success;
    }
}
