package co.edu.unicauca.barber_service.repository;

import co.edu.unicauca.barber_service.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Schedule findScheduleByBarber_Id(Long id);
}
