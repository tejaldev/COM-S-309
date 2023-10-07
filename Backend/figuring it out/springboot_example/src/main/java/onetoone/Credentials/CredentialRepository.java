package onetoone.Credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

//package onetoone.Credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vivek Bengre
 *
 */

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    Credential findById(int id);

    @Transactional
    void deleteById(int id);
}
