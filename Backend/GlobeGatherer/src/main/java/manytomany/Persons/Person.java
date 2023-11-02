package manytomany.Persons;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import java.util.Set;
import manytomany.Friends.Friend;
import manytomany.Profile.Description;
import manytomany.TravelToDos.TravelToDo;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@Entity
public class Person {

     /* 
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sign_up_name", unique = true)
    private String SignUpName;
    private String SignUpUsername;
    private String SignUpPassword;
    private String SignUpEmail;
    private String SignUpPhoneNo;




    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private Description description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "travel_id")
    private TravelToDo travelToDo;


    public Person(String SignUpName, String SignUpUsername, String SignUpPassword, String SignUpEmail, String SignUpPhoneNo) {
        this.SignUpName = SignUpName;
        this.SignUpUsername = SignUpUsername;
        this.SignUpPassword = SignUpPassword;
        this.SignUpEmail = SignUpEmail;
        this.SignUpPhoneNo = SignUpPhoneNo;
    }

    public Person() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getSignUpName(){
        return SignUpName;
    }

    public void setSignUpName(String name){
        this.SignUpName = name;
    }

    public String getSignUpUsername(){
        return SignUpUsername;
    }

    public void setSignUpUsername(String emailId){
        this.SignUpUsername = emailId;
    }

    public String getSignUpPassword(){
        return SignUpPassword;
    }

    public void setSignUpPassword(String password){
        this.SignUpPassword = password;
    }

    public String getSignUpEmail(){
        return SignUpEmail;
    }

    public void setSignUpEmail(String email){
        this.SignUpEmail = email;
    }

    public String getSignUpPhoneNo(){
        return SignUpPhoneNo;
    }

    public void setSignUpPhoneNo(String phoneNo){
        this.SignUpPhoneNo = phoneNo;
    }

    public Friend getFriend(){
        return friend;
    }

    public void setFriend(Friend friend){
        this.friend = friend;
    }

    public Description getDescription(){
        return description;
    }

    public void setDescription(Description description){
        this.description = description;
    }

    public TravelToDo getTravelToDo(){
        return travelToDo;
    }

    public void setTravelToDo(TravelToDo travelToDo){
        this.travelToDo = travelToDo;
    }
    
}
