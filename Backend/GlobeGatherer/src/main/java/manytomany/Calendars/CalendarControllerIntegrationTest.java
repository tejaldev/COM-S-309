package manytomany.Calendars;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CalendarController.class)
public class CalendarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalendarRepository calendarRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCalendarEvent() throws Exception {
        Calendar calendarEvent = new Calendar("Destination", "2023-01-01", "2023-01-07");
        when(calendarRepository.findById(1)).thenReturn(calendarEvent);

        mockMvc.perform(get("/calendar/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destination").value("Destination"))
                .andExpect(jsonPath("$.startDate").value("2023-01-01"))
                .andExpect(jsonPath("$.endDate").value("2023-01-07"));
    }

    @Test
    public void testAddCalendarEvent() throws Exception {
        Calendar newCalendarEvent = new Calendar("New Destination", "2023-02-01", "2023-02-07");
        when(calendarRepository.save(any(Calendar.class))).thenReturn(newCalendarEvent);

        mockMvc.perform(post("/calendar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"destination\":\"New Destination\",\"startDate\":\"2023-02-01\",\"endDate\":\"2023-02-07\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.destination").value("New Destination"))
                .andExpect(jsonPath("$.startDate").value("2023-02-01"))
                .andExpect(jsonPath("$.endDate").value("2023-02-07"));
    }
}
