package co.edu.unicauca.barber_service.infra.dto.request;

import co.edu.unicauca.barber_service.entity.SlotStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class TimeSlotRequestDTO {
    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private SlotStatus status;

    @NotBlank
    private String reason;
}
