package co.edu.unicauca.client_service.repository;

import co.edu.unicauca.client_service.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByEmail(String email);
}

