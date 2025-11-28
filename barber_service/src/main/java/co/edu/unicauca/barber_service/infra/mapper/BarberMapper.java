package co.edu.unicauca.barber_service.infra.mapper;

import co.edu.unicauca.barber_service.entity.Barber;
import co.edu.unicauca.barber_service.entity.Schedule;
import co.edu.unicauca.barber_service.infra.dto.request.BarberRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.BarberResponseDTO;

public class BarberMapper {
    public static BarberResponseDTO toResponse(Barber barber) {
        return new BarberResponseDTO(
                barber.getId(),
                barber.getName(),
                barber.getPhone(),
                barber.getEmail(),
                ScheduleMapper.toResponse(barber.getSchedule())
        );
    }

    public static Barber toEntity(BarberRequestDTO dto) {
        Schedule schedule = new Schedule();

        schedule.setStartTime(dto.getSchedule().getStartTime());
        schedule.setEndTime(dto.getSchedule().getEndTime());
        schedule.setWorkDays(dto.getSchedule().getWorkDays());

        Barber barber = new Barber();

        barber.setName(dto.getName());
        barber.setPhone(dto.getPhone());
        barber.setEmail(dto.getEmail());

        schedule.setBarber(barber);
        barber.setSchedule(schedule);

        return barber;
    }
}
