package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import onetoone.Locations.Location;
import onetoone.Locations.LocationRepository;
import onetoone.Users.User;
import onetoone.Users.UserRepository;

/**
 * 
 * @author Raghuram Guddati
 * 
 */ 

@SpringBootApplication
//@EnableJpaRepositories
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Create 3 users with their machines
    /**
     * 
     * @param userRepository repository for the User entity
     * @param locationRepository repository for the Laptop entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser(UserRepository userRepository, LocationRepository locationRepository) {
        return args -> {
            User user1 = new User("John", "john@somemail.com");
            User user2 = new User("Jane", "jane@somemail.com");
            User user3 = new User("Justin", "justin@somemail.com");
            Location location1 = new Location( "void", "Japan", "Tokyo", "sema", 60015);
            Location location2 = new Location( "dera", "United States", "Iowa", "Ames", 50014);
            Location location3 = new Location( "feals", "United States", "New York", "apple", 75642);
            user1.setLocation(location1);
            user2.setLocation(location2);
            user3.setLocation(location3);
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

        };
    }

}
