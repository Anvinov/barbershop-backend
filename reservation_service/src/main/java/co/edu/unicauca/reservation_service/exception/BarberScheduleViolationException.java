package co.edu.unicauca.reservation_service.exception;

import java.time.LocalTime;

public class BarberScheduleViolationException extends RuntimeException {
    public BarberScheduleViolationException(Long barberId, LocalTime startTime, LocalTime endTime) {
        super("Reservation not allowed: barber with id " + barberId + " works from " + startTime +
                " to " + endTime);
    }
}
