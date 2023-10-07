package onetoone.Credentials;

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
public class CredentialController {

    @Autowired
    CredentialRepository credentialRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/credentials")
    List<Credential> getAllCredentials(){
        return credentialRepository.findAll();
    }

    @GetMapping(path = "/credentials/{id}")
    Credential getCredentialById(@PathVariable int id){
        return credentialRepository.findById(id);
    }

    @PostMapping(path = "/credentials")
    String createCredential(@RequestBody Credential credential){
        if (credential == null)
            return failure;
        credentialRepository.save(credential);
        return success;
    }

    @PutMapping(path = "/credentials/{id}")
    Credential updateCredential(@PathVariable int id, @RequestBody Credential request){
        Credential credential = credentialRepository.findById(id);
        if(credential == null)
            return null;
        credentialRepository.save(request);
        return credentialRepository.findById(id);
    }

    @DeleteMapping(path = "/credentials/{id}")
    String deleteCredential(@PathVariable int id){
        credentialRepository.deleteById(id);
        return success;
    }
}
