package co.edu.unicauca.reservation_service.service;

import co.edu.unicauca.reservation_service.entity.Reservation;
import co.edu.unicauca.reservation_service.entity.state.PendingState;
import co.edu.unicauca.reservation_service.exception.*;
import co.edu.unicauca.reservation_service.infra.client.BarberClient;
import co.edu.unicauca.reservation_service.infra.client.ClientClient;
import co.edu.unicauca.reservation_service.infra.client.ServiceClient;
import co.edu.unicauca.reservation_service.infra.dto.barber.request.TimeSlotRequestDTO;
import co.edu.unicauca.reservation_service.infra.dto.barber.response.BarberResponseDTO;
import co.edu.unicauca.reservation_service.infra.dto.client.response.ClientResponseDTO;
import co.edu.unicauca.reservation_service.infra.dto.reservation.request.ReservationRequestDTO;
import co.edu.unicauca.reservation_service.infra.dto.reservation.response.ReservationResponseDTO;
import co.edu.unicauca.reservation_service.infra.mapper.ReservationMapper;
import co.edu.unicauca.reservation_service.repository.ReservationRepository;
import feign.RetryableException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    private final ClientClient clientClient;
    private final BarberClient barberClient;
    private final ServiceClient serviceClient;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ClientClient clientClient, BarberClient barberClient, ServiceClient serviceClient) {
        this.reservationRepository = reservationRepository;
        this.clientClient = clientClient;
        this.barberClient = barberClient;
        this.serviceClient = serviceClient;
    }

    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO request) {

        // Validations for clients
        try {
            ClientResponseDTO client = clientClient.getClientById(request.getClientId()).getBody();

            assert client != null;
            if(!client.isAvailable()) throw new ClientDisabledException(client.getId());

        } catch (RetryableException e) {
            throw new ServiceNotAvailableException("clients");
        }

        // Validations for barbers
        try{
            BarberResponseDTO barber = barberClient.getBarberById(request.getBarberId()).getBody();

            assert barber != null;

            if(!barber.isAvailable()) throw new BarberDisabledException(barber.getId());

            if (request.getStartTime().isBefore(barber.getSchedule().getStartTime()) ||
                    request.getEndTime().isAfter(barber.getSchedule().getEndTime())) {

                throw new BarberScheduleViolationException(
                        barber.getId(),
                        barber.getSchedule().getStartTime(),
                        barber.getSchedule().getEndTime()
                );
            }

        } catch (RetryableException  e) {
            throw new ServiceNotAvailableException("barbers");
        }

        // Validations for services
        try{
            List<Long> servicesDisabled = serviceClient.servicesAreDisabled(request.getServices()).getBody();
            assert servicesDisabled != null;

            if (!servicesDisabled.isEmpty()) throw new ServiceDisabledException(servicesDisabled.toString());

        } catch (RetryableException  e) {
            throw new ServiceNotAvailableException("services");
        }

        Reservation reservation = ReservationMapper.toEntity(request);

        if (!reservation.getStartTime().isBefore(reservation.getEndTime())) {
            throw new InvalidTimeRangeException();
        }

        if (!reservationRepository
                .findOverlappingBarberReservations(reservation.getBarberId(), reservation.getDate(), reservation.getStartTime(), reservation.getEndTime())
                .isEmpty()) {
            throw new OverlappingReservationException("barber with id "
                    + reservation.getBarberId()
                    + " has already reservation between "
                    + reservation.getStartTime()
                    + " and "
                    + reservation.getEndTime()
            );
        }

        if (!reservationRepository
                .findOverlappingClientReservations(reservation.getClientId(), reservation.getDate(), reservation.getStartTime(), reservation.getEndTime())
                .isEmpty()) {
            throw new OverlappingReservationException("client with id "
                    + reservation.getClientId()
                    + " has already reservation between "
                    + reservation.getStartTime()
                    + " and "
                    + reservation.getEndTime()
            );
        }

        reservation.setState(new PendingState());

        reservationRepository.save(reservation);

        return ReservationMapper.toResponse(reservation);
    }

    @Override
    public ReservationResponseDTO updateReservation(Long id, ReservationRequestDTO request) {
        return null;
    }

    @Override
    public ReservationResponseDTO startReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        reservation.startReservation();

        reservationRepository.save(reservation);

        return ReservationMapper.toResponse(reservation);
    }

    @Override
    public ReservationResponseDTO cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        reservation.cancelReservation();

        reservationRepository.save(reservation);

        return ReservationMapper.toResponse(reservation);
    }

    @Override
    public ReservationResponseDTO finishReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        TimeSlotRequestDTO timeSlotRequest = new TimeSlotRequestDTO(
                reservation.getDate(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                "IN_SERVICE",
                "Atencion finalizada"
        );

        barberClient.addTimeSlot(reservation.getBarberId(), timeSlotRequest);

        reservation.finishReservation();

        reservationRepository.save(reservation);

        return ReservationMapper.toResponse(reservation);
    }

    @Override
    public ReservationResponseDTO deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        reservation.deleteReservation();

        reservationRepository.save(reservation);

        return ReservationMapper.toResponse(reservation);
    }

    @Override
    public ReservationResponseDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        return ReservationMapper.toResponse(reservation);
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByBarberId(Long barberId) {
        List<Reservation> reservations = reservationRepository.findByBarberId(barberId);

        return reservations.stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByBarberIdAndDate(Long barberId, LocalDate date) {
        List<Reservation> reservations = reservationRepository.findByBarberIdAndDate(barberId,  date);

        return reservations.stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByClientId(Long clientId) {
        List<Reservation> reservations = reservationRepository.findByClientId(clientId);

        return reservations.stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReservationResponseDTO> getReservationsByClientIdAndDate(Long clientId, LocalDate date) {
        List<Reservation> reservations = reservationRepository.findByClientIdAndDate(clientId, date);

        return reservations.stream()
                .map(ReservationMapper::toResponse)
                .toList();
    }
}
