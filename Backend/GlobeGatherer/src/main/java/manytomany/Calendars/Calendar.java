package manytomany.Calendars;

import com.fasterxml.jackson.annotation.JsonIgnore;
import manytomany.Persons.Person;

import javax.persistence.*;

@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String destination;
    private String startDate;
    private String endDate;

    //one half of ManytoMany
    @ManyToOne
    @JsonIgnore
    private Person person;

    public Calendar(String destination, String startDate, String endDate) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Calendar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startdate) {
        this.startDate = startdate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String enddate) {
        this.endDate = enddate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
