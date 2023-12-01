package manytomany.Friends;

import com.fasterxml.jackson.annotation.JsonIgnore;
import manytomany.Persons.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "person_in_app_id")
    private Long personInAppId;

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
