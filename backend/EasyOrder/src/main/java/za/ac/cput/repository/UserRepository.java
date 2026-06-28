package za.ac.cput.repository;


import za.ac.cput.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByContactEmailAndPassword(String email, String password);
    Optional<User> findByContactEmail(String email);
    Optional<User> findByContactEmailAndPasswordAndRole(String email, String password, User.Role role);
    boolean existsByContactEmail(String email);
    boolean existsByContactNumberAndRole(String number, User.Role role);
}