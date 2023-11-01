package manytomany.Friends;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import manytomany.Persons.Person;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String username;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public Friend(String name, String username) {
        this.name = name;
        this.username = username;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
