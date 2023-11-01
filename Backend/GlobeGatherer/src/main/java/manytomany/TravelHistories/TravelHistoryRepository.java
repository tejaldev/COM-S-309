package manytomany.TravelHistories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface TravelHistoryRepository extends JpaRepository<TravelHistory, Long> {
    TravelHistory findById(int id);

    @Transactional
    void deleteById(int id);
}
