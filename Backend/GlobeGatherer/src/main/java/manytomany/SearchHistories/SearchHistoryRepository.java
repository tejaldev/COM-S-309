package manytomany.SearchHistories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    SearchHistory findById(int id);

    @Transactional
    void deleteById(int id);
}
