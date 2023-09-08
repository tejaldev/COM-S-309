package coms309.people;

/**
 * Provides the Definition/Structure for the people row
 *
 * @author Raghuram Guddati
 */
public class User {
    private String fullName;
    private String country;
    private String state;
    private String zipCode;

    public User() {

    }

    public User(String fullName, String country, String state, String zipCode) {
        this.fullName = fullName;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getFullName() {

        return this.fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public String getCountry() {

        return this.country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getState() {

        return this.state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public String getZipCode() {

        return this.zipCode;
    }

    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return fullName + " "
                + country + " "
                + state + " "
                + zipCode;
    }
}
