package co.edu.unicauca.api_gateway.facade.DTO.reservation;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
public class ReservationRequestDTO {
    private Long barberId;
    private Long clientId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Long> services;
}
