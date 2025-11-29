package co.edu.unicauca.service_service.exception;

public class ServiceNameAlreadyExistsException extends RuntimeException {
    public ServiceNameAlreadyExistsException(String name) {
        super("Service with name " + name + " already exists");
    }
}
