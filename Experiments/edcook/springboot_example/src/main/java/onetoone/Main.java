package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import onetoone.TravelToDos.TravelToDo;
import onetoone.TravelToDos.TravelToDoRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

/**
 * 
 * @author Vivek Bengre
 * 
 */ 

@SpringBootApplication
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines
    /**
     * 
     * @param userRepository repository for the User entity
     * @param laptopRepository repository for the Laptop entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, TravelToDoRepository travelToDoRepository) {
        return args -> {
            User user1 = new User("John", "john@somemail.com");
            User user2 = new User("Jane", "jane@somemail.com");
            User user3 = new User("Justin", "justin@somemail.com");
            TravelToDo travel1 = new TravelToDo("Paris","France","Jan 1 2024");
            TravelToDo travel2 = new TravelToDo("Chicago","USA","Jan 1 2024");
            TravelToDo travel3 = new TravelToDo("New York","USA","Jan 1 2024");
            user1.setDestination(travel1);
            user2.setDestination(travel2);
            user3.setDestination(travel3);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        };
    }

}
