package co.edu.unicauca.reservation_service.exception;

public class ClientDisabledException extends RuntimeException {
    public ClientDisabledException(Long id) {
        super("Reservation not allowed: client with id " + id + " disabled");
    }
}
