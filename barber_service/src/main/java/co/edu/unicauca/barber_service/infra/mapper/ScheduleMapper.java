package co.edu.unicauca.barber_service.infra.mapper;

import co.edu.unicauca.barber_service.entity.Schedule;
import co.edu.unicauca.barber_service.infra.dto.request.ScheduleRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.ScheduleResponseDTO;

public class ScheduleMapper {
    public static ScheduleResponseDTO toResponse(Schedule schedule) {
        return new ScheduleResponseDTO(
                schedule.getBarber().getId(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getWorkDays()
        );
    }

    public static Schedule toEntity(ScheduleRequestDTO dto) {
        Schedule schedule = new Schedule();

        schedule.setStartTime(dto.getStartTime());
        schedule.setEndTime(dto.getEndTime());
        schedule.setWorkDays(dto.getWorkDays());

        return schedule;
    }
}
