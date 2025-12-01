package co.edu.unicauca.reservation_service.controller;

import co.edu.unicauca.reservation_service.infra.dto.reservation.request.ReservationRequestDTO;
import co.edu.unicauca.reservation_service.infra.dto.reservation.response.ReservationResponseDTO;
import co.edu.unicauca.reservation_service.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO request) {
        ReservationResponseDTO response = reservationService.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(
            @PathVariable Long id,
            @RequestBody ReservationRequestDTO request
    ) {
        ReservationResponseDTO response = reservationService.updateReservation(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/start/{id}")
    public ResponseEntity<?> startReservation(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.startReservation(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.cancelReservation(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<?> finishReservation(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.finishReservation(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.deleteReservation(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        ReservationResponseDTO response = reservationService.getReservationById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/barber/{id}")
    public ResponseEntity<?> getReservationsByBarberId(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDate date
    ) {
        List<ReservationResponseDTO> response;

        if (date != null) {
            response = reservationService.getReservationsByBarberIdAndDate(id, date);
        } else {
            response = reservationService.getReservationsByBarberId(id);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<?> getReservationsByClientId(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDate date
    ) {
        List<ReservationResponseDTO> response;

        if (date != null) {
            response = reservationService.getReservationsByClientIdAndDate(id, date);
        }  else {
            response = reservationService.getReservationsByClientId(id);
        }

        return ResponseEntity.ok(response);
    }

}
