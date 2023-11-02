package manytomany.Persons;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findById(int id);

    @Transactional
    void deleteById(int id);

    // Custom query method to find a Person by SignUpName
    @Query("SELECT p FROM Person p WHERE p.SignUpName = :SignUpName")
    Person findBySignUpName(@Param("SignUpName") String SignUpName);

    @Query("SELECT p FROM Person p WHERE p.SignUpUsername = :SignUpUsername")
    Person findBySignUpUsername(@Param("SignUpUsername") String SignUpUsername);

    // Custom query method to delete a Person by SignUpName

    @Modifying
    @Transactional
    @Query("DELETE FROM Person p WHERE p.SignUpName = :signUpName")
    void deleteBySignUpName(@Param("signUpName") String signUpName);
}

