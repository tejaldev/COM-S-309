package manytomany.Calendars;

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
public class CalendarController {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/cal/all")
    List<Calendar> getAllGoogleMap(){
        return calendarRepository.findAll();
    }

    @GetMapping(path = "/cal/{SignUpName}")
    public ResponseEntity<List<Calendar>> getMapBySignUpName(@PathVariable String SignUpName) {
        // Find the user by SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getCals() == null) {
            return ResponseEntity.notFound().build();
        }

        List<Calendar> cals = new ArrayList<>(user.getCals());

        return ResponseEntity.ok(cals);
    }

    @PostMapping("/cal/{SignUpName}")
    public ResponseEntity<Calendar> createGoogleMapForSignUpName(
            @PathVariable String SignUpName,
            @RequestBody Calendar googleMap
    ) {
        // Find the user with the provided SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Associate the GoogleMap entry with the user
        googleMap.setPerson(user);

        // Save the GoogleMap entry
        Calendar savedGoogleMap = calendarRepository.save(googleMap);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGoogleMap);
    }
}
