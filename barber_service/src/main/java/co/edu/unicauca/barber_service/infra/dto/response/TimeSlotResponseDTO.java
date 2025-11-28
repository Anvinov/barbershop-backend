package co.edu.unicauca.barber_service.infra.dto.response;

import co.edu.unicauca.barber_service.entity.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
public class TimeSlotResponseDTO {
    private Long id;
    private Long scheduleId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private SlotStatus status;
    private String reason;
}
