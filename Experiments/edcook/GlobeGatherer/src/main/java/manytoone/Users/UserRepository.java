package manytoone.Users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findById(int id);

    void deleteById(int id);

//    User findByTravel_Id(int id);
}
