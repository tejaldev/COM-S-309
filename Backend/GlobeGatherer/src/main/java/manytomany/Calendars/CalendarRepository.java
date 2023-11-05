package manytomany.Calendars;


import manytomany.Calendars.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Calendar findById(int id);

    @Transactional
    void deleteById(int id);
}
