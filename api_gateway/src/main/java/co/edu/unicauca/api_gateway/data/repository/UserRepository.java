package co.edu.unicauca.api_gateway.data.repository;

import java.util.Optional;

import co.edu.unicauca.api_gateway.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
    Boolean existsByEmail(String email);
}
