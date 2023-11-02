package manytomany.Ratings;

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
public class RatingController {

    @Autowired
    RatingRepository ratingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/rating/all")
    List<Rating> getAllRating(){
        return ratingRepository.findAll();
    }

    @GetMapping(path = "/rating/{id}")
    Rating getRatingById(@PathVariable int id){
        return ratingRepository.findById(id);
    }

    @PostMapping(path = "/rating/add")
    String createRatingDescription(@RequestBody Rating Rating){
        if (Rating == null)
            return failure;
        ratingRepository.save(Rating);
        return success;
    }

    @PutMapping(path = "/rating/update/{id}")
    Rating updateRating(@PathVariable int id, @RequestBody Rating request){
        Rating description = ratingRepository.findById(id);
        if(description == null)
            return null;
        ratingRepository.save(request);
        return ratingRepository.findById(id);
    }

    @DeleteMapping(path = "/rating/delete/{id}")
    String deleteRating(@PathVariable int id){
        ratingRepository.deleteById(id);
        return success;
    }
    
}
