package co.edu.unicauca.reservation_service.entity.state;

import co.edu.unicauca.reservation_service.entity.Reservation;
import co.edu.unicauca.reservation_service.exception.StateTransitionNotAllowedException;

public class CancelledState implements ReservationState{
    @Override
    public void startReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to STARTED from CANCELLED");
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation has already been CANCELLED");
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to DELETED from CANCELLED");
    }

    @Override
    public void finishReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to FINISHED from CANCELLED");
    }

    @Override
    public String getStatus() {
        return "CANCELLED";
    }
}
