package co.edu.unicauca.reservation_service.service;

import co.edu.unicauca.reservation_service.infra.dto.reservation.request.ReservationRequestDTO;
import co.edu.unicauca.reservation_service.infra.dto.reservation.response.ReservationResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponseDTO createReservation(ReservationRequestDTO request);
    ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO request);

    ReservationResponseDTO startReservation(Long id);
    ReservationResponseDTO cancelReservation(Long id);
    ReservationResponseDTO finishReservation(Long id);
    ReservationResponseDTO deleteReservation(Long id);

    ReservationResponseDTO getReservationById(Long id);
    List<ReservationResponseDTO> getReservationsByBarberId(Long barberId);
    List<ReservationResponseDTO> getReservationsByBarberIdAndDate(Long barberId,  LocalDate date);
    List<ReservationResponseDTO> getReservationsByClientId(Long clientId);
    List<ReservationResponseDTO> getReservationsByClientIdAndDate(Long clientId,  LocalDate date);

}
