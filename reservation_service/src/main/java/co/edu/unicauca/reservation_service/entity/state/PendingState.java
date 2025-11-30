package co.edu.unicauca.reservation_service.entity.state;

import co.edu.unicauca.reservation_service.entity.Reservation;
import co.edu.unicauca.reservation_service.exception.StateTransitionNotAllowedException;

public class PendingState implements ReservationState{

    @Override
    public void startReservation(Reservation reservation) {
        reservation.setState(new StartedState());
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        reservation.setState(new CancelledState());
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservation.setState(new DeletedState());
    }

    @Override
    public void finishReservation(Reservation reservation) {
        throw new StateTransitionNotAllowedException("The reservation cannot change to \"FINISH\" from \"PENDING\"");
    }

    @Override
    public String getStatus() {
        return "PENDING";
    }
}
