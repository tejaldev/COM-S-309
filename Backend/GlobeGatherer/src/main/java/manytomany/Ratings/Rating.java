package manytomany.Ratings;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import manytomany.Persons.Person;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comment;
    private String stars;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public Rating(String comment, String stars) {

        this.comment = comment;
        this.stars = stars;
    }

    public Rating() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String description) {

        this.comment = description;
    }

    public String getStars() {

        return stars;
    }

    public void setStars(String stars) {

        this.stars = stars;
    }

    public Person getPerson() {

        return person;
    }

    public void setPerson(Person person) {

        this.person = person;
    }
}
