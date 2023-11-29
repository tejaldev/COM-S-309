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
    private byte[] imageData;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public GoogleImage(byte[] imageData) {
        this.imageData = imageData;
    }

    public GoogleImage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
