package co.edu.unicauca.api_gateway.facade.DTO.service.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CategoryRequestDTO {
    @NotEmpty
    private String name;
}
