package coms309.people;

import java.time.LocalDate;

public class Place {
    private String city;
    private String country;
    private String zipcode;
    private String coordinates;
    private LocalDate dateTraveled;

    public Place(String city, String country, String zipcode, String coordinates, LocalDate dateTraveled){
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.coordinates = coordinates;
        this.dateTraveled = dateTraveled;
    }

    public String getCity(){
        return city;
    }

    public String getCountry(){
        return country;
    }

    public String getZipcode(){
        return zipcode;
    }

    public LocalDate getDateTraveled(){
        return dateTraveled;
    }
}
