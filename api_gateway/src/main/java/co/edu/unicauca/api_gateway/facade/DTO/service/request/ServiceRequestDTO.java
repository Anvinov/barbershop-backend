package co.edu.unicauca.api_gateway.facade.DTO.service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ServiceRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Long price;

    @NotNull
    @Positive
    private Integer duration;

    @NotBlank
    private String category;
}
