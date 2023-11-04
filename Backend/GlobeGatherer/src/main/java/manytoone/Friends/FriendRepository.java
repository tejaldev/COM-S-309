package manytoone.Friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend findById(int id);

    @Transactional
    void deleteById(int id);
}
