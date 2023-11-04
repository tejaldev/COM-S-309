package manytomany.TravelHistories;

import java.util.ArrayList;
import java.util.List;

import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import manytomany.SearchHistories.SearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TravelHistoryController {

    @Autowired
    TravelHistoryRepository travelHistoryRepository;

    @Autowired
    private PersonRepository personRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/TravelHistory")
    List<TravelHistory> getAllTravelToDo(){
        return travelHistoryRepository.findAll();
    }

    @GetMapping("/history/{SignUpName}")
    public ResponseEntity<List<TravelHistory>> getAllTravelHBySignUpName(@PathVariable String SignUpName) {
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getHistories() == null || user.getHistories().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<TravelHistory> histories = new ArrayList<>(user.getHistories());

        return ResponseEntity.ok(histories);
    }


    @PostMapping("/TravelHistory/{SignUpName}")
    TravelHistory createTravelHistoryForUser(@PathVariable String SignUpName, @RequestBody TravelHistory travel) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || travel == null) {
            return null; // User not found or travel is null, return null or handle as needed
        }

        // Set the user as the owner of this travel history entry
        travel.setPerson(user);

        // Save the travel history entry
        return travelHistoryRepository.save(travel);
    }




    @PutMapping(path = "/TravelHistory/{id}")
    TravelHistory updateTravelToDo(@PathVariable int id, @RequestBody TravelHistory request){
        TravelHistory travel = travelHistoryRepository.findById(id);
        if(travel == null)
            return null;
        travelHistoryRepository.save(request);
        return travelHistoryRepository.findById(id);
    }

    @DeleteMapping(path = "/TravelHistory/{id}")
    String deleteTravelToDo(@PathVariable int id){

        // Check if there is an object depending on user and then remove the dependenc

        // delete the laptop if the changes have not been reflected by the above statement
        travelHistoryRepository.deleteById(id);
        return success;
    }
}
