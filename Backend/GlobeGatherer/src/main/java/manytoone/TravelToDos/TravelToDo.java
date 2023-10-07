package manytoone.TravelToDos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import manytoone.Users.User;

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

    @ManyToOne
    @JsonIgnore
    private User user;

    public TravelToDo(String destinationName, String country) {
        this.destinationName = destinationName;
        this.country = country;
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
    public String getCountry(){return country;}
    public void setCountry(String country){
        this.country = country;}
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

}
