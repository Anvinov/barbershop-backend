package co.edu.unicauca.barber_service.infra.dto.request;

import lombok.Getter;

@Getter
public class BarberRequestDTO {
    private String name;
    private String phone;
    private String email;
    private ScheduleRequestDTO schedule;
}
