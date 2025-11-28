package co.edu.unicauca.barber_service.service;

import co.edu.unicauca.barber_service.entity.Schedule;
import co.edu.unicauca.barber_service.entity.TimeSlot;
import co.edu.unicauca.barber_service.exception.BarberNotFoundException;
import co.edu.unicauca.barber_service.exception.OverlappingTimeSlotException;
import co.edu.unicauca.barber_service.exception.ScheduleNotFoundException;
import co.edu.unicauca.barber_service.exception.TimeSlotNotFoundException;
import co.edu.unicauca.barber_service.infra.dto.request.ScheduleRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.request.TimeSlotRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.ScheduleResponseDTO;
import co.edu.unicauca.barber_service.infra.dto.response.TimeSlotResponseDTO;
import co.edu.unicauca.barber_service.infra.mapper.ScheduleMapper;
import co.edu.unicauca.barber_service.infra.mapper.TimeSlotMapper;
import co.edu.unicauca.barber_service.repository.BarberRepository;
import co.edu.unicauca.barber_service.repository.ScheduleRepository;
import co.edu.unicauca.barber_service.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final BarberRepository barberRepository;
    private final ScheduleRepository scheduleRepository;
    private final TimeSlotRepository timeSlotRepository;

    public ScheduleServiceImpl(BarberRepository barberRepository, ScheduleRepository scheduleRepository, TimeSlotRepository timeSlotRepository) {
        this.barberRepository = barberRepository;
        this.scheduleRepository = scheduleRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public ScheduleResponseDTO getScheduleByBarberId(Long barberId) {
        if(!barberRepository.existsById(barberId)){
            throw new BarberNotFoundException(barberId);
        }

        Schedule schedule = scheduleRepository.findScheduleByBarber_Id(barberId);

        return ScheduleMapper.toResponse(schedule);
    }

    @Override
    public ScheduleResponseDTO updateSchedule(Long barberId, ScheduleRequestDTO request) {
        if(!barberRepository.existsById(barberId)){
            throw new BarberNotFoundException(barberId);
        }

        Schedule schedule = scheduleRepository.findScheduleByBarber_Id(barberId);

        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setWorkDays(request.getWorkDays());

        scheduleRepository.save(schedule);

        return ScheduleMapper.toResponse(schedule);

    }

    @Override
    public List<TimeSlotResponseDTO> getTimeSlotsByBarberIdAndDate(Long barberId, LocalDate date) {
        if(!barberRepository.existsById(barberId)){
            throw new BarberNotFoundException(barberId);
        }

        Schedule schedule = scheduleRepository.findScheduleByBarber_Id(barberId);

        List<TimeSlot> slots = timeSlotRepository.findBySchedule_IdAndDate(schedule.getId(), date);

        return slots.stream()
                .map(TimeSlotMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSlotResponseDTO AddTimeSlot(Long barberId, TimeSlotRequestDTO request) {
        if(!barberRepository.existsById(barberId)){
            throw new BarberNotFoundException(barberId);
        }

        Schedule schedule = scheduleRepository.findScheduleByBarber_Id(barberId);

        TimeSlot timeSlot = TimeSlotMapper.toEntity(request);
        timeSlot.setSchedule(schedule);

        List<TimeSlot> slots = timeSlotRepository.findBySchedule_IdAndDate(schedule.getId(), timeSlot.getDate());

        for (TimeSlot slot : slots) {
            if (timeSlot.getStartTime().isBefore(slot.getEndTime()) &&
                    timeSlot.getEndTime().isAfter(slot.getStartTime())) {
                throw new OverlappingTimeSlotException(timeSlot.getStartTime(), timeSlot.getEndTime());
            }
        }

        schedule.addSlot(timeSlot);

        scheduleRepository.save(schedule);

        return TimeSlotMapper.toResponse(timeSlot);
    }

    @Override
    public TimeSlotResponseDTO DeleteTimeSlot(Long id) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new TimeSlotNotFoundException(id));

        Schedule schedule = scheduleRepository.findById(timeSlot.getSchedule().getId())
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        schedule.removeSlot(timeSlot);

        scheduleRepository.save(schedule);

        return TimeSlotMapper.toResponse(timeSlot);
    }
}
