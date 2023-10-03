package onetoone.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findById(int id);

    void deleteById(int id);

    User findByLaptop_Id(int id);

    User findByEmailId(String emailId);

}
