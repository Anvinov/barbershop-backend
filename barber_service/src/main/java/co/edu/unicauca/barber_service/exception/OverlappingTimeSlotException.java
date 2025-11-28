package co.edu.unicauca.barber_service.exception;

import java.time.LocalTime;

public class OverlappingTimeSlotException extends RuntimeException {
    public OverlappingTimeSlotException(LocalTime startDate, LocalTime endDate) {
        super("There is already a time slot between " + startDate + " and " + endDate);
    }
}
