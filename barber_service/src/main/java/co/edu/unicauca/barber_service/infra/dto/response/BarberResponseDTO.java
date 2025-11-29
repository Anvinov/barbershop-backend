package co.edu.unicauca.barber_service.infra.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BarberResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private ScheduleResponseDTO schedule;
}
