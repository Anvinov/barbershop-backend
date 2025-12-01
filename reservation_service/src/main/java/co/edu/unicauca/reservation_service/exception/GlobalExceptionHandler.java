package co.edu.unicauca.reservation_service.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<?> handleReservationNotFoundException(ReservationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("RESERVATION_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ClientDisabledException.class)
    public ResponseEntity<?> handleClientDisabled(ClientDisabledException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("CLIENT_DISABLED", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(BarberDisabledException.class)
    public ResponseEntity<?> handleBarberDisabled(BarberDisabledException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("BARBER_DISABLED", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(ServiceDisabledException.class)
    public ResponseEntity<?> handleServiceDisabled(ServiceDisabledException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("SERVICE_DISABLED", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(OverlappingReservationException.class)
    public ResponseEntity<?> handleOverlappingReservation(OverlappingReservationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("OVERLAPPING_RESERVATION", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(BarberScheduleViolationException.class)
    public ResponseEntity<?> handleBarberScheduleViolation(BarberScheduleViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("BARBER_SCHEDULE_VIOLATION", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(InvalidTimeRangeException.class)
    public ResponseEntity<?> handleInvalidTimeRange(InvalidTimeRangeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("INVALID_TIME_RANGE", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(StateTransitionNotAllowedException.class)
    public ResponseEntity<?> handleStateTransitionNotAllowed(StateTransitionNotAllowedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(error("STATE_TRANSITION_NOT_ALLOWED", ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(ServiceNotAvailableException.class)
    public ResponseEntity<?> handleServiceIsNotAvailable(ServiceNotAvailableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error("SERVICE_NOT_AVAILABLE", ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException ex) {
        try {
            String json = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();

            // Convertir JSON → Map (diccionario)
            Map<String, Object> errorMap =
                    mapper.readValue(json, Map.class);

            int status = (int) errorMap.get("status");

            return ResponseEntity
                    .status(status)
                    .body(errorMap);

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(Map.of("error", "UNPARSABLE_REMOTE_ERROR"));
        }
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

