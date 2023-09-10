package coms309.people;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import coms309.people.Place;

/**
 * Controller used to showcase Create and Read from a LIST
 *
 * @author Vivek Bengre
 */

@RestController
public class PeopleController {

    // Note that there is only ONE instance of PeopleController in 
    // Springboot system.
    HashMap<String, Person> peopleList = new  HashMap<>();

    //constructor to initialize and add 4 team members
    public PeopleController() {
        Person Ella = new Person("Ella", "Cook", "123 Apple Rd", "123-456-7890");
//        Ella.addVisitedPlace(new Place("New York", "USA", "10001", "40.7128° N, 74.0060° W", LocalDate.of(2022, 1, 15)));
//        Ella.addPlaceToTravel(new Place("Paris", "France", "75001", "48.8566° N, 2.3522° E", LocalDate.of(2023, 5, 20)));

        Person Raghu = new Person("Raghuram", "Guddati", "456 Bakers Ln", "111-222-3333");
//        Raghu.addVisitedPlace(new Place("San Francisco", "USA", "94102", "37.7749° N, 122.4194° W", LocalDate.of(2021, 7, 10)));
//        Raghu.addPlaceToTravel(new Place("Tokyo", "Japan", "100-0001", "35.6762° N, 139.6503° E", LocalDate.of(2023, 8, 5)));

        Person Tanvi = new Person("Tanvi", "Mehetre", "789 Chef cir", "112-233-4455");
//        Tanvi.addVisitedPlace(new Place("Dublin", "Ireland", "N/A", "53.3498° N, 6.2603° W", LocalDate.of(2022, 2, 21)));
//        Tanvi.addPlaceToTravel(new Place("Chicago", "USA", "60007", "41.8781° N, 87.6298° W", LocalDate.of(2024, 1, 1)));

        Person Tejal = new Person("Tejal", "Deveshetwar", "012 Decorators Drive", "123-456-7889");
//        Tejal.addVisitedPlace(new Place("Denver", "USA", "80014", "39.7392° N, 104.9903° W", LocalDate.of(2020, 9, 18)));
//        Tejal.addPlaceToTravel(new Place("Miami", "USA", "33101", "25.7617° N, 80.1918° W", LocalDate.of(2023, 12, 15)));

        peopleList.put(Ella.getFirstName(), Ella);
        peopleList.put(Raghu.getFirstName(), Raghu);
        peopleList.put(Tanvi.getFirstName(), Tanvi);
        peopleList.put(Tejal.getFirstName(), Tejal);
    }

   @GetMapping("/{personUid}")
   public Person getPersonByUid(@PathVariable String personUid){
        Person person = peopleList.get(personUid);
        if(person != null){
            return ResponseEntity.ok(person).getBody();
        }
        else{
            return (Person) ResponseEntity.notFound(); //not sure about if this will work ...
        }
   }

    @PostMapping("/{personUid}/add-visited-places")
    public void addVisitedPlace(@PathVariable String personUid, @RequestBody Place place){
        Person person = peopleList.get(personUid);
        if(person != null){
            person.addVisitedPlace(place);
        }
    }

    @PostMapping("{personUid}/add-places-to-travel")
    public void addPlaceToTravel(@PathVariable String personUid, @RequestBody Place place){
        Person person = peopleList.get(personUid);
        if(person != null){
            person.addPlaceToTravel(place);
        }
    }

    @PostMapping("{personUid}/visited-places")
    public List<Place> getPlacesTraveled(@PathVariable String personUid){
        Person person = peopleList.get(personUid);
        if(person != null){
            return person.getVisitedPlaces();
        }
        else{
            return null;
        }
    }

    @PostMapping("{personUid}/places-to-travel")
    public List<Place> getPlacesToTravel(@PathVariable String personUid){
        Person person = peopleList.get(personUid);
        if(person != null){
            return person.getPlacesToTravel();
        }
        else{
            return null;
        }
    }


    //CRUDL (create/read/update/delete/list)
    // use POST, GET, PUT, DELETE, GET methods for CRUDL

    // THIS IS THE LIST OPERATION
    // gets all the people in the list and returns it in JSON format
    // This controller takes no input. 
    // Springboot automatically converts the list to JSON format 
    // in this case because of @ResponseBody
    // Note: To LIST, we use the GET method
    @GetMapping("/people")
    public @ResponseBody HashMap<String,Person> getAllPersons() {
        return peopleList;
    }

    // THIS IS THE CREATE OPERATION
    // springboot automatically converts JSON input into a person object and 
    // the method below enters it into the list.
    // It returns a string message in THIS example.
    // in this case because of @ResponseBody
    // Note: To CREATE we use POST method
    @PostMapping("/people")
    public @ResponseBody String createPerson(@RequestBody Person person) {
        System.out.println(person);
        peopleList.put(person.getFirstName(), person);
        return "New person "+ person.getFirstName() + " Saved";
    }

    @GetMapping("/people/last-name/{lastName}")
    public @ResponseBody Person getPersonLast(@PathVariable String lastName) {
        System.out.println("Searching for last name: " + lastName);

        for (Person person : peopleList.values()){
            if (person.getLastName().equalsIgnoreCase(lastName)){
                System.out.println("Found: " + person);
                return person;
            }
        }

        System.out.println("Person not found for last name: " + lastName);
        return null;
    }



    // THIS IS THE READ OPERATION
    // Springboot gets the PATHVARIABLE from the URL
    // We extract the person from the HashMap.
    // springboot automatically converts Person to JSON format when we return it
    // in this case because of @ResponseBody
    // Note: To READ we use GET method
    @GetMapping("/people/{firstName}")
    public @ResponseBody Person getPersonFirst(@PathVariable String firstName) {
        Person p = peopleList.get(firstName);
        return p;
    }

    // THIS IS THE UPDATE OPERATION
    // We extract the person from the HashMap and modify it.
    // Springboot automatically converts the Person to JSON format
    // Springboot gets the PATHVARIABLE from the URL
    // Here we are returning what we sent to the method
    // in this case because of @ResponseBody
    // Note: To UPDATE we use PUT method
    @PutMapping("/people/{firstName}")
    public @ResponseBody Person updatePerson(@PathVariable String firstName, @RequestBody Person p) {
        peopleList.replace(firstName, p);
        return peopleList.get(firstName);
    }

    // THIS IS THE DELETE OPERATION
    // Springboot gets the PATHVARIABLE from the URL
    // We return the entire list -- converted to JSON
    // in this case because of @ResponseBody
    // Note: To DELETE we use delete method
    
    @DeleteMapping("/people/{firstName}")
    public @ResponseBody HashMap<String, Person> deletePerson(@PathVariable String firstName) {
        peopleList.remove(firstName);
        return peopleList;
    }
}

