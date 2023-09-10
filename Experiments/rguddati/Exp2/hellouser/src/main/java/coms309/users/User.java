package coms309.users;

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
    private String email;
    private String contactInfo;

    public User() {
    }

    public User(String fullName, String country, String state, String zipCode, String email,
                String contactInfo) {
        this.fullName = fullName;
        this.country = country;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
        this.contactInfo = contactInfo;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    public void setContactInfo() {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return fullName + " "
                + country + " "
                + state + " "
                + zipCode + " "
                + email + " "
                + contactInfo;
    }
}
