package co.edu.unicauca.reservation_service.infra.dto.reservation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private Long barberId;
    private Long clientId;
    private String state;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Long> services;
}
