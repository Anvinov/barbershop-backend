package co.edu.unicauca.reservation_service.repository;

import co.edu.unicauca.reservation_service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByBarberId(Long barberId);
    List<Reservation> findByBarberIdAndDate(Long barberId,  LocalDate date);

    List<Reservation> findByClientId(Long clientId);
    List<Reservation> findByClientIdAndDate(Long clientId,  LocalDate date);

    @Query("""
       SELECT r FROM Reservation r
       WHERE r.barberId = :barberId
         AND r.date = :date
         AND r.startTime < :endTime
         AND r.endTime > :startTime
       """)
    List<Reservation> findOverlappingBarberReservations(
            Long barberId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    );

    @Query("""
       SELECT r FROM Reservation r
       WHERE r.clientId = :clientId
         AND r.date = :date
         AND r.startTime < :endTime
         AND r.endTime > :startTime
       """)
    List<Reservation> findOverlappingClientReservations(
            Long clientId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    );

}
