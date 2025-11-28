package co.edu.unicauca.barber_service.exception;

public class TimeSlotNotFoundException extends RuntimeException {
    public TimeSlotNotFoundException(Long id) {
        super("TimeSlot with ID " + id + " not found.");
    }
}
