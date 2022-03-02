
package base.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import base.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByEmail(String email);



    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsById(int id);
    
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    Boolean existsByPhoneNumber(String phoneNumber);
}
