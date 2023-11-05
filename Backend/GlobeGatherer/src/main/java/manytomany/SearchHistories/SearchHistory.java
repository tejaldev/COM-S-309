package manytomany.SearchHistories;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import manytomany.Persons.Person;

@Entity
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String search;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public SearchHistory(String search) {

        this.search = search;
    }

    public SearchHistory() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getSearch() {

        return search;
    }


    public void setSearch(String description) {

        this.search = description;
    }

    public Person getPerson() {

        return person;
    }

    public void setPerson(Person person) {

        this.person = person;
    }{}
}
