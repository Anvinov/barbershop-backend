package co.edu.unicauca.api_gateway.facade.DTO.barber;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleRequestDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> workDays;
}
