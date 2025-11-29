package co.edu.unicauca.api_gateway.facade.client;

import co.edu.unicauca.api_gateway.facade.DTO.barber.request.BarberRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.request.BarberSimpleRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.request.ScheduleRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.request.TimeSlotRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient(
        name = "barber-service",
        url = "http://localhost:8083/api/barber"
)
public interface BarberClient {

    @GetMapping("/health")
    ResponseEntity<?> getHealth();

    // Barber endpoints
    @PostMapping
    ResponseEntity<?> createBarber(@RequestBody BarberRequestDTO request);

    @GetMapping("/{id}")
    ResponseEntity<?> getBarberById(@PathVariable Long id);

    @GetMapping("/email")
    ResponseEntity<?> getBarberByEmail(@RequestParam String email);

    @PutMapping("/{id}")
    ResponseEntity<?> updateBarber(
            @PathVariable Long id,
            @RequestBody BarberSimpleRequestDTO request);

    @PutMapping("/disable/{id}")
    ResponseEntity<?> disableBarber(@PathVariable Long id);

    // Schedule endpoints
    @GetMapping("/schedule/{barberId}")
    ResponseEntity<?> getScheduleByBarberId(@PathVariable Long barberId);

    @PutMapping("/schedule/{barberId}")
    ResponseEntity<?> updateSchedule(
            @PathVariable Long barberId,
            @RequestBody ScheduleRequestDTO request);

    // Time slots endpoints
    @GetMapping("/schedule/slot/{barberId}")
    ResponseEntity<?> getTimeSlotsByBarberIdAndDate(
            @PathVariable Long barberId,
            @RequestParam LocalDate day);

    @PostMapping("/schedule/slot/{barberId}")
    ResponseEntity<?> addTimeSlot(
            @PathVariable Long barberId,
            @RequestBody TimeSlotRequestDTO request);

    @DeleteMapping("schedule/slot/{id}")
    ResponseEntity<?> deleteTimeSlot(@PathVariable Long id);






}
