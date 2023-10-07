package onetoone.Laptops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

public interface LaptopRepository extends JpaRepository<Laptop, String> {
    Laptop findByName(String name);

    @Transactional
    void deleteByName(String name);
}
