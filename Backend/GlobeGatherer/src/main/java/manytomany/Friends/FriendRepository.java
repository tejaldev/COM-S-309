package manytomany.Friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT f FROM Friend f WHERE f.name = :friendName")
    Friend findByFriendName(@Param("friendName") String friendName);

    @Modifying
    @Query("UPDATE Friend f SET f.personInAppId = (SELECT p.id FROM Person p WHERE p.SignUpName = f.name) WHERE f.name = :friendName")
    void updatePersonInAppIdByFriendName(@Param("friendName") String friendName);

}
