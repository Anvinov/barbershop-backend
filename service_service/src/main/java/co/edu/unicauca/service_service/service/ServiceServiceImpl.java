package co.edu.unicauca.service_service.service;

import co.edu.unicauca.service_service.entity.Category;
import co.edu.unicauca.service_service.entity.ServiceEntity;
import co.edu.unicauca.service_service.exception.*;
import co.edu.unicauca.service_service.infra.dto.request.ServiceRequestDTO;
import co.edu.unicauca.service_service.infra.dto.response.ServiceResponseDTO;
import co.edu.unicauca.service_service.infra.mapper.ServiceMapper;
import co.edu.unicauca.service_service.repository.CategoryRepository;
import co.edu.unicauca.service_service.repository.ServiceRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository, CategoryRepository categoryRepository) {
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ServiceResponseDTO createService(ServiceRequestDTO request) {
        if(serviceRepository.existsByName(request.getName())){
            throw new ServiceNameAlreadyExistsException(request.getName());
        }

        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategory()));

        ServiceEntity service = ServiceMapper.toEntity(request);
        service.setCategory(category);
        service.setAvailable(true);

        serviceRepository.save(service);

        return ServiceMapper.toResponse(service);
    }

    @Override
    public ServiceResponseDTO updateService(Long id, ServiceRequestDTO request) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));

        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(request.getCategory()));

        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setPrice(request.getPrice());
        service.setDuration(request.getDuration());
        service.setCategory(category);

        serviceRepository.save(service);

        return ServiceMapper.toResponse(service);
    }

    @Override
    public ServiceResponseDTO deleteService(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));

        serviceRepository.delete(service);

        return ServiceMapper.toResponse(service);
    }

    @Override
    public List<ServiceResponseDTO> getAllServices() {
        List<ServiceEntity> services = serviceRepository.findAll();

        return services.stream()
                .map(ServiceMapper::toResponse)
                .toList();
    }

    @Override
    public List<ServiceResponseDTO> getServicesByCategory(String category) {
        List<ServiceEntity> services = serviceRepository.findAllByCategory_Name(category);

        return services.stream()
                .map(ServiceMapper::toResponse)
                .toList();
    }

    @Override
    public ServiceResponseDTO getServiceById(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));

        return ServiceMapper.toResponse(service);
    }

    @Override
    public ServiceResponseDTO disableService(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));

        if(!service.isAvailable()){
            throw new ServiceIsAlreadyDisabledException(id);
        }

        service.setAvailable(false);

        serviceRepository.save(service);

        return ServiceMapper.toResponse(service);
    }

    @Override
    public ServiceResponseDTO enableService(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));

        if(service.isAvailable()){
            throw new ServiceIsAlreadyEnabledException(id);
        }

        service.setAvailable(true);

        serviceRepository.save(service);

        return ServiceMapper.toResponse(service);
    }
}
