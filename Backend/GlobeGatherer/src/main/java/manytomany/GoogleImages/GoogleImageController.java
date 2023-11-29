package manytomany.GoogleImages;

import manytomany.Persons.Person;
import manytomany.Persons.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Raghuram Guddati
 *
 */
@RestController
public class GoogleImageController {

    @Autowired
    GoogleImageRepository googleImageRepository;

    @Autowired
    private PersonRepository personRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";



    @GetMapping(path = "/GoogleImages")
    List<GoogleImage> getAllGoogleImage(){
        return googleImageRepository.findAll();
    }


    @GetMapping(path = "/GoogleImages/{SignUpName}")
    public ResponseEntity<List<GoogleImage>> getImageBySignUpName(@PathVariable String SignUpName) {

        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getImages() == null) {
            return ResponseEntity.notFound().build();
        }

        List<GoogleImage> images = new ArrayList<>(user.getImages());

        return ResponseEntity.ok(images);
    }

    @GetMapping(path = "/GoogleImages/{SignUpName}/{imageId}")
    public ResponseEntity<GoogleImage> getImageByIdForSignUpName(
            @PathVariable String SignUpName,
            @PathVariable int imageId) {

        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null || user.getImages() == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<GoogleImage> optionalImage = user.getImages()
                .stream()
                .filter(image -> image.getId() == imageId)
                .findFirst();

        if (optionalImage.isPresent()) {
            GoogleImage image = optionalImage.get();
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/GoogleImages/{SignUpName}")
    public ResponseEntity<GoogleImage> createGoogleImageForSignUpName(
            @PathVariable String SignUpName,
            @RequestBody GoogleImage googleImage
    ) {
        // Find the user with the provided SignUpName
        Person user = personRepository.findBySignUpName(SignUpName);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        googleImage.setPerson(user);

        GoogleImage savedGoogleImage = googleImageRepository.save(googleImage);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGoogleImage);
    }


}
