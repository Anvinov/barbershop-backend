package co.edu.unicauca.reservation_service.entity.state;

import co.edu.unicauca.reservation_service.entity.Reservation;
import co.edu.unicauca.reservation_service.exception.StateTransitionNotAllowedException;

public class StartedState implements ReservationState{
    @Override
    public void startReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation has already been \"STARTED\"");
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to \"CANCELLED\" from \"STARTED\"");
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to \"DELETED\" from \"STARTED\"");
    }

    @Override
    public void finishReservation(Reservation reservation) {
        reservation.setState(new FinishedState());
    }

    @Override
    public String getStatus() {
        return "STARTED";
    }
}
