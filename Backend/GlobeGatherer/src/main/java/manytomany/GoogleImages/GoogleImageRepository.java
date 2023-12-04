package manytomany.GoogleImages;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
public interface GoogleImageRepository extends JpaRepository<GoogleImage, Long> {

    GoogleImage findById(int id);

    @Transactional
    void deleteById(int id);
}
