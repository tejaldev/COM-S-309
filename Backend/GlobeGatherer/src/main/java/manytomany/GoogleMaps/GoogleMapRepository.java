package manytomany.GoogleMaps;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface GoogleMapRepository extends JpaRepository<GoogleMap, Long> {
    GoogleMap findById(int id);

    @Transactional
    void deleteById(int id);
}
