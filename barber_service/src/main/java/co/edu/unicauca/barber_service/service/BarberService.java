package co.edu.unicauca.barber_service.service;

import co.edu.unicauca.barber_service.infra.dto.request.BarberRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.BarberResponseDTO;

public interface BarberService {
    BarberResponseDTO createBarber(BarberRequestDTO request);

    BarberResponseDTO getBarberById(Long id);

    BarberResponseDTO updateBarber(Long id, BarberRequestDTO request);

    BarberResponseDTO disableBarber(Long id);

}
