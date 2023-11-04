package manytomany.Friends;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import manytomany.Persons.Person;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String username;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<Person> friends = new HashSet<>();

    //one half of ManytoMany
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
