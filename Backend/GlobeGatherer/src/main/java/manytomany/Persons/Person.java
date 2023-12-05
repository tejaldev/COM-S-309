package manytomany.Persons;

import manytomany.Calendars.Calendar;
import manytomany.ExpenseAnalyzers.ExpenseAnalyzer;
import manytomany.Friends.Friend;
import manytomany.GoogleImages.GoogleImage;
import manytomany.GoogleMaps.GoogleMap;
import manytomany.Profile.Description;
import manytomany.Ratings.Rating;
import manytomany.SearchHistories.SearchHistory;
import manytomany.Texting.Message;
import manytomany.TravelHistories.TravelHistory;
import manytomany.TravelToDos.TravelToDo;

import javax.persistence.*;
import java.util.List;
import java.util.Set;



/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@Entity
public class Person {

     /* 
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sign_up_name", unique = true)
    private String SignUpName;
    private String SignUpUsername;
    private String SignUpPassword;
    private String SignUpEmail;
    private String SignUpPhoneNo;






    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private Description description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "travel_id")
    private TravelToDo travelToDo;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Friend> friends;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<TravelToDo> travelToDos;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<SearchHistory> searches;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<TravelHistory> histories;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<GoogleMap> maps;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<GoogleImage> images;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Calendar> cals;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<ExpenseAnalyzer> expen;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Message> messages;




    public Person(String SignUpName, String SignUpUsername, String SignUpPassword, String SignUpEmail, String SignUpPhoneNo) {
        this.SignUpName = SignUpName;
        this.SignUpUsername = SignUpUsername;
        this.SignUpPassword = SignUpPassword;
        this.SignUpEmail = SignUpEmail;
        this.SignUpPhoneNo = SignUpPhoneNo;
    }

    public Person() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getSignUpName(){
        return SignUpName;
    }

    public void setSignUpName(String name){
        this.SignUpName = name;
    }

    public String getSignUpUsername(){
        return SignUpUsername;
    }

    public void setSignUpUsername(String emailId){
        this.SignUpUsername = emailId;
    }

    public String getSignUpPassword(){
        return SignUpPassword;
    }

    public void setSignUpPassword(String password){
        this.SignUpPassword = password;
    }

    public String getSignUpEmail(){
        return SignUpEmail;
    }

    public void setSignUpEmail(String email){
        this.SignUpEmail = email;
    }

    public String getSignUpPhoneNo(){
        return SignUpPhoneNo;
    }

    public void setSignUpPhoneNo(String phoneNo){
        this.SignUpPhoneNo = phoneNo;
    }

    public Friend getFriend(){
        return friend;
    }

    public void setFriend(Friend friend){
        this.friend = friend;
    }

    public Description getDescription(){
        return description;
    }

    public void setDescription(Description description){
        this.description = description;
    }

    public TravelToDo getTravelToDo(){
        return travelToDo;
    }

    public void setTravelToDo(TravelToDo travelToDo){
        this.travelToDo = travelToDo;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public Set<TravelToDo> getTravelToDos() {
        return travelToDos;
    }

    public void setTravelToDos(Set<TravelToDo> travelToDos) {
        this.travelToDos = travelToDos;
    }

    public void setSearches(Set<SearchHistory> searches) {
        this.searches = searches;
    }

    public Set<SearchHistory> getSearches() {
        return searches;
    }

    public void setHistories(Set<TravelHistory> histories) {
        this.histories = histories;
    }

    public Set<TravelHistory> getHistories() {
        return histories;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setMaps(Set<GoogleMap> maps) {
        this.maps = maps;
    }

    public Set<GoogleMap> getMaps() {
        return maps;
    }

    public void setCals(Set<Calendar> cals) {
        this.cals = cals;
    }

    public Set<Calendar> getCals() {
        return cals;
    }

    public void setImages(Set<GoogleImage> images) {
        this.images = images;
    }

    public Set<GoogleImage> getImages() {
        return images;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setExpen(Set<ExpenseAnalyzer> expen) {
        this.expen = expen;
    }

    public Set<ExpenseAnalyzer> getExpen() {
        return expen;
    }
}
