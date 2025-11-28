package co.edu.unicauca.barber_service.infra.dto.request;

import co.edu.unicauca.barber_service.entity.SlotStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class TimeSlotRequestDTO {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private SlotStatus status;
    private String reason;
}
