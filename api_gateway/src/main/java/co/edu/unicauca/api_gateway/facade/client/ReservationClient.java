package co.edu.unicauca.api_gateway.facade.client;

import co.edu.unicauca.api_gateway.facade.DTO.reservation.ReservationRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient(
        name = "reservation-service",
        url = "http://localhost:8085/api/reservation"
)
public interface ReservationClient {

    @GetMapping("/health")
    ResponseEntity<?> getHealth();

    @PostMapping
    ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO request);

    @PutMapping("/{id}")
    ResponseEntity<?> updateReservation(
            @PathVariable Long id,
            @RequestBody ReservationRequestDTO request
    );

    @PutMapping("/start/{id}")
    ResponseEntity<?> startReservation(@PathVariable Long id);

    @PutMapping("/cancel/{id}")
    ResponseEntity<?> cancelReservation(@PathVariable Long id);

    @PutMapping("/finish/{id}")
    ResponseEntity<?> finishReservation(@PathVariable Long id);

    @PutMapping("/delete/{id}")
    ResponseEntity<?> deleteReservation(@PathVariable Long id);

    @GetMapping("/{id}")
    ResponseEntity<?> getReservationById(@PathVariable Long id);

    @GetMapping("/barber/{id}")
    ResponseEntity<?> getReservationsByBarberId(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDate date
    );

    @GetMapping("/client/{id}")
    ResponseEntity<?> getReservationsByClientId(
            @PathVariable Long id,
            @RequestParam(required = false) LocalDate date
    );
}
