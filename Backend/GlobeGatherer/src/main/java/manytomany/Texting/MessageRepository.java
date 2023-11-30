package manytomany.Texting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByUserNameAndContent(String userName, String content);
    List<Message> findByUserNameAndSeen(String userName, boolean seen);

}

