package manytoone.TravelToDos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import manytoone.Users.User;
import manytoone.Users.UserRepository;

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
    UserRepository userRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/travel_todo")
    List<TravelToDo> getAllTravelToDo(){
        return travelToDoRepository.findAll();
    }

    @GetMapping(path = "/travel_todo/{id}")
    TravelToDo getTravelToDoById(@PathVariable int id){
        return travelToDoRepository.findById(id);
    }

    @PostMapping(path = "/travel_todo")
    String createTravelToDo(TravelToDo travel){
        if (travel == null)
            return failure;
        travelToDoRepository.save(travel);
        return success;
    }

    @PutMapping(path = "/travel_todo/{id}")
    TravelToDo updateTravelToDo(@PathVariable int id, @RequestBody TravelToDo request){
        TravelToDo travel = travelToDoRepository.findById(id);
        if(travel == null)
            return null;
        travelToDoRepository.save(request);
        return travelToDoRepository.findById(id);
    }

    @DeleteMapping(path = "/travel_todo/{id}")
    String deleteTravelToDo(@PathVariable int id){

        // Check if there is an object depending on user and then remove the dependency
        User user = userRepository.findById(id);
        user.setDestination(null);
        userRepository.save(user);

        // delete the laptop if the changes have not been reflected by the above statement
        travelToDoRepository.deleteById(id);
        return success;
    }
}
