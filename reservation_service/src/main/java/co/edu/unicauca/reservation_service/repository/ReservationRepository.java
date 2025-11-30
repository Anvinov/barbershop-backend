package co.edu.unicauca.reservation_service.repository;

import co.edu.unicauca.reservation_service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByBarberIdAndDate(Long barberId,  LocalDate date);
    //List<Reservation> findByClientId(Long clientId);
}
