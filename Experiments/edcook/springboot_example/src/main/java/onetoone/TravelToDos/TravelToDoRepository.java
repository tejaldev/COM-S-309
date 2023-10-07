package onetoone.TravelToDos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface TravelToDoRepository extends JpaRepository<TravelToDo, Long> {
    TravelToDo findById(int id);

    @Transactional
    void deleteById(int id);
}
