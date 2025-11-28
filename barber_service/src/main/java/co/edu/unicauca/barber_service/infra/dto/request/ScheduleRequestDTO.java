package co.edu.unicauca.barber_service.infra.dto.request;

import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
public class ScheduleRequestDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> workDays;
}
