package onetoone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import onetoone.Credentials.Credential;
import onetoone.Credentials.CredentialRepository;
//import onetoone.Users.User;
//import onetoone.Users.UserRepository;

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
//     * @param userRepository repository for the User entity
     * @param laptopRepository repository for the Laptop entity
     * Creates a commandLine runner to enter dummy data into the database
     * As mentioned in User.java just associating the Laptop object with the User will save it into the database because of the CascadeType
     */
    @Bean
    CommandLineRunner initUser( CredentialRepository laptopRepository) {
        return args -> {
//            User user1 = new User("John", "john@somemail.com");
//            User user2 = new User("Jane", "jane@somemail.com");
//            User user3 = new User("Justin", "justin@somemail.com");
            Credential cred1 = new Credential( "van", "van1", "hi", "6121221234", "van@email.com");
            Credential cred2 = new Credential( "bob", "bob1", "password", "6121232345", "bob@email.com");
            Credential cred3 = new Credential( "pam", "pam1", "pampass", "6122223333", "pam@email.com");
//            user1.setCredential(cred1);
//            user2.setCredential(cred2);
//            user3.setCredential(cred3);
//            CredentialRepository.save(user1);
//            CredentialRepository.save(user2);
//            CredentialRepository.save(user3);

        };
    }

}
