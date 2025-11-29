package co.edu.unicauca.api_gateway.facade.DTO.barber.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ScheduleResponseDTO {
    private Long barberId;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> workDays;
}
