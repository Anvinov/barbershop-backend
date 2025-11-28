package co.edu.unicauca.barber_service.repository;

import co.edu.unicauca.barber_service.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber, Long> {
    boolean existsById(Long id);
    boolean existsByEmail(String email);
}
