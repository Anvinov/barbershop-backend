package co.edu.unicauca.reservation_service.infra.dto.barber.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class TimeSlotResponseDTO {
    private Long id;
    private Long scheduleId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private String reason;
}
