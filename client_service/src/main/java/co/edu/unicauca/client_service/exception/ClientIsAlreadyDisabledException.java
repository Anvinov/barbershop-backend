package co.edu.unicauca.client_service.exception;

public class ClientIsAlreadyDisabledException extends RuntimeException {
    public ClientIsAlreadyDisabledException(Long id) {
        super("Client with ID " + id + " is already disabled.");
    }
}
