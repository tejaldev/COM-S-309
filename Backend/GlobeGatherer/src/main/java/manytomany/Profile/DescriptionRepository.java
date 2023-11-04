package manytomany.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface DescriptionRepository extends JpaRepository<Description, Long> {
    Description findById(int id);

    @Transactional
    void deleteById(int id);
}
