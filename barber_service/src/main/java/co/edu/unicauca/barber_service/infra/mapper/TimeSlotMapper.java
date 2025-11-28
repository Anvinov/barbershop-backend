package co.edu.unicauca.barber_service.infra.mapper;

import co.edu.unicauca.barber_service.entity.TimeSlot;
import co.edu.unicauca.barber_service.infra.dto.request.TimeSlotRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.TimeSlotResponseDTO;

public class TimeSlotMapper {
    public static TimeSlotResponseDTO toResponse(TimeSlot timeSlot) {
        return new TimeSlotResponseDTO(
                timeSlot.getId(),
                timeSlot.getSchedule().getId(),
                timeSlot.getDate(),
                timeSlot.getStartTime(),
                timeSlot.getEndTime(),
                timeSlot.getStatus(),
                timeSlot.getReason()
        );
    }

    public static TimeSlot toEntity(TimeSlotRequestDTO dto) {
        TimeSlot timeSlot = new TimeSlot();

        timeSlot.setDate(dto.getDate());
        timeSlot.setStartTime(dto.getStartTime());
        timeSlot.setEndTime(dto.getEndTime());
        timeSlot.setStatus(dto.getStatus());
        timeSlot.setReason(dto.getReason());

        return timeSlot;
    }
}
