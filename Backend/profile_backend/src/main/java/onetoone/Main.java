package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import onetoone.Friends.Friend;
import onetoone.Friends.FriendRepository;
import onetoone.Persons.Person;
import onetoone.Persons.PersonRepository;

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
    CommandLineRunner initUser(PersonRepository personRepository, FriendRepository friendRepository) {
        return args -> {
            Person person1 = new Person("Raghu", "raghu@somemail.com");
            Person person2 = new Person("Ella", "ella@somemail.com");
            Person person3 = new Person("Tejal", "tejal@somemail.com");
            Friend friend1 = new Friend("Robert");
            Friend friend2 = new Friend( "Sam");
            Friend friend3 = new Friend( "Tony");
            person1.setFriend(friend1);
            person2.setFriend(friend2);
            person3.setFriend(friend3);
            personRepository.save(person1);
            personRepository.save(person2);
            personRepository.save(person3);

        };
    }

}
