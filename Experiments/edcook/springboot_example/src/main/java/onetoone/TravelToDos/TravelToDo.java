package onetoone.TravelToDos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import onetoone.Users.User;

/**
 * 
 * @author Vivek Bengre
 */ 

@Entity
public class TravelToDo {
    
    /* 
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destinationName;
    private String country;
    private String date;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * @JsonIgnore is to assure that there is no infinite loop while returning either user/laptop objects (laptop->user->laptop->...)
     */
    @OneToOne
    @JsonIgnore
    private User user;

    public TravelToDo(String destinationName, String country, String date) {
        this.destinationName = destinationName;
        this.country = country;
        this.date = date;
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
    public void setDestinationName(String destinationName){this.destinationName = destinationName;}
    public String getCountry(){return country;}
    public void setCountry(String country){this.country = country;}
    public String getDate(){return country;}
    public void setDate(String date){this.date = date;}
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

}
