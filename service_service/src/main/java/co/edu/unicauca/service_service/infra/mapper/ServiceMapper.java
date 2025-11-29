package co.edu.unicauca.service_service.infra.mapper;

import co.edu.unicauca.service_service.entity.ServiceEntity;
import co.edu.unicauca.service_service.infra.dto.request.ServiceRequestDTO;
import co.edu.unicauca.service_service.infra.dto.response.ServiceResponseDTO;

public class ServiceMapper {
    public static ServiceResponseDTO toResponse(ServiceEntity service){
        return new ServiceResponseDTO(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getPrice().toString(),
                service.getDuration().toString(),
                service.isAvailable(),
                service.getCategory().getName()
        );
    }

    public static ServiceEntity toEntity(ServiceRequestDTO dto){
        ServiceEntity service = new ServiceEntity();

        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setPrice(dto.getPrice());
        service.setDuration(dto.getDuration());

        return service;
    }
}
