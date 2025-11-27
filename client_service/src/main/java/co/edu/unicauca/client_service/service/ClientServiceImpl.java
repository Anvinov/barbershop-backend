package co.edu.unicauca.client_service.service;

import co.edu.unicauca.client_service.entity.Client;
import co.edu.unicauca.client_service.exception.ClientIsAlreadyDisableException;
import co.edu.unicauca.client_service.exception.ClientNotFoundException;
import co.edu.unicauca.client_service.exception.EmailAlreadyExistsException;
import co.edu.unicauca.client_service.infra.dto.ClientRequestDTO;
import co.edu.unicauca.client_service.infra.dto.ClientResponseDTO;
import co.edu.unicauca.client_service.infra.mapper.ClientMapper;
import co.edu.unicauca.client_service.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        Client client = ClientMapper.toEntity(request);
        client.setAvailable(true);

        client = repository.save(client);

        return ClientMapper.toResponse(client);
    }

    @Override
    public ClientResponseDTO getClientById(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return ClientMapper.toResponse(client);
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientRequestDTO request) {

        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        if (!client.getEmail().equals(request.getEmail())) {
            if (repository.existsByEmail(request.getEmail())) {
                throw new EmailAlreadyExistsException(request.getEmail());
            }
            client.setEmail(request.getEmail());
        }

        client.setName(request.getName());
        client.setPhone(request.getPhone());

        client = repository.save(client);

        return ClientMapper.toResponse(client);
    }

    @Override
    public ClientResponseDTO disableClient(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        if(!client.isAvailable()){
            throw new ClientIsAlreadyDisableException(id);
        }

        client.setAvailable(false);

        client = repository.save(client);

        return ClientMapper.toResponse(client);
    }
}

