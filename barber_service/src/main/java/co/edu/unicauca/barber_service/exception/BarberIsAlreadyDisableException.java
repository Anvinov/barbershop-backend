package co.edu.unicauca.barber_service.exception;

public class BarberIsAlreadyDisableException extends RuntimeException {
    public BarberIsAlreadyDisableException(Long id) {
        super("Barber with ID " + id + " is already disabled.");
    }
}
