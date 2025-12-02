package co.edu.unicauca.api_gateway.controller;

import co.edu.unicauca.api_gateway.facade.DTO.barber.request.ScheduleRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.barber.request.TimeSlotRequestDTO;
import co.edu.unicauca.api_gateway.facade.client.BarberClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/barber")
public class BarberController {

    private final BarberClient  barberClient;

    public BarberController(BarberClient barberClient) {
        this.barberClient = barberClient;
    }

    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return barberClient.getHealth();
    }

    // Barber endpoints
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBarbers() {
        return barberClient.getAllBarbers();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('BARBER')")
    public ResponseEntity<?> getBarberById(@PathVariable Long id) {
        return barberClient.getBarberById(id);
    }

    @GetMapping("/email")
    @PreAuthorize("hasRole('BARBER')")
    public ResponseEntity<?> getBarberByEmail(@RequestParam String email) {
        return barberClient.getBarberByEmail(email);
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BARBER')")
    public ResponseEntity<?> disableBarber(@PathVariable Long id) {
        return barberClient.disableBarber(id);
    }

    // Schedule endpoints
    @GetMapping("/schedule/{barberId}")
    @PreAuthorize("hasRole('BARBER')")
    public ResponseEntity<?> getScheduleByBarberId(@PathVariable Long barberId) {
        return barberClient.getScheduleByBarberId(barberId);
    }

    @PutMapping("/schedule/{barberId}")
    @PreAuthorize("hasRole('BARBER')")
    public ResponseEntity<?> updateSchedule(
            @PathVariable Long barberId,
            @RequestBody ScheduleRequestDTO request) {
        return barberClient.updateSchedule(barberId, request);
    }

    // Time slots endpoints
    @GetMapping("/schedule/slot/{barberId}")
    @PreAuthorize("hasRole('BARBER')")
    public ResponseEntity<?> getTimeSlotsByBarberIdAndDate(
            @PathVariable Long barberId,
            @RequestParam LocalDate day) {
        return barberClient.getTimeSlotsByBarberIdAndDate(barberId, day);
    }

    @PostMapping("/schedule/slot/{barberId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BARBER')")
    public ResponseEntity<?> addTimeSlot(
            @PathVariable Long barberId,
            @RequestBody TimeSlotRequestDTO request) {
        return barberClient.addTimeSlot(barberId, request);
    }

    @DeleteMapping("schedule/slot/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTimeSlot(@PathVariable Long id){
        return barberClient.deleteTimeSlot(id);
    }



}
