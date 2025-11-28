package co.edu.unicauca.api_gateway.facade.DTO.barber;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BarberResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private ScheduleResponseDTO schedule;
}
