package onetoone.Friends;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import onetoone.Persons.Person;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; // Display only the 'name' field

    @ManyToOne
    @JsonIgnore
    private Person person;

    public Friend(String name) {
        this.name = name;
    }

    public Friend() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
