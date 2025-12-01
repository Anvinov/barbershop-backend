package co.edu.unicauca.reservation_service.exception;

public class BarberDisabledException extends RuntimeException {
    public BarberDisabledException(Long id) {
        super("Reservation not allowed: barber with id " + id + " disabled");
    }
}
