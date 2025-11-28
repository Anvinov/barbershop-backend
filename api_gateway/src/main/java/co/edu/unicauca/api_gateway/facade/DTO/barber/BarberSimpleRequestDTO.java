package co.edu.unicauca.api_gateway.facade.DTO.barber;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BarberSimpleRequestDTO {
    private String name;
    private String phone;
    private String email;
}
