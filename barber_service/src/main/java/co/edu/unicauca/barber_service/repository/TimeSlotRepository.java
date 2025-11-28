package co.edu.unicauca.barber_service.repository;

import co.edu.unicauca.barber_service.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findBySchedule_IdAndDate(Long scheduleId, LocalDate date);
}
