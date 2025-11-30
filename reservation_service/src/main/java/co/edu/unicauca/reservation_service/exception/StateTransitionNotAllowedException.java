package co.edu.unicauca.reservation_service.exception;

public class StateTransitionNotAllowedException extends RuntimeException {
    public StateTransitionNotAllowedException(String message) {
        super(message);
    }
}
