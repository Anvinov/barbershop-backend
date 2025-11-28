package co.edu.unicauca.api_gateway.facade.client;

import co.edu.unicauca.api_gateway.facade.DTO.barber.BarberRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.BarberSimpleRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.ScheduleRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.TimeSlotRequestDTO;
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
    ResponseEntity<?> create(@RequestBody BarberRequestDTO request);

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id);

    @GetMapping("/email")
    ResponseEntity<?> getByEmail(@RequestParam String email);

    @PutMapping("/{id}")
    ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody BarberSimpleRequestDTO request);

    @PutMapping("/disable/{id}")
    ResponseEntity<?> disableClient(@PathVariable Long id);

    // Schedule endpoints
    @GetMapping("/schedule/{barberId}")
    ResponseEntity<?> getSchedule(@PathVariable Long barberId);

    @PutMapping("/schedule/{barberId}")
    ResponseEntity<?> updateSchedule(
            @PathVariable Long barberId,
            @RequestBody ScheduleRequestDTO request);

    // Time slots endpoints
    @GetMapping("/schedule/slot/{barberId}")
    ResponseEntity<?> getScheduleByBarberId(
            @PathVariable Long barberId,
            @RequestParam LocalDate day);

    @PostMapping("/schedule/slot/{barberId}")
    ResponseEntity<?> addTimeSlot(
            @PathVariable Long barberId,
            @RequestBody TimeSlotRequestDTO request);

    @DeleteMapping("schedule/slot/{id}")
    ResponseEntity<?> deleteTimeSlot(@PathVariable Long id);






}
