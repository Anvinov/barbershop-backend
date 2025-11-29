package co.edu.unicauca.service_service.infra.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CategoryRequestDTO {
    @NotEmpty
    private String name;
}
