package co.edu.unicauca.api_gateway.facade.DTO.barber;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BarberRequestDTO {
    private String name;
    private String phone;
    private String email;
    private ScheduleRequestDTO schedule;
}
