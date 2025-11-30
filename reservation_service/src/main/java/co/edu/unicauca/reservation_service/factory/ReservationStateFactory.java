package co.edu.unicauca.reservation_service.factory;

import co.edu.unicauca.reservation_service.entity.state.*;

public class ReservationStateFactory {

    public static ReservationState from(String stateName) {
        return switch (stateName) {
            case "PENDING"   -> new PendingState();
            case "STARTED"   -> new StartedState();
            case "CANCELLED" -> new CancelledState();
            case "DELETED"   -> new DeletedState();
            case "FINISHED"  -> new FinishedState();
            default -> throw new IllegalArgumentException("Unknown state: " + stateName);
        };
    }

}

