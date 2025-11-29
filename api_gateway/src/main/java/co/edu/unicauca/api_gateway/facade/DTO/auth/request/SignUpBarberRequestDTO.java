package co.edu.unicauca.api_gateway.facade.DTO.auth.request;

import co.edu.unicauca.api_gateway.facade.DTO.barber.request.ScheduleRequestDTO;
import jakarta.validation.constraints.*;
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

    @NotNull
    private ScheduleRequestDTO schedule;
}
