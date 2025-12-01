package co.edu.unicauca.reservation_service.infra.dto.barber.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BarberResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private boolean available;
    private ScheduleResponseDTO schedule;
}
