package coms309.people;


import java.util.ArrayList;
import java.util.List;

/**
 * Provides the Definition/Structure for the people row
 *
 * @author Vivek Bengre
 */

public class Person {
    private String uid;
    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private List<Place> visitedPlaces;
    private List<Place> placesToTravel;

    public Person(String firstName, String lastName, String address, String telephone){
        this.uid = UIDGenerator.generateUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.visitedPlaces = new ArrayList<>();
        this.placesToTravel = new ArrayList<>();
    }

    public String getUid() {
        return uid;
    }

    // don't think this will be necessary with UIDGenerator
    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Place> getVisitedPlaces() {
        return this.visitedPlaces;
    }

    public void addVisitedPlace(Place place) {
        visitedPlaces.add(place);
    }

    public List<Place> getPlacesToTravel() {
        return this.placesToTravel;
    }

    public void addPlaceToTravel(Place place) {
        placesToTravel.add(place);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return firstName + " " 
               + lastName + " "
               + address + " "
               + telephone + " "
                + uid + " "
                + visitedPlaces + " "
                + placesToTravel;
    }
}
