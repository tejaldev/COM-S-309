package manytomany.Calendars;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CalendarRepositoryTest {

    @Autowired
    private CalendarRepository calendarRepository;

    @Test
    public void testFindById() {
        Calendar calendar = new Calendar("Test Destination", "2023-03-01", "2023-03-07");
        calendar = calendarRepository.save(calendar);

        Calendar found = calendarRepository.findById(calendar.getId());
        assertThat(found).isEqualTo(calendar);
    }

    @Test
    public void testDeleteById() {
        Calendar calendar = new Calendar("Test Destination", "2023-04-01", "2023-04-07");
        calendar = calendarRepository.save(calendar);

        calendarRepository.deleteById(calendar.getId());
        Calendar found = calendarRepository.findById(calendar.getId());
        assertThat(found).isNull();
    }
}
