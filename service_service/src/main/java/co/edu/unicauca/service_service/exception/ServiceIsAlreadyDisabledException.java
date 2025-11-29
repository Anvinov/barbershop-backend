package co.edu.unicauca.service_service.exception;

public class ServiceIsAlreadyDisabledException extends RuntimeException {
    public ServiceIsAlreadyDisabledException(Long id) {
        super("Service with ID " + id + " is already disabled.");
    }
}
