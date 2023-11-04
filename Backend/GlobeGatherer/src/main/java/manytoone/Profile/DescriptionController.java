package manytoone.Profile;

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
public class DescriptionController {

    @Autowired
    DescriptionRepository descriptionRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/description/all")
    List<Description> getAllDescription(){
        return descriptionRepository.findAll();
    }

    @GetMapping(path = "/description/{id}")
    Description getDescriptionById(@PathVariable int id){
        return descriptionRepository.findById(id);
    }

    @PostMapping(path = "/description/add")
    String createDescription(@RequestBody Description Description){
        if (Description == null)
            return failure;
        descriptionRepository.save(Description);
        return success;
    }

    @PutMapping(path = "/description/update/{id}")
    Description updateDescription(@PathVariable int id, @RequestBody Description request){
        Description description = descriptionRepository.findById(id);
        if(description == null)
            return null;
        descriptionRepository.save(request);
        return descriptionRepository.findById(id);
    }

    @DeleteMapping(path = "/description/delete/{id}")
    String deleteDescription(@PathVariable int id){
        descriptionRepository.deleteById(id);
        return success;
    }
}
