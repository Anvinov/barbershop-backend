package co.edu.unicauca.reservation_service.infra.mapper;

import co.edu.unicauca.reservation_service.entity.Reservation;
import co.edu.unicauca.reservation_service.factory.ReservationStateFactory;
import co.edu.unicauca.reservation_service.infra.dto.request.ReservationRequestDTO;
import co.edu.unicauca.reservation_service.infra.dto.response.ReservationResponseDTO;

public class ReservationMapper {

    public static ReservationResponseDTO toResponse(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getBarberId(),
                reservation.getClientId(),
                reservation.getState().getStatus(),
                reservation.getDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getServices()
        );
    }

    public static Reservation toEntity(ReservationRequestDTO dto) {
        Reservation reservation = new Reservation();

        reservation.setBarberId(dto.getBarberId());
        reservation.setClientId(dto.getClientId());
        reservation.setDate(dto.getDate());
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setServices(dto.getServices());

        return reservation;
    }
}
