package co.edu.unicauca.barber_service.repository;

import co.edu.unicauca.barber_service.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarberRepository extends JpaRepository<Barber, Long> {
    Optional<Barber> findByEmail(String email);

    boolean existsById(Long id);
    boolean existsByEmail(String email);
}
