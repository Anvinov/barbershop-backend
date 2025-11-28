package co.edu.unicauca.api_gateway.facade.DTO.auth;

import co.edu.unicauca.api_gateway.facade.DTO.barber.ScheduleRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpBarberRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private ScheduleRequestDTO schedule;
}
