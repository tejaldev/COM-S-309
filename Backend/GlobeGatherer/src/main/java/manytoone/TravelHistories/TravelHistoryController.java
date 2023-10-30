package manytoone.TravelHistories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    manytoone.TravelHistories.GoogleMapRepository travelHistoryRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/TravelHistory")
    List<TravelHistory> getAllTravelToDo(){
        return travelHistoryRepository.findAll();
    }

    @GetMapping(path = "/TravelHistory/{id}")
    TravelHistory getTravelToDoById(@PathVariable int id){
        return travelHistoryRepository.findById(id);
    }

    @PostMapping(path = "/TravelHistory")
    TravelHistory createTravelToDo(@RequestBody TravelHistory travel){
        if (travel == null)
            return null;
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
