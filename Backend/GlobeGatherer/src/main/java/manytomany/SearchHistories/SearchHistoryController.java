package manytomany.SearchHistories;

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
public class SearchHistoryController {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/search/all")
    List<SearchHistory> getAllRating(){

        return searchHistoryRepository.findAll();
    }

    @GetMapping(path = "/search/{id}")
    SearchHistory getRatingById(@PathVariable int id){

        return searchHistoryRepository.findById(id);
    }

    @PostMapping(path = "/search/add")
    String createRatingDescription(@RequestBody SearchHistory Rating){
        if (Rating == null)
            return failure;
        searchHistoryRepository.save(Rating);
        return success;
    }

    @PutMapping(path = "/search/update/{id}")
    SearchHistory updateRating(@PathVariable int id, @RequestBody SearchHistory request){
        SearchHistory description = searchHistoryRepository.findById(id);
        if(description == null)
            return null;
        searchHistoryRepository.save(request);
        return searchHistoryRepository.findById(id);
    }

    @DeleteMapping(path = "/search/delete/{id}")
    String deleteRating(@PathVariable int id){
        searchHistoryRepository.deleteById(id);
        return success;
    }
}
