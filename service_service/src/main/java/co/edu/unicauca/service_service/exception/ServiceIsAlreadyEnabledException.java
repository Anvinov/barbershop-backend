package co.edu.unicauca.service_service.exception;

public class ServiceIsAlreadyEnabledException extends RuntimeException {
    public ServiceIsAlreadyEnabledException(Long id) {
        super("Service with ID " + id + " is already enabled.");
    }
}
