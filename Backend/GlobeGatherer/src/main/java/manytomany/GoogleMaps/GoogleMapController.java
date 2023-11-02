package manytomany.GoogleMaps;

import java.util.List;

import manytomany.TravelHistories.GoogleMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@RestController
public class GoogleMapController {

    @Autowired
    GoogleMapRepository googleMapRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/GoogleMaps")
    List<GoogleMap> getAllGoogleMap(){
        return googleMapRepository.findAll();
    }

//    @GetMapping(path = "/GoogleMaps/{id}")
//    GoogleMap getTravelToDoById(@PathVariable int id){
//        return GoogleMapRepository.findById(id);
//    }

    @PostMapping(path = "/GoogleMaps")
    GoogleMap createGoogleMap(@RequestBody GoogleMap dest){
        if (dest == null)
            return null;
        return googleMapRepository.save(dest);
    }



}
