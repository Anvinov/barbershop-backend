package co.edu.unicauca.reservation_service.exception;

public class InvalidTimeRangeException extends RuntimeException {
    public InvalidTimeRangeException() {
        super("Reservation not allowed: start time must be before end time");
    }
}
