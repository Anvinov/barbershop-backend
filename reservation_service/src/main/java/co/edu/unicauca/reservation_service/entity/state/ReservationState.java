package co.edu.unicauca.reservation_service.entity.state;

import co.edu.unicauca.reservation_service.entity.Reservation;

public interface ReservationState {
    void startReservation(Reservation reservation);
    void cancelReservation(Reservation reservation);
    void deleteReservation(Reservation reservation);
    void finishReservation(Reservation reservation);
    String getStatus();
}
