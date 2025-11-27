package co.edu.unicauca.client_service.exception;

public class ClientIsAlreadyDisableException extends RuntimeException {
    public ClientIsAlreadyDisableException(Long id) {
        super("Client with ID " + id + " is already disabled.");
    }
}
