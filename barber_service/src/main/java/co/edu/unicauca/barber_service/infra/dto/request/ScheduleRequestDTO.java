package co.edu.unicauca.barber_service.infra.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
public class ScheduleRequestDTO {
    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotEmpty
    private List<String> workDays;
}
