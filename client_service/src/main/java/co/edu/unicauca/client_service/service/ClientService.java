package co.edu.unicauca.client_service.service;

import co.edu.unicauca.client_service.infra.dto.ClientRequestDTO;
import co.edu.unicauca.client_service.infra.dto.ClientResponseDTO;

public interface ClientService {
    ClientResponseDTO createClient(ClientRequestDTO request);

    ClientResponseDTO getClientById(Long id);

    ClientResponseDTO getClientByEmail(String email);

    ClientResponseDTO updateClient(Long id, ClientRequestDTO request);

    ClientResponseDTO disableClient(Long id);
}

