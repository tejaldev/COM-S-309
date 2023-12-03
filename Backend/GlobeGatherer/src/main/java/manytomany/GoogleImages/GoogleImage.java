package manytomany.GoogleImages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import manytomany.Persons.Person;

import javax.persistence.*;

@Entity
public class GoogleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob // Use @Lob annotation for large objects like images
    private byte[] params;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public GoogleImage(byte[] params) {
        this.params = params;
    }

    public GoogleImage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getParams() {
        return params;
    }

    public void setParams(byte[] imageData) {
        this.params = imageData;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
