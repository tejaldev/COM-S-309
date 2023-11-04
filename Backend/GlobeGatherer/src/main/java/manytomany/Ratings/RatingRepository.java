package manytomany.Ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findById(int id);

    @Transactional
    void deleteById(int id);
}
