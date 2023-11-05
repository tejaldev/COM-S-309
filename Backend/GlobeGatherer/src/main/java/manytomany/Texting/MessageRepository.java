package manytomany.Texting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByUserNameAndSeenIsFalse(String userName);

    Optional<Message> findByUserNameAndContent(String userName, String content);



    @Modifying
    @Query("UPDATE Message m SET m.seen = true WHERE m.id = :messageId")
    void markMessageAsSeen(@Param("messageId") Long messageId);
}

