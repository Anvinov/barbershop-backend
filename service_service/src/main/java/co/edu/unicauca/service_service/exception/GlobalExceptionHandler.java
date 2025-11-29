package co.edu.unicauca.service_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleCategoryNotFound(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("CATEGORY_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<?> handleServiceNotFound(ServiceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("SERVICE_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(CategoryNameAlreadyExistsException.class)
    public ResponseEntity<?> handleCategoryNameDuplicate(CategoryNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("CATEGORY_NAME_ALREADY_EXISTS", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(ServiceNameAlreadyExistsException.class)
    public ResponseEntity<?> handleServiceNameDuplicate(ServiceNameAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("SERVICE_NAME_ALREADY_EXISTS", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(ServiceIsAlreadyDisabledException.class)
    public ResponseEntity<?> handleServiceIsAlreadyDisabled(ServiceIsAlreadyDisabledException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("SERVICE_IS_ALREADY_DISABLED", ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(ServiceIsAlreadyEnabledException.class)
    public ResponseEntity<?> handleServiceIsAlreadyEnabled(ServiceIsAlreadyEnabledException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error("SERVICE_IS_ALREADY_ENABLED", ex.getMessage(), HttpStatus.CONFLICT));
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

