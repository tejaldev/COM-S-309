package manytomany.GoogleMaps;

import java.util.ArrayList;
import java.util.List;


import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/GoogleMaps")
    List<GoogleMap> getAllGoogleMap(){
        return googleMapRepository.findAll();
    }

    @GetMapping(path = "/GoogleMaps/{SignUpName}")
    public ResponseEntity<List<GoogleMap>> getMapBySignUpName(@PathVariable String SignUpName) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getMaps() == null) {
            return ResponseEntity.notFound().build();
        }

        List<GoogleMap> maps = new ArrayList<>(user.getMaps());

        return ResponseEntity.ok(maps);
    }

    @PostMapping("/GoogleMaps/{SignUpName}")
    public ResponseEntity<GoogleMap> createGoogleMapForSignUpName(
            @PathVariable String SignUpName,
            @RequestBody GoogleMap googleMap
    ) {
        // Find the user with the provided SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Associate the GoogleMap entry with the user
        googleMap.setPerson(user);

        // Save the GoogleMap entry
        GoogleMap savedGoogleMap = googleMapRepository.save(googleMap);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGoogleMap);
    }




}
