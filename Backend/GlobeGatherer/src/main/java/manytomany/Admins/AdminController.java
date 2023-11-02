package manytomany.Admins;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author Raghuram Guddati
 *
 */

@RestController
public class AdminController {

    HashMap<String, Person> personList = new  HashMap<>();

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PersonRepository personRepository;  // Inject the PersonRepository

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/admins/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return ResponseEntity.ok(admins);
    }

//    @GetMapping(path = "/admins/{id}")
//    Credential getPersonCredential(@PathVariable int id) {
//        Person admin = personRepository.findById(id);
//        if (admin != null) {
//            Credential credentialDTO = new Credential();
//            credentialDTO.setSignUpUsername(admin.getSignUpUsername());
//            credentialDTO.setSignUpPassword(admin.getSignUpPassword());
//            return credentialDTO;
//        } else {
//            return null; // Handle the case where no person with the given ID is found
//        }
//    }

    @GetMapping("/admins/{SignUpName}")
    public ResponseEntity<Credential> getPersonBySignUpName(@PathVariable String SignUpName) {
        Person person = personRepository.findBySignUpName(SignUpName);

        if (person != null) {
            // Create a Credential instance and set the desired fields
            Credential credential = new Credential();
            credential.setSignUpUsername(person.getSignUpUsername());
            credential.setSignUpPassword(person.getSignUpPassword());

            return ResponseEntity.ok(credential);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @PostMapping(path = "/admins/add")
    String createAdmin(@RequestBody Admin admin){
        if (admin == null)
            return failure;
        adminRepository.save(admin);
        return success;
    }

    @PutMapping("/admins/update/{id}")
    Admin updateAdmin(@PathVariable int id, @RequestBody Admin request){
        Admin admin = adminRepository.findById(id);
        if(admin == null)
            return null;
        adminRepository.save(request);
        return adminRepository.findById(id);
    }

    @DeleteMapping(path = "/admins/delete/{SignUpName}")
    String banPerson(@PathVariable String SignUpName) {
        personRepository.deleteBySignUpName(SignUpName);
        return success;
    }

}
