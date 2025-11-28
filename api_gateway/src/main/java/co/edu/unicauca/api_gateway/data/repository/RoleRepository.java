package co.edu.unicauca.api_gateway.data.repository;

import java.util.Optional;

import co.edu.unicauca.api_gateway.data.entity.ERole;
import co.edu.unicauca.api_gateway.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
