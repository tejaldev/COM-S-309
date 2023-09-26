package onetoone.Locations;

import onetoone.Locations.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Raghuram Guddati
 * 
 */ 

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findById(int id);

    @Transactional
    void deleteById(int id);
}
