package manytomany.TravelToDos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import manytomany.Persons.Person;

/**
 *
 * @author Vivek Bengre
 */

@Entity
public class TravelToDo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destinationName;
    private String country;

    private String startDate;

    private String endDate;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public TravelToDo(String destinationName, String country, String startDate, String endDate) {
        this.destinationName = destinationName;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TravelToDo() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getDestinationName(){return destinationName;}
    public void setDestinationName(String destinationName){
        this.destinationName = destinationName;}

    public String getStartDate(){return startDate;}

    public void setStartDate(String startdate){ this.startDate = startdate;}

    public String getEndDate(){return endDate;}

    public void setEndDate(String enddate){this.endDate = enddate;}

    public String getCountry(){return country;}
    public void setCountry(String country){
        this.country = country;}

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

}