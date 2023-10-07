package onetoone.Laptops;

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
public class LaptopController {

    @Autowired
    LaptopRepository laptopRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/laptops")
    List<Laptop> getAllLaptops(){
        return laptopRepository.findAll();
    }

    @GetMapping(path = "/laptops/{name}")
    Laptop getLaptopById(@PathVariable String name){
        return laptopRepository.findByName(name);
    }

    @PostMapping(path = "/laptops")
    String createLaptop(@RequestBody Laptop Laptop){
        if (Laptop == null)
            return failure;
        laptopRepository.save(Laptop);
        return success;
    }

    @PutMapping(path = "/laptops/{name}")
    Laptop updateLaptop(@PathVariable String name, @RequestBody Laptop request){
        Laptop laptop = laptopRepository.findByName(name);
        if(laptop == null)
            return null;
        laptopRepository.save(request);
        return laptopRepository.findByName(name);
    }

    @DeleteMapping(path = "/laptops/{name}")
    String deleteLaptop(@PathVariable String name){
        laptopRepository.deleteByName(name);
        return success;
    }
}
