package co.edu.unicauca.reservation_service.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email) {
        super("Client with email " + email + " not found");
    }
}
