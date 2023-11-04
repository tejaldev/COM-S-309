package manytomany;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import manytomany.Friends.FriendRepository;
import manytomany.Persons.PersonRepository;
import manytomany.Profile.DescriptionRepository;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@SpringBootApplication
@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        //SpringApplication.run(Websocket3Application.class, args);
    }

    // Create 3 users with their machines
    /**
     * 
     * @param personRepository repository for the User entity
     * @param friendRepository repository for the Laptop entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */

    @Bean
    CommandLineRunner initUser(PersonRepository personRepository, FriendRepository friendRepository, DescriptionRepository descriptionRepository) {
        return args -> {
//            Person person1 = new Person("Raghu", "raghu@somemail.com", "12345678", "Raghu", "12345678");
//            Person person2 = new Person("john", "john@somemail.com", "12345678", "john", "12345678");
//            Person person3 = new Person("xyz", "xyz@somemail.com", "12345678", "xyz", "12345678");
//
//
//            Friend friend1 = new Friend("Robert", "noob");
//            Friend friend2 = new Friend( "Sam", "pro");
//            Friend friend3 = new Friend( "Tony", "noob");
//            person1.setFriend(friend1);
//            person2.setFriend(friend2);
//            person3.setFriend(friend3);
//            personRepository.save(person1);
//            personRepository.save(person2);
//            personRepository.save(person3);
//
//            Description description1 = new Description("I am a java developer");
//            Description description2 = new Description("I am a C developer");
//            Description description3 = new Description("I am a python developer");
//            person1.setDescription(description1);
//            person2.setDescription(description2);
//            person3.setDescription(description3);
//            personRepository.save(person1);
//            personRepository.save(person2);
//            personRepository.save(person3);

        };
    }
}
