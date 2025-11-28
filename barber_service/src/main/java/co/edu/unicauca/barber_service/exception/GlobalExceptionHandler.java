package co.edu.unicauca.barber_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BarberIsAlreadyDisableException.class)
    public ResponseEntity<?> handleBarberIsAlreadyDisable(BarberIsAlreadyDisableException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("BARBER_IS_ALREADY_DISABLE", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(BarberNotFoundException.class)
    public ResponseEntity<?> handleBarberNotFound(BarberNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("BARBER_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(TimeSlotNotFoundException.class)
    public ResponseEntity<?> handleTimeSlotNotFound(TimeSlotNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("TIME_SLOT_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<?> handleScheduleNotFound(ScheduleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("SCHEDULE_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailDuplicate(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("EMAIL_ALREADY_EXISTS", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(OverlappingTimeSlotException.class)
    public ResponseEntity<?> handleOverlappingTimeSlot(OverlappingTimeSlotException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("OVERLAPPING_TIME_SLOT", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<?> handleBadRequest(IllegalRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error("BAD_REQUEST", ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error("INTERNAL_ERROR", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private Map<String, Object> error(String code, String message, HttpStatus status) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", code,
                "message", message
        );
    }
}

