package co.edu.unicauca.service_service.exception;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(Long id) {
        super("Service with id " + id + " not found");
    }
}
