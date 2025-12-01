package co.edu.unicauca.reservation_service.entity;

import co.edu.unicauca.reservation_service.entity.state.ReservationState;
import co.edu.unicauca.reservation_service.repository.converter.ReservationStateConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barberId;
    private Long clientId;

    @Convert(converter = ReservationStateConverter.class)
    private ReservationState state;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private List<Long> services;

    public void startReservation() {
        state.startReservation(this);
    }

    public void cancelReservation() {
        state.cancelReservation(this);
    }

    public void deleteReservation() {
        state.deleteReservation(this);
    }

    public void finishReservation() {
        state.finishReservation(this);
    }

}
