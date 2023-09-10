package coms309.people;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Place {
    private String placeID;
    private String city;
    private String country;
    private String zipcode;
    private String coordinates;
    private LocalDate dateTraveled;

    public Place(String city, String country, String zipcode, String coordinates, LocalDate dateTraveled){
        this.placeID = UUID.randomUUID().toString();
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.coordinates = coordinates;
        this.dateTraveled = dateTraveled;
    }

    // constructor that allows a place with placeID
    public Place(String placeID, String city, String country, String zipcode, String coordinates, LocalDate dateTraveled){
        this.placeID = (placeID != null) ? placeID : UUID.randomUUID().toString();
        this.placeID = UUID.randomUUID().toString();
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
        this.coordinates = coordinates;
        this.dateTraveled = dateTraveled;
    }

    public String getPlaceID(){
        return placeID;
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

    public static Place getPlaceById(String placeID, List<Place> places){
        for(Place place : places){
            if(place.getPlaceID().equalsIgnoreCase(placeID)){
                return place;
            }
        }
        return null;
    }

    public static boolean removePlaceByID(String placeID, List<Place> places){
        for(Place place : places){
            if(place.getPlaceID().equalsIgnoreCase(placeID)){
                places.remove(place);
                System.out.println("Removed place successfully");
                return true;
            }
        }
        return false;
    }

}
