package co.edu.unicauca.barber_service.service;

import co.edu.unicauca.barber_service.infra.dto.request.ScheduleRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.request.TimeSlotRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.ScheduleResponseDTO;
import co.edu.unicauca.barber_service.infra.dto.response.TimeSlotResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDTO getScheduleByBarberId (Long barberId);

    ScheduleResponseDTO updateSchedule(Long barberId, ScheduleRequestDTO request);

    List<TimeSlotResponseDTO> getTimeSlotsByBarberIdAndDate(Long barberId, LocalDate date);

    TimeSlotResponseDTO AddTimeSlot(Long barberId, TimeSlotRequestDTO request);

    void DeleteTimeSlot(Long id);
}
