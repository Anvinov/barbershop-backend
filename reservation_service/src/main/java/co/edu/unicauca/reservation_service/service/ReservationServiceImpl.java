package co.edu.unicauca.reservation_service.service;

import co.edu.unicauca.reservation_service.infra.dto.request.ReservationRequestDTO;
import co.edu.unicauca.reservation_service.infra.dto.response.ReservationResponseDTO;
import co.edu.unicauca.reservation_service.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO request) {
        return null;
    }

    @Override
    public ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO request) {
        return null;
    }

    @Override
    public ReservationResponseDTO startReservation(Long id) {
        return null;
    }

    @Override
    public ReservationResponseDTO cancelReservation(Long id) {
        return null;
    }

    @Override
    public ReservationResponseDTO finishReservation(Long id) {
        return null;
    }

    @Override
    public ReservationResponseDTO deleteReservation(Long id) {
        return null;
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        return null;
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByBarberIdAndDate(Long barberId, LocalDate date) {
        return List.of();
    }
}
