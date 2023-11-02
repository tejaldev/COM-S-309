package manytomany.Admins;

import javax.persistence.*;

import manytomany.Persons.Person;

/**
 *
 * @author Raghuram Guddati
 *
 */

@Entity
public class Admin {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by Spring Boot
     * The @GeneratedValue generates a value if not already present. The strategy in this case is to start from 1 and increment for each table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String SignUpUsername;
    private String SignUpPassword;
    private String SignUpEmail;
    private String SignUpPhoneNo;

    /*
     * @OneToOne creates a relation between the current entity/table (Admin) with the entity/table defined below it (Person).
     * Cascade is responsible for propagating all changes, even to children of the class. For example, changes made to Admin within a Person object will be reflected in the database.
     * @JoinColumn defines the ownership of the foreign key. In this case, the Person table will have a field called person_id.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    public Admin(String SignUpUsername, String SignUpPassword, String SignUpEmail, String SignUpPhoneNo) {
        this.SignUpUsername = SignUpUsername;
        this.SignUpPassword = SignUpPassword;
        this.SignUpEmail = SignUpEmail;
        this.SignUpPhoneNo = SignUpPhoneNo;
    }

    public Admin() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSignUpUsername() {
        return SignUpUsername;
    }

    public void setSignUpUsername(String emailId) {
        this.SignUpUsername = emailId;
    }

    public String getSignUpPassword() {
        return SignUpPassword;
    }

    public void setSignUpPassword(String password) {
        this.SignUpPassword = password;
    }

    public String getSignUpEmail() {
        return SignUpEmail;
    }

    public void setSignUpEmail(String email) {
        this.SignUpEmail = email;
    }

    public String getSignUpPhoneNo() {
        return SignUpPhoneNo;
    }

    public void setSignUpPhoneNo(String phoneNo) {
        this.SignUpPhoneNo = phoneNo;
    }

    public Person getPerson() {
        return person;
    }
}
