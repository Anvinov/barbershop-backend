package co.edu.unicauca.service_service.service;

import co.edu.unicauca.service_service.infra.dto.request.ServiceRequestDTO;
import co.edu.unicauca.service_service.infra.dto.response.ServiceResponseDTO;

import java.util.List;
import java.util.Map;

public interface ServiceService {
    ServiceResponseDTO createService(ServiceRequestDTO request);
    ServiceResponseDTO updateService(Long id, ServiceRequestDTO request);
    ServiceResponseDTO deleteService(Long id);
    List<ServiceResponseDTO> getAllServices();
    List<ServiceResponseDTO> getServicesByCategory(String category);
    ServiceResponseDTO getServiceById(Long id);
    ServiceResponseDTO disableService(Long id);
    ServiceResponseDTO enableService(Long id);

    List<Long> servicesAreDisabled(List<Long> ids);

}
