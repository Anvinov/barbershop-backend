package co.edu.unicauca.barber_service.infra.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ScheduleResponseDTO {
    private Long barberId;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> workDays;
}
