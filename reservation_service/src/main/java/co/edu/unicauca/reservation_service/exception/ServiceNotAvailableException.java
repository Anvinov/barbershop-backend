package co.edu.unicauca.reservation_service.exception;

public class ServiceNotAvailableException extends RuntimeException {
    public ServiceNotAvailableException(String serviceName) {
        super("Service of " + serviceName + " is not available");
    }
}
