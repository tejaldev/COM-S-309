package manytomany.TravelToDos;

import java.util.ArrayList;
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

    @GetMapping("/TravelToDo/{SignUpName}")
    public ResponseEntity<List<TravelToDo>> getTravelToDosBySignUpName(@PathVariable String SignUpName) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getTravelToDo() == null) {
            return ResponseEntity.notFound().build();
        }

        List<TravelToDo> travelToDos = new ArrayList<>(user.getTravelToDos());

        return ResponseEntity.ok(travelToDos);
    }



    @PostMapping("/TravelToDo/{SignUpName}")
    public ResponseEntity<String> createTravelToDoForPerson(@PathVariable String SignUpName, @RequestBody TravelToDo travel) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Set the user as the owner of this TravelToDo
        travel.setPerson(user);

        // Save the TravelToDo
        TravelToDo savedTravel = travelToDoRepository.save(travel);

        // Update the user's TravelToDo reference
        user.setTravelToDo(savedTravel);
        personRepository.save(user);

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
