package co.edu.unicauca.api_gateway.facade.DTO.barber.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleRequestDTO {
    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotEmpty
    private List<String> workDays;
}
