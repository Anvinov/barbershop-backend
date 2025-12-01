package co.edu.unicauca.reservation_service.infra.client;

import co.edu.unicauca.reservation_service.infra.dto.barber.request.TimeSlotRequestDTO;
import co.edu.unicauca.reservation_service.infra.dto.barber.response.BarberResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient(
        name = "barber-service",
        url = "http://localhost:8083/api/barber"
)
public interface BarberClient {

    // Barber endpoints
    @GetMapping("/{id}")
    ResponseEntity<BarberResponseDTO> getBarberById(@PathVariable Long id);

    // Schedule endpoints
    @GetMapping("/schedule/{barberId}")
    ResponseEntity<?> getScheduleByBarberId(@PathVariable Long barberId);

    // Time slots endpoints
    @GetMapping("/schedule/slot/{barberId}")
    ResponseEntity<?> getTimeSlotsByBarberIdAndDate(
            @PathVariable Long barberId,
            @RequestParam LocalDate day);

    @PostMapping("/schedule/slot/{barberId}")
    ResponseEntity<?> addTimeSlot(
            @PathVariable Long barberId,
            @RequestBody TimeSlotRequestDTO request);


}
