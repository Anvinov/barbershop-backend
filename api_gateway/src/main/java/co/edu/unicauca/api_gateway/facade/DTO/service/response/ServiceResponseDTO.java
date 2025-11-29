package co.edu.unicauca.api_gateway.facade.DTO.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String price;
    private String duration;
    private boolean available;
    private String category;
}
