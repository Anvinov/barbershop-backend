package co.edu.unicauca.reservation_service.exception;

public class ServiceDisabledException extends RuntimeException {
    public ServiceDisabledException(String ids) {
        super("Reservation not allowed: services with id " + ids + " disabled");
    }
}
