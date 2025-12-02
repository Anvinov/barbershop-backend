package co.edu.unicauca.reservation_service.entity.state;

import co.edu.unicauca.reservation_service.entity.Reservation;
import co.edu.unicauca.reservation_service.exception.StateTransitionNotAllowedException;

public class FinishedState implements ReservationState{
    @Override
    public void startReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to STARTED from FINISHED");
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to CANCELLED from FINISHED");
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to DELETED from FINISHED");
    }

    @Override
    public void finishReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation has already been FINISHED");
    }

    @Override
    public String getStatus() {
        return "FINISHED";
    }
}
