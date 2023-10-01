package onetoone.Locations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import onetoone.Users.User;
import onetoone.Users.UserRepository;

/**
 * 
 * @author Raghuram Guddati
 * 
 */ 

@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/locations/all")
    List<Location> getAllLocations(){
        return locationRepository.findAll();
    }

    @GetMapping(path = "/locations/{id}")
    Location getLocationById(@PathVariable int id){
        return locationRepository.findById(id);
    }

    @PostMapping(path = "/locations/add")
    String createLocation(@RequestBody Location Laptop){
        if (Laptop == null)
            return failure;
        locationRepository.save(Laptop);
        return success;
    }

    @PutMapping(path = "/locations/update/{id}")
    Location updateLocation(@PathVariable int id, @RequestBody Location request){
        Location laptop = locationRepository.findById(id);
        if(laptop == null)
            return null;
        locationRepository.save(request);
        return locationRepository.findById(id);
    }

    @DeleteMapping(path = "/locations/delete/{id}")
    String deleteLocation(@PathVariable int id){

        // Check if there is an object depending on user and then remove the dependency
        User user = userRepository.findByLocation_Id(id);
        user.setLocation(null);
        userRepository.save(user);

        // delete the laptop if the changes have not been reflected by the above statement
        locationRepository.deleteById(id);
        return success;
    }
}
