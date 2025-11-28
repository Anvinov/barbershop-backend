package co.edu.unicauca.barber_service.exception;

public class BarberNotFoundException extends RuntimeException {
    public BarberNotFoundException(Long id) {
        super("Barber with ID " + id + " not found.");
    }
}

