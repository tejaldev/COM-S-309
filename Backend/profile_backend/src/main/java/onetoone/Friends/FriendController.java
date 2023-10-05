package onetoone.Friends;

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
public class FriendController {

    @Autowired
    FriendRepository friendRepository;
    
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/friends/all")
    List<Friend> getAllFriend(){
        return friendRepository.findAll();
    }

    @GetMapping(path = "/friends/{id}")
    Friend getFriendById(@PathVariable int id){
        return friendRepository.findById(id);
    }

    @PostMapping(path = "/friends/add")
    String createFriend(@RequestBody Friend Friend){
        if (Friend == null)
            return failure;
        friendRepository.save(Friend);
        return success;
    }

    @PutMapping(path = "/friends/update/{id}")
    Friend updateFriend(@PathVariable int id, @RequestBody Friend request){
        Friend friend = friendRepository.findById(id);
        if(friend == null)
            return null;
        friendRepository.save(request);
        return friendRepository.findById(id);
    }

    @DeleteMapping(path = "/friends/delete/{id}")
    String deleteFriend(@PathVariable int id){
        friendRepository.deleteById(id);
        return success;
    }
}
