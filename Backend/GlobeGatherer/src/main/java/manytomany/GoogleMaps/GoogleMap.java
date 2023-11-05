package manytomany.GoogleMaps;


import com.fasterxml.jackson.annotation.JsonIgnore;
import manytomany.Persons.Person;

import javax.persistence.*;

/**
 * 
 * @author Vivek Bengre
 */ 

@Entity
public class GoogleMap {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destinationName;
//    private String country;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public GoogleMap(String destinationName) { //, String country
        this.destinationName = destinationName;
//        this.country = country;
    }

    public GoogleMap() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {

        this.destinationName = destinationName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {

        this.person = person;
    }
}

