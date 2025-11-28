package co.edu.unicauca.barber_service.service;

import co.edu.unicauca.barber_service.infra.dto.request.BarberRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.request.BarberSimpleRequestDTO;
import co.edu.unicauca.barber_service.infra.dto.response.BarberResponseDTO;

public interface BarberService {
    BarberResponseDTO createBarber(BarberRequestDTO request);

    BarberResponseDTO getBarberById(Long id);

    BarberResponseDTO getBarberByEmail(String email);

    BarberResponseDTO updateBarber(Long id, BarberSimpleRequestDTO request);

    BarberResponseDTO disableBarber(Long id);

}
