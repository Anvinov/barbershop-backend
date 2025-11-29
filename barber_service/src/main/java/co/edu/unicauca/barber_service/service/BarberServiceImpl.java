package co.edu.unicauca.barber_service.service;

import co.edu.unicauca.barber_service.entity.Barber;
import co.edu.unicauca.barber_service.exception.*;
import co.edu.unicauca.barber_service.infra.dto.request.BarberRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.request.BarberSimpleRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.BarberResponseDTO;
import co.edu.unicauca.barber_service.infra.mapper.BarberMapper;
import co.edu.unicauca.barber_service.repository.BarberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class BarberServiceImpl implements BarberService{

    private final BarberRepository barberRepository;

    public BarberServiceImpl(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    @Override
    public BarberResponseDTO createBarber(BarberRequestDTO request) {
        if(barberRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        Barber barber = BarberMapper.toEntity(request);
        barber.setAvailable(true);

        try {
            String name = barber.getName().substring(0, 1).toUpperCase(Locale.ROOT) + barber.getName().substring(1);
            barber.setName(name);
        }catch (Exception e){
            throw new IllegalRequestException("Barber name not valid");
        }

        barber = barberRepository.save(barber);

        return BarberMapper.toResponse(barber);
    }

    @Override
    public List<BarberResponseDTO> getAllBarbers() {
        List<Barber> barbers = barberRepository.findAll();

        return barbers.stream()
                .map(BarberMapper::toResponse)
                .toList();
    }

    @Override
    public BarberResponseDTO getBarberById(Long id) {
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new BarberNotFoundException(id));

        return BarberMapper.toResponse(barber);
    }

    @Override
    public BarberResponseDTO getBarberByEmail(String email) {
        Barber barber = barberRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));

        return BarberMapper.toResponse(barber);
    }

    @Override
    public BarberResponseDTO updateBarber(Long id, BarberSimpleRequestDTO request) {
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new BarberNotFoundException(id));

        if (!barber.getEmail().equals(request.getEmail())) {
            if (barberRepository.existsByEmail(request.getEmail())) {
                throw new EmailAlreadyExistsException(request.getEmail());
            }
            barber.setEmail(request.getEmail());
        }

        barber.setName(request.getName());
        barber.setPhone(request.getPhone());

        barber = barberRepository.save(barber);

        return BarberMapper.toResponse(barber);
    }

    @Override
    public BarberResponseDTO disableBarber(Long id) {
        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new BarberNotFoundException(id));

        if(!barber.isAvailable()){
            throw new BarberIsAlreadyDisableException(id);
        }

        barber.setAvailable(false);

        barber = barberRepository.save(barber);

        return BarberMapper.toResponse(barber);
    }
}
