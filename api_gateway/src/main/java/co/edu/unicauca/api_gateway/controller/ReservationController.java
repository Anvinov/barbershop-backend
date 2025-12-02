package co.edu.unicauca.api_gateway.controller;

import co.edu.unicauca.api_gateway.facade.DTO.reservation.ReservationRequestDTO;
import co.edu.unicauca.api_gateway.facade.client.ReservationClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationClient  reservationClient;

    public ReservationController(ReservationClient reservationClient) {
        this.reservationClient = reservationClient;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return reservationClient.getHealth();
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO request) {
        return reservationClient.createReservation(request);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(
            @PathVariable Long id,
            @RequestBody ReservationRequestDTO request
    ) {
        return reservationClient.updateReservation(id, request);
    }

    @PutMapping("/start/{id}")
    public ResponseEntity<?> startReservation(@PathVariable Long id) {
        return reservationClient.startReservation(id);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        return reservationClient.cancelReservation(id);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<?> finishReservation(@PathVariable Long id) {
        return reservationClient.finishReservation(id);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        return reservationClient.deleteReservation(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        return reservationClient.getReservationById(id);
    }

    @GetMapping("/barber/{id}")
    public ResponseEntity<?> getReservationsByBarberId(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDate date
    ) {
        return reservationClient.getReservationsByBarberId(id, date);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<?> getReservationsByClientId(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDate date
    ) {
        return reservationClient.getReservationsByClientId(id, date);
    }
}
