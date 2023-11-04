package manytomany.Friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @author Raghuram Guddati
 * 
 */ 

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend findById(int id);

    @Transactional
    void deleteById(int id);

    List<Friend> findByPersonId(Long personId);
}
