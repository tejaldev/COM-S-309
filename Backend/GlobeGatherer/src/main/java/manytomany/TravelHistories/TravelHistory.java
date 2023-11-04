package manytomany.TravelHistories;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import manytomany.Persons.Person;

/**
 * 
 * @author Vivek Bengre
 */ 

@Entity
public class TravelHistory {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destinationName;
//    private String country;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public TravelHistory(String destinationName) { //, String country
        this.destinationName = destinationName;
//        this.country = country;
    }

    public TravelHistory() {
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
//    public String getCountry(){return country;}
//    public void setCountry(String country){
//        this.country = country;}
    public Person getPerson(){
        return person;
    }
    public void setPerson(Person person){
        this.person = person;
    }

}
