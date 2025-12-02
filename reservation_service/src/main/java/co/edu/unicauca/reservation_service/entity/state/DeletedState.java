package co.edu.unicauca.reservation_service.entity.state;

import co.edu.unicauca.reservation_service.entity.Reservation;
import co.edu.unicauca.reservation_service.exception.StateTransitionNotAllowedException;

public class DeletedState implements ReservationState{
    @Override
    public void startReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to STARTED from DELETED");
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to CANCELLED from DELETED");
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation has already been DELETED");
    }

    @Override
    public void finishReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to FINISHED from DELETED");
    }

    @Override
    public String getStatus() {
        return "DELETED";
    }
}
