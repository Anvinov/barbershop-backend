package co.edu.unicauca.client_service.infra.mapper;

import co.edu.unicauca.client_service.entity.Client;
import co.edu.unicauca.client_service.infra.dto.ClientRequestDTO;
import co.edu.unicauca.client_service.infra.dto.ClientResponseDTO;

public class ClientMapper {

    public static ClientResponseDTO toResponse(Client client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getPhone(),
                client.getEmail(),
                client.isAvailable()
        );
    }

    public static Client toEntity(ClientRequestDTO dto) {
        Client client = new Client();

        client.setName(dto.getName());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());

        return client;
    }

}
