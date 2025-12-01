package co.edu.unicauca.reservation_service.exception;

import java.time.LocalTime;

public class OverlappingReservationException extends RuntimeException {
    public OverlappingReservationException(String message) {
        super("Reservation not allowed: " + message);
    }
}
