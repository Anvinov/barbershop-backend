package co.edu.unicauca.barber_service.exception;

public class EmailNotFoundException extends RuntimeException {
  public EmailNotFoundException(String email) {
    super("Barber with email " + email + " not found");
  }
}
