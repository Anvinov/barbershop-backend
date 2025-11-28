package co.edu.unicauca.api_gateway.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<?> handleBadRequest(IllegalRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error("BAD_REQUEST", ex.getMessage(), HttpStatus.BAD_REQUEST));
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

