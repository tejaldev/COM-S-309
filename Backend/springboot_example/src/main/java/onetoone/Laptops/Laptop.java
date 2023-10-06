package onetoone.Laptops;

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
public class Laptop {
    
    /* 
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String qualifications;
    private String description;
    private String phone;
    private String email;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * @JsonIgnore is to assure that there is no infinite loop while returning either user/laptop objects (laptop->user->laptop->...)
     */
    @OneToOne
    @JsonIgnore
    private User user;

    public Laptop(String name, String qualifications, String description, String phone, String email) {
        this.name = name;
        this.qualifications = qualifications;
        this.description = description;
        this.phone = phone;
        this.email = email;
    }

    public Laptop() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String cpuClock){
        this.name = cpuClock;
    }

    public String getQualifications(){
        return qualifications;
    }

    public void setQualifications(String cpuCores){
        this.qualifications = cpuCores;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String manufacturer){
        this.phone = manufacturer;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String cost){
        this.email = cost;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String ram){
        this.description = ram;
    }

}
