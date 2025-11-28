package co.edu.unicauca.barber_service.controller;

import co.edu.unicauca.barber_service.infra.dto.request.BarberRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.request.BarberSimpleRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.request.ScheduleRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.request.TimeSlotRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.BarberResponseDTO;
import co.edu.unicauca.barber_service.infra.dto.response.ScheduleResponseDTO;
import co.edu.unicauca.barber_service.infra.dto.response.TimeSlotResponseDTO;
import co.edu.unicauca.barber_service.service.BarberService;
import co.edu.unicauca.barber_service.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/barber")
public class BarberController {

    private final BarberService barberService;
    private final ScheduleService scheduleService;

    public BarberController(BarberService barberService, ScheduleService scheduleService) {
        this.barberService = barberService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok().build();
    }

    // Barber endpoints
    @PostMapping
    public ResponseEntity<?> create(@RequestBody BarberRequestDTO request) {
        BarberResponseDTO barber = barberService.createBarber(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(barber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByEmail(@PathVariable Long id) {
        BarberResponseDTO barber = barberService.getBarberById(id);
        return ResponseEntity.ok(barber);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        BarberResponseDTO barber = barberService.getBarberByEmail(email);
        return ResponseEntity.ok(barber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody BarberSimpleRequestDTO request) {
        BarberResponseDTO barber = barberService.updateBarber(id, request);
        return ResponseEntity.ok(barber);
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<?> disableClient(@PathVariable Long id) {
        BarberResponseDTO barber = barberService.disableBarber(id);
        return ResponseEntity.ok(barber);
    }

    // Schedule endpoints
    @GetMapping("/schedule/{barberId}")
    public ResponseEntity<?> getSchedule(@PathVariable Long barberId) {
        ScheduleResponseDTO schedule = scheduleService.getScheduleByBarberId(barberId);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/schedule/{barberId}")
    public ResponseEntity<?> updateSchedule(
            @PathVariable Long barberId,
            @RequestBody ScheduleRequestDTO request) {
        ScheduleResponseDTO schedule = scheduleService.updateSchedule(barberId, request);
        return ResponseEntity.ok(schedule);
    }

    // Time slots endpoints
    @GetMapping("/schedule/slot/{barberId}")
    public ResponseEntity<?> getScheduleByBarberId(
            @PathVariable Long barberId,
            @RequestParam LocalDate day) {
        List<TimeSlotResponseDTO> slots = scheduleService.getTimeSlotsByBarberIdAndDate(barberId, day);
        return ResponseEntity.ok(slots);
    }

    @PostMapping("/schedule/slot/{barberId}")
    public ResponseEntity<?> addTimeSlot(
            @PathVariable Long barberId,
            @RequestBody TimeSlotRequestDTO request) {
        TimeSlotResponseDTO timeSlot = scheduleService.AddTimeSlot(barberId, request);
        return ResponseEntity.ok(timeSlot);
    }

    @DeleteMapping("schedule/slot/{id}")
    public ResponseEntity<?> deleteTimeSlot(@PathVariable Long id){
        scheduleService.DeleteTimeSlot(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
